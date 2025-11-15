package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.io.IOException;

public class CarManagementController {

    @FXML
    private Label mainTitle;

    @FXML
    private TableView<Car> carTable;

    // Custom designed columns
    @FXML private TableColumn<Car, Car> carInfoColumn;
    @FXML private TableColumn<Car, Car> detailsColumn;
    @FXML private TableColumn<Car, String> statusColumn;
    @FXML private TableColumn<Car, Double> priceColumn;
    @FXML private TableColumn<Car, Void> actionsColumn;

    // New photo column
    @FXML private TableColumn<Car, String> photoColumn;


    @FXML
    public void initialize() {
        mainTitle.setText("Car Management");

        setupCarInfoColumn();
        setupDetailsColumn();
        setupStatusColumn();
        setupPriceColumn();
        setupActionsColumn();

        // ==========================
        // PHOTO PREVIEW COLUMN
        // ==========================
        photoColumn.setCellFactory(col -> new TableCell<>() {

            private final ImageView imageView = new ImageView();

            {
                imageView.setFitHeight(60);
                imageView.setFitWidth(80);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(String imageUrl, boolean empty) {
                super.updateItem(imageUrl, empty);

                if (empty || imageUrl == null || imageUrl.isEmpty()) {
                    setGraphic(null);
                } else {
                    try {
                        imageView.setImage(new Image(imageUrl, true));
                        setGraphic(imageView);
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                }
            }
        });

        refreshCarTable();
    }


    /* ==============================
       CAR NAME + PLATE COLUMN
    ============================== */
    private void setupCarInfoColumn() {
        carInfoColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue()));

        carInfoColumn.setCellFactory(col -> new TableCell<Car, Car>() {
            @Override
            protected void updateItem(Car car, boolean empty) {
                super.updateItem(car, empty);
                if (empty || car == null) {
                    setGraphic(null);
                    return;
                }

                VBox box = new VBox(2);

                Label model = new Label(car.getModel());
                model.getStyleClass().add("car-name");

                Label plate = new Label(car.getPlateNumber());
                plate.getStyleClass().add("car-plate");

                box.getChildren().addAll(model, plate);
                setGraphic(box);
            }
        });
    }


    /* ==============================
       DETAILS COLUMN
    ============================== */
    private void setupDetailsColumn() {
        detailsColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue()));

        detailsColumn.setCellFactory(col -> new TableCell<Car, Car>() {
            @Override
            protected void updateItem(Car car, boolean empty) {
                super.updateItem(car, empty);
                if (empty || car == null) {
                    setGraphic(null);
                    return;
                }

                Label label = new Label(
                        car.getYear() + " • " +
                                car.getType() + " • " +
                                car.getSeats() + " seats • " +
                                car.getTransmission() + " • " +
                                car.getFuelType()
                );
                label.getStyleClass().add("car-details");

                setGraphic(label);
            }
        });
    }


    /* ==============================
       STATUS BADGE COLUMN
    ============================== */
    private void setupStatusColumn() {
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusColumn.setCellFactory(col -> new TableCell<Car, String>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setGraphic(null);
                    return;
                }

                Label badge = new Label(status);

                if (status.equalsIgnoreCase("Available")) {
                    badge.getStyleClass().add("status-available");
                } else {
                    badge.getStyleClass().add("status-booked");
                }

                setGraphic(badge);
            }
        });
    }


    /* ==============================
       PRICE COLUMN
    ============================== */
    private void setupPriceColumn() {
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        priceColumn.setCellFactory(col -> new TableCell<Car, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setGraphic(null);
                    return;
                }

                Label label = new Label("₱" + String.format("%,.0f", price) + "/day");
                label.getStyleClass().add("price-text");

                setGraphic(label);
            }
        });
    }


    /* ==============================
       ACTION ICONS (EDIT + DELETE)
    ============================== */
    private void setupActionsColumn() {
        actionsColumn.setCellFactory(col -> new TableCell<Car, Void>() {

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                Button editBtn = new Button("✏");
                editBtn.getStyleClass().add("action-edit");

                Button deleteBtn = new Button("🗑");
                deleteBtn.getStyleClass().add("action-delete");

                // Edit action
                editBtn.setOnAction(e -> {
                    Car selected = getTableView().getItems().get(getIndex());
                    openEditPopup(selected);
                });

                // Delete action
                deleteBtn.setOnAction(e -> {
                    Car selected = getTableView().getItems().get(getIndex());
                    CarDatabase.deleteCar(selected);
                    refreshCarTable();
                });

                HBox box = new HBox(10, editBtn, deleteBtn);
                setGraphic(box);
            }
        });
    }


    /* ==============================
       REFRESH TABLE DATA
    ============================== */
    private void refreshCarTable() {
        carTable.getItems().setAll(CarDatabase.getAllCars());
    }


    /* ==============================
       ADD CAR POPUP
    ============================== */
    @FXML
    private void handleAddCar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/add-car-view.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Add New Car");
        popupStage.setScene(new Scene(root));

        popupStage.showAndWait();
        refreshCarTable();
    }


    /* ==============================
       EDIT CAR POPUP
    ============================== */
    private void openEditPopup(Car car) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/edit-car-view.fxml"));
            Parent root = loader.load();

            EditCarController controller = loader.getController();
            controller.setCar(car);

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Edit Car");
            popup.setScene(new Scene(root));
            popup.showAndWait();

            refreshCarTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* ==============================
       NAVIGATION
    ============================== */
    @FXML private void showDashboardView() { loadScene("/com/example/autorideapp/dashboard-view.fxml"); }
    @FXML private void showCarManagementView() { loadScene("/com/example/autorideapp/CarManagement-view.fxml"); }
    @FXML private void showBookingView() { loadScene("/com/example/autorideapp/BookingManagement-view.fxml"); }
    @FXML private void showCustomerManagementView() { loadScene("/com/example/autorideapp/CustomerManagement-view.fxml"); }
    @FXML private void showUserManagementView() { loadScene("/com/example/autorideapp/UserManagement-view.fxml"); }
    @FXML private void handleLogout() { loadScene("/com/example/autorideapp/login-view.fxml"); }

    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) mainTitle.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
