package edu.westga.cs1302.project3.view;

import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.MainWindowVM;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	@FXML
	private MenuItem saveTaskMenuItem;
	@FXML
	private MenuItem addTaskMenuItem;
	@FXML
	private Button addTaskButton;

	@FXML
	private Button removeButton;

	private MainWindowVM mainWindowVM;

	@FXML
	void initialize() {
		this.mainWindowVM = new MainWindowVM();

		this.taskListView.itemsProperty().bind(this.mainWindowVM.tasksProperty());

		this.loadMenu();
		this.saveMenu();
		this.addTaskWindow();
		this.addButton();
		this.setupRemoveButton();
	}

	private void setupRemoveButton() {
		this.removeButton.setOnAction(event -> {
			Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();
			if (selectedTask != null) {
				try {
					this.mainWindowVM.removeSelectedTask(selectedTask);
				} catch (IllegalArgumentException ex) {
					this.popup("Cannot remove task: " + ex.getMessage(), Alert.AlertType.ERROR);
				}
			} else {
				this.popup("Please select a task to remove.", Alert.AlertType.WARNING);
			}
		});

		// Disable the button if no task is selected
		this.removeButton.disableProperty().bind(this.taskListView.getSelectionModel().selectedItemProperty().isNull());
	}

	private void addButton() {
		this.addTaskButton.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTaskWindow.fxml"));
				Scene scene = new Scene(loader.load());

				// Get the controller for AddTaskWindow
				AddTaskWindow controller = loader.getController();

				// Pass the existing ViewModel to AddTaskWindow
				controller.setViewModel(this.mainWindowVM);

				// Create and show the stage
				Stage addTaskStage = new Stage();
				addTaskStage.setTitle("Add Task Window");
				addTaskStage.setScene(scene);
				addTaskStage.initModality(Modality.APPLICATION_MODAL);
				addTaskStage.initOwner(this.taskListView.getScene().getWindow());
				addTaskStage.showAndWait();
				this.mainWindowVM.taskDescriptionProperty().set("");
				this.mainWindowVM.taskTitleProperty().set("");
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
				this.popup(ex.getMessage(), Alert.AlertType.ERROR);
			}
		});
	}

	private void loadMenu() {
		this.loadTaskMenuItem.setOnAction(event -> {
			try {
				this.mainWindowVM.loadTasks(this.taskFileChooser(true));
			} catch (IOException ex) {
				this.popup(ex.getMessage(), AlertType.ERROR);

			} catch (IllegalArgumentException ex) {
				this.popup(ex.getMessage(), AlertType.ERROR);
			}
		});
	}

	private void addTaskWindow() {
		this.addTaskMenuItem.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTaskWindow.fxml"));
				Scene scene = new Scene(loader.load());

				// Get the controller for AddTaskWindow
				AddTaskWindow controller = loader.getController();

				// Pass the existing ViewModel to AddTaskWindow
				controller.setViewModel(this.mainWindowVM);

				// Create and show the stage
				Stage addTaskStage = new Stage();
				addTaskStage.setTitle("Add Task Window");
				addTaskStage.setScene(scene);
				addTaskStage.initModality(Modality.APPLICATION_MODAL);
				addTaskStage.initOwner(this.taskListView.getScene().getWindow());
				addTaskStage.showAndWait();
				this.mainWindowVM.taskDescriptionProperty().set("");
				this.mainWindowVM.taskTitleProperty().set("");
			} catch (IOException ex) {
				this.popup(ex.getMessage(), Alert.AlertType.ERROR);
			}
		});
	}

	private void saveMenu() {
		this.saveTaskMenuItem.setOnAction(event -> {
			try {
				this.mainWindowVM.saveTask(this.taskFileChooser(false));
			} catch (IOException ex) {
				this.popup(ex.getMessage(), AlertType.ERROR);
			} catch (IllegalArgumentException ex) {
				this.popup(ex.getMessage(), AlertType.ERROR);
			}
		});
	}

	/**
	 * Save task file.
	 *
	 * 
	 * @return the file that the user selects
	 * @param showOpen if true the button shows a prompt to open otherwise it shows
	 *                 to save;
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private File taskFileChooser(Boolean showOpen) throws IOException {
		// can't test this because its user input but its handled by built in
		// filechooser
		FileChooser chooser = new FileChooser();
		if (showOpen) {
			chooser.setTitle("Open Task File");
			chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			return chooser.showOpenDialog(null);
		} else {
			chooser.setTitle("Save Task File");
			chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			return chooser.showSaveDialog(null);
		}
	}

	private void popup(String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
