package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerManagementController {

    @FXML
    private Label mainTitle;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn; // updated from passwordColumn

    @FXML
    public void initialize() {
        mainTitle.setText("Customer Management");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone")); // updated

        refreshCustomerTable();
    }

    private void refreshCustomerTable() {
        customerTable.getItems().setAll(CustomerDatabase.getAllCustomers());
    }

    @FXML
    private void handleAddCustomer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/add-customer-view.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Add New Customer");
        popupStage.setScene(new Scene(root));
        popupStage.showAndWait();

        refreshCustomerTable();
    }

    // Sidebar navigation
    @FXML private void showDashboardView() { loadScene("/com/example/autorideapp/dashboard-view.fxml"); }
    @FXML private void showCarManagementView() { loadScene("/com/example/autorideapp/CarManagement-view.fxml"); }
    @FXML private void showBookingView() { loadScene("/com/example/autorideapp/BookingManagement-view.fxml"); }
    @FXML private void showCustomerManagementView() { loadScene("/com/example/autorideapp/CustomerManagement-view.fxml"); }
    @FXML private void showUserManagementView() { loadScene("/com/example/autorideapp/UserManagement-view.fxml"); }
    @FXML private void handleLogout() { loadScene("/com/example/autorideapp/login-view.fxml"); }

    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) mainTitle.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
