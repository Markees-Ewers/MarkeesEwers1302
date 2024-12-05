package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.stage.FileChooser;

// TODO: Auto-generated Javadoc
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
	 * 
	 * @return the file that the user selects
	 * @param showOpen if true the button shows a prompt to open otherwise it shows
	 *                 to save;
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static File taskFileChooser(Boolean showOpen) throws IOException {
		// can't test this because its user input but its handled by built in
		// filechooser
		FileChooser chooser = new FileChooser();
		if (showOpen) {
			chooser.setTitle("Open Task File");
			chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			return chooser.showOpenDialog(null);
		} else {
			chooser.setTitle("Save Task File");
			chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			return chooser.showSaveDialog(null);
		}
	}

	/**
	 * Save task file.
	 *
	 * @param tasks        the tasks
	 * @param selectedFile the fileLocation to write to
	 * @throws IOException           Signals that an I/O exception has occurred.
	 * @throws IllegalStateException if file is null likely because filechooser was
	 *                               closed
	 * @returns the file location of the saved file
	 */
	public static void saveTaskFile(TaskManager tasks, File selectedFile) throws IOException, IllegalStateException {
		if (tasks == null) {
			throw new IllegalArgumentException("tasks cannot be null");
		}
		if (selectedFile == null) {
			throw new IllegalStateException("File could not be loaded file was null");
		}
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
	}

	/**
	 * Load task file.
	 *
	 * @return the list
	 * @param file to load
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Task> loadTaskFile(File file) throws IOException {

		List<Task> tasks = new ArrayList<>();
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
						throw new IllegalArgumentException("Invalid task fomat error on line: " + line);
					}
				}
			}

		}
		return tasks;
	}

}
