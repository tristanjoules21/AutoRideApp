package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class for the add-customer-view.fxml form.
 * This version contains only the field declarations and no functional logic or event handlers.
 */
public class AddCustomerController {

    // FXML elements mapped by their fx:id attributes
    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;
}