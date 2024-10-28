package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Class RecipeFileManager.
 * 
 * @author me00070
 * @version fall 2024
 */
public class RecipeFileManager {
	/**
	 * Write recipe.
	 *
	 * @param recipe the recipe
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeRecipe(Recipe recipe) throws IOException {
		File file = new File("data/recipes.txt");
		try (FileWriter writer = new FileWriter(file)) {

			writer.write(recipe.getName() + System.lineSeparator());
			for (Ingredient curr : recipe.getIngredients()) {
				writer.write(curr.getName() + ", ");
			}

		}
	}

}
