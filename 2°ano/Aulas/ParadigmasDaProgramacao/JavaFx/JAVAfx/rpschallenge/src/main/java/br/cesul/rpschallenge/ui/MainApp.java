package br.cesul.rpschallenge.ui;

import br.cesul.rpschallenge.dao.ScoreDao;
import br.cesul.rpschallenge.model.Move;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.smartcardio.Card;
import java.util.List;

public class MainApp extends Application {

    private final ScoreDao dao = new ScoreDao();
    // Itens do listView
    private final ObservableList<String> history = FXCollections.observableArrayList();

    private Button btnPlay, btnResetButton;
    private Label lblStates, lblPlayer, lblCPU, lblRes;
    private ProgressBar bar;
    private HBox cardBox; // para os contadores com figuras

     @Override
    public void start(Stage stage) throws Exception{
         ComboBox<Move> cbo = new ComboBox<>(
                 FXCollections.observableArrayList(Move.values())
         );
         cbo.getSelectionModel().selectFirst(); // Por defalt, fica PEDRA

         btnPlay = new Button("Jogar");
         btnResetButton = new Button("Nova Partida");
         btnResetButton.setDisable(true);


         HBox top = new HBox(8, new Label("Sua Jogada: "), cbo, btnPlay, btnResetButton);
         top.setAlignment(Pos.CENTER_LEFT);

         lblPlayer = new Label( "Você: -");
         lblCPU = new Label( "CPU: -");
         lblRes = new Label( "Resultado: -");
         lblRes. setStyle("-fx-front-weight:bold; -fx-text-fill:#003366");
         HBox lestInfo = new HBox(20, lblPlayer, lblCPU, lblRes);

         lblStates = new Label(); // V: 0 D: 0 L: 0 (0,0% vit)
         bar = new ProgressBar(0);

         cardBox = new HBox(15, card("🪨"), card("🧻"), card("✂️"));
         cardBox.setPadding(new Insets(10));

         ListView<String> list = new ListView<>(history);

         // ------------------ EVENTOS -----------------

         btnPlay.setOnAction(e ->
                playRound(cbo.getValue())
         );

         btnResetButton.setOnAction(e ->
                 resetGame()
         );

         // ------------------- Montagem do Layout ----------------------
            VBox root = new VBox(10, top, lestInfo, lblStates, bar, cardBox,
                        new Label("Partidas anteriores: "), list
            );
            root.setPadding(new Insets(10));

            // Carregar historico existente (uma vez)
            history.setAll(dao.list);

            // Inicializar a primeira partida
            resetGame();

            stage.setScene(new Scene(root, 500, 500));
            stage.setTitle("RPS CHALLENGE");
            stage.show();
     }

     private void resetGame(){

     }


     // PlayRound que executa UMA jogada (clique em jogar)
    private void playRound(Move player){

    }

    // Fabrica de cards (Emoji + contador)
    private VBox card(String emoji){
        Label icon = new Label(emoji);
        icon.setStyle("-fx-font-size:28;");

        Label count = new Label("0");
        VBox box = new VBox(icon, count);
        box.setStyle("-fx-padding:10;" +
                "fx-background-color:#f2f2f2;" +
                "fx-border-radius:8;" +
                "fx-background-radius:8;"
                );
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
