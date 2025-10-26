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
    public void start(Stage stage) {
        stage.setTitle("AutoRide - Admin Login");

        // --- Title Section ---
        Label title = new Label("AutoRide");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#065f46"));

        Label subtitle = new Label("Admin Portal");
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setTextFill(Color.web("#4b5563"));

        VBox header = new VBox(5, title, subtitle);
        header.setAlignment(Pos.CENTER);

        // --- Email Field ---
        Label emailLabel = new Label("Email Address");
        emailLabel.setFont(Font.font("Arial", 14));
        emailLabel.setTextFill(Color.web("#374151"));

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setPrefWidth(300);
        emailField.setStyle("""
            -fx-padding: 10;
            -fx-border-color: #d1d5db;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);

        // --- Password Field ---
        Label passLabel = new Label("Password");
        passLabel.setFont(Font.font("Arial", 14));
        passLabel.setTextFill(Color.web("#374151"));

        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter your password");
        passField.setPrefWidth(300);
        passField.setStyle("""
            -fx-padding: 10;
            -fx-border-color: #d1d5db;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);

        // --- Sign In Button ---
        Button loginBtn = new Button("Sign In");
        loginBtn.setPrefWidth(300);
        loginBtn.setStyle("""
            -fx-background-color: #16a34a;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-font-size: 14px;
            -fx-background-radius: 8;
            -fx-padding: 10 0;
        """);

        // when clicked -> open Dashboard
        loginBtn.setOnAction(e -> {
            Stage dashboardStage = new Stage();
            Dashboard dashboard = new Dashboard();
            try {
                dashboard.start(dashboardStage);
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- Demo Account Section ---
        Label demoTitle = new Label("Demo Admin Account:");
        demoTitle.setFont(Font.font("Arial", FontWeight.MEDIUM, 13));
        demoTitle.setTextFill(Color.web("#374151"));

        Label demoInfo = new Label("Admin: admin@autoride.com / admin123");
        demoInfo.setFont(Font.font("Arial", 13));
        demoInfo.setStyle("""
            -fx-background-color: #dcfce7;
            -fx-padding: 8;
            -fx-background-radius: 6;
            -fx-text-fill: #14532d;
        """);

        VBox demoBox = new VBox(5, demoTitle, demoInfo);
        demoBox.setAlignment(Pos.CENTER_LEFT);

        // --- Form Layout ---
        VBox form = new VBox(15,
                emailLabel, emailField,
                passLabel, passField,
                loginBtn,
                new Separator(),
                demoBox
        );
        form.setAlignment(Pos.CENTER_LEFT);

        VBox content = new VBox(25, header, form);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(30, 40, 40, 40));

        VBox card = new VBox(content);
        card.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);
        """);

        StackPane root = new StackPane(card);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f3f4f6;");
        StackPane.setMargin(card, new Insets(30));

        Scene scene = new Scene(root, 450, 550);
        stage.setScene(scene);
        stage.show();
    }
}
