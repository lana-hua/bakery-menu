package com.example.cs213project4;

import com.example.cs213project4.menu.MenuItem;
import com.example.cs213project4.menu.donut.CakeDonut;
import com.example.cs213project4.menu.donut.DonutHole;
import com.example.cs213project4.menu.donut.SeasonalDonut;
import com.example.cs213project4.menu.donut.YeastDonut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DonutController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private ObservableList<String> donutTypeList, cakeFlavorsList, holeFlavorsList, seasonalFlavorsList, yeastFlavorsList;
    private ObservableList<Integer> quantity;

    @FXML private ComboBox<String> donutType;
    @FXML private ComboBox<Integer> donutQuantity;

    @FXML private ListView<String> donutFlavors;
    @FXML private ListView<String> orderedDonuts;

    @FXML private Button addDonut;
    @FXML private Button removeDonut;
    @FXML private Label subtotal;
    @FXML private Button donutAddToOrder;
    @FXML private Button returnToMainMenu;

    @FXML
    private void initialize() {
        donutTypeList = FXCollections.observableArrayList("Cake Donut", "Donut Hole", "Seasonal Donut", "Yeast Donut");
        donutType.setItems(donutTypeList);

        donutType.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateFlavors(newValue);
        });

        quantity = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
        donutQuantity.setItems(quantity);

        subtotal.setText("subtotal: $0.00");
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

    private void updateFlavors(String type) {
        if (type == null) return;

        switch (type) {
            case "Cake Donut" -> {
                ObservableList<String> cakeFlavors = FXCollections.observableArrayList("Plain", "Glazed", "Chocolate Frosted");
                donutFlavors.setItems(cakeFlavors);
            }
            case "Donut Hole" -> {
                ObservableList<String> holeFlavors = FXCollections.observableArrayList("Plain", "Jelly", "Chocolate");
                donutFlavors.setItems(holeFlavors);
            }
            case "Seasonal Donut" -> {
                ObservableList<String> seasonalFlavors = FXCollections.observableArrayList("Pumpkin Spice", "Apple Crumb", "Maple");
                donutFlavors.setItems(seasonalFlavors);
            }
            case "Yeast Donut" -> {
                ObservableList<String> yeastFlavors = FXCollections.observableArrayList("Plain", "Glazed", "Chocolate Frosted", "Vanilla Frosted", "Powdered Sugar", "Cinnamon Sugar");
                donutFlavors.setItems(yeastFlavors);
            }
        }
    }

    @FXML
    private void addDonut() {
        String type = donutType.getValue();
        String flavor = donutFlavors.getSelectionModel().getSelectedItem();
        Integer quantity = donutQuantity.getValue();

        if(type == null || flavor == null || quantity == null){
            return;
        }

        for (int i = 0; i < orderedDonuts.getItems().size(); i++){
            String existingDonut = orderedDonuts.getItems().get(i);
            if (existingDonut.contains(flavor + " " + type)){
                return;
            }
        }

        String donutDisplay = flavor + " " + type + " (" + quantity + ")";
        orderedDonuts.getItems().add(donutDisplay);
        calculateSubtotal();
    }

    @FXML
    private void removeDonut() {
        int index = orderedDonuts.getSelectionModel().getSelectedIndex();
        if(index >= 0) {
            orderedDonuts.getItems().remove(index);
            calculateSubtotal();
        }
    }

    @FXML
    private void addDonutOrder() {
        if(orderedDonuts.getItems().isEmpty()){
            return;
        }

        for (int i = 0; i < orderedDonuts.getItems().size(); i++) {
            String donutString = orderedDonuts.getItems().get(i);
            MenuItem donut = createDonut(donutString);
            if (donut != null){
                mainController.getcurrentOrder().add(donut);
            }
        }
        orderedDonuts.getItems().clear();
    }

    private MenuItem createDonut(String donutString){
        String[] parts = donutString.split(" ");

        String type = parts[parts.length - 3] + " " + parts[parts.length - 2];
        int quantity = Integer.parseInt(parts[parts.length - 1].replace("(", "").replace(")", ""));

        String flavor = "";
        for (int i = 0; i < parts.length - 3; i++) {
            flavor += parts[i] + " ";
        }
        flavor = flavor.trim();

        switch (type) {
            case "Cake Donut" -> {
                return new CakeDonut(quantity, flavor);
            }
            case "Donut Hole" -> {
                return new DonutHole(quantity, flavor);
            }
            case "Seasonal Donut" -> {
                return new SeasonalDonut(quantity, flavor);
            }
            case "Yeast Donut" -> {
                return new YeastDonut(quantity, flavor);
            }
            default -> {
                return null;
            }
        }
    }

    private void calculateSubtotal() {
        double total = 0.0;
        for (int i = 0; i < orderedDonuts.getItems().size(); i++) {
            String donutString = orderedDonuts.getItems().get(i);
            total += calculateSpecificPrice(donutString);
        }
        subtotal.setText(String.format("subtotal: $%.2f", total));
    }

    private double calculateSpecificPrice(String donutString) {
        String[] parts = donutString.split(" ");
        String type = parts[parts.length - 3] + " " + parts[parts.length - 2];
        int quantity = Integer.parseInt(parts[parts.length - 1].replace("(", "").replace(")", ""));

        switch (type) {
            case "Cake Donut" -> {
                return CakeDonut.basePrice() * quantity;
            }
            case "Donut Hole" -> {
                return DonutHole.basePrice() * quantity;
            }
            case "Seasonal Donut" -> {
                return SeasonalDonut.basePrice() * quantity;
            }
            case "Yeast Donut" -> {
                return YeastDonut.basePrice() * quantity;
            }
            default -> {
                return 0.0;
            }
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