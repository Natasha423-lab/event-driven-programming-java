package com.university;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.binding.Bindings;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentController {

    @FXML private TextField regNumberField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;

    @FXML private Button saveButton;
    @FXML private Label statusLabel;

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Number> idColumn;
    @FXML private TableColumn<Student, String> regColumn;
    @FXML private TableColumn<Student, String> firstNameColumn;
    @FXML private TableColumn<Student, String> lastNameColumn;

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(
            cell -> cell.getValue().idProperty()
        );

        regColumn.setCellValueFactory(
            cell -> cell.getValue().regNumberProperty()
        );

        firstNameColumn.setCellValueFactory(
            cell -> cell.getValue().firstNameProperty()
        );

        lastNameColumn.setCellValueFactory(
            cell -> cell.getValue().lastNameProperty()
        );

        saveButton.disableProperty().bind(
            Bindings.isEmpty(regNumberField.textProperty())
                .or(Bindings.isEmpty(firstNameField.textProperty()))
                .or(Bindings.isEmpty(lastNameField.textProperty()))
                .or(Bindings.isEmpty(emailField.textProperty()))
        );

        loadStudents();
    }

    @FXML
    public void saveStudent() {

        String regNumber = regNumberField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        Task<Void> saveTask = new Task<>() {

            @Override
            protected Void call() throws Exception {

                String sql = "INSERT INTO students "
                    + "(registration_number, first_name, last_name, email) "
                    + "VALUES (?, ?, ?, ?)";

                try (Connection conn = DatabaseUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

                    stmt.setString(1, regNumber);
                    stmt.setString(2, firstName);
                    stmt.setString(3, lastName);
                    stmt.setString(4, email);

                    stmt.executeUpdate();
                }

                return null;
            }
        };

        saveTask.setOnSucceeded(event -> {
            statusLabel.setText("Student saved successfully!");

            regNumberField.clear();
            firstNameField.clear();
            lastNameField.clear();
            emailField.clear();

            loadStudents();
        });

        saveTask.setOnFailed(event -> {
            statusLabel.setText(
                "Error: " + saveTask.getException().getMessage()
            );
        });

        statusLabel.setText("Saving student...");

        Thread thread = new Thread(saveTask);
        thread.setDaemon(true);
        thread.start();
    }

    private void loadStudents() {

        LoadStudentsTask loadTask = new LoadStudentsTask();

        loadTask.setOnSucceeded(event -> {
            ObservableList<Student> students = loadTask.getValue();
            studentTable.setItems(students);
        });

        loadTask.setOnFailed(event -> {
            statusLabel.setText(
                "Loading error: " + loadTask.getException().getMessage()
            );
        });

        Thread thread = new Thread(loadTask);
        thread.setDaemon(true);
        thread.start();
    }
}
