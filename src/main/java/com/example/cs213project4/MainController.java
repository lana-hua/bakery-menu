package com.example.cs213project4;

import com.example.cs213project4.menu.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private Stage primaryStage; //the reference of the main window.
    private Scene primaryScene; //the ref. of the scene set to the primaryStage
    public Order currentOrder = new Order();;

    /**
     * Set the reference of the stage and scene before show()
     * @param stage the stage used to display the scene
     * @param scene the scene set to the stage
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    public Order getCurrentOrder() {
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
            primaryStage.setTitle("Coffee Ordering Screen");
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
        Stage view2 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donutOrdering.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 600, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Donut Ordering Screen");
            DonutController donutController = loader.getController();
            donutController.setMainController(this, view2, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading donutOrdering.fxml.");
            alert.setContentText("Couldn't load donutOrdering.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displaySandwichView() {
        Stage view2 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sandwichOrdering.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 600, 600);
            primaryStage.setScene(scene);
            SandwichController sandwichController = loader.getController();
            sandwichController.setMainController(this, view2, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading sandwichOrdering.fxml.");
            alert.setContentText("Couldn't load sandwichOrdering.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayCurrentOrder() {
        Stage view3 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("currentOrder.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 600, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Current Order Screen");
            CurrentOrderController currentOrderController = loader.getController();
            currentOrderController.setMainController(this, view3, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading currentOrder.fxml.");
            alert.setContentText("Couldn't load currentOrder.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayPlacedOrder() {
        Stage view4 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("placedOrders.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 600, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Placed Order Screen");
            OrdersPlacedController ordersPlacedController = loader.getController();
            ordersPlacedController.setMainController(this, view4, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading placedOrders.fxml.");
            alert.setContentText("Couldn't load placedOrders.fxml.");
            alert.showAndWait();
        }
    }
}
