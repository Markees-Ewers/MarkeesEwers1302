package edu.westga.cs1302.project2.model;

import java.util.List;

/**
 * The Class UtilityRecipe.
 * 
 * @author me00070
 * @version fall 2024
 * 
 */
public class UtilityRecipe {

	/**
	 * Recipe to string.
	 *
	 * @param recipe the recipe
	 * @return the string
	 */
	public static String recipeToString(Recipe recipe) {
		String recipeString = "";

		recipeString += recipe.getName() + System.lineSeparator();

		for (Ingredient curr : recipe.getIngredients()) {
			recipeString += curr.getName() + "-" + curr.getType() + ", ";

		}
		return recipeString + System.lineSeparator();
	}

	/**
	 * creates a string version of every recipe within a list seprated by a line.
	 *
	 * @param recipeList the recipe list
	 * @return the string
	 */
	public static String recipeListToString(List<Recipe> recipeList) {
		StringBuilder list = new StringBuilder();
		for (Recipe curr : recipeList) {
			list.append(curr.getName() + System.lineSeparator() + System.lineSeparator());
		}
		return list.toString();

	}
}
