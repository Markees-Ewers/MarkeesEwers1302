package edu.westga.cs1302.project3.test.model.TaskFileHandler;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskFileHandler;



	public class TestLoadTaskFile {

		private static final String TEST_DIRECTORY = "dataLoader/";

		

		@Test
		void testLoadTaskFileValidFile() throws IOException {
			// Create a temporary file with valid task data
			File tempFile = new File(TEST_DIRECTORY + "testValidFile.txt");
			tempFile.getParentFile().mkdirs();

			try (FileWriter writer = new FileWriter(tempFile)) {
				writer.write("Task 1 - Description 1\n");
				writer.write("Task 2 - Description 2\n");
			}

			// Act
			List<Task> tasks = TaskFileHandler.loadTaskFile(tempFile);

			// Assert
			assertEquals(2, tasks.size(), "Expected 2 tasks to be loaded");
			assertEquals("Task 1", tasks.get(0).getName());
			assertEquals("Description 1", tasks.get(0).getDescription());
			assertEquals("Task 2", tasks.get(1).getName());
			assertEquals("Description 2", tasks.get(1).getDescription());
		}

		@Test
		void testLoadTaskFileEmptyFile() throws IOException {
			// Create an empty temporary file
			File tempFile = new File(TEST_DIRECTORY + "testEmptyFile.txt");
			tempFile.getParentFile().mkdirs();
			tempFile.createNewFile();

			// Act
			List<Task> tasks = TaskFileHandler.loadTaskFile(tempFile);

			// Assert
			assertTrue(tasks.isEmpty(), "Expected no tasks to be loaded from an empty file");
		}

		@Test
		void testLoadTaskFileInvalidFormat() throws IOException {
			// Create a temporary file with invalid task data
			File tempFile = new File(TEST_DIRECTORY + "testInvalidFormat.txt");
			tempFile.getParentFile().mkdirs();

			try (FileWriter writer = new FileWriter(tempFile)) {
				writer.write("Invalid Task Line\n");
			}

			// Act & Assert
			IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
				TaskFileHandler.loadTaskFile(tempFile);
			});

			assertTrue(exception.getMessage().contains("Invalid task fomat error on line"),
					"Expected an invalid format exception for incorrect task line");
		}

		@Test
		void testLoadTaskFileWithNullFile() throws IOException {
			// Act
			List<Task> tasks = TaskFileHandler.loadTaskFile(null);

			// Assert
			assertTrue(tasks.isEmpty(), "Expected no tasks to be loaded when file is null");
		}

		@Test
		void testLoadTaskFileWithExtraSpaces() throws IOException {
			// Create a temporary file with extra spaces in task lines
			File tempFile = new File(TEST_DIRECTORY + "testExtraSpaces.txt");
			tempFile.getParentFile().mkdirs();

			try (FileWriter writer = new FileWriter(tempFile)) {
				writer.write("  Task 1   -    Description 1   \n");
				writer.write("Task 2 - Description 2\n");
			}

			// Act
			List<Task> tasks = TaskFileHandler.loadTaskFile(tempFile);

			// Assert
			assertEquals(2, tasks.size(), "Expected 2 tasks to be loaded");
			assertEquals("  Task 1  ", tasks.get(0).getName(), "Expected task name to match");
			assertEquals("   Description 1   ", tasks.get(0).getDescription(), "Expected task description to match");
			assertEquals("Task 2", tasks.get(1).getName());
			assertEquals("Description 2", tasks.get(1).getDescription());
		}

		@Test
		void testLoadTaskFileWithMultipleValidLines() throws IOException {
			// Create a temporary file with multiple valid task lines
			File tempFile = new File(TEST_DIRECTORY + "testMultipleValidLines.txt");
			tempFile.getParentFile().mkdirs();

			try (FileWriter writer = new FileWriter(tempFile)) {
				writer.write("Task 1 - Description 1\n");
				writer.write("Task 2 - Description 2\n");
				writer.write("Task 3 - Description 3\n");
			}

			// Act
			List<Task> tasks = TaskFileHandler.loadTaskFile(tempFile);

			// Assert
			assertEquals(3, tasks.size(), "Expected 3 tasks to be loaded");
			assertEquals("Task 1", tasks.get(0).getName());
			assertEquals("Description 1", tasks.get(0).getDescription());
			assertEquals("Task 3", tasks.get(2).getName());
			assertEquals("Description 3", tasks.get(2).getDescription());
		}
	}

