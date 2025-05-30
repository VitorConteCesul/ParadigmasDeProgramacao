package org.vito.ui;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.vito.dao.PlayerDao;
import org.vito.model.Player;

import java.util.Random;

// A estrura do projeto vai precisar de:
// - Uma classe Player
// - Uma classe que estabeleça uma conexão com o banco
// - Uma classe que conecte o model Player com o banco criado

//Qual é a estrutura lógica do projeto:
// [TELA JFX] -> [PlayerDAO] -> usar -> [MongoUtil] -> conectar ao banco

// Dessa forma a main não conhece o playerDao e o Player
// Isso chamamos de MODULARIDADE: Cada parte faz só

public class MainApp extends Application {

    // Toda vez que você for manipular uma lista, voce remove ou adiciona elementos a ela
    // você não recria a lista

    // placeholder = []
    private final ObservableList<Player> data = FXCollections.observableArrayList();
    private final PlayerDao pd = new PlayerDao();
    private final Random rnd = new Random();




    public void start(Stage stage){
        // Tabela de dados
        TableView<Player> table = new TableView<>(data);
        table.setPlaceholder(new Label("No placeholder"));

        //Coluna
//        TableColumn<String, String> colName = new TableColumn<>("Name");
//        colName.setMinWidth(169);
//
//        // Inserindo coluna na table
//        table.getColumns().add(colName);


        //addAll adiciona uma lista á lista
        table.getColumns().addAll(
                col("Nome", "nome", 150), // 1º título da coluna, 2º campo a exibir, 3º largura
                col("Pontos", "pontos", 60),
                col("Vitorias", "vitorias", 60)
        );


        // -------barra de controles ----------
        TextField txtName = new TextField();
        txtName.setPromptText("Nome do jogador");

        Button novo = new Button("Novo");
        novo.setOnAction(e ->{ // Quando o botão for clicado, execute isso;
            pd.insert(txtName.getText());
            txtName.clear();
            refresh();
        });

        Button Rolar = new Button("Rolar dado");
        Rolar.setOnAction(e ->
                Player p = table.getSelectionModel().getSelectedItem();
                if(p != null){
                    int dado =  rnd.nextInt(6) + 1;
                    pd.addPoints(p.id(), dado);
                }
        );
        Button Reset = new Button("Reset");
        Reset.setOnAction(e ->
                pd.resetAll());
                refresh();
        );


        // até aqui não colocamos nenhuma ação, apenas visual

        HBox comandos = new HBox(8, new Label("Nome"), txtName, novo, Rolar, Reset);

        comandos.setPadding(new Insets(10));

        VBox root = new VBox(comandos, table);

        Scene cena = new Scene(root, 380, 420);

        stage.setScene(cena);
        stage.setTitle("Dice Game");

        stage.show();
    }

    // Criar uma coluna da tabela baseada no nome do campo e do titulo
    private TableColumn<Player, String> col(String title, String field, int width){
        TableColumn<Player, String> c = new TableColumn<>(title);


        c.setMinWidth(width);

        // Aqui dizemos a coluna qual o campo do Player ela deve exibir
        c.setCellValueFactory( cell -> {
            Player p = cell.getValue();

            // Aqui decidimos qual campo mostrar como texto
            return switch (field){
                case "nome" -> new ReadOnlyStringWrapper(p.nome());
                case "pontos" -> new ReadOnlyStringWrapper(String.valueOf(p.pontos()));
                default -> new ReadOnlyStringWrapper(String.valueOf(p.vitorias()));
            };
        });
        return c;
    }

    private void refresh(){
        data.setAll(pd.findAll());
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}