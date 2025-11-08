package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Label adminEmail;

    @FXML
    private VBox mainContent; // VBox that holds dynamic views

    private String userEmail;

    public void setUserEmail(String email) {
        this.userEmail = email;
        if (adminEmail != null) {
            adminEmail.setText(email);
        }
    }

    @FXML
    public void initialize() {
        if (userEmail != null && adminEmail != null) {
            adminEmail.setText(userEmail);
        }
    }

    // 🔹 Load Car Management view
    @FXML
    private void showCarManagementView() {
        loadView("/com/example/autorideapp/autoride/CarManagement-view.fxml");
    }

    // 🔹 Load Booking Management view
    @FXML
    private void showBookingView() {
        loadView("/com/example/autorideapp/autoride/Booking-view.fxml");
    }

    // 🔹 Load Customer Management view
    @FXML
    private void showCustomerManagementView() {
        loadView("/com/example/autorideapp/autoride/CustomerManagement-view.fxml");
    }

    // 🔹 Load User Management view
    @FXML
    private void showUserManagementView() {
        loadView("/com/example/autorideapp/autoride/UserManagement-view.fxml");
    }

    // 🔹 Reload Dashboard view (optional)
    @FXML
    private void showDashboardView() {
        loadView("/com/example/autorideapp/autoride/Dashboard-inner-view.fxml"); // This is the content VBox portion only
    }

    // 🔹 Logout
    @FXML
    private void onLogoutClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/autoride/login-view.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(loader.load());
            javafx.stage.Stage stage = (javafx.stage.Stage) adminEmail.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Helper to load another FXML into mainContent VBox
    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane view = loader.load();

            mainContent.getChildren().clear(); // remove previous content
            mainContent.getChildren().add(view); // add new content
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
