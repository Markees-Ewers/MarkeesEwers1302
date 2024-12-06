package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TaskManager.
 * 
 * @author me00070
 * @version fall 2024
 */
public class TaskManager {

	/** The tasks. */
	private List<Task> tasks;

	/**
	 * Instantiates a new task manager.
	 */
	public TaskManager() {
		this.tasks = new ArrayList<Task>();
	}

	/**
	 * Adds the task.
	 *
	 * @param task the task
	 */
	public void addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null");
		}
		this.tasks.add(task);
	}

	/**
	 * Removes the task.
	 *
	 * @param task the task that gets deleted
	 */
	public void removeTask(Task task) {
		this.tasks.remove(task);

	}

	/**
	 * Gets the all tasks.
	 *
	 * @return the all tasks
	 */
	public ArrayList<Task> getAllTasks() {
		return new ArrayList<>(this.tasks);
	}

}
