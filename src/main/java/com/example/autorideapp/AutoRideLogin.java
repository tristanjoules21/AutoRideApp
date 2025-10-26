package com.example.autorideapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AutoRideLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AutoRide Login");

        Label titleLabel = new Label("AutoRide");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#0A662E"));

        Label emailLabel = new Label("Email Address");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");

        Label passLabel = new Label("Password");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter your password");

        Button signInBtn = new Button("Sign in");
        signInBtn.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-weight: bold;");
        signInBtn.setPrefWidth(240);
        signInBtn.setOnAction(e -> {
            String email = emailField.getText();
            String pass = passField.getText();

            if (email.equals("admin@autoride.com") && pass.equals("admin123")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Successful!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Credentials!");
                alert.show();
            }
        });

        Label demoLabel = new Label("Demo Admin Account:");
        Label demoInfo = new Label("Admin: admin@autoride.com / admin123");
        demoInfo.setStyle("-fx-background-color: #E8F8EC; -fx-padding: 5 10 5 10; -fx-border-radius: 5;");
        demoInfo.setFont(Font.font("Arial", 12));

        VBox formLayout = new VBox(10, titleLabel,
                emailLabel, emailField,
                passLabel, passField,
                signInBtn, demoLabel, demoInfo);
        formLayout.setAlignment(Pos.CENTER);
        formLayout.setPadding(new Insets(30));
        formLayout.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

        StackPane cardPane = new StackPane(formLayout);
        cardPane.setPadding(new Insets(50));
        cardPane.setStyle("-fx-background-color: #f5f6f7;");

        Scene scene = new Scene(cardPane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

