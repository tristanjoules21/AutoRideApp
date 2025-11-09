package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Label adminEmail;

    private String userEmail = "admin@autoride.com"; // Default email

    public void setUserEmail(String email) {
        this.userEmail = email;
        if (adminEmail != null) {
            adminEmail.setText(email);
        }
    }

    @FXML
    public void initialize() {
        if (adminEmail != null) {
            adminEmail.setText(userEmail);
        }
    }

    // 🔹 Load Dashboard main view
    @FXML
    private void showDashboardView() {
        loadScene("/com/example/autorideapp/dashboard-view.fxml");
    }

    // 🔹 Load Car Management
    @FXML
    private void showCarManagementView() {
        loadScene("/com/example/autorideapp/CarManagement-view.fxml");
    }

    // 🔹 Load Booking Management
    @FXML
    private void showBookingView() {
        loadScene("/com/example/autorideapp/Booking-view.fxml");
    }

    // 🔹 Load Customer Management
    @FXML
    private void showCustomerManagementView() {
        loadScene("/com/example/autorideapp/CustomerManagement-view.fxml");
    }

    // 🔹 Load User Management
    @FXML
    private void showUserManagementView() {
        loadScene("/com/example/autorideapp/UserManagement-view.fxml");
    }

    // 🔹 Logout → Back to Login
    @FXML
    private void onLogoutClick() {
        loadScene("/com/example/autorideapp/login-view.fxml");
    }

    // 🔹 Helper: Load a new FXML scene
    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            if (loader.getLocation() == null) {
                System.err.println("FXML file not found: " + fxmlPath);
                return; // Early return if the FXML file is not found.
            }

            Scene scene = new Scene(loader.load());

            // Apply CSS (if available)
            try {
                String styleCss = getClass().getResource("/com/example/autorideapp/dashboard.css").toExternalForm();
                scene.getStylesheets().add(styleCss);
            } catch (Exception ignored) {}

            Stage stage = (Stage) adminEmail.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("Error loading FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
