package com.example.autorideapp.autoride;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@SuppressWarnings("unused")
public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void onSignInClick(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("⚠️ Please enter your email and password.");
            messageLabel.setStyle("-fx-text-fill: #d32f2f;");
            messageLabel.setVisible(true);
        } else if (email.equalsIgnoreCase("admin@autoride.com") && password.equals("admin123")) {
            messageLabel.setText("✅ Login successful! (Dashboard coming soon...)");
            messageLabel.setStyle("-fx-text-fill: #12a24a;");
            messageLabel.setVisible(true);
        } else {
            messageLabel.setText("❌ Invalid email or password.");
            messageLabel.setStyle("-fx-text-fill: #d32f2f;");
            messageLabel.setVisible(true);
        }
    }
}
