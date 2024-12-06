package edu.westga.cs1302.project3.test.model.taskManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class TestAddAll {

	private TaskManager taskManager;

	@BeforeEach
	void setUp() {
		this.taskManager = new TaskManager();
	}

	@Test
	void testAddAllValidTasks() {
		// Arrange
		Task task1 = new Task("Task 1", "Description 1");
		Task task2 = new Task("Task 2", "Description 2");
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		// Act
		this.taskManager.addAll(tasks);

		// Assert
		assertEquals(2, this.taskManager.getAllTasks().size());
		assertTrue(this.taskManager.getAllTasks().contains(task1));
		assertTrue(this.taskManager.getAllTasks().contains(task2));
	}

	@Test
	void testAddAllWithNullTasksList() {
		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.taskManager.addAll(null);
		}, "Expected IllegalArgumentException for null tasks list");
	}

	@Test
	void testAddAllWithNullTaskInList() {
		// Arrange
		Task task1 = new Task("Task 1", "Description 1");
		Task nullTask = null;
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(nullTask);

		// Act & Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			this.taskManager.addAll(tasks);
		});
		assertEquals("Task in the list cannot be null", exception.getMessage());
	}

	@Test
	void testAddAllWithDuplicateTasks() {
		// Arrange
		Task task1 = new Task("Task 1", "Description 1");
		Task duplicateTask = new Task("Task 1", "Description 2");
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(duplicateTask);

		// Act & Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			this.taskManager.addAll(tasks);
		});
		assertEquals("Duplicate task found in input list: Task 1", exception.getMessage());
	}

	@Test
	void testAddAllDoesNotModifyOnError() {
		// Arrange
		Task task1 = new Task("Task 1", "Description 1");
		Task task2 = new Task("Task 2", "Description 2");
		Task duplicateTask = new Task("Task 1", "Duplicate Description");
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);
		tasks.add(duplicateTask);

		// Act
		try {
			this.taskManager.addAll(tasks);
		} catch (IllegalArgumentException e) {
			// Catch the exception and proceed to assertion
		}

		// Assert
		assertEquals(0, this.taskManager.getAllTasks().size(), "TaskManager should remain empty after an error");
	}
}
