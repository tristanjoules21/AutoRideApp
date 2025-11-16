package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCustomerController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField confirmPhoneField;

    @FXML
    private Button registerButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void initialize() {
        // Optional: Initialization logic if needed
    }

    @FXML
    private void onAddCustomerClick() {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String confirmPhone = confirmPhoneField.getText().trim();

        // Validation
        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || confirmPhone.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill in all fields.");
            return;
        }

        if (!phone.equals(confirmPhone)) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Phone numbers do not match.");
            return;
        }

        if (CustomerDatabase.findByPhone(phone) != null) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Phone number already exists.");
            return;
        }

        // Add customer to database
        Customer newCustomer = new Customer(fullName, email, phone);
        CustomerDatabase.addCustomer(newCustomer);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Customer added successfully!");

        // Close window
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancelClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
