package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * appends file to the recipe file
 * 
 * @author me00070
 * @version fall 2024
 */
public class RecipeFileAppender {
	private static final String FILE_PATH = "data/recipes.txt";

	/**
	 * Appends a recipe to the end of the file if it does not already exist.
	 *
	 * @param recipe the recipe to append
	 * @throws IOException           if there is an I/O error or the file cannot be
	 *                               created
	 * @throws IllegalStateException if a recipe with the same name already exists
	 */
	public static void appendRecipe(Recipe recipe) throws IOException {
		File file = new File(RecipeFileAppender.FILE_PATH);
		String name = recipe.getName();
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
	            writer.write(System.lineSeparator() + recipe.getName() + System.lineSeparator());
	            for (Ingredient ingredient : recipe.getIngredients()) {
	                writer.write(ingredient.getName() + ", ");
	            }
	        }
		}
	}
}
