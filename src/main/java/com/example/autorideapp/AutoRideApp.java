package com.example.autorideapp;

import javafx.application.Application;
import javafx.stage.Stage;

public class AutoRideApp extends Application {

    @Override
    public void start(Stage stage) {
        // Start with login screen
        AutoRideLogin login = new AutoRideLogin();
        login.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
