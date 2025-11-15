package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class EditCarController {

    @FXML
    private TextField brandField;  // maps to model

    @FXML
    private TextField modelField;  // optional, can remove if redundant

    @FXML
    private TextField plateField;

    @FXML
    private ComboBox<String> statusCombo;

    @FXML
    private ImageView carImageView;

    @FXML
    private Button browseButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private Car car; // The car object being edited

    private File selectedImageFile;

    // Called by CarManagementController to pass the selected car
    public void setCar(Car car) {
        this.car = car;
        loadCarDetails();
    }

    private void loadCarDetails() {
        brandField.setText(car.getModel()); // map brandField → model
        if (modelField != null) {
            modelField.setText(car.getModel());
        }
        plateField.setText(car.getPlateNumber());
        statusCombo.setValue(car.getStatus());

        if (car.getImageUrl() != null && !car.getImageUrl().isEmpty()) { // fixed
            carImageView.setImage(new Image(new File(car.getImageUrl()).toURI().toString()));
        }
    }

    @FXML
    private void initialize() {
        // Browse button to select a new image
        browseButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Car Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File file = fileChooser.showOpenDialog(browseButton.getScene().getWindow());
            if (file != null) {
                selectedImageFile = file;
                carImageView.setImage(new Image(file.toURI().toString()));
            }
        });

        // Save button
        saveButton.setOnAction(e -> saveChanges());

        // Cancel button
        cancelButton.setOnAction(e -> cancel());
    }

    private void saveChanges() {
        // Update the car object
        car.setModel(brandField.getText()); // mapped to model
        if (modelField != null) {
            car.setModel(modelField.getText()); // optional
        }
        car.setPlateNumber(plateField.getText());
        car.setStatus(statusCombo.getValue());

        if (selectedImageFile != null) {
            car.setImageUrl(selectedImageFile.getAbsolutePath()); // fixed
        }

        // TODO: Update the car in the database here

        // Close popup
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    private void cancel() {
        // Close popup without saving
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
