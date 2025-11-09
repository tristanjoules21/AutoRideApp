package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddCarController {

    @FXML
    private TextField modelField;

    @FXML
    private TextField plateNumberField;

    @FXML
    private TextField yearField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField seatsField;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<String> fuelTypeComboBox;

    @FXML
    private ComboBox<String> transmissionComboBox;

    @FXML
    private TextField imageUrlField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button addCarButton;

    @FXML
    public void initialize() {
        // Initialize ComboBoxes with values to match your design

        typeComboBox.getItems().addAll("Sedan", "SUV", "Hatchback", "Truck");
        fuelTypeComboBox.getItems().addAll("Gasoline", "Diesel", "Electric", "Hybrid");
        transmissionComboBox.getItems().addAll("Automatic", "Manual");

        // Optionally, set default values if you want
        yearField.setText("2025");
        typeComboBox.getSelectionModel().select("Sedan");
        fuelTypeComboBox.getSelectionModel().select("Gasoline");
        transmissionComboBox.getSelectionModel().select("Automatic");
        imageUrlField.setText("https://example.com/car-image.jpg");

        // No functional event handlers — pure design
    }
}
