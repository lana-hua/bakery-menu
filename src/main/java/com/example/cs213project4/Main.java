package com.example.cs213project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        MainController mainController = fxmlLoader.getController();
        mainController.setPrimaryStage(stage, scene);
        stage.setTitle("RU Donuts");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
