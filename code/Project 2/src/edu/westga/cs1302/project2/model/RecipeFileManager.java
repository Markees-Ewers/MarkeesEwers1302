package edu.westga.cs1302.project2.model;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * The Class RecipeFileManager.
 * 
 * @author me00070
 * @version fall 2024
 */
public class RecipeFileManager {
	private static final String FILE_PATH = "data/recipes.txt";

	/**
	 * creates and writes a recipe to a new file
	 * 
	 * if file already exist it will be overwritten with the new recipe
	 *
	 * @param recipe the recipe
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeRecipe(Recipe recipe) throws IOException {
		File file = new File(FILE_PATH);
		try (FileWriter writer = new FileWriter(file)) {

			writer.write(UtilityRecipe.recipeToString(recipe));

		}
	}

	/**
	 * Appends a recipe to the end of the file if it does not already exist.
	 *
	 * @param recipe the recipe to append
	 * @throws IOException           if there is an I/O error or the file cannot be
	 *                               created
	 * @throws IllegalStateException if a recipe with the same name already exists
	 */
	public static void appendRecipe(Recipe recipe) throws IOException {
		File file = new File(FILE_PATH);
		System.out.println("file - " + file.exists());
		String name = recipe.getName();
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}

		try (Scanner scnr = new Scanner(file)) {
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine();

				if (!line.contains(",")) {
					if (line.trim().equals(name)) {
						throw new IllegalStateException();
					}

				}
			}
			try (FileWriter writer = new FileWriter(file, true)) {
				writer.write(UtilityRecipe.recipeToString(recipe));
			}
		}
	}

}
