package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.MainWindowVM;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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

	private MainWindowVM vm;

	/**
	 * Sets the view model.
	 *
	 * @param vm the new view model
	 */
	public void setViewModel(MainWindowVM vm) {
		this.vm = vm;

		// Bind the ViewModel properties to the UI components
		this.titleTextField.textProperty().bindBidirectional(this.vm.taskTitleProperty());
		this.descriptionTextArea.textProperty().bindBidirectional(this.vm.taskDescriptionProperty());
	}

	@FXML
	void initialize() {
		this.closeWindow();
		this.handleAddTaskAction();
	}

	private void closeWindow() {
		this.cancelButton.setOnAction(event -> {
			Stage stage = (Stage) this.cancelButton.getScene().getWindow();
			stage.close();
		});
	}

	/**
	 * Handle add task action.
	 */
	private void handleAddTaskAction() {
		this.addTaskButton.setOnAction(event -> {
			try {
				this.vm.addTask(); 
				Stage stage = (Stage) this.addTaskButton.getScene().getWindow();
				stage.close();
			} catch (IllegalArgumentException ex) {
				this.popup(ex.getMessage(), AlertType.ERROR);
			}
		});
	}

	private void popup(String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
