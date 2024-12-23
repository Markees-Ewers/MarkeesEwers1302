package edu.westga.cs1302.project2.model;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * the class for loading recipes to a file.
 *
 * @author me00070
 * @version fall 2024
 */
public class RecipeFileLoader {

	/**
	 * Finds a recipe with the specified ingredient within a file and returns a list
	 * of recipes with the target ingredient.
	 *
	 * @param targetIngredient the target ingredient
	 * @return the list of recipes containing the target ingredient
	 */
	public static List<Recipe> recipesWithIngredient(Ingredient targetIngredient) {
		List<Recipe> recipes = new ArrayList<>();
		File file = new File("data/recipes.txt");

		if (!file.exists() || file.length() == 0) {
			return recipes;
		}

		try (Scanner scnr = new Scanner(file)) {
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine().trim();
				String recipeName = null;
				boolean isTarget = false;

				if (!line.contains("-")) {
					recipeName = line;
					line = scnr.nextLine();
				}
				if (line.contains(",")) {
					ArrayList<Ingredient> fullIng = new ArrayList<>();
					String[] ingredients = line.split(", ");
					for (int is = 0; is < ingredients.length; is++) {
						String[] parts = ingredients[is].split("-");
						String ingName = parts[0].trim();
						String ingType = parts[1].trim();
						if (ingName.trim().equals(targetIngredient.getName().trim())
								&& ingType.trim().equals(targetIngredient.getType().trim())) {
							isTarget = true;
						}

						Ingredient ing = new Ingredient(ingName, ingType);
						fullIng.add(ing);
					}
					if (isTarget) {
						Recipe recipe = new Recipe(recipeName, fullIng);
						recipes.add(recipe);
					}
				}

			}
		} catch (FileNotFoundException ex) {
			return recipes;
		}
		return recipes;

	}

	/**
	 * Load recipes.
	 *
	 * @return the list
	 */
	public static List<Recipe> loadRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		File file = new File("data/recipes.txt");
		// Check if the file exists and is not empty
		if (!file.exists() || file.length() == 0) {
			return recipes;
		}
		try (Scanner scnr = new Scanner(file)) {
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine().trim();
				String recipeName = null;
				if (!line.contains("-")) {
					recipeName = line;
					line = scnr.nextLine();
				}
				if (line.contains(",")) {
					ArrayList<Ingredient> fullIng = new ArrayList<>();
					String[] ingredients = line.split(", ");
					for (int is = 0; is < ingredients.length; is++) {
						String[] parts = ingredients[is].split("-");
						String ingName = parts[0].trim();

						String ingType = parts[1].trim();
						Ingredient ing = new Ingredient(ingName, ingType);
						fullIng.add(ing);
					}
					Recipe recipe = new Recipe(recipeName, fullIng);
					recipes.add(recipe);

				}
			}
		} catch (FileNotFoundException ex) {
			return recipes;
		}
		return recipes;
	}
}
