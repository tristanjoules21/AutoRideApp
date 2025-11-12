package com.example.autorideapp.autoride;

public class Customer {
    private String fullName;
    private String email;
    private String password;

    public Customer(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
