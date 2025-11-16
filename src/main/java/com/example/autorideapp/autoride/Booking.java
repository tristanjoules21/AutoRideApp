package com.example.autorideapp.autoride;

public class Booking {
    private int id;
    private String customerName;
    private String carModel;
    private String date; // "YYYY-MM-DD to YYYY-MM-DD"
    private String status; // "Pending" or "Completed"
    private double totalCost;

    public Booking(String customerName, String carModel, String date, double totalCost) {
        this.customerName = customerName;
        this.carModel = carModel;
        this.date = date;
        this.totalCost = totalCost;
        this.status = "Pending"; // default
    }

    // Getters
    public int getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getCarModel() { return carModel; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public double getTotalCost() { return totalCost; }

    // Setters
    public void setId(int id) { this.id = id; } // <-- Added setter for ID
    public void setStatus(String status) { this.status = status; }

    // For TableView display
    public String getFormattedTotalCost() {
        return String.format("₱%,.0f", totalCost);
    }
}
