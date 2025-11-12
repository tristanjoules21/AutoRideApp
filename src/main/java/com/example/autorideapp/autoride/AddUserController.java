package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddUserController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button registerButton;

    // --------------------------------------------------
    // INITIALIZE (this runs automatically when popup loads)
    // --------------------------------------------------
    @FXML
    public void initialize() {
        System.out.println("✅ AddUserController initialized successfully.");

        // 🟢 Populate the role combo box options
        roleComboBox.getItems().addAll("Admin", "Staff", "Manager", "Support");

        // Optional default value
        roleComboBox.getSelectionModel().selectFirst();
    }

    // --------------------------------------------------
    // EVENT HANDLERS
    // --------------------------------------------------

    @FXML
    private void onAddUserClick() {
        try {
            String fullName = fullNameField.getText().trim();
            String email = emailField.getText().trim();
            String role = roleComboBox.getValue();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            // ✅ Validation checks
            if (fullName.isEmpty() || email.isEmpty() || role == null ||
                    password.isEmpty() || confirmPassword.isEmpty()) {
                System.out.println("⚠️ Please fill out all fields.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                System.out.println("⚠️ Passwords do not match!");
                return;
            }

            // 🟢 Create new User object (update based on your User class fields)
            User newUser = new User(fullName, email, role, password);

            // 🟢 Add to your in-memory database
            UserDatabase.addUser(newUser);
            System.out.println("✅ Registered new user: " + fullName + " (" + role + ")");

            // 🟢 Close popup window after success
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            System.err.println("⚠️ Error adding user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        System.out.println("❎ Add User window closed.");
    }
}
