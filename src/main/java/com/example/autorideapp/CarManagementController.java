package com.example.autorideapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CarManagementController {

    @FXML
    private Button dashboardButton;

    @FXML
    public void initialize() {
        dashboardButton.setOnAction(e -> {
            Dashboard dashboard = new Dashboard();
            dashboard.start((Stage) dashboardButton.getScene().getWindow());
        });
    }
}
