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

public class Dashboard extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("AutoRide - Dashboard");

        // === Sidebar ===
        VBox sidebar = new VBox(25);
        sidebar.setPadding(new Insets(25));
        sidebar.setPrefWidth(230);
        sidebar.setStyle("-fx-background-color: #166534;"); // updated green

        Label brand = new Label("AutoRide");
        brand.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        brand.setTextFill(Color.WHITE);

        Label subtitle = new Label("Car Rental System");
        subtitle.setFont(Font.font("Arial", 13));
        subtitle.setTextFill(Color.rgb(224, 242, 232));

        VBox header = new VBox(3, brand, subtitle);

        VBox navMenu = new VBox(10);
        navMenu.setPadding(new Insets(30, 0, 30, 0));
        navMenu.getChildren().addAll(
                navItem("🏠 Dashboard", true),
                navItem("🚗 Car Management", false),
                navItem("📅 Booking Management", false),
                navItem("👥 Customer Management", false),
                navItem("👤 User Management", false)
        );

        // --- Admin Info Section ---
        Label adminName = new Label("Admin User");
        adminName.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        adminName.setTextFill(Color.WHITE);

        Label adminEmail = new Label("admin@autoride.com");
        adminEmail.setFont(Font.font("Arial", 12));
        adminEmail.setTextFill(Color.rgb(224, 242, 232));

        VBox adminInfo = new VBox(3, adminName, adminEmail);
        adminInfo.setPadding(new Insets(10, 0, 10, 0));

        Button logout = new Button("⏻ Logout");
        logout.setPrefWidth(180);
        logout.setStyle("""
            -fx-background-color: transparent;
            -fx-text-fill: white;
            -fx-font-size: 13px;
            -fx-border-color: white;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);

        VBox bottom = new VBox(10, adminInfo, logout);
        bottom.setAlignment(Pos.CENTER_LEFT);

        VBox.setVgrow(bottom, Priority.ALWAYS);
        sidebar.getChildren().addAll(header, navMenu, new Region(), bottom);

        // === Main Dashboard Content ===
        Label title = new Label("Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.web("#111827"));

        HBox topCards = new HBox(20,
                createCard("Total Cars", "5", "4 available"),
                createCard("Total Bookings", "2", "1 active"),
                createCard("Pending Bookings", "1", ""),
                createCard("Total Revenue", "₱14,400", "")
        );
        topCards.setPadding(new Insets(20, 0, 10, 0));

        // --- Recent Bookings Table ---
        Label recentLabel = new Label("Recent Bookings");
        recentLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        TableView<String> table = createRecentBookingsTable();

        VBox mainContent = new VBox(20, title, topCards, recentLabel, table);
        mainContent.setPadding(new Insets(30));
        mainContent.setStyle("-fx-background-color: white;");
        mainContent.setAlignment(Pos.TOP_LEFT);

        // === Root Layout ===
        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(mainContent);
        root.setStyle("-fx-background-color: #f3f4f6;");

        Scene scene = new Scene(root, 1100, 700);
        stage.setScene(scene);
        stage.show();
    }

    // Sidebar item builder
    private HBox navItem(String label, boolean active) {
        Label lbl = new Label(label);
        lbl.setFont(Font.font("Arial", 14));

        HBox box = new HBox(lbl);
        box.setPadding(new Insets(10, 12, 10, 12));
        box.setAlignment(Pos.CENTER_LEFT);
        box.setCursor(javafx.scene.Cursor.HAND);

        if (active) {
            box.setStyle("-fx-background-color: white; -fx-background-radius: 8;");
            lbl.setTextFill(Color.web("#166534")); // active green
        } else {
            lbl.setTextFill(Color.WHITE);
        }

        return box;
    }

    // Dashboard card builder
    private VBox createCard(String title, String value, String subtext) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 13));
        titleLabel.setTextFill(Color.web("#6b7280"));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        valueLabel.setTextFill(Color.web("#111827"));

        Label subLabel = new Label(subtext);
        subLabel.setFont(Font.font("Arial", 13));
        subLabel.setTextFill(Color.web("#166534")); // accent green

        VBox card = new VBox(5, titleLabel, valueLabel, subLabel);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(15));
        card.setPrefWidth(200);
        card.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 10;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 6, 0, 0, 2);
        """);

        return card;
    }

    // Table of recent bookings
    private TableView<String> createRecentBookingsTable() {
        TableView<String> table = new TableView<>();

        TableColumn<String, String> id = new TableColumn<>("ID");
        id.setPrefWidth(40);
        TableColumn<String, String> customer = new TableColumn<>("Customer");
        customer.setPrefWidth(150);
        TableColumn<String, String> car = new TableColumn<>("Car");
        car.setPrefWidth(150);
        TableColumn<String, String> dates = new TableColumn<>("Dates");
        dates.setPrefWidth(180);
        TableColumn<String, String> status = new TableColumn<>("Status");
        status.setPrefWidth(100);
        TableColumn<String, String> amount = new TableColumn<>("Amount");
        amount.setPrefWidth(100);

        table.getColumns().addAll(id, customer, car, dates, status, amount);
        table.setPlaceholder(new Label("No recent bookings"));
        table.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
        table.setPrefHeight(250);

        return table;

    }
}
