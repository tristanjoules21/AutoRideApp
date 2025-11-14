package com.example.autorideapp.autoride;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    private static final List<User> users = new ArrayList<>();

    static {
        // Default admin
        users.add(new User(
                "admin",
                "admin@autoride.com",
                "System Administrator",
                LocalDate.now().toString()
        ));
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static List<User> getAllUsers() {
        return users;
    }

    public static User findUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
