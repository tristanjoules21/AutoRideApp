package com.example.autorideapp.autoride;

import java.util.ArrayList;
import java.util.List;

public class BookingDatabase {
    private static final List<Booking> bookings = new ArrayList<>();

    // Add a booking to the database
    public static void addBooking(Booking booking) {
        bookings.add(booking);
    }

    // Get all bookings from the database
    public static List<Booking> getAllBookings() {
        return bookings;
    }

    // Remove a booking from the database
    public static void removeBooking(Booking booking) {
        bookings.remove(booking);
    }
}
