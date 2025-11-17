package com.example.cs213project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
    @FXML private Button donutAddToOrder;
    @FXML private Button returnToMainMenu;

    @FXML
    private void initialize() {
        donutTypeList = FXCollections.observableArrayList("Cake Donut", "Donut Hole", "Seasonal Donut", "Yeast Donut");
        donutType.setItems(donutTypeList);

        String type = donutType.getValue();

        switch (type) {
            case "Cake Donut" -> {
                cakeFlavorsList = FXCollections.observableArrayList("Plain", "Glazed", "Chocolate Frosted");
                donutFlavors.setItems(cakeFlavorsList);
            }
            case "Donut Hole" -> {
                holeFlavorsList = FXCollections.observableArrayList("Plain", "Jelly", "Chocolate");
                donutFlavors.setItems(holeFlavorsList);
            }
            case "Seasonal Donut" -> {
                seasonalFlavorsList = FXCollections.observableArrayList("Pumpkin Spice", "Apple Crumb", "Maple");
                donutFlavors.setItems(seasonalFlavorsList);
            }
            case "Yeast Donut" -> {
                yeastFlavorsList = FXCollections.observableArrayList("Plain", "Glazed", "Chocolate Frosted", "Vanilla Frosted", "Powdered Sugar", "Cinnamon Sugar");
                donutFlavors.setItems(yeastFlavorsList);
            }
        }

        quantity = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
        donutQuantity.setItems(quantity);

        orderedDonuts.setSelectionModel(null);
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
    private void addDonut() {
        String type = donutType.getValue();
        String flavor = donutFlavors.getSelectionModel().getSelectedItem();
        Integer quantity = donutQuantity.getValue();

        if(type == null || flavor == null || quantity == null){
            return;
        }

        String donut = flavor + " " + type + " (" + quantity + ")";

        if(!orderedDonuts.getItems().contains(donut)){
            orderedDonuts.getItems().add(donut);
        }
    }

    @FXML
    private void removeDonut() {
        String selected = String.valueOf(orderedDonuts.getSelectionModel().getSelectedItems());
        if(orderedDonuts.getItems().contains(selected)) {
            orderedDonuts.getItems().removeAll(selected);
        }
    }

    private void addDonutOrder() {
        if(orderedDonuts.getItems().isEmpty()){
            return;
        }

        for (String donutDisplay : orderedDonuts.getItems()) {

        }
        orderedDonuts.getItems().clear();
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
