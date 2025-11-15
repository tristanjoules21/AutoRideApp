package com.example.autorideapp.autoride;

import java.util.ArrayList;
import java.util.List;

public class CustomerDatabase {
    private static final List<Customer> customers = new ArrayList<>();

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static List<Customer> getAllCustomers() {
        return customers;
    }

    public static void removeCustomer(Customer customer) {
        customers.remove(customer);
    }
}