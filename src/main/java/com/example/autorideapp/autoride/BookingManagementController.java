package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingManagementController {

    @FXML
    private TableView<?> bookingTable;

    @FXML
    private Label mainTitle;

    @FXML
    public void initialize() {
        if (mainTitle != null) {
            mainTitle.setText("Booking Management ");
        }
    }

    // 🔹 Add Booking Button
    @FXML
    private void handleAddBooking() {
        System.out.println("Add Booking button clicked!");
    }

    // 🔹 Sidebar Navigation
    @FXML private void showDashboardView() {
        loadScene("/com/example/autorideapp/dashboard-view.fxml");
    }

    @FXML private void showCarManagementView() {
        loadScene("/com/example/autorideapp/CarManagement-view.fxml");
    }

    @FXML private void showBookingView() {
        loadScene("/com/example/autorideapp/BookingManagement-view.fxml");
    }

    @FXML private void showCustomerManagementView() {
        loadScene("/com/example/autorideapp/CustomerManagement-view.fxml");
    }

    @FXML private void showUserManagementView() {
        loadScene("/com/example/autorideapp/UserManagement-view.fxml");
    }

    @FXML private void handleLogout() {
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

            // Apply CSS (optional)
            try {
                String styleCss = getClass()
                        .getResource("/com/example/autorideapp/dashboard.css")
                        .toExternalForm();
                scene.getStylesheets().add(styleCss);
            } catch (Exception ignored) {}

            Stage stage = (Stage) mainTitle.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("⚠️ Error loading FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }
}