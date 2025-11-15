package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    @FXML
    private TableColumn<User, String> positionColumn;

    @FXML
    private TableColumn<User, String> createdColumn;

    @FXML
    private TableColumn<User, Void> updateColumn;

    // --------------------------------------------------
    // INITIALIZATION
    // --------------------------------------------------
    @FXML
    public void initialize() {
        titleLabel.setText("User Management - Manage Users");

        usernameColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("email"));

        positionColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("position"));
        createdColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("created"));

        // UPDATE BUTTON
        updateColumn.setCellFactory(col -> new TableCell<>() {
            private final Button updateBtn = new Button("Update");

            {
                updateBtn.setStyle(
                        "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5 10; -fx-background-radius: 6;"
                );

                updateBtn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    System.out.println("UPDATE CLICKED on: " + user.getUsername());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : updateBtn);
            }
        });

        refreshUserTable();
    }

    private void refreshUserTable() {
        userTable.getItems().setAll(UserDatabase.getAllUsers());
    }

    // --------------------------------------------------
    // ADD USER POPUP
    // --------------------------------------------------
    @FXML
    private void handleAddUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/add-user-view.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Add New User");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

            refreshUserTable();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --------------------------------------------------
    // Navigation Buttons
    // --------------------------------------------------
    @FXML
    private void showDashboardView() { loadScene("/com/example/autorideapp/dashboard-view.fxml"); }

    @FXML
    private void showCarManagementView() { loadScene("/com/example/autorideapp/CarManagement-view.fxml"); }

    @FXML
    private void showBookingView() { loadScene("/com/example/autorideapp/BookingManagement-view.fxml"); }

    @FXML
    private void showCustomerManagementView() { loadScene("/com/example/autorideapp/CustomerManagement-view.fxml"); }

    @FXML
    private void showUserManagementView() { loadScene("/com/example/autorideapp/UserManagement-view.fxml"); }

    @FXML
    private void handleLogout() { loadScene("/com/example/autorideapp/login-view.fxml"); }

    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}