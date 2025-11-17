package com.example.cs213project4;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrdersPlacedController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    /**
     * Sets the main controller reference for communication between controllers.
     * @param controller the main controller instance
     * @param stage the current stage
     * @param primaryStage the primary application stage
     * @param primaryScene the main menu scene
     */
    public void setMainController (MainController controller,
                                   Stage stage,
                                   Stage primaryStage,
                                   Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    /**
     * Navigate back to the main view
     */
    @FXML
    public void displayMain() {
        //stage.close(); //close the window.
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }
}
