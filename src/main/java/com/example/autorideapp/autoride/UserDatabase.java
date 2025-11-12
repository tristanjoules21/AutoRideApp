package com.example.autorideapp.autoride;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static final List<User> users = new ArrayList<>();

    static {
        // Default admin account
        users.add(new User("admin", "admin@autoride.com", "1234", "Admin"));
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static List<User> getAllUsers() {
        return users;
    }

    public static User findUser(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
