package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.stage.FileChooser;

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
	 * @param tasks the tasks
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void saveTaskFile(TaskManager tasks) throws IOException {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save Task");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = chooser.showSaveDialog(null);
		if (selectedFile != null) {

			if (!selectedFile.getName().endsWith(".txt")) {
				selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
			}

			try (FileWriter writer = new FileWriter(selectedFile)) {
				for (Task curr : tasks.getAllTasks()) {
					writer.write(curr.getName() + " - " + curr.getDescription() + System.lineSeparator());
				}
				System.out.println("Tasks successfully saved to " + selectedFile.getAbsolutePath());
			} catch (IOException ex) {
				System.err.println("Error saving tasks: " + ex.getMessage());
			}
		} else {
			System.out.println("Save operation canceled.");
		}
	}

	/**
	 * Load task file.
	 *
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Task> loadTaskFile() throws IOException {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Task File");
		File selectedFile = chooser.showOpenDialog(null);
		List<Task> tasks = new ArrayList<>();
		if (selectedFile != null) {

			try (Scanner scnr = new Scanner(selectedFile)) {
				while (scnr.hasNextLine()) {
					String line = scnr.nextLine();
					String[] parts = line.split(" - ");

					if (parts.length == 2) {
						String name = parts[0];
						String description = parts[1];
						tasks.add(new Task(name, description));
					} else {
						throw new IllegalArgumentException("Invalid task fomat error on line: " + line);
					}
				}
			}

		}
		return tasks;
	}

}
