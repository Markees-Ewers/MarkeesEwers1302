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
import javafx.collections.ObservableList;

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
		ObservableList<Task> observableTasks = FXCollections.observableList(this.taskManager.getAllTasks());
		this.tasks = new SimpleListProperty<>(observableTasks);
		Task defaultTask = new Task("take walk", "walk around the building");
		this.addTask(defaultTask);
		System.out.println(this.taskManager.getAllTasks().size());
	}

	/**
	 * Adds the task.
	 *
	 * @param task the task
	 */
	public void addTask(Task task) {
		this.taskManager.addTask(task);
		this.tasks.set(FXCollections.observableArrayList(this.taskManager.getAllTasks()));
	}

	/**
	 * Removes the task.
	 *
	 * @param task the task
	 */
	public void removeTask(Task task) {
		this.taskManager.removeTask(task);
		this.tasks.set(FXCollections.observableArrayList(this.taskManager.getAllTasks()));
	}

	/**
	 * Task property.
	 *
	 * @return the list property
	 */
	public ListProperty<Task> tasksProperty() {
		return this.tasks;
	}

	/**
	 * Load tasks into listView.
	 * 
	 * @param file the file that it rights to
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void loadTasks(File file) throws IOException {

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
	 * Save task.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveTask(File file) throws IOException {
		if (file == null) {
			System.out.println("No file selected. Save operation canceled.");
			return;
		}

		// Convert ObservableList to standard ArrayList

		File savedFile = TaskFileHandler.saveTaskFile(this.taskManager, file);
		System.out.println("Tasks successfully saved to " + savedFile.getAbsolutePath());

	}
}
