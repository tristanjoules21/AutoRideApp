module com.example.autorideapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.autorideapp to javafx.fxml;
    exports com.example.autorideapp;
}
