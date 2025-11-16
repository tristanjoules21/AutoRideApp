package com.example.autorideapp.autoride;

public class Customer {
    private String fullName;
    private String email;
    private String phone; // changed from password

    public Customer(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone; // updated
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() { // updated
        return phone;
    }

    public void setPhone(String phone) { // updated
        this.phone = phone;
    }
}
