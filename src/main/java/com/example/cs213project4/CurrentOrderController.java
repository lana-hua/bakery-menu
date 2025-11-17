package com.example.cs213project4;

import com.example.cs213project4.menu.MenuItem;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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


    public void initialize() {
        subTotalTextField.setEditable(false);
        salesTaxTextField.setEditable(false);
        totalTextField.setEditable(false);

        subTotalTextField.setText("$0.00");
        salesTaxTextField.setText("$0.00");
        totalTextField.setText("$0.00");
    }

    public void setMainController (MainController controller,
                                   Stage stage,
                                   Stage primaryStage,
                                   Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
        currentOrderOutput.setItems(mainController.getCurrentOrder());
        mainController.getCurrentOrder().addListener((ListChangeListener<MenuItem>) c -> updatePriceFields());
        updatePriceFields();

    }

    private double getSubtotal() {
        return mainController.getCurrentOrder().stream()
                .mapToDouble(MenuItem::price)
                .sum();
    }

    private double getSalesTax() {
        return getSubtotal() * taxRate;
    }

    private double getTotal() {
        return getSubtotal() + getSalesTax();
    }

    private void updatePriceFields() {
        subTotalTextField.setText(String.format("$%.2f", getSubtotal()));
        salesTaxTextField.setText(String.format("$%.2f", getSalesTax()));
        totalTextField.setText(String.format("$%.2f", getTotal()));
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
