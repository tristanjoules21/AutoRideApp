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
            // Load the dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/autorideapp/dashboard-view.fxml"));
            Scene scene = new Scene(loader.load());

            // Optionally, pass some info to DashboardController
            // DashboardController controller = loader.getController();
            // controller.setUserEmail("demo@autoride.com");

            // Apply dashboard CSS
            String styleCss = getClass().getResource("/com/example/autorideapp/dashboard.css").toExternalForm();
            scene.getStylesheets().add(styleCss);

            Stage stage = (Stage) signinBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // You could log or show an alert here if dashboard fails to load
        }
    }
}
