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

public class CarManagementController {

    @FXML
    private Label mainTitle;

    @FXML
    private TableView<Car> carTable;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, String> plateNumberColumn;

    @FXML
    private TableColumn<Car, String> typeColumn;

    @FXML
    private TableColumn<Car, Integer> yearColumn;

    @FXML
    private TableColumn<Car, Integer> seatsColumn;

    @FXML
    private TableColumn<Car, Double> priceColumn;

    @FXML
    private TableColumn<Car, String> fuelTypeColumn;

    @FXML
    private TableColumn<Car, String> transmissionColumn;

    @FXML
    private TableColumn<Car, String> imageUrlColumn;

    @FXML
    public void initialize() {
        mainTitle.setText("Car Management - Manage Cars");

        // Setup columns to match Car fields
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        plateNumberColumn.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        fuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        transmissionColumn.setCellValueFactory(new PropertyValueFactory<>("transmission"));
        imageUrlColumn.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));

        // Load current cars
        refreshCarTable();
    }

    // 🧠 This reloads data from CarDatabase
    private void refreshCarTable() {
        carTable.getItems().setAll(CarDatabase.getAllCars());
    }

    // 🔘 Open Add Car Popup
    @FXML
    private void handleAddCar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/add-car-view.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Add New Car");
        popupStage.setScene(new Scene(root));

        // Wait for the popup to close
        popupStage.showAndWait();

        // ✅ Refresh the table when popup closes
        refreshCarTable();
    }

    // ✅ Navigation Buttons (if you have them)
    @FXML private void showDashboardView() { loadScene("/com/example/autorideapp/dashboard-view.fxml"); }
    @FXML private void showCarManagementView() { loadScene("/com/example/autorideapp/CarManagement-view.fxml"); }
    @FXML private void showBookingView() { loadScene("/com/example/autorideapp/Booking-view.fxml"); }
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