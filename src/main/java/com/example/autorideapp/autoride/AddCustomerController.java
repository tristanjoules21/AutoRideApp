package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCustomerController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private void onAddCustomerClick() {
        String name = fullNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirm = confirmPasswordField.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("⚠️ Please fill in all fields.");
            return;
        }

        if (!password.equals(confirm)) {
            System.out.println("⚠️ Passwords do not match.");
            return;
        }

        Customer newCustomer = new Customer(name, email, password);
        CustomerDatabase.addCustomer(newCustomer);
        System.out.println("✅ Added customer: " + newCustomer.getFullName());

        // Close popup
        ((Stage) fullNameField.getScene().getWindow()).close();
    }
}