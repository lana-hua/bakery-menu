package com.example.cs213project4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private Stage primaryStage; //the reference of the main window.
    private Scene primaryScene; //the ref. of the scene set to the primaryStage

    /**
     * Set the reference of the stage and scene before show()
     * @param stage the stage used to display the scene
     * @param scene the scene set to the stage
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    @FXML
    protected void displaySecondView() {
        Stage view1 = new Stage(); //if we want to use a new window
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffeeOrdering.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 600, 600);
            //view1.setScene(scene); //if we wnat to use the new window to draw the scene graph
            //view1.setTitle("view1");
            //view1.show();
            primaryStage.setScene(scene);
            CoffeeController coffeeController = loader.getController();
            /*
              The statement below is to pass the references of the MainController objects
              to the SecondViewController object so the SecondViewController can call the
              public methods in the MainController or to navigate back to the main view.
             */
            coffeeController.setMainController(this, view1, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading coffeeOrdering.fxml.");
            alert.setContentText("Couldn't load coffeeOrdering.fxml.");
            alert.showAndWait();
        }
    }

}
