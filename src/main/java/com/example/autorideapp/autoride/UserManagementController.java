package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UserManagementController {

    @FXML
    private Label mainTitle;

    @FXML
    private TextField searchField;

    @FXML
    private Button addUserButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<?> userTable;

    @FXML
    public void initialize() {
        // Design only — no functionality yet
    }
}
