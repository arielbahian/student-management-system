package com.example.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var splashScreenFxml = new FXMLLoader(HelloApplication.class.getResource("loading_screen.fxml"));

        var scene = new Scene(splashScreenFxml.load(), 800, 600);

        var splashScreenCtrl = (LoadingScreen)splashScreenFxml.getController();
        splashScreenCtrl.setStage(stage);


        stage.setMinWidth(600);
        stage.setMinHeight(300);
        stage.setTitle("Activity");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}