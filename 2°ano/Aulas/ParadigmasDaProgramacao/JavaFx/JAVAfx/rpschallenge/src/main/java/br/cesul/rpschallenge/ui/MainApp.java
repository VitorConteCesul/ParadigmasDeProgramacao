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

import static br.cesul.rpschallenge.model.Move.*;

public class MainApp extends Application {

    private final ScoreDao dao = new ScoreDao();
    // Itens do listView
    private final ObservableList<String> history = FXCollections.observableArrayList();

    private Button btnPlay, btnResetButton;
    private Label lblStates, lblPlayer, lblCPU, lblRes;
    private ProgressBar bar;
    private HBox cardBox; // para os contadores com figuras

    private int vitorias;
    private int derrotas;
    private int empates;

    @Override
    public void start(Stage stage) throws Exception {
        ComboBox<Move> cbo = new ComboBox<>(
                FXCollections.observableArrayList(Move.values())
        );
        cbo.getSelectionModel().selectFirst(); // Por defalt, fica PEDRA

        btnPlay = new Button("Jogar");
        btnResetButton = new Button("Nova Partida");
        btnResetButton.setDisable(true);

        HBox top = new HBox(8, new Label("Sua Jogada: "), cbo, btnPlay, btnResetButton);
        top.setAlignment(Pos.CENTER_LEFT);

        lblPlayer = new Label("Você: -");
        lblCPU = new Label("CPU: -");
        lblRes = new Label("Resultado: -");
        lblRes.setStyle("-fx-font-weight:bold; -fx-text-fill:#003366");
        HBox lestInfo = new HBox(20, lblPlayer, lblCPU, lblRes);

        lblStates = new Label(); // V: 0 D: 0 L: 0 (0,0% vit)
        bar = new ProgressBar(0);

        cardBox = new HBox(15, card("🪨"), card("🧻"), card("✂️"));
        cardBox.setPadding(new Insets(10));

        ListView<String> list = new ListView<>(history);

        // ------------------ EVENTOS -----------------
        btnPlay.setOnAction(e -> playRound(cbo.getValue()));
        btnResetButton.setOnAction(e -> resetGame());

        // ------------------- Montagem do Layout ----------------------
        VBox root = new VBox(10, top, lestInfo, lblStates, bar, cardBox,
                new Label("Partidas anteriores: "), list
        );
        root.setPadding(new Insets(10));

        // Carregar historico existente (uma vez)
        history.setAll(dao.list());

        // Inicializar a primeira partida
        resetGame();

        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("RPS CHALLENGE");
        stage.show();
    }

    private void resetGame() {
        vitorias = 0;
        derrotas = 0;
        empates = 0;

        updateStatus();

        lblPlayer.setText("Você: -");
        lblCPU.setText("CPU: -");
        lblRes.setText("Resultado: -");

        // Zerar contadores nos cards
        for (int i = 0; i < 3; i++) {
            VBox box = (VBox) cardBox.getChildren().get(i);
            Label count = (Label) box.getChildren().get(1);
            count.setText("0");
        }

        bar.setProgress(0);
        btnResetButton.setDisable(true); // ainda não jogou
    }

    // PlayRound que executa UMA jogada (clique em jogar)
    private void playRound(Move player) {
        Move cpuMove = Move.getRandomMove();

        lblPlayer.setText("Você: " + emoji(player));
        lblCPU.setText("CPU: " + emoji(cpuMove));

        String resultado;

        if (player == cpuMove) {
            resultado = "Empate!";
            empates++;
        } else if (
                (player == PEDRA && cpuMove == TESOURA) ||
                        (player == PAPEL && cpuMove == PEDRA) ||
                        (player == TESOURA && cpuMove == PAPEL)
        ) {
            resultado = "Você venceu!";
            vitorias++;
        } else {
            resultado = "Você perdeu!";
            derrotas++;
        }

        lblRes.setText("Resultado: " + resultado);

        // Atualiza contadores de jogadas
        int index = switch (player) {
            case PEDRA -> 0;
            case PAPEL -> 1;
            case TESOURA -> 2;
        };
        VBox box = (VBox) cardBox.getChildren().get(index);
        Label count = (Label) box.getChildren().get(1);
        int atual = Integer.parseInt(count.getText());
        count.setText(String.valueOf(atual + 1));

        updateStatus();

        // Salvar no histórico (para persistência)
        String linha = String.format("Você: %s | CPU: %s | %s", player, cpuMove, resultado);
        dao.save(linha);
        history.add(0, linha); // adiciona no topo

        btnResetButton.setDisable(false);
    }

    private String emoji(Move move) {
        if (move == null) return "-";
        return switch (move) {
            case PEDRA -> "🪨";
            case PAPEL -> "🧻";
            case TESOURA -> "✂️";
        };
    }

    private void updateStatus() {
        int total = vitorias + derrotas + empates;
        double pct = total > 0 ? (double) vitorias / total : 0;
        lblStates.setText(String.format("V: %d  D: %d  E: %d (%.0f%% vit)", vitorias, derrotas, empates, pct * 100));
        bar.setProgress(pct);
    }

    // Fabrica de cards (Emoji + contador)
    private VBox card(String emoji) {
        Label icon = new Label(emoji);
        icon.setStyle("-fx-font-size:28;");

        Label count = new Label("0");
        VBox box = new VBox(icon, count);
        box.setStyle("-fx-padding:10;" +
                "-fx-background-color:#f2f2f2;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}