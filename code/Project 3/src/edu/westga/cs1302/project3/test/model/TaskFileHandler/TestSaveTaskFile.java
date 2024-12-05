package edu.westga.cs1302.project3.test.model.TaskFileHandler;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskFileHandler;
import edu.westga.cs1302.project3.model.TaskManager;

class TestSaveTaskFile {

	private static final String TEST_DIRECTORY = "data/";

	

	@Test
	void testSaveWithNoTask() throws IOException {
		TaskManager taskManager = new TaskManager();
		File testFile = new File(TEST_DIRECTORY + "testSaveWithNoTask.txt");
		testFile.getParentFile().mkdirs();

			TaskFileHandler.saveTaskFile(taskManager, testFile);


		List<String> lines = Files.readAllLines(testFile.toPath());
		assertTrue(lines.isEmpty(), "Expected no lines in the output file for an empty TaskManager");
	}

	@Test
	void testSaveWithLongTaskNamesAndDescriptions() throws IOException {

		String longName = "Task".repeat(100); 
		String longDescription = "Description".repeat(100); 

		TaskManager taskManager = new TaskManager();
		taskManager.addTask(new Task(longName, longDescription));

		File testFile = new File(TEST_DIRECTORY + "testSaveWithLongTaskNamesAndDescriptions.txt");
		testFile.getParentFile().mkdirs();

		TaskFileHandler.saveTaskFile(taskManager, testFile);

		List<String> lines = Files.readAllLines(testFile.toPath());
		assertEquals(1, lines.size(), "Expected a single line in the output file");
		assertEquals(longName + " - " + longDescription, lines.get(0),
				"Expected the long task name and description to be saved correctly");
	}

	@Test
	void testSaveFileWithSingleTask() throws IOException {

		TaskManager taskManager = new TaskManager();
		taskManager.addTask(new Task("Single Task", "Single Task Description"));

		File testFile = new File(TEST_DIRECTORY + "testSaveFileWithSingleTask.txt");
		testFile.getParentFile().mkdirs();

		
		TaskFileHandler.saveTaskFile(taskManager, testFile);

		// Assert
		List<String> lines = Files.readAllLines(testFile.toPath());
		assertEquals(1, lines.size(), "Expected a single line in the output file");
		assertEquals("Single Task - Single Task Description", lines.get(0),
				"Expected the single task to be saved correctly");
	}

	@Test
	void testSaveTaskFileSuccess() throws IOException {
		
		TaskManager taskManager = new TaskManager();
		taskManager.addTask(new Task("Task 1", "Description 1"));
		taskManager.addTask(new Task("Task 2", "Description 2"));

		File testFile = new File(TEST_DIRECTORY + "testSaveTaskFileSuccess.txt");
		testFile.getParentFile().mkdirs();

		// Act
		TaskFileHandler.saveTaskFile(taskManager, testFile);

		// Assert
		List<String> lines = Files.readAllLines(testFile.toPath());
		assertEquals(2, lines.size(), "Expected two lines in the output file");
		assertEquals("Task 1 - Description 1", lines.get(0));
		assertEquals("Task 2 - Description 2", lines.get(1));
	}
	@Test
	void testSaveTaskFileWithNullFile() {
	    // Setup
	    TaskManager taskManager = new TaskManager();
	    taskManager.addTask(new Task("Task 1", "Description 1"));

	    // Act & Assert
	    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
	        TaskFileHandler.saveTaskFile(taskManager, null);
	    });

	    assertEquals("File could not be loaded file was null", exception.getMessage(), 
	                 "Expected IllegalStateException with a specific error message when file is null");
	}
}
