package com.example.autorideapp.autoride;

public class Car {
    private String model;
    private String plateNumber;
    private String type;
    private int year;
    private int seats;
    private double price;
    private String fuelType;
    private String transmission;
    private String imageUrl;

    public Car(String model, String plateNumber, String type, int year, int seats, double price, String fuelType, String transmission, String imageUrl) {
        this.model = model;
        this.plateNumber = plateNumber;
        this.type = type;
        this.year = year;
        this.seats = seats;
        this.price = price;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.imageUrl = imageUrl;
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
}
