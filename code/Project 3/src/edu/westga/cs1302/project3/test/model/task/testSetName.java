package edu.westga.cs1302.project3.test.model.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

class testSetName {

	 @Test
	    void testSetDescriptionWithValidDescription() {
	        // Arrange
	        Task task = new Task("Initial Name", "Initial Description");
	        String newDescription = "Updated Description";

	        // Act
	        task.setDescription(newDescription);

	        // Assert
	        assertEquals(newDescription, task.getDescription());
	    }

	    @Test
	    void testSetDescriptionWithNullThrowsException() {
	        // Arrange
	        Task task = new Task("Initial Name", "Initial Description");

	        // Act and Assert
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setDescription(null));
	        assertEquals("Description cannot be blank or null", exception.getMessage());
	    }

	    @Test
	    void testSetDescriptionWithBlankThrowsException() {
	        // Arrange
	        Task task = new Task("Initial Name", "Initial Description");

	        // Act and Assert
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> task.setDescription("   "));
	        assertEquals("Description cannot be blank or null", exception.getMessage());
	    }

}
