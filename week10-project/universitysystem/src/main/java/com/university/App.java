package com.university;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App extends Application {

    private ScheduledExecutorService scheduler;
    private MainController mainController;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/university/MainView.fxml")
        );

        Scene scene = new Scene(loader.load());

        mainController = loader.getController();

        stage.setTitle("University Management System");
        stage.setScene(scene);
        stage.show();

        scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(
                () -> System.out.println(
                        "System heartbeat: Application is running"
                ),
                0, 5, TimeUnit.SECONDS
        );
    }

    @Override
    public void stop() {

        if (scheduler != null) {
            scheduler.shutdown();
        }

        if (mainController != null) {
            mainController.shutdown();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
