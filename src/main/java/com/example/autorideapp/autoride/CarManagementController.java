package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CarManagementController {

    @FXML
    private Label mainTitle;

    @FXML
    private VBox contentContainer;

    @FXML
    private TableView<Car> carTable;

    @FXML private TableColumn<Car, String> photoColumn;
    @FXML private TableColumn<Car, Car> carInfoColumn;
    @FXML private TableColumn<Car, Car> detailsColumn;
    @FXML private TableColumn<Car, String> statusColumn;
    @FXML private TableColumn<Car, Double> priceColumn;
    @FXML private TableColumn<Car, Void> actionsColumn;

    @FXML private TextField searchField;

    @FXML
    public void initialize() {
        mainTitle.setText("Car Management");

        setupCarInfoColumn();
        setupDetailsColumn();
        setupStatusColumn();
        setupPriceColumn();
        setupActionsColumn();
        setupPhotoColumn();

        refreshCarTable();
        enableRowClickNavigation();
    }

    /* ======================================
            CLICK ROW → SHOW CAR DETAILS
       ====================================== */
    private void enableRowClickNavigation() {
        carTable.setRowFactory(tv -> {
            TableRow<Car> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Car selected = row.getItem();
                    showCarDetails(selected);
                }
            });

            return row;
        });
    }

    private void showCarDetails(Car car) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/autorideapp/car-details-view.fxml")
            );


            Parent details = loader.load();

            CarDetailsController controller = loader.getController();
            controller.setCar(car);

            contentContainer.getChildren().setAll(details);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ======================================
              COLUMN SETUP
       ====================================== */

    private void setupPhotoColumn() {
        photoColumn.setCellFactory(col -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(80);
                imageView.setFitHeight(60);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(String imageUrl, boolean empty) {
                super.updateItem(imageUrl, empty);

                if (empty || imageUrl == null || imageUrl.isEmpty()) {
                    setGraphic(null);
                    return;
                }

                try {
                    imageView.setImage(new Image(imageUrl, true));
                    setGraphic(imageView);
                } catch (Exception e) {
                    setGraphic(null);
                }
            }
        });
        photoColumn.setCellValueFactory(new PropertyValueFactory<>("photoUrl"));
    }

    private void setupCarInfoColumn() {
        carInfoColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue()));

        carInfoColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Car car, boolean empty) {
                super.updateItem(car, empty);
                if (empty || car == null) {
                    setGraphic(null);
                    return;
                }

                VBox box = new VBox(2);
                Label model = new Label(car.getModel());
                Label plate = new Label(car.getPlateNumber());
                box.getChildren().addAll(model, plate);
                setGraphic(box);
            }
        });
    }

    private void setupDetailsColumn() {
        detailsColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue()));

        detailsColumn.setCellFactory(col -> new TableCell<>() {
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

                setGraphic(label);
            }
        });
    }

    private void setupStatusColumn() {
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setGraphic(null);
                    return;
                }

                Label badge = new Label(status);
                setGraphic(badge);
            }
        });
    }

    private void setupPriceColumn() {
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        priceColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setGraphic(null);
                    return;
                }

                Label label = new Label("₱" + price + "/day");
                setGraphic(label);
            }
        });
    }

    private void setupActionsColumn() {
        actionsColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                Button edit = new Button("✏");
                Button delete = new Button("🗑");

                edit.setOnAction(e -> {
                    Car selected = getTableView().getItems().get(getIndex());
                    openEditPopup(selected);
                });

                delete.setOnAction(e -> {
                    Car selected = getTableView().getItems().get(getIndex());
                    CarDatabase.deleteCar(selected);
                    refreshCarTable();
                });

                HBox box = new HBox(8, edit, delete);
                setGraphic(box);
            }
        });
    }

    /* ======================================
                 POPUPS
       ====================================== */

    @FXML
    private void handleAddCar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/add-car-view.fxml"));
        Parent root = loader.load();

        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setScene(new Scene(root));
        popup.showAndWait();

        refreshCarTable();
    }

    private void openEditPopup(Car car) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/edit-car-view.fxml"));
            Parent root = loader.load();

            EditCarController controller = loader.getController();
            controller.setCar(car);

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setScene(new Scene(root));
            popup.showAndWait();

            refreshCarTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ======================================
                 REFRESH
       ====================================== */

    private void refreshCarTable() {
        carTable.getItems().setAll(CarDatabase.getAllCars());
    }

    /* ======================================
                NAVIGATION
       ====================================== */

    @FXML private void showDashboardView() { loadScene("/com/example/autorideapp/dashboard-view.fxml"); }
    @FXML private void showCarManagementView() { loadScene("/com/example/autorideapp/CarManagement-view.fxml"); }
    @FXML private void showBookingView() { loadScene("/com/example/autorideapp/BookingManagement-view.fxml"); }
    @FXML private void showCustomerManagementView() { loadScene("/com/example/autorideapp/CustomerManagement-view.fxml"); }
    @FXML private void showUserManagementView() { loadScene("/com/example/autorideapp/UserManagement-view.fxml"); }
    @FXML private void handleLogout() { loadScene("/com/example/autorideapp/login-view.fxml"); }

    private void loadScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) mainTitle.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("Error loading FXML: " + path);
            e.printStackTrace();
        }
    }
}
