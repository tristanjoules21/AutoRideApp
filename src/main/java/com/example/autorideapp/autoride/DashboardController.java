package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label adminEmail; // sidebar email label

    private String userEmail;

    public void setUserEmail(String email) {
        this.userEmail = email;
        if (adminEmail != null) {
            adminEmail.setText(email);
        }
    }

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome to AutoRide Dashboard!");
        if (userEmail != null) {
            adminEmail.setText(userEmail);
        }
    }
}
