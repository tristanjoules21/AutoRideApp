package com.example.autorideapp.autoride;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button signinBtn;

    @FXML
    private void onSignInClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/dashboard-view.fxml"));
            Scene scene = new Scene(loader.load());

            String styleCss = getClass().getResource("/com/example/autorideapp/dashboard.css").toExternalForm();
            scene.getStylesheets().add(styleCss);

            Stage stage = (Stage) signinBtn.getScene().getWindow();
            stage.setScene(scene);

            // Maximize the window
            stage.setMaximized(true);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
