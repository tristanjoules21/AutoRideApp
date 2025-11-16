package com.example.autorideapp.autoride;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @FXML
    private void handleBook() {
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

        // Format date range (e.g. "2024-10-12 to 2024-10-15")
        String dateRange = startDatePicker.getValue() + " to " + endDatePicker.getValue();

        // No customer name yet? Use placeholder
        String customerName = "Walk-in Customer";

        // Create booking object
        Booking booking = new Booking(
                customerName,
                car.getModel(),
                dateRange,
                total
        );

        // Save to database
        BookingDatabase.addBooking(booking);

        totalLabel.setText("Booked ✓");
    }
}