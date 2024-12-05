package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.MainWindowVM;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private ListView<Task> taskListView;

	private MainWindowVM mainWindowVM;

	@FXML
	void initialize() {
		this.mainWindowVM = new MainWindowVM();

		  this.taskListView.itemsProperty().bind(this.mainWindowVM.tasksProperty());
	}

}
