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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Controller class for handling donut ordering functionality.
 * Manages the donut ordering view and interactions.
 * @author Sharon Chen
 */
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
    @FXML private Text subtotal;
    @FXML private Button donutAddToOrder;
    @FXML private Button returnToMainMenu;

    @FXML private ImageView donutImage;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize() {
        donutTypeList = FXCollections.observableArrayList("Cake Donut", "Donut Hole", "Seasonal Donut", "Yeast Donut");
        donutType.setItems(donutTypeList);

        donutType.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateFlavors(newValue);
        });

        quantity = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
        donutQuantity.setItems(quantity);

        subtotal.setText("Subtotal: $0.00");

        donutType.setOnAction(event -> updateImage());

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
     * Dynamically updates Image depending on donut type in ComboBox
     */
    private void updateImage() {
        String selected = donutType.getValue();

        if (selected == null) return;

        String fileName = switch (selected) {
            case "Cake Donut" -> "cakedonut.png";
            case "Donut Hole" -> "donuthole.png";
            case "Seasonal Donut" -> "seasonaldonut.png";
            case "Yeast Donut" -> "yeastdonut.png";
            default -> null;
        };

        if (fileName != null) {
            Image img = new Image(getClass().getResource("/images/" + fileName).toString());
            donutImage.setImage(img);
        }
    }


    /**
     * Updates the available flavors based on the selected donut type.
     * @param type the selected donut type
     */
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
                ObservableList<String> seasonalFlavors = FXCollections.observableArrayList("Pumpkin Spice", "Spooky");
                donutFlavors.setItems(seasonalFlavors);
            }
            case "Yeast Donut" -> {
                ObservableList<String> yeastFlavors = FXCollections.observableArrayList("Plain", "Glazed", "Chocolate Frosted", "Strawberry Frosted", "Powdered Sugar", "Cinnamon Sugar");
                donutFlavors.setItems(yeastFlavors);
            }
        }
    }

    /**
     * Adds a donut to the current order display and prevents duplicate donut type and flavor combinations.
     */
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

    /**
     * Removes the selected donut from the current order display.
     */
    @FXML
    private void removeDonut() {
        int index = orderedDonuts.getSelectionModel().getSelectedIndex();
        if(index >= 0) {
            orderedDonuts.getItems().remove(index);
            calculateSubtotal();
        }
    }

    /**
     * Adds all donuts in the current order to the main order list and converts the display strings to MenuItem objects.
     */
    @FXML
    private void addDonutOrder() {
        if(orderedDonuts.getItems().isEmpty()){
            return;
        }

        for (int i = 0; i < orderedDonuts.getItems().size(); i++) {
            String donutString = orderedDonuts.getItems().get(i);
            MenuItem donut = createDonut(donutString);
            if (donut != null){
                mainController.getCurrentOrder().addItem(donut);
            }
        }
        confirmationDonutsAdded();
        orderedDonuts.getItems().clear();
        calculateSubtotal();
    }

    /**
     * Creates a MenuItem object from a donut display string.
     * @param donutString the display string representing the donut
     * @return the created MenuItem object, or null if the parsing fails
     */
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

    /**
     * Calculates and updates the subtotal for the current donut order.
     */
    private void calculateSubtotal() {
        double total = 0.0;
        for (int i = 0; i < orderedDonuts.getItems().size(); i++) {
            String donutString = orderedDonuts.getItems().get(i);
            total += calculateSpecificPrice(donutString);
        }
        subtotal.setText(String.format("Subtotal: $%.2f", total));
    }

    /**
     * Calculates the price for a specific donut from its display string.
     * @param donutString the display string represent the donut
     * @return the calculated price for the donut 
     */
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

    /**
     * Sends out a confirmation window that the donut order was added.
     * Specifies the donut order in the confirmation window
     */
    private void confirmationDonutsAdded() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Donut Order Confirmation");
        alert.setHeaderText("Add to Current Order");
        alert.setContentText("Added " + orderedDonuts.getItems().size() + " donut items to current order.");
        alert.showAndWait();
    }

    /**
     * Navigate back to the main view.
     */
    @FXML
    public void displayMain() {
        //stage.close(); //close the window.
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }
}