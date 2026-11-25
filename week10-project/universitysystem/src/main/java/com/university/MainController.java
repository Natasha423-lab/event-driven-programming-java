package com.university;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainController {

    @FXML
    private StackPane contentArea;

    private Label studentCountLabel;

    private final ScheduledExecutorService dashboardScheduler =
            Executors.newSingleThreadScheduledExecutor();

    private final ExecutorService databaseExecutor =
            Executors.newSingleThreadExecutor();

    @FXML
    public void initialize() {
        showDashboard();

        dashboardScheduler.scheduleWithFixedDelay(
                this::refreshStudentCount,
                0, 5, TimeUnit.SECONDS
        );
    }

    @FXML
    public void showDashboard() {

        Label welcomeLabel = new Label(
                "Welcome to the University Management System"
        );

        welcomeLabel.setStyle("-fx-font-size: 20px;");

        studentCountLabel = new Label("Students registered: Loading...");

        studentCountLabel.setStyle("-fx-font-size: 16px;");

        VBox dashboard = new VBox(20, welcomeLabel, studentCountLabel);
        dashboard.setStyle("-fx-alignment: center;");

        contentArea.getChildren().setAll(dashboard);
    }

    private void refreshStudentCount() {

        DashboardCountTask task = new DashboardCountTask();

        task.setOnSucceeded(event -> {
            if (studentCountLabel != null) {
                studentCountLabel.setText(
                        "Students registered: " + task.getValue()
                );
            }
        });

        task.setOnFailed(event -> {
            if (studentCountLabel != null) {
                studentCountLabel.setText(
                        "Unable to load student count"
                );
            }
        });

        databaseExecutor.execute(task);
    }

    @FXML
    public void showStudents() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/university/StudentView.fxml")
            );

            contentArea.getChildren().clear();
            contentArea.getChildren().add(loader.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        dashboardScheduler.shutdownNow();
        databaseExecutor.shutdownNow();
    }
}
