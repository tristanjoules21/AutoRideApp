package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ChooseCustomerController {

    @FXML
    private ListView<Customer> customerList;

    private Customer selectedCustomer;

    @FXML
    public void initialize() {
        // Load customers into list
        customerList.getItems().addAll(CustomerDatabase.getAllCustomers());

        // Show full name + email in list
        customerList.setCellFactory(list -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Customer customer, boolean empty) {
                super.updateItem(customer, empty);
                if (empty || customer == null) {
                    setText(null);
                } else {
                    setText(customer.getFullName() + " (" + customer.getEmail() + ")");
                }
            }
        });
    }

    @FXML
    private void confirmSelection() {
        selectedCustomer = customerList.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) customerList.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelSelection() {
        selectedCustomer = null;
        Stage stage = (Stage) customerList.getScene().getWindow();
        stage.close();
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }
}
