package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CarManagementController {

    @FXML
    private Label titleLabel;

    @FXML
    private TableView<?> carTable;

    @FXML
    public void initialize() {
        if (titleLabel != null) {
            titleLabel.setText("Car Management - Manage Cars in AutoRide");
        }
    }

    // 🔹 Add Car Button → Opens popup window
    @FXML
    private void handleAddCarClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/add-car-view.fxml"));
        Parent root = loader.load();

        // Apply CSS (if exists)
        Scene scene = new Scene(root);
        try {
            String styleCss = this.getClass().getResource("/com/example/autorideapp/dashboard.css").toExternalForm();
            scene.getStylesheets().add(styleCss);
        } catch (Exception ignored) {}

        // Create popup modal window
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Add New Car");
        popupStage.setScene(scene);
        popupStage.showAndWait();
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
                System.err.println("❌ FXML file not found: " + fxmlPath);
                return;
            }

            Scene scene = new Scene(loader.load());

            // Apply CSS (if available)
            try {
                String styleCss = getClass().getResource("/com/example/autorideapp/dashboard.css").toExternalForm();
                scene.getStylesheets().add(styleCss);
            } catch (Exception ignored) {}

            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("⚠️ Error loading FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
