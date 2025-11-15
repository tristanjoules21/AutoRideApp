package com.example.autorideapp.autoride;

import java.util.Objects;

public class Car {
    private String model;
    private String plateNumber;
    private String type;
    private int year;
    private int seats;
    private double price;
    private String fuelType;
    private String transmission;
    private String imageUrl; // Used for image path
    private String status;   // Added status field

    // Constructor
    public Car(String model, String plateNumber, String type, int year, int seats, double price, String fuelType, String transmission, String imageUrl, String status) {
        this.model = model;
        this.plateNumber = plateNumber;
        this.type = type;
        this.year = year;
        this.seats = seats;
        this.price = price;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.imageUrl = imageUrl;
        this.status = status; // added
    }

    // Getters
    public String getModel() { return model; }
    public String getPlateNumber() { return plateNumber; }
    public String getType() { return type; }
    public int getYear() { return year; }
    public int getSeats() { return seats; }
    public double getPrice() { return price; }
    public String getFuelType() { return fuelType; }
    public String getTransmission() { return transmission; }
    public String getImageUrl() { return imageUrl; }
    public String getStatus() { return status; } // Getter for status

    // Setters for editable fields
    public void setModel(String model) { this.model = model; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
    public void setType(String type) { this.type = type; }
    public void setYear(int year) { this.year = year; }
    public void setSeats(int seats) { this.seats = seats; }
    public void setPrice(double price) { this.price = price; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public void setTransmission(String transmission) { this.transmission = transmission; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setStatus(String status) { this.status = status; } // Setter for status

    // Override equals and hashCode to compare Car objects based on plateNumber
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return plateNumber.equals(car.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber);
    }
}
