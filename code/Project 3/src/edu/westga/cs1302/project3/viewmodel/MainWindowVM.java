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
		System.out.println(this.taskManager.getAllTasks().size());

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
		}
		this.taskManager.addTask(task);
		this.tasks.add(task);
		System.out.println("task Manager size = " + this.taskManager.getAllTasks().size());
		System.out.println("ObservableList size: " + this.tasks.size());
	}

	/**
	 * Removes the task.
	 *
	 * @param task the task
	 */
	private void removeTask(Task task) {
		this.taskManager.removeTask(task);
		this.tasks.set(FXCollections.observableArrayList(this.taskManager.getAllTasks()));
	}

	/**
	 * Adds the task.
	 */
	public void addTask() {
		if (this.taskTitle.get() == null || this.taskDescription.get().isBlank()) {
			throw new IllegalArgumentException("Task title cannot be blank");
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

		ArrayList<Task> tasks = TaskFileHandler.loadTaskFile(file);
		if (file != null) {
			if (!file.getName().toLowerCase().endsWith(".txt")) {
				throw new IllegalArgumentException("File must end: .txt");
			}
			this.tasks.clear();
			this.tasks.addAll(tasks);
			this.taskManager.clear();
			this.taskManager.addAll(tasks);
			
			System.out.println("load manager size " + this.taskManager.getAllTasks().size());
			System.out.println("load task size " + this.tasks.getSize());
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
