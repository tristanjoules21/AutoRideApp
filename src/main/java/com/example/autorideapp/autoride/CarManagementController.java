package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class CarManagementController {

    @FXML
    private Label titleLabel;

    @FXML
    private TableView<?> carTable;

    @FXML
    public void initialize() {
        if (titleLabel != null) {
            titleLabel.setText("Car Management - Manage Cars in AutoRide");
        }
    }
}
