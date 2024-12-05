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
	void testShouldNotAllowDashDescription() {
		// Arrange
		String name = "valid name";
		String description = "This Description contains -";

		// Act and Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Task(name, description));
		assertEquals("description cannot contain: -", exception.getMessage());
	}
	@Test
	void testShouldNotAllowDashName() {
		// Arrange
		String name = "name contains -";
		String description = "valid description";

		// Act and Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Task(name, description));
		assertEquals("name cannot contain: -", exception.getMessage());
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
		assertThrows(NullPointerException.class,
				() -> new Task(name, description));
		
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
