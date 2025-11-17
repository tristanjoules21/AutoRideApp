package com.example.autorideapp.autoride;

import java.util.ArrayList;
import java.util.List;

public class BookingDatabase {

    private static final List<Booking> bookings = new ArrayList<>();

    public static void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public static List<Booking> getAllBookings() {
        return bookings;
    }

    public static void removeBooking(Booking booking) {
        bookings.remove(booking);
    }
    public static void updateBooking(Booking updated) {
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i) == updated) {
                bookings.set(i, updated);
                return;
            }
        }
    }



}
