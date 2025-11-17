package com.example.cs213project4;

import com.example.cs213project4.menu.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    private Stage primaryStage; //the reference of the main window.
    private Scene primaryScene; //the ref. of the scene set to the primaryStage
    public Orders orders;
    public ArrayList<MenuItem> currentOrder = new ArrayList<>();

    /**
     * Set the reference of the stage and scene before show()
     * @param stage the stage used to display the scene
     * @param scene the scene set to the stage
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    public ArrayList<MenuItem> getcurrentOrder() {
        return currentOrder;
    }

    @FXML
    protected void displayCoffeeView() {
        Stage view1 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffeeOrdering.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 600, 600);
            primaryStage.setScene(scene);
            CoffeeController coffeeController = loader.getController();
            coffeeController.setMainController(this, view1, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading coffeeOrdering.fxml.");
            alert.setContentText("Couldn't load coffeeOrdering.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayDonutView() {
        Stage view1 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donutOrdering.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 600, 600);
            primaryStage.setScene(scene);
            DonutController donutController = loader.getController();
            donutController.setMainController(this, view1, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading coffeeOrdering.fxml.");
            alert.setContentText("Couldn't load coffeeOrdering.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayCurrentOrder() {
        Stage view1 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("currentOrder.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 600, 600);
            primaryStage.setScene(scene);
            CurrentOrderController currentOrderController = loader.getController();
            currentOrderController.setMainController(this, view1, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading currentOrder.fxml.");
            alert.setContentText("Couldn't load currentOrder.fxml.");
            alert.showAndWait();
        }
    }

}
