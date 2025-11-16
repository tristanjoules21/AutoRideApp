package com.example.autorideapp.autoride;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DashboardController {

    // ==========================
    // FXML COMPONENTS
    // ==========================
    @FXML private Label adminEmail;

    @FXML private Label totalCarsLabel;
    @FXML private Label availableCarsLabel;

    @FXML private Label totalBookingsLabel;
    @FXML private Label activeBookingsLabel;

    @FXML private Label pendingBookingsLabel;

    @FXML private Label totalRevenueLabel;

    @FXML private TableView<Booking> recentBookingsTable;
    @FXML private TableColumn<Booking, Integer> colId;
    @FXML private TableColumn<Booking, String> colCustomer;
    @FXML private TableColumn<Booking, String> colCar;
    @FXML private TableColumn<Booking, String> colDates;
    @FXML private TableColumn<Booking, String> colStatus;
    @FXML private TableColumn<Booking, String> colAmount;

    private String userEmail = "admin@autoride.com"; // Default email

    // ==========================
    // INITIALIZATION
    // ==========================
    @FXML
    public void initialize() {
        // Set admin email
        if (adminEmail != null) {
            adminEmail.setText(userEmail);
        }

        // Setup TableView columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCar.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        colDates.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("formattedTotalCost"));

        // Update dashboard stats dynamically
        updateDashboardStats();
    }

    // ==========================
    // DASHBOARD STATISTICS
    // ==========================
    private void updateDashboardStats() {
        // Cars
        List<Car> cars = CarDatabase.getAllCars();
        int totalCars = cars.size();
        long availableCars = cars.stream().filter(c -> c.getStatus().equalsIgnoreCase("Available")).count();
        totalCarsLabel.setText(String.valueOf(totalCars));
        availableCarsLabel.setText(availableCars + " available");

        // Bookings
        List<Booking> bookings = BookingDatabase.getAllBookings();
        int totalBookings = bookings.size();
        long activeBookings = bookings.stream().filter(b -> b.getStatus().equalsIgnoreCase("Active")).count();
        long pendingBookings = bookings.stream().filter(b -> b.getStatus().equalsIgnoreCase("Pending")).count();
        double totalRevenue = bookings.stream()
                .filter(b -> b.getStatus().equalsIgnoreCase("Completed"))
                .mapToDouble(Booking::getTotalCost)
                .sum();

        totalBookingsLabel.setText(String.valueOf(totalBookings));
        activeBookingsLabel.setText(activeBookings + " active");
        pendingBookingsLabel.setText(String.valueOf(pendingBookings));
        totalRevenueLabel.setText("₱" + String.format("%,.0f", totalRevenue));

        // Populate recent bookings table
        ObservableList<Booking> recentBookings = FXCollections.observableArrayList(bookings);
        recentBookingsTable.setItems(recentBookings);
    }

    // ==========================
    // EMAIL SETTER
    // ==========================
    public void setUserEmail(String email) {
        this.userEmail = email;
        if (adminEmail != null) {
            adminEmail.setText(email);
        }
    }

    // ==========================
    // NAVIGATION METHODS
    // ==========================
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

    @FXML private void onLogoutClick() {
        loadScene("/com/example/autorideapp/login-view.fxml");
    }

    // ==========================
    // SCENE LOADER HELPER
    // ==========================
    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            try {
                String styleCss = getClass().getResource("/com/example/autorideapp/dashboard.css").toExternalForm();
                scene.getStylesheets().add(styleCss);
            } catch (Exception ignored) {}
            Stage stage = (Stage) adminEmail.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
