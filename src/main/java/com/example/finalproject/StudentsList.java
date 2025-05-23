package com.example.finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.collections.transformation.FilteredList;

import java.io.IOException;

public class StudentsList {

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Boolean> selectCol;
    @FXML private TableColumn<Student, String> idCol;
    @FXML private TableColumn<Student, String> lastNameCol;
    @FXML private TableColumn<Student, String> firstNameCol;
    @FXML private TableColumn<Student, String> middleInitialCol;
    @FXML private TableColumn<Student, String> sexCol;
    @FXML private TableColumn<Student, String> emailCol;
    @FXML private TableColumn<Student, String> courseCol;
    @FXML private TableColumn<Student, String> yearLevelCol;
    @FXML private CheckBox selectAllCheckBox;
    @FXML private TextField searchField;

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private final FilteredList<Student> filteredList = new FilteredList<>(studentList, p -> true);

    @FXML
    private void initialize() {
        System.out.println("StudentsList FXML loaded successfully!");

        studentTable.setEditable(true);
        selectCol.setEditable(true);

        selectCol.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));

        idCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStudentNumber()));
        lastNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));
        firstNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        middleInitialCol.setCellValueFactory(data -> {
            String middle = data.getValue().getMiddleName();
            return new SimpleStringProperty((middle != null && !middle.isEmpty()) ? middle.substring(0, 1) : "");
        });
        sexCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSex()));
        emailCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        courseCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCourse()));
        yearLevelCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getYearLevel()));

        // Bind FilteredList to TableView
        studentTable.setItems(filteredList);

        // Select All checkbox
        selectAllCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            for (Student s : filteredList) {
                s.setSelected(newVal);
            }
        });

        studentList.addListener((javafx.collections.ListChangeListener<Student>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Student s : change.getAddedSubList()) {
                        s.selectedProperty().addListener((obs, oldVal, newVal) -> updateSelectAllCheckBox());
                    }
                }
            }
        });
    }

    private void updateSelectAllCheckBox() {
        boolean allSelected = filteredList.stream().allMatch(Student::isSelected);
        boolean noneSelected = filteredList.stream().noneMatch(Student::isSelected);

        if (allSelected) {
            selectAllCheckBox.setSelected(true);
            selectAllCheckBox.setIndeterminate(false);
        } else if (noneSelected) {
            selectAllCheckBox.setSelected(false);
            selectAllCheckBox.setIndeterminate(false);
        } else {
            selectAllCheckBox.setIndeterminate(true);
        }
    }

    @FXML
    private void onSearch() {
        String keyword = searchField.getText().toLowerCase().trim();

        if (keyword.isEmpty()) {
            filteredList.setPredicate(s -> true);
        } else {
            filteredList.setPredicate(s -> {
                return s.getStudentNumber().toLowerCase().contains(keyword)
                        || s.getLastName().toLowerCase().contains(keyword)
                        || s.getFirstName().toLowerCase().contains(keyword)
                        || s.getMiddleName().toLowerCase().contains(keyword)
                        || s.getSex().toLowerCase().contains(keyword)
                        || s.getEmail().toLowerCase().contains(keyword)
                        || s.getCourse().toLowerCase().contains(keyword)
                        || s.getYearLevel().toLowerCase().contains(keyword);
            });
        }

        updateSelectAllCheckBox();
    }

    @FXML
    public void onAddClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_screen.fxml"));
            DialogPane dialogPane = loader.load();

            AddScreen controller = loader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Add New Student");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            dialog.initModality(Modality.APPLICATION_MODAL);

            dialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Student newStudent = controller.getStudentFromForm();
                    if (newStudent != null) {
                        studentList.add(newStudent);
                        System.out.println("Student added: " + newStudent.getFirstName());
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onUpdateClick() {
        var selectedStudents = studentList.filtered(Student::isSelected);

        if (selectedStudents.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Students Selected");
            alert.setContentText("Please select at least one student using the checkboxes.");
            alert.showAndWait();
            return;
        }

        for (Student selectedStudent : selectedStudents) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("update_screen.fxml"));
                DialogPane dialogPane = loader.load();

                UpdateScreen controller = loader.getController();
                controller.setStudentData(selectedStudent);

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(dialogPane);
                dialog.setTitle("Update Student");
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                dialog.initModality(Modality.APPLICATION_MODAL);

                var result = dialog.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    controller.updateStudent(selectedStudent);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        studentTable.refresh();
    }

    @FXML
    public void onDeleteClick() {
        var selectedStudents = studentList.filtered(Student::isSelected);

        if (selectedStudents.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Students Selected");
            alert.setContentText("Please select at least one student using the checkboxes.");
            alert.showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Deletion");
        confirm.setHeaderText("Delete Selected Students");
        confirm.setContentText("Are you sure you want to delete the selected students?");

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                studentList.removeIf(Student::isSelected);
            }
        });
    }

}
