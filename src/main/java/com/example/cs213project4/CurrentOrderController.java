package com.example.cs213project4;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class CurrentOrderController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private static CurrentOrderController instance;

    @FXML private TextArea outputArea = new TextArea();

    public void initialize() {
        CurrentOrderController.getInstance().outputArea.setText("Welcome to the Vehicle Management System");
        instance = this;
    }

    public static CurrentOrderController getInstance() {
        return instance;
    }


    public void setMainController (MainController controller,
                                   Stage stage,
                                   Stage primaryStage,
                                   Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    @FXML
    /**
     * Navigate back to the main view.
     */
    public void displayMain() {
        //stage.close(); //close the window.
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
}
