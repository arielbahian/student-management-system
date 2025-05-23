package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UpdateScreen {
    @FXML private TextField editFirstNameField;
    @FXML private TextField editMiddleNameField;
    @FXML private TextField editLastNameField;
    @FXML private RadioButton editMaleRadio;
    @FXML private RadioButton editFemaleRadio;
    @FXML private TextField editBirthdateField;
    @FXML private TextField editPhoneField;
    @FXML private TextArea editAddressArea;
    @FXML private TextField editEmailField;
    @FXML private TextField editStudentNumberField;
    @FXML private TextField editCourseField;
    @FXML private TextField editYearLevelField;

    private final ToggleGroup sexToggleGroup = new ToggleGroup();

    @FXML
    private void initialize() {
        editMaleRadio.setToggleGroup(sexToggleGroup);
        editFemaleRadio.setToggleGroup(sexToggleGroup);
    }

    public void setStudentData(Student student) {
        editFirstNameField.setText(student.getFirstName());
        editMiddleNameField.setText(student.getMiddleName());
        editLastNameField.setText(student.getLastName());
        if ("Male".equalsIgnoreCase(student.getSex())) {
            editMaleRadio.setSelected(true);
        } else {
            editFemaleRadio.setSelected(true);
        }
        editBirthdateField.setText(student.getBirthdate());
        editPhoneField.setText(student.getPhone());
        editAddressArea.setText(student.getAddress());
        editEmailField.setText(student.getEmail());
        editStudentNumberField.setText(student.getStudentNumber());
        editCourseField.setText(student.getCourse());
        editYearLevelField.setText(student.getYearLevel());
    }

    public void updateStudent(Student student) {
        student.setFirstName(editFirstNameField.getText());
        student.setMiddleName(editMiddleNameField.getText());
        student.setLastName(editLastNameField.getText());
        student.setSex(editMaleRadio.isSelected() ? "Male" : "Female");
        student.setBirthdate(editBirthdateField.getText());
        student.setPhone(editPhoneField.getText());
        student.setAddress(editAddressArea.getText());
        student.setEmail(editEmailField.getText());
        student.setStudentNumber(editStudentNumberField.getText());
        student.setCourse(editCourseField.getText());
        student.setYearLevel(editYearLevelField.getText());
    }
}
