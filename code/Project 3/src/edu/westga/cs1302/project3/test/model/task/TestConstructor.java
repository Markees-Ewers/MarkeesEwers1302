package edu.westga.cs1302.project3.test.model.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

class TestConstructor {

	

	@Test
	void testValidTaskCreation() {
		// Arrange
		String name = "Sample Task";
		String description = "This is a sample task.";

		// Act
		Task task = new Task(name, description);

		// Assert
		assertEquals(name, task.getName());
		assertEquals(description, task.getDescription());
	}

	@Test
	void testNullNameThrowsException() {
		// Arrange
		String name = null;
		String description = "This is a valid description.";

		// Act and Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Task(name, description));
		assertEquals("Name cannot be blank or null ", exception.getMessage());
	}

	@Test
	void testBlankNameThrowsException() {
		// Arrange
		String name = "   ";
		String description = "This is a valid description.";

		// Act and Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Task(name, description));
		assertEquals("Name cannot be blank or null ", exception.getMessage());
	}

	@Test
	void testNullDescriptionThrowsException() {
		// Arrange
		String name = "Valid Task";
		String description = null;

		// Act and Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Task(name, description));
		assertEquals("Description cannot be blank or null", exception.getMessage());
	}

	@Test
	void testBlankDescriptionThrowsException() {
		// Arrange
		String name = "Valid Task";
		String description = "   ";

		// Act and Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Task(name, description));
		assertEquals("Description cannot be blank or null", exception.getMessage());
	}

}
