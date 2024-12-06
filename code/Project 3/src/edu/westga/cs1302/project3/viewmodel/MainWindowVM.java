package edu.westga.cs1302.project3.viewmodel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskFileHandler;
import edu.westga.cs1302.project3.model.TaskManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

	/** The task title. */
	private StringProperty taskTitle;

	/** The task description. */
	private StringProperty taskDescription;

	/**
	 * Instantiates a new main window VM.
	 */
	public MainWindowVM() {
		this.taskManager = new TaskManager();
		ObservableList<Task> observableTasks = FXCollections.observableList(this.taskManager.getAllTasks());
		this.tasks = new SimpleListProperty<>(observableTasks);
		Task defaultTask = new Task("take walk", "walk around the building");
		this.addTask(defaultTask);

		this.taskDescription = new SimpleStringProperty();
		this.taskTitle = new SimpleStringProperty();

	}

	/**
	 * Task title property.
	 *
	 * @return the string property
	 */
	public StringProperty taskTitleProperty() {
		return this.taskTitle;
	}

	/**
	 * Task description property.
	 *
	 * @return the string property
	 */
	public StringProperty taskDescriptionProperty() {
		return this.taskDescription;
	}

	/**
	 * private helper add task.
	 *
	 * @param task the task
	 */
	private void addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null");
		} // behind the scenes
		this.taskManager.addTask(task);
		// visible one
		this.tasks.add(task);

	}

	/**
	 * Removes the task.
	 *
	 * @param selectedTask the task to be removed
	 */
	public void removeSelectedTask(Task selectedTask) {
		if (selectedTask == null) {
			throw new IllegalArgumentException("No task selected for removal.");
		} // Update TaskManager
		this.taskManager.removeTask(selectedTask);
		// Update ObservableList
		this.tasks.remove(selectedTask);
	}

	/**
	 * Adds the task.
	 */
	public void addTask() {
		if (this.taskTitle.get() == null || this.taskTitle.get().isBlank()) {
			throw new IllegalArgumentException("Task title cannot be blank");
		}
		if (this.taskDescription.get() == null || this.taskDescription.get().isBlank()) {
			throw new IllegalArgumentException("description cannot be blank");
		}
		Task newTask = new Task(this.taskTitle.get(), this.taskDescription.get());
		this.addTask(newTask);
		this.taskManager.getAllTasks().size();
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
		if (file == null) {
			throw new IllegalArgumentException("File cannot be null");
		}
		if (!file.getName().toLowerCase().endsWith(".txt")) {
			throw new IllegalArgumentException("File must end with .txt");
		}

		// Clear existing tasks
		this.tasks.clear();
		this.taskManager.clear();

		// Load tasks from file
		ArrayList<Task> tasks = TaskFileHandler.loadTaskFile(file);

		// Add loaded tasks
		// Add to TaskManager (performs hash checks)
		this.taskManager.addAll(tasks);
		// Add to ObservableList
		this.tasks.addAll(tasks);

	}

	/**
	 * Save task.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveTask(File file) throws IOException {
		if (file == null) {
			return;
		}
		// Convert ObservableList to standard ArrayList
		// this is used to bring up the file
		@SuppressWarnings("unused")
		File savedFile = TaskFileHandler.saveTaskFile(this.taskManager, file);

	}
}
