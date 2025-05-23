package org.vito.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainApp extends Application {

    // Toda vez que você for manipular uma lista, voce remove ou adiciona elementos a ela
    // você não recria a lista

    // placeholder = []
    private final ObservableList<String> placeholder = FXCollections.observableArrayList();

    public void start(Stage stage){
        // Tabela de dados
        TableView<String> table = new TableView<>(placeholder);
        table.setPlaceholder(new Label("No placeholder"));

        //Coluna
        TableColumn<String, String> colName = new TableColumn<>("Name");
        colName.setMinWidth(169);

        // Inserindo coluna na table
        table.getColumns().add(colName);

        // -------barra de controles ----------
        TextField txtName = new TextField();
        txtName.setPromptText("Nome do jogador");

        Button btnNovo = new Button("Novo");
        Button btnRolar = new Button("Rolar dado");
        Button btnReset = new Button("Reset");
        // até aqui não colocamos nenhuma ação, apenas visual

        HBox comandos = new HBox(8, new Label("Nome"), txtName, btnNovo, btnRolar, btnReset);

        comandos.setPadding(new Insets(10));

        VBox root = new VBox(comandos, table);

        Scene cena = new Scene(root, 380, 420);

        stage.setScene(cena);
        stage.setTitle("Dice Game");

        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}