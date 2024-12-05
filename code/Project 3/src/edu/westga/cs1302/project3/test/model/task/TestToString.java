package edu.westga.cs1302.project3.test.model.task;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

class TestToString {

	@Test
	void testToStringWithValidNameAndDescription() {
		// Arrange
		String name = "Task 1";
		String description = "Description of Task 1";
		Task task = new Task(name, description);

		// Act
		String result = task.toString();

		// Assert
		assertEquals("Task 1:   Description of Task 1", result);
	}

	@Test
	void testToStringWithEmptyDescription() {
		// Arrange
		String name = "Task 2";
		String description = "";

		// Act

		// Assert
		assertThrows(IllegalArgumentException.class, () -> new Task(name, description));

	}

	@Test
	void testToStringWithSpecialCharactersInNameAndDescription() {
		// Arrange
		String name = "Task@#1";
		String description = "This & that!";
		Task task = new Task(name, description);

		// Act
		String result = task.toString();

		// Assert
		assertEquals("Task@#1:   This & that!", result);
	}

	@Test
	void testToStringWithLongNameAndDescription() {
		// Arrange
		String name = "This is a very long task name to test the functionality of toString";
		String description = "This is a similarly long description that should still work correctly.";
		Task task = new Task(name, description);

		// Act
		String result = task.toString();

		// Assert
		assertEquals(
				"This is a very long task name to test the functionality of toString:   This is a similarly long description that should still work correctly.",
				result);
	}
}
