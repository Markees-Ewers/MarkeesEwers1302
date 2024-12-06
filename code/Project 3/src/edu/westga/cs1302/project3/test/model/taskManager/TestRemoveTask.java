package edu.westga.cs1302.project3.test.model.taskManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class TestRemoveTask {

	private TaskManager taskManager;

	@BeforeEach
	void setUp() {
		this.taskManager = new TaskManager();
	}

	@Test
	void testRemoveTaskSuccessfully() {
		// Arrange
		Task taskToRemove = new Task("Study", "Read chapter 5");
		this.taskManager.addTask(taskToRemove);

		// Act
		this.taskManager.removeTask(taskToRemove);

		// Assert
		assertFalse(this.taskManager.getAllTasks().contains(taskToRemove), "The task should be removed from the list.");
	}

	@Test
	void testRemoveTaskFromEmptyList() {
		// Arrange
		Task taskToRemove = new Task("Non existent Task", "This task does not exist");

		// Act & Assert
		assertDoesNotThrow(() -> this.taskManager.removeTask(taskToRemove),
				"Removing a task from an empty list should not throw an exception.");
		assertTrue(this.taskManager.getAllTasks().isEmpty(), "The task list should remain empty.");
	}

	@Test
	void testRemoveNullTaskThrowsException() {
		// Act & Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> this.taskManager.removeTask(null),
				"Removing a null task should throw an IllegalArgumentException.");
		assertEquals("Task cannot be null", exception.getMessage());
	}

	@Test
	void testRemoveTaskNotInList() {
		// Arrange
		Task existingTask = new Task("Walk", "Walk around the park");
		Task nonExistentTask = new Task("Swim", "Go swimming");
		this.taskManager.addTask(existingTask);

		// Act
		this.taskManager.removeTask(nonExistentTask);

		// Assert
		assertTrue(this.taskManager.getAllTasks().contains(existingTask),
				"The existing task should remain in the list.");
		assertFalse(this.taskManager.getAllTasks().contains(nonExistentTask),
				"The non-existent task should not be in the list.");
	}

	@Test
	void testRemoveTaskFromListWithMultipleItems() {
		// Arrange
		Task task1 = new Task("Task 1", "Description 1");
		Task task2 = new Task("Task 2", "Description 2");
		Task task3 = new Task("Task 3", "Description 3");
		this.taskManager.addTask(task1);
		this.taskManager.addTask(task2);
		this.taskManager.addTask(task3);

		// Act
		this.taskManager.removeTask(task2);

		// Assert
		assertTrue(this.taskManager.getAllTasks().contains(task1), "Task 1 should remain in the list.");
		assertFalse(this.taskManager.getAllTasks().contains(task2), "Task 2 should be removed from the list.");
		assertTrue(this.taskManager.getAllTasks().contains(task3), "Task 3 should remain in the list.");
	}
}
