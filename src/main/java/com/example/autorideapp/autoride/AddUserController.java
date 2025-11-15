package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddUserController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> positionComboBox;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button cancelButton;
    @FXML private Button registerButton;

    @FXML
    public void initialize() {
        System.out.println("AddUserController initialized.");

        positionComboBox.getItems().addAll(
                "Administrator",
                "Staff",
                "Manager",
                "Support",
                "Clerk"
        );

        positionComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void onAddUserClick() {
        try {
            String fullName = fullNameField.getText().trim();
            String email = emailField.getText().trim();
            String position = positionComboBox.getValue();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (fullName.isEmpty() || email.isEmpty() || position == null ||
                    password.isEmpty() || confirmPassword.isEmpty()) {
                System.out.println("⚠️ Please fill out all fields.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                System.out.println("⚠️ Passwords do not match!");
                return;
            }

            User newUser = new User(
                    fullName,
                    email,
                    position,
                    LocalDate.now().toString()
            );

            UserDatabase.addUser(newUser);
            System.out.println("New user registered: " + fullName);

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}