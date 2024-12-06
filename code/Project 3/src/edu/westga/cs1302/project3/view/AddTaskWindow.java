package edu.westga.cs1302.project3.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Codebehind for the Add Task Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class AddTaskWindow {
	@FXML
	private Button addTaskButton;

	@FXML
	private Button cancelButton;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private TextField titleTextField;

	private AddTaskWindow addTaskVm;

	@FXML
	void initialize() {
		this.addTaskVm = new AddTaskWindow();

		this.closeWindow();

	}

	private void closeWindow() {
		this.cancelButton.setOnAction(event -> {
			Stage stage = (Stage) this.cancelButton.getScene().getWindow();
			stage.close();
		});
	}

}
