package com.example.cs213project4;

import com.example.cs213project4.menu.MenuItem;
import com.example.cs213project4.menu.Order;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class OrdersPlacedController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    @FXML private TextField total;
    @FXML private TextField orderTotal;

    @FXML private ListView<Order> listOfOrders;
    @FXML private ListView<MenuItem> orderDetails;

    public void initialize() {
        total.setEditable(false);
        orderTotal.setEditable(false);

        total.setText("$0.00");
        orderTotal.setText("$0.00");
    }

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

        listOfOrders.setItems(mainController.getListOfOrders().getOrders());
        mainController.getCurrentOrder().getItems().addListener((ListChangeListener<MenuItem>) c -> updatePriceFields());
        updatePriceFields();

        listOfOrders.getSelectionModel().selectedItemProperty().addListener((obs, oldOrder, newOrder) -> {
            if (newOrder != null) {
                orderDetails.setItems(newOrder.getItems());
                updatePriceFields();
            }
        });
    }

    @FXML
    private void exportOrders() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Orders");

        fileChooser.setInitialFileName("orders.txt");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            mainController.getListOfOrders().exportToFile(file);
        }
    }

    @FXML
    private void cancelOrder() {
        Order selected = listOfOrders.getSelectionModel().getSelectedItem();

        if (selected == null) {
            return;
        }
        mainController.getListOfOrders().removeItem(selected);

        // Update price fields
        updatePriceFields();
    }

    private void updatePriceFields() {
        total.setText(String.format("$%.2f", mainController.getListOfOrders().getTotalCost()));

        Order selected = listOfOrders.getSelectionModel().getSelectedItem();
        if (selected != null) {
            orderTotal.setText(String.format("$%.2f", selected.getTotalCost()));
        }
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
