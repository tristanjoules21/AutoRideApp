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

public class CustomerManagementController {

    @FXML
    private Label mainTitle;

    @FXML
    private TextField searchField;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<?> customerTable;

    @FXML
    public void initialize() {
        if (mainTitle != null) {
            mainTitle.setText("Customer Management - Manage Customers");
        }
    }

    // 🔹 Add Customer Button
    @FXML
    private void handleAddCustomer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/add-customer-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        try {
            String styleCss = getClass().getResource("/com/example/autorideapp/dashboard.css").toExternalForm();
            scene.getStylesheets().add(styleCss);
        } catch (Exception ignored) {}

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Add New Customer");
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

            // Apply CSS if available
            try {
                String styleCss = getClass().getResource("/com/example/autorideapp/dashboard.css").toExternalForm();
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
