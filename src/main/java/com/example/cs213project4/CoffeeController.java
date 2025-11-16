package com.example.cs213project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.util.List;

public class CoffeeController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private ObservableList<String> coffeeSizeList, addInList, quantity;

    @FXML private ComboBox<String> coffeeSize;
    @FXML private ComboBox<String> coffeeQuantity;

    @FXML private ListView<String> alladdInListView;
    @FXML private ListView<String> orderAddInListView;

    @FXML private Button addAddIn;
    @FXML private Button removeAddIn;
    @FXML private Button coffeeAddToOrder;
    @FXML private Button returnToMainMenu;

    @FXML
    private void initialize() {
        coffeeSizeList = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
        coffeeSize.setItems(coffeeSizeList);

        addInList = FXCollections.observableArrayList("Whipped Cream", "Milk", "Vanilla", "Caramel", "Mocha");
        alladdInListView.setItems(addInList);

        quantity = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        coffeeQuantity.setItems(quantity);

        orderAddInListView.setSelectionModel(null);
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
    private void addAddIn() {
        String selected = String.valueOf(alladdInListView.getSelectionModel().getSelectedItems());
        if(!orderAddInListView.getItems().contains(selected)) {
            orderAddInListView.getItems().add(selected);
        }
    }

    @FXML
    private void removeAddIn() {
        String selected = String.valueOf(alladdInListView.getSelectionModel().getSelectedItems());
        if(orderAddInListView.getItems().contains(selected)) {
            orderAddInListView.getItems().removeAll(selected);
        }
    }

    @FXML
    private void addCoffeeOrder() {
        String size = coffeeSize.getValue();
        
        if(size == null || size.isEmpty()) {
            return;
        }

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
