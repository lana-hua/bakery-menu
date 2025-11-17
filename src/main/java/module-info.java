module com.example.cs213project4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens com.example.cs213project4 to javafx.fxml;
    exports com.example.cs213project4;
    exports com.example.cs213project4.menu;
    opens com.example.cs213project4.menu to javafx.fxml;
    exports com.example.cs213project4.menu.donut;
    opens com.example.cs213project4.menu.donut to javafx.fxml;
    exports com.example.cs213project4.menu.coffee;
    opens com.example.cs213project4.menu.coffee to javafx.fxml;
}