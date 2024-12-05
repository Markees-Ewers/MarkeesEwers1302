package edu.westga.cs1302.project3.view;

import java.io.IOException;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.MainWindowVM;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private ListView<Task> taskListView;
	@FXML
	private MenuItem loadTaskMenuItem;

	private MainWindowVM mainWindowVM;

	@FXML
	void initialize() {
		this.mainWindowVM = new MainWindowVM();

		this.taskListView.itemsProperty().bind(this.mainWindowVM.tasksProperty());
		this.menuButtons();
	}

	private void menuButtons() {
		this.loadTaskMenuItem.setOnAction(event -> {
			try {
				this.mainWindowVM.loadTasks();
			} catch (IOException ex) {
				this.popup(ex.getMessage(), AlertType.ERROR);
			} catch (IllegalArgumentException ex) {
				this.popup(ex.getMessage(), AlertType.ERROR);
			}
		});
	}

	private void popup(String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setContentText(message);
		alert.setHeaderText(message);
		alert.showAndWait();
	}

}
