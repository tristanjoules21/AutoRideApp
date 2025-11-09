package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UserManagementController {

    @FXML
    private Label titleLabel;

    @FXML
    private TextField searchField;

    @FXML
    private Button addUserButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<?> userTable;

    @FXML
    public void initialize() {
        if (titleLabel != null) {
            titleLabel.setText("User Management - Manage Application Users");
        }
    }

    // 🔹 Add User Button → Opens Add User Modal
    @FXML
    private void handleAddUser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/add-user-view.fxml"));
        Parent root = loader.load();

        // Apply CSS (optional)
        Scene scene = new Scene(root);
        try {
            String styleCss = this.getClass()
                    .getResource("/com/example/autorideapp/dashboard.css")
                    .toExternalForm();
            scene.getStylesheets().add(styleCss);
        } catch (Exception ignored) {}

        // Create popup modal window
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Add New User");
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    // 🔹 Sidebar Navigation
    @FXML private void showDashboardView() {
        loadScene("/com/example/autorideapp/dashboard-view.fxml");
    }

    @FXML private void showCarManagementView() {
        loadScene("/com/example/autorideapp/CarManagement-view.fxml");
    }

    @FXML private void showBookingView() {
        loadScene("/com/example/autorideapp/Booking-view.fxml");
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

            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("⚠️ Error loading FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
