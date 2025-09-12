package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var root = FXMLLoader.load(getClass().getResource("/org.example.view/lancamento.fxml"));
        stage.setTitle("Lancamentos - Factory Method + MongoDB");
        stage.setScene(new Scene((Parent) root));
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}
