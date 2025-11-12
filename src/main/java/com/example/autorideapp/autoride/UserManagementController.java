package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class UserManagementController {

    @FXML
    private Label titleLabel;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> roleColumn;

    // --------------------------------------------------
    // INITIALIZATION
    // --------------------------------------------------
    @FXML
    public void initialize() {
        titleLabel.setText("User Management - Manage Users");

        usernameColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("role"));

        refreshUserTable();
    }

    private void refreshUserTable() {
        userTable.getItems().setAll(UserDatabase.getAllUsers());
    }

    // --------------------------------------------------
    // ADD USER POPUP (✅ Fixed version)
    // --------------------------------------------------
    @FXML
    private void handleAddUser() {
        try {
            System.out.println("🟢 Opening Add User popup...");

            // ✅ Make sure your FXML file is located here:
            // src/main/resources/com/example/autorideapp/add-user-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/add-user-view.fxml"));
            if (loader.getLocation() == null) {
                throw new IOException("❌ FXML not found at /com/example/autorideapp/add-user-view.fxml");
            }

            Parent root = loader.load();

            // ✅ Show popup window
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Add New User");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

            System.out.println("✅ Add User popup closed — refreshing table...");
            refreshUserTable();

        } catch (IOException e) {
            System.err.println("⚠️ Error loading Add User view:");
            e.printStackTrace();
        } catch (Exception ex) {
            System.err.println("⚠️ Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // ======================================================
    // 🔹 Navigation Buttons (Fixes “Dashboard not working” issue)
    // ======================================================
    @FXML
    private void showDashboardView() {
        loadScene("/com/example/autorideapp/dashboard-view.fxml");
    }

    @FXML
    private void showCarManagementView() {
        loadScene("/com/example/autorideapp/CarManagement-view.fxml");
    }

    @FXML
    private void showBookingView() {
        loadScene("/com/example/autorideapp/Booking-view.fxml");
    }

    @FXML
    private void showCustomerManagementView() {
        loadScene("/com/example/autorideapp/CustomerManagement-view.fxml");
    }

    @FXML
    private void showUserManagementView() {
        loadScene("/com/example/autorideapp/UserManagement-view.fxml");
    }

    @FXML
    private void handleLogout() {
        loadScene("/com/example/autorideapp/login-view.fxml");
    }

    // ======================================================
    // 🔧 Helper method for all navigation
    // ======================================================
    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("⚠️ Error loading FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
