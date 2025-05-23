package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddScreen {

    @FXML private TextField firstNameField;
    @FXML private TextField middleNameField;   // ← new
    @FXML private TextField lastNameField;
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private TextField birthdateField;
    @FXML private TextField phoneField;
    @FXML private TextArea addressArea;
    @FXML private TextField emailField;
    @FXML private TextField studentNumberField;
    @FXML private TextField courseField;
    @FXML private TextField yearLevelField;

    private ToggleGroup genderGroup;

    @FXML
    public void initialize() {
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
    }

    public Student getStudentFromForm() {
        String gender = genderGroup.getSelectedToggle() == null
                ? ""
                : ((RadioButton) genderGroup.getSelectedToggle()).getText();

        return new Student(
                firstNameField.getText(),
                middleNameField.getText(),
                lastNameField.getText(),
                gender,
                birthdateField.getText(),
                phoneField.getText(),
                addressArea.getText(),
                emailField.getText(),
                studentNumberField.getText(),
                courseField.getText(),
                yearLevelField.getText()
        );
    }
}
