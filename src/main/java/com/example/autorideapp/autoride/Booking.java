package com.example.autorideapp.autoride;

public class Booking {
    private String customerName;
    private String carModel;
    private String date;
    private double totalCost;

    public Booking(String customerName, String carModel, String date, double totalCost) {
        this.customerName = customerName;
        this.carModel = carModel;
        this.date = date;
        this.totalCost = totalCost;
    }

    public String getCustomerName() { return customerName; }
    public String getCarModel() { return carModel; }
    public String getDate() { return date; }
    public double getTotalCost() { return totalCost; }
}