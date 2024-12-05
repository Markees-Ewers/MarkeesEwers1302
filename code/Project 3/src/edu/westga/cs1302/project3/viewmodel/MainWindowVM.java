package edu.westga.cs1302.project3.viewmodel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskFileHandler;
import edu.westga.cs1302.project3.model.TaskManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

// TODO: Auto-generated Javadoc
/**
 * The Class MainWindowVM.
 * 
 * @author me00070
 * @version fall 2024
 */
public class MainWindowVM {

	/** The tasks. */
	private ListProperty<Task> tasks;

	/** The task manager. */
	private TaskManager taskManager;

	/**
	 * Instantiates a new main window VM.
	 */
	public MainWindowVM() {
		this.taskManager = new TaskManager();
		this.tasks = new SimpleListProperty<>(FXCollections.observableArrayList(this.taskManager.getAllTasks()));
		Task defualtTask = new Task("take walk", "walk around the building");
		this.tasks.add(defualtTask);
	}

	/**
	 * Load tasks into listView.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void loadTasks() throws IOException {
		File file = TaskFileHandler.taskFileChooser(true);
		List<Task> tasks = TaskFileHandler.loadTaskFile(file);
		if (file != null) {
			if (!file.getName().toLowerCase().endsWith(".txt")) {
				throw new IllegalArgumentException("File must end: .txt");
			}
			this.tasks.clear();
			this.tasks.addAll(tasks);
		}

	}

	/**
	 * Task property.
	 *
	 * @return the list property
	 */
	public ListProperty<Task> tasksProperty() {
		return this.tasks;
	}

}
