package com.example.autorideapp.autoride;

import java.util.ArrayList;
import java.util.List;

public class CustomerDatabase {
    private static final List<Customer> customers = new ArrayList<>();

    // Add a new customer
    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Get all customers
    public static List<Customer> getAllCustomers() {
        return customers;
    }

    // Remove a customer
    public static void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    // Optional: Find a customer by phone number
    public static Customer findByPhone(String phone) {
        return customers.stream()
                .filter(c -> c.getPhone().equals(phone))
                .findFirst()
                .orElse(null);
    }
}
