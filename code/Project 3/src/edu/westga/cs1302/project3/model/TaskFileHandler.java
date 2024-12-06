package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Class TaskFileHandler.
 * 
 * @author me00070
 * @version spring 2024
 */
public class TaskFileHandler {
	/**
	 * Save task file.
	 *
	 * @param tasks        the tasks to be saved
	 * @param selectedFile the file location to write to
	 * @return the file location of the saved file
	 * @throws IOException              if an I/O exception occurs
	 * @throws IllegalStateException    if the file is null (likely because the file
	 *                                  chooser was closed)
	 * @throws IllegalArgumentException if tasks is null
	 */
	public static File saveTaskFile(TaskManager tasks, File selectedFile) throws IOException {
		if (tasks == null) {
			throw new IllegalArgumentException("Tasks cannot be null");
		}
		if (selectedFile == null) {
			throw new IllegalStateException("File could not be loaded: file was null");
		}

		// Ensure the file has a ".txt" extension
		if (!selectedFile.getName().endsWith(".txt")) {
			selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
		}

		// Write tasks to the file
		try (FileWriter writer = new FileWriter(selectedFile)) {
			for (Task curr : tasks.getAllTasks()) {
				writer.write(curr.getName() + " - " + curr.getDescription() + System.lineSeparator());
			}
		
		}

		return selectedFile;
	}

	/**
	 * Load task file.
	 *
	 * @return the list
	 * @param file to load
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Task> loadTaskFile(File file) throws IOException {

		ArrayList<Task> tasks = new ArrayList<>();
		if (file != null) {

			try (Scanner scnr = new Scanner(file)) {
				while (scnr.hasNextLine()) {
					String line = scnr.nextLine();
					String[] parts = line.split(" - ");

					if (parts.length == 2) {
						String name = parts[0];
						String description = parts[1];
						tasks.add(new Task(name, description));
					} else {
						throw new IllegalArgumentException(
								"Invalid File format error on line:    " + System.lineSeparator() + line);
					}
				}
			}

		}
		return tasks;
	}

}
