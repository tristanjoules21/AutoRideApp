package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class BookingManagementController {

    @FXML
    private TableView<Booking> bookingTable;

    @FXML
    private TableColumn<Booking, String> customerNameColumn;

    @FXML
    private TableColumn<Booking, String> carModelColumn;

    @FXML
    private TableColumn<Booking, String> dateColumn;

    @FXML
    private TableColumn<Booking, Double> totalCostColumn;

    @FXML
    private TableColumn<Booking, String> statusColumn;

    @FXML
    private TableColumn<Booking, Void> actionsColumn;

    @FXML
    private Label mainTitle;


    @FXML
    public void initialize() {
        if (mainTitle != null)
            mainTitle.setText("Booking Management");

        setupColumns();
        loadBookings();
    }


    private void setupColumns() {

        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        carModelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalCostColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        setupStatusDropdown();   // 🔥 NEW
        setupDeleteButton();     // 🔥 NEW
    }

    /* ===========================================================
       STATUS COLUMN (Paid / Unpaid Dropdown)
       =========================================================== */
    private void setupStatusDropdown() {

        statusColumn.setCellFactory(col -> new TableCell<>() {

            private final ComboBox<String> comboBox = new ComboBox<>();

            {
                comboBox.getItems().addAll("Paid", "Unpaid");

                comboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
                    if (getTableRow().getItem() != null) {
                        Booking booking = getTableRow().getItem();
                        booking.setStatus(newVal);
                        updateStyle(newVal);
                    }
                });
            }

            private void updateStyle(String value) {
                if (value == null) return;

                if (value.equals("Paid")) {
                    comboBox.setStyle("-fx-background-color: #4ade80; -fx-text-fill: white; -fx-font-weight: bold;");
                } else {
                    comboBox.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: bold;");
                }
            }

            @Override
            protected void updateItem(String val, boolean empty) {
                super.updateItem(val, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    comboBox.setValue(val);
                    updateStyle(val);
                    setGraphic(comboBox);
                }
            }
        });

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }


    /* ===========================================================
       ACTIONS COLUMN (DELETE ONLY)
       =========================================================== */
    private void setupDeleteButton() {

        Callback<TableColumn<Booking, Void>, TableCell<Booking, Void>> factory = (col) -> {

            return new TableCell<>() {

                private final Button deleteBtn = new Button("Delete");

                {
                    deleteBtn.setStyle("-fx-background-color: #dc2626; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
                    deleteBtn.setOnAction(e -> {
                        Booking booking = getTableView().getItems().get(getIndex());
                        BookingDatabase.removeBooking(booking);
                        bookingTable.getItems().remove(booking);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) setGraphic(null);
                    else setGraphic(new HBox(5, deleteBtn));
                }
            };
        };

        actionsColumn.setCellFactory(factory);
    }


    private void loadBookings() {
        bookingTable.getItems().setAll(BookingDatabase.getAllBookings());
    }


    // NAVIGATION —
    @FXML private void showDashboardView() { loadScene("/com/example/autorideapp/dashboard-view.fxml"); }
    @FXML private void showCarManagementView() { loadScene("/com/example/autorideapp/CarManagement-view.fxml"); }
    @FXML private void showBookingView() { loadScene("/com/example/autorideapp/BookingManagement-view.fxml"); }
    @FXML private void showCustomerManagementView() { loadScene("/com/example/autorideapp/CustomerManagement-view.fxml"); }
    @FXML private void showUserManagementView() { loadScene("/com/example/autorideapp/UserManagement-view.fxml"); }
    @FXML private void handleLogout() { loadScene("/com/example/autorideapp/login-view.fxml"); }

    private void loadScene(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) mainTitle.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.err.println("❌ Error loading " + fxml);
            e.printStackTrace();
        }
    }
}
