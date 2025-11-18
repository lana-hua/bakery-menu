package com.example.cs213project4;

import com.example.cs213project4.menu.MenuItem;
import com.example.cs213project4.menu.Order;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for displaying and managing the current order.
 * Manages the coffee ordering view and interactions.
 * @author Lana Huang
 */
public class CurrentOrderController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private static CurrentOrderController instance;
    private double taxRate = 0.06625;

    @FXML private ListView<MenuItem> currentOrderOutput;

    @FXML private TextField subTotalTextField;
    @FXML private TextField salesTaxTextField;
    @FXML private TextField totalTextField;


    /**
     * This method initializes the CurrentOrder controller display.
     * It sets up all the TextFields for subtotal, sales-tax total, and total.
     */
    public void initialize() {
        subTotalTextField.setEditable(false);
        salesTaxTextField.setEditable(false);
        totalTextField.setEditable(false);

        subTotalTextField.setText("$0.00");
        salesTaxTextField.setText("$0.00");
        totalTextField.setText("$0.00");
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
        currentOrderOutput.setItems(mainController.getCurrentOrder().getItems());
        mainController.getCurrentOrder().getItems().addListener((ListChangeListener<MenuItem>) c -> updatePriceFields());
        updatePriceFields();

    }

    /**
     * Removes a MenuItem from the current order.
     * Makes sure that the current order actually contains the MenuItem.
     */
    @FXML
    private void removeMenuItem() {
        MenuItem selected = currentOrderOutput.getSelectionModel().getSelectedItem();
        currentOrderOutput.getItems().remove(selected);
    }

    /**
     * Adds the current order to the list of completed orders.
     */
    @FXML
    private void addOrder() {
        if (mainController.currentOrder.getItems().isEmpty()) {
            return;
        }

        Order orderCopy = new Order(mainController.currentOrder);
        mainController.listOfOrders.addOrder(orderCopy);
        mainController.currentOrder = new Order();
        currentOrderOutput.setItems(mainController.currentOrder.getItems());
        updatePriceFields();
    }

    /**
     * Calculates the subtotal of all MenuItems in the current order.
     * @return the subtotal price
     */
    private double getSubtotal() {
        double subtotal = 0.0;
        for (MenuItem item : mainController.getCurrentOrder().getItems()) {
            subtotal += item.price();
        }
        return subtotal;
    }

    /**
     * Calculates the sales tax for the current order.
     * @return the sales tax
     */
    private double getSalesTax() {
        return getSubtotal() * taxRate;
    }


    /**
     * Calculates the total price for the current order, including sales tax.
     * @return the total price
     */
    private double getTotal() {
        return getSubtotal() + getSalesTax();
    }

    /**
     * Updates the subtotal, sales tax, and total TextFields
     * based on the current order contents.
     */
    private void updatePriceFields() {
        subTotalTextField.setText(String.format("$%.2f", getSubtotal()));
        salesTaxTextField.setText(String.format("$%.2f", getSalesTax()));
        totalTextField.setText(String.format("$%.2f", getTotal()));
    }

    /**
     * Navigate back to the main view
     */
    @FXML
    public void displayMain() {
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }
}
