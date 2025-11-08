package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerManagementController {

    @FXML
    private Label mainTitle;

    @FXML
    private TextField searchField;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<?> customerTable;

    @FXML
    public void initialize() {
        if (mainTitle != null) {
            mainTitle.setText("Customer Management");
        }
    }
}
