package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class Dashboard {

    @FXML
    private BorderPane boarderLayout;

    @FXML
    public void initialize() {
        try {
            var root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("studentsList.fxml")));
            assert boarderLayout != null : "boarderLayout is null — check fx:id and FXML";
            boarderLayout.setCenter((Node) root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
