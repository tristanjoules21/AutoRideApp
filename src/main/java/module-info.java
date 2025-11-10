module com.example.autorideapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.autorideapp.autoride to javafx.fxml, javafx.graphics; // ✅ allow JavaFX to access
    exports com.example.autorideapp.autoride; // ✅ export for launcher
}
