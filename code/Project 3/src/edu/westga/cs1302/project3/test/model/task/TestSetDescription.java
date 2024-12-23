package edu.westga.cs1302.project3.test.model.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

class TestSetDescription {

	@Test
	void testSetDescriptionWithValidDescription() {

		Task task = new Task("Initial Name", "Initial Description");
		String newDescription = "Updated Description";

		task.setDescription(newDescription);

		assertEquals(newDescription, task.getDescription());
	}

	@Test
	void testSetDescriptionWithNullThrowsException() {

		Task task = new Task("Initial Name", "Initial Description");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> task.setDescription(null));
		assertEquals("Description cannot be blank or null", exception.getMessage());
	}

	@Test
	void testSetDescriptionWithBlankThrowsException() {

		Task task = new Task("Initial Name", "Initial Description");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> task.setDescription("   "));
		assertEquals("Description cannot be blank or null", exception.getMessage());
	}@Test
	void testCannotSetNameWithDash() {
		Task task = new Task("Initial Name", "Initial Description");

		String name = "name contains -";

		// Act and Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> task.setName(name));
		assertEquals("name cannot contain: -", exception.getMessage());
	}

	@Test
	void testCannotSetDescriptionWithDash() {
		Task task = new Task("Initial Name", "Initial Description");

		String description = "description contains -";

		// Act and Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> task.setDescription(description));
		assertEquals("description cannot contain: -", exception.getMessage());
	}

	
}
