package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * The Class TaskManager.
 * 
 * @author me00070
 * @version fall 2024
 */
public class TaskManager {

	/** The tasks. */
	private List<Task> tasks;
	private Map<String, Task> taskLookupTable;

	/**
	 * Instantiates a new task manager.
	 */
	public TaskManager() {
		this.tasks = new ArrayList<Task>();
		this.taskLookupTable = new HashMap<>();
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
		if (this.taskLookupTable.containsKey(task.getName())) {
			throw new IllegalArgumentException("A task with the same title already exists");
		}
		this.tasks.add(task);

		this.taskLookupTable.put(task.getName(), task);
	}

	/**
	 * Removes the task.
	 *
	 * @param task the task that gets deleted
	 */
	public void removeTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null");
		}
		if (this.taskLookupTable.remove(task.getName()) != null) {
			this.tasks.remove(task);
		}
	}

	/**
	 * Gets the all tasks.
	 *
	 * @return the all tasks
	 */
	public ArrayList<Task> getAllTasks() {
		return new ArrayList<>(this.tasks);
	}

	/**
	 * Adds the all.
	 *
	 * @param tasks the tasks
	 */
	public void addAll(ArrayList<Task> tasks) {
		if (tasks == null) {
			throw new IllegalArgumentException("Tasks cannot be null");
		}

		// Temporary set to track duplicates within the input list
		HashSet<String> seenTitles = new HashSet<>();

		ArrayList<Task> taskToAdd = new ArrayList<>();

		// Validation and staging
		for (Task task : tasks) {
			if (task == null) {
				throw new IllegalArgumentException("Task in the list cannot be null");
			}
			if (seenTitles.contains(task.getName())) {
				throw new IllegalArgumentException("Duplicate task found in input list: " + task.getName());
			}
			if (this.taskLookupTable.containsKey(task.getName())) {
				throw new IllegalArgumentException("Duplicate task found: " + task.getName());
			}

			seenTitles.add(task.getName());
			taskToAdd.add(task);
		}

		// Adding to both tasks and lookup table
		for (Task task : taskToAdd) {
			this.tasks.add(task);
			this.taskLookupTable.put(task.getName(), task);
		}

	}

	/**
	 * Clear.
	 */
	public void clear() {
		this.tasks.clear();
		this.taskLookupTable.clear();
	}

}
