package com.example.autorideapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CarManagement {

    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CarManagement.fxml"));
            Scene scene = new Scene(loader.load(), 1100, 700);
            stage.setScene(scene);
            stage.setTitle("Car Management");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
