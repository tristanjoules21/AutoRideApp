package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class BookingManagementController {

    @FXML
    private TableView<?> bookingTable; // TableView reference

    @FXML
    private Label mainTitle; // Label reference for main title

    @FXML
    public void initialize() {
        // Set initial title
        mainTitle.setText("Booking Management");
    }

    @FXML
    private void handleAddBooking() {
        System.out.println("Add Booking button clicked!");
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logout button clicked!");
    }
}
