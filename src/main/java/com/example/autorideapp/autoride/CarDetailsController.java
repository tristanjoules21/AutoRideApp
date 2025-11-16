package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.temporal.ChronoUnit;

public class CarDetailsController {

    @FXML
    private ImageView carImage;
    @FXML
    private Label carName;

    @FXML
    private Label specsLabel;      // NEW → full bullet specs text
    @FXML
    private Label priceValue;      // "2,500 / day"
    @FXML
    private Label totalLabel;      // "10,000"

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button bookButton;

    private Car car;

    public void setCar(Car car) {
        this.car = car;

        /* Load Image */
        if (car.getImageUrl() != null && !car.getImageUrl().isEmpty()) {
            try {
                carImage.setImage(new Image(car.getImageUrl(), true));
            } catch (Exception e) {
                System.out.println("Image load failed: " + e.getMessage());
            }
        }

        /* Car Name */
        carName.setText(car.getModel());

        /* Full Specs */
        specsLabel.setText(
                "• " + car.getModel() + " " + car.getYear() + " — Specs\n" +
                        "• Engine: unknown (adjust your model if needed)\n" +
                        "• Transmission: " + car.getTransmission() + "\n" +
                        "• Seats: " + car.getSeats() + " persons\n" +
                        "• Fuel Type: " + car.getFuelType()
        );

        /* Price */
        priceValue.setText(String.format("₱%,.0f / day", car.getPrice()));

        /* Live total calculation */
        startDatePicker.valueProperty().addListener((o, a, b) -> updateTotal());
        endDatePicker.valueProperty().addListener((o, a, b) -> updateTotal());
    }

    private void updateTotal() {
        if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
            totalLabel.setText("₱0");
            return;
        }

        long days = ChronoUnit.DAYS.between(startDatePicker.getValue(), endDatePicker.getValue());
        if (days < 1) {
            totalLabel.setText("Invalid");
            return;
        }

        double total = days * car.getPrice();
        totalLabel.setText("₱" + String.format("%,.0f", total));
    }

    // ===============================
    // Book button logic with customer selection
    // ===============================
    @FXML
    private void handleBook() {

        // 1. Check if start/end dates are selected
        if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
            totalLabel.setText("Select dates");
            return;
        }

        long days = ChronoUnit.DAYS.between(startDatePicker.getValue(), endDatePicker.getValue());
        if (days < 1) {
            totalLabel.setText("Invalid");
            return;
        }

        double total = days * car.getPrice();
        String dateRange = startDatePicker.getValue() + " to " + endDatePicker.getValue();

        // 2. Check if customers exist
        if (CustomerDatabase.getAllCustomers().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("No Customers Found");
            alert.setContentText("Please add a customer first before booking.");
            alert.show();
            return;
        }

        // 3. Open customer selection popup
        Customer selectedCustomer = showCustomerSelectionPopup();
        if (selectedCustomer == null) {
            System.out.println("Booking cancelled: No customer selected.");
            return;
        }

        // 4. Create booking object with selected customer
        Booking booking = new Booking(
                selectedCustomer.getFullName(),
                car.getModel(),
                dateRange,
                total
        );

        // 5. Save to database
        BookingDatabase.addBooking(booking);

        // 6. Show success
        totalLabel.setText("Booked ✓");
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Booking Successful!");
        success.setContentText("Car booked under: " + selectedCustomer.getFullName());
        success.show();
    }

    // ===============================
    // Helper method to show customer selection popup
    // ===============================
    private Customer showCustomerSelectionPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/autorideapp/choose-customer.fxml"
            ));
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.setTitle("Choose Customer");
            popup.setScene(new Scene(root));
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();

            ChooseCustomerController controller = loader.getController();
            return controller.getSelectedCustomer();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}