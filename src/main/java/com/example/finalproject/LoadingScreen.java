package com.example.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.io.IOException;

public class LoadingScreen {

    public Stage stage;

    @FXML
    private ProgressBar loader;

    @FXML
    public void initialize(){
        var timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> loader.setProgress(0)),
                new KeyFrame(Duration.millis(200), e -> loader.setProgress(0.1)),
                new KeyFrame(Duration.millis(350), e -> loader.setProgress(0.3)),
                new KeyFrame(Duration.millis(400), e -> loader.setProgress(0.5)),
                new KeyFrame(Duration.millis(550), e -> loader.setProgress(0.7)),
                new KeyFrame(Duration.millis(600), e -> loader.setProgress(0.9)),
                new KeyFrame(Duration.millis(1050), e -> loader.setProgress(1.0))
        );
        timeline.play();
        timeline.setOnFinished(_ -> onCompleted());
    }

    public void onCompleted () {
        var dashboardFxml = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
        try {
            var scene = new Scene(dashboardFxml.load(), 800, 600);
            System.out.println(stage.toString());
            this.stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setStage(Stage stage) {
        System.out.println("Set Stage");
        this.stage = stage;
        System.out.println(stage.toString());
    }
}
