package com.example.autorideapp.autoride;

public class User {

    private String username;
    private String email;

    // NEW FIELDS
    private String position;
    private String created;

    public User(String username, String email, String position, String created) {
        this.username = username;
        this.email = email;
        this.position = position;
        this.created = created;
    }

    // Getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPosition() { return position; }
    public String getCreated() { return created; }

    // Setters
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPosition(String position) { this.position = position; }
    public void setCreated(String created) { this.created = created; }
}