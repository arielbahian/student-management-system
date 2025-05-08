package com.example.finalproject;

import javafx.fxml.FXML;

public class AddScreen {

    @FXML
    protected void onAddClick() {
        // Call a helper method to load the new scene
        loadPage("add_screen.fxml");
    }

    }
}
