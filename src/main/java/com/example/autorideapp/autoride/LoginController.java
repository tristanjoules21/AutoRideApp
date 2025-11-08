package com.example.autorideapp.autoride;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button signinBtn;

    @FXML
    private void onSignInClick(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("⚠️ Please enter your email and password.");
            messageLabel.setStyle("-fx-text-fill: #d32f2f;");
            messageLabel.setVisible(true);
            return;
        }

        if (email.equalsIgnoreCase("admin@autoride.com") && password.equals("admin123")) {
            messageLabel.setText("✅ Login successful!");
            messageLabel.setStyle("-fx-text-fill: #12a24a;");
            messageLabel.setVisible(true);

            // Load the dashboard
            try {
                // ✅ Updated path (matches your folder structure)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/dashboard-view.fxml"));
                Scene scene = new Scene(loader.load());

                // Pass email to DashboardController
                DashboardController controller = loader.getController();
                controller.setUserEmail(email);

                // Apply dashboard CSS
                String styleCss = getClass().getResource("/com/example/autorideapp/dashboard.css").toExternalForm();
                scene.getStylesheets().add(styleCss);

                Stage stage = (Stage) signinBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                messageLabel.setText("❌ Failed to load Dashboard.");
                messageLabel.setStyle("-fx-text-fill: #d32f2f;");
                messageLabel.setVisible(true);
            }

        } else {
            messageLabel.setText("❌ Invalid email or password.");
            messageLabel.setStyle("-fx-text-fill: #d32f2f;");
            messageLabel.setVisible(true);
        }
    }
}
