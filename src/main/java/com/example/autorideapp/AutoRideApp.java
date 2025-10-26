package com.example.autorideapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AutoRideApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Launch the JavaFX Login Scene
        AutoRideLogin login = new AutoRideLogin();
        login.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

