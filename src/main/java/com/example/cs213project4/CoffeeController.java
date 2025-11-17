package com.example.cs213project4;

import com.example.cs213project4.menu.coffee.AddIns;
import com.example.cs213project4.menu.coffee.Coffee;
import com.example.cs213project4.menu.coffee.CupSize;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @FXML
    private void initialize() {
        coffeeSizeList = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
        coffeeSize.setItems(coffeeSizeList);

        addInList = FXCollections.observableArrayList("Whipped Cream", "2% Milk", "Vanilla", "Caramel", "Mocha");
        alladdInListView.setItems(addInList);

        quantity = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        coffeeQuantity.setItems(quantity);

        orderAddInListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        String selected = alladdInListView.getSelectionModel().getSelectedItem();
        if (selected != null && !orderAddInListView.getItems().contains(selected)) {
            orderAddInListView.getItems().add(selected);
        }
    }

    @FXML
    private void removeAddIn() {
        String selected = String.valueOf(alladdInListView.getSelectionModel().getSelectedItem());
        if(orderAddInListView.getItems().contains(selected)) {
            orderAddInListView.getItems().removeAll(selected);
        }
    }

    @FXML
    private void addCoffeeOrder() {
        String Stringsize = coffeeSize.getValue();
        String stringQuantity = coffeeQuantity.getValue();
        String stringSize = coffeeSize.getValue();
        ObservableList<String> selectedStrings = orderAddInListView.getSelectionModel().getSelectedItems();

        if(stringSize == null || stringSize.isEmpty() || stringQuantity == null || stringQuantity.isEmpty() || Stringsize == null || Stringsize.isEmpty()) {
            return;
        }

        ArrayList<AddIns> addInsList = new ArrayList<>();

        for (String s : orderAddInListView.getItems()) {
            AddIns addIn = AddIns.fromString(s);
            if (addIn != null) {
                addInsList.add(addIn);
            } else {
                System.out.println("Invalid AddIn: " + s);
            }
        }

        int quantity = Integer.parseInt(stringQuantity);
        CupSize size = CupSize.valueOf(stringSize);
        Coffee newCoffee = new Coffee(quantity, size, addInsList);
        System.out.println(newCoffee);

        mainController.getcurrentOrder().add(newCoffee);
        System.out.println(mainController.getcurrentOrder());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Coffee Order Confirmation");
        alert.setHeaderText("Added to Coffee Order");
        alert.setContentText("Added " + newCoffee + " to current order.");
        alert.showAndWait();
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
