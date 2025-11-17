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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Controller class for handling coffee ordering functionality.
 * Manages the coffee ordering view and interactions.
 * @author Lana Huang
 */
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

    @FXML private Text subtotal;

    /**
     * This method initializes the Coffee controller display.
     * It sets up the ListView and the ComboBox displays and adds Listener for the dynamic subtotal price.
     */
    @FXML
    private void initialize() {
        coffeeSizeList = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
        coffeeSize.setItems(coffeeSizeList);

        addInList = FXCollections.observableArrayList("Whipped Cream", "2% Milk", "Vanilla", "Caramel", "Mocha");
        alladdInListView.setItems(addInList);

        quantity = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        coffeeQuantity.setItems(quantity);

        orderAddInListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        coffeeSize.valueProperty().addListener((obs, oldVal, newVal) -> updateSubtotal());
        coffeeQuantity.valueProperty().addListener((obs, oldVal, newVal) -> updateSubtotal());

        orderAddInListView.getItems().addListener((javafx.collections.ListChangeListener<String>) c -> updateSubtotal());

        subtotal.setText("Subtotal: $0.00");
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
    }

    /**
     * Adds Add-In to the current order.
     * Doesn't add the Add-In if it is already in the AddIn list.
     */
    @FXML
    private void addAddIn() {
        String selected = alladdInListView.getSelectionModel().getSelectedItem();
        if (selected != null && !orderAddInListView.getItems().contains(selected)) {
            orderAddInListView.getItems().add(selected);
        }
    }

    /**
     * Removes Add-In from the current order.
     * Makes sure that the current order actually contains the Add-In.
     */
    @FXML
    private void removeAddIn() {
        String selected = String.valueOf(orderAddInListView.getSelectionModel().getSelectedItem());
        if(orderAddInListView.getItems().contains(selected)) {
            orderAddInListView.getItems().removeAll(selected);
        }
    }

    /**
     * Creates a coffee object from the information given and adds it to the current running order list.
     * the size from the size ComboList.
     * the quantity from the quantity ComboList.
     * the list of Add-Ins from the order AddInListView
     */
    @FXML
    private void addCoffeeOrder() {
        String Stringsize = coffeeSize.getValue();
        String stringQuantity = coffeeQuantity.getValue();
        String stringSize = coffeeSize.getValue();

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
        mainController.getCurrentOrder().addItem(newCoffee);
        confirmationCoffeeAdded(newCoffee);
    }

    /**
     * Sends out a confirmation window that the Coffee order was added.
     * Specifies the coffee order in the confirmation window
     * @param coffee the given coffee order that's been added to the Current Order
     */
    private void confirmationCoffeeAdded(Coffee coffee) {
        System.out.println(mainController.getCurrentOrder());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Coffee Order Confirmation");
        alert.setHeaderText("Added to Coffee Order");
        alert.setContentText("Added " + coffee + " to current order.");
        alert.showAndWait();
    }

    /**
     * Updates the current running subtotal as the employee is building the coffee.
     * If no quantity is chosen it assumes a quantity of 1
     */
    private void updateSubtotal() {
        String sizeString = coffeeSize.getValue();
        String quantityString = coffeeQuantity.getValue();
        int qty = 1;
        if (quantityString != null) {
            qty = Integer.parseInt(quantityString);
        }

        if (sizeString == null) {
            subtotal.setText("Subtotal: $0.00");
            return;
        }
        CupSize size = CupSize.valueOf(sizeString);

        ArrayList<AddIns> addInsList = new ArrayList<>();
        for (String s : orderAddInListView.getItems()) {
            AddIns a = AddIns.fromString(s);
            if (a != null) addInsList.add(a);
        }

        Coffee tempCoffee = new Coffee(qty, size, addInsList);

        subtotal.setText(String.format("Subtotal: $%.2f", tempCoffee.price()));
    }

    @FXML
    /**
     * Navigate back to the main view.
     */
    public void displayMain() {
        //stage.close(); //close the window.
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Main Menu");
        stage.close(); //close the window.

    }


}
