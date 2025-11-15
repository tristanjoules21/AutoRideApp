package com.example.autorideapp.autoride;

import java.util.ArrayList;
import java.util.List;

public class CarDatabase {
    // A list to store the cars in memory
    private static final List<Car> cars = new ArrayList<>();

    // Method to add a car to the database (list)
    public static void addCar(Car car) {
        cars.add(car);
    }

    // Method to get all cars from the database
    public static List<Car> getAllCars() {
        return cars;
    }

    // Method to remove a car from the database (list)
    public static boolean deleteCar(Car car) {
        return cars.remove(car); // Remove the car and return whether it was successful
    }
}
