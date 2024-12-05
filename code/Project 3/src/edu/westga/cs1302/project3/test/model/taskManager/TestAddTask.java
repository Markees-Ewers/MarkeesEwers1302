package edu.westga.cs1302.project3.test.model.taskManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class TestAddTask {

	private TaskManager taskManager;

	@BeforeEach
	void setup() {
		taskManager = new TaskManager();
	}

	@Test
	void testAddTaskValidTask() {
		// Setup
		Task task = new Task("Task 1", "Description 1");

		// Act
		taskManager.addTask(task);

		// Assert
		List<Task> tasks = taskManager.getAllTasks();
		assertEquals(1, tasks.size(), "Expected task list size to be 1 after adding a task");
		assertEquals(task, tasks.get(0), "Expected the task added to match the retrieved task");
	}

	@Test
	void testAddTaskMultipleTasks() {
		// Setup
		Task task1 = new Task("Task 1", "Description 1");
		Task task2 = new Task("Task 2", "Description 2");

		// Act
		taskManager.addTask(task1);
		taskManager.addTask(task2);

		// Assert
		List<Task> tasks = taskManager.getAllTasks();
		assertEquals(2, tasks.size(), "Expected task list size to be 2 after adding two tasks");
		assertEquals(task1, tasks.get(0), "Expected the first task to match the first added task");
		assertEquals(task2, tasks.get(1), "Expected the second task to match the second added task");
	}

	@Test
	void testAddTaskNullTask() {
		// Act & Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			taskManager.addTask(null);
		});
		assertEquals("Task cannot be null", exception.getMessage(), "Expected IllegalArgumentException for null task");
	}

	@Test
	void testAddTaskDoesNotModifyOriginalList() {
		// Setup
		Task task = new Task("Task 1", "Description 1");
		taskManager.addTask(task);

		// Act
		List<Task> tasksBefore = taskManager.getAllTasks();
		tasksBefore.add(new Task("Modified Task", "Modified Description"));

		// Assert
		List<Task> tasksAfter = taskManager.getAllTasks();
		assertEquals(1, tasksAfter.size(),
				"Expected original task list size to remain unchanged after external modification");
	}
}
