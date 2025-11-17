package com.example.cs213project4;

import com.example.cs213project4.menu.sandwich.AddOns;
import com.example.cs213project4.menu.sandwich.Bread;
import com.example.cs213project4.menu.sandwich.Protein;
import com.example.cs213project4.menu.sandwich.Sandwich;
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

public class SandwichController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private ObservableList<String> proteinList, breadList, addOnList;
    private ObservableList<Integer> quantity;


    @FXML private ComboBox<String> proteinType;
    @FXML private ComboBox<String> breadType;
    @FXML private ComboBox<Integer> sandwichQuantity;

    @FXML private ListView<String> allAddOnsListView;
    @FXML private ListView<String> orderAddOnListView;

    @FXML private Text subtotal;

    @FXML
    private void initialize() {
        breadList = FXCollections.observableArrayList("Bagel", "Sourdough", "Wheat Bread");
        breadType.setItems(breadList);

        proteinList = FXCollections.observableArrayList("Beef", "Chicken", "Salmon");
        proteinType.setItems(proteinList);

        addOnList = FXCollections.observableArrayList("Cheese", "Lettuce", "Onion", "Tomato");
        allAddOnsListView.setItems(addOnList);

        quantity = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
        sandwichQuantity.setItems(quantity);

        orderAddOnListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        proteinType.valueProperty().addListener((obs, oldVal, newVal) -> updateSubtotal());
        sandwichQuantity.valueProperty().addListener((obs, oldVal, newVal) -> updateSubtotal());

        orderAddOnListView.getItems().addListener((javafx.collections.ListChangeListener<String>) c -> updateSubtotal());

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
    private void addAddOn() {
        String selected = allAddOnsListView.getSelectionModel().getSelectedItem();
        if (selected != null && !orderAddOnListView.getItems().contains(selected)) {
            orderAddOnListView.getItems().add(selected);
        }
    }

    /**
     * Removes Add-On from the current order.
     * Makes sure that the current order actually contains the Add-On.
     */
    @FXML
    private void removeAddIn() {
        String selected = String.valueOf(orderAddOnListView.getSelectionModel().getSelectedItem());
        if(orderAddOnListView.getItems().contains(selected)) {
            orderAddOnListView.getItems().removeAll(selected);
        }
    }

    /**
     * Creates a Sandwich object from the information given and adds it to the current running order list.
     */
    @FXML
    private void addSandwichOrder() {
        String proteinInput = proteinType.getValue();
        String breadInput = breadType.getValue();
        Integer quantity = sandwichQuantity.getValue();

        if(proteinInput == null || proteinInput.isEmpty() || breadInput == null || breadInput.isEmpty() || quantity == null) {
            return;
        }

        ArrayList<AddOns> addOnsList = new ArrayList<>();

        for (String s : orderAddOnListView.getItems()) {
            AddOns addOn = AddOns.fromString(s);
            if (addOn != null) {
                addOnsList.add(addOn);
            } else {
                System.out.println("Invalid AddIn: " + s);
            }
        }

        Bread bread = Bread.valueOf(breadInput);
        Protein protein = Protein.valueOf(proteinInput);

        Sandwich newSandwich = new Sandwich(quantity, bread, protein, addOnsList);
        mainController.getCurrentOrder().addItem(newSandwich);
        confirmationSandwichAdded(newSandwich);
    }

    /**
     * Sends out a confirmation window that the Sandwich order was added.
     * Specifies the sandwich order in the confirmation window
     * @param sandwich the given sandwich order that's been added to the Current Order
     */
    private void confirmationSandwichAdded(Sandwich sandwich) {
        System.out.println(mainController.getCurrentOrder());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sandwich Order Confirmation");
        alert.setHeaderText("Add to Current Order");
        alert.setContentText("Added " + sandwich + " to current order.");
        alert.showAndWait();
    }

    /**
     * Updates the current running subtotal as the employee is building the coffee.
     * If no quantity is chosen it assumes a quantity of 1
     */
    private void updateSubtotal() {
        String proteinInput = proteinType.getValue();
        String breadInput = breadType.getValue();
        Integer quantityInput = sandwichQuantity.getValue();

        int quantity = 1;
        if (quantityInput != null) {
            quantity = quantityInput;
        }

        if (proteinInput == null) {
            subtotal.setText("Subtotal: $0.00");
            return;
        }

        Bread bread = Bread.valueOf(breadInput);
        Protein protein = Protein.valueOf(proteinInput);

        ArrayList<AddOns> addOnsList = new ArrayList<>();
        for (String addOnString : orderAddOnListView.getItems()) {
            AddOns addOn = AddOns.fromString(addOnString);
            if (addOn != null) addOnsList.add(addOn);
        }
        Sandwich tempSandwich = new Sandwich(quantity, bread, protein, addOnsList);

        subtotal.setText(String.format("Subtotal: $%.2f", tempSandwich.price()));
    }

    @FXML
    /**
     * Navigate back to the main view.
     */
    public void displayMain() {
        //stage.close(); //close the window.
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        stage.close(); //close the window.

    }
}
