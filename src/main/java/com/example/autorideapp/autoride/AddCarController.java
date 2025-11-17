package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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
    private ImageView photoPreview;

    @FXML
    private Button cancelButton;

    @FXML
    private Button addCarButton;

    @FXML
    public void initialize() {
        // Populate combo boxes
        typeComboBox.getItems().addAll("Sedan", "SUV", "Hatchback", "Truck");
        fuelTypeComboBox.getItems().addAll("Gasoline", "Diesel", "Electric", "Hybrid");
        transmissionComboBox.getItems().addAll("Automatic", "Manual");

        // Default values
        yearField.setText("2025");
        typeComboBox.getSelectionModel().select("Sedan");
        fuelTypeComboBox.getSelectionModel().select("Gasoline");
        transmissionComboBox.getSelectionModel().select("Automatic");
        imageUrlField.setText("https://example.com/car-image.jpg");
        photoPreview.setImage(new Image(imageUrlField.getText()));
    }

    @FXML
    private void handleChoosePhoto() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Car Photo");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File file = chooser.showOpenDialog(addCarButton.getScene().getWindow());

        if (file != null) {
            String path = file.toURI().toString();
            imageUrlField.setText(path);
            photoPreview.setImage(new Image(path));
        }
    }

    @FXML
    private void onAddCarClick() {
        try {
            Car newCar = new Car(
                    modelField.getText(),
                    plateNumberField.getText(),
                    typeComboBox.getValue(),
                    Integer.parseInt(yearField.getText()),
                    Integer.parseInt(seatsField.getText()),
                    Double.parseDouble(priceField.getText()),
                    fuelTypeComboBox.getValue(),
                    transmissionComboBox.getValue(),
                    imageUrlField.getText(),
                    "Available"
            );

            CarDatabase.addCar(newCar);
            System.out.println("✅ Added car: " + newCar.getModel());

            // Close window after adding
            ((Stage) addCarButton.getScene().getWindow()).close();

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid number format in Year, Seats, or Price.");
        } catch (Exception e) {
            System.out.println("⚠️ Error adding car: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelClick() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }
}
