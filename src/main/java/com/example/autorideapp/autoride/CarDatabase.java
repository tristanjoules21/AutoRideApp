package com.example.autorideapp.autoride;

import java.util.ArrayList;
import java.util.List;

public class CarDatabase {
    private static final List<Car> cars = new ArrayList<>();

    public static void addCar(Car car) {
        cars.add(car);
    }

    public static List<Car> getAllCars() {
        return cars;
    }

    public static void removeCar(Car car) {
        cars.remove(car);
    }
}
