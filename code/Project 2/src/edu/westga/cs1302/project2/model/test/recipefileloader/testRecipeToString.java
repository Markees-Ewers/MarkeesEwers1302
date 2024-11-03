package edu.westga.cs1302.project2.model.test.recipefileloader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileLoader;
import edu.westga.cs1302.project2.model.RecipeFileManager;

public class testRecipeToString {
	private final File testFile = new File("data/recipes.txt");

	@Test
	public void testFindsOneRecipe() throws IOException {
		// Arrange
		Ingredient target = new Ingredient("Flour", "Batter"); // Adjusted to match your format
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(target); // Assuming you want to add the ingredient "Flour" as the target
		Recipe recipe = new Recipe("Chocolate Cake", ingredients);

		// Write the recipe to a file
		RecipeFileManager.writeRecipe(recipe); // Ensure you pass the testFile here

		// Debugging output
		System.out.println("Writing recipe: " + recipe.getName());
		try (Scanner scnr = new Scanner(this.testFile)) {
			System.out.println(scnr.nextLine());
			while (scnr.hasNextLine()) {
				System.out.println("printing fille ingfo");
				String line = scnr.nextLine();

				System.out.println(line);

			}
		}
		// Act
		List<Recipe> expected = RecipeFileLoader.recipesWithIngredient(target);

		// Debugging output
		System.out.println("Expected recipes count: " + expected.size());
		for (Recipe r : expected) {
			System.out.println("Recipe found: " + r.getName());
		}

		// Assert
		assertEquals(1, expected.size(), "Expected one recipe to be found.");
		assertEquals(recipe.getName(), expected.get(0).getName(), "Recipe names should match.");
		assertEquals(1, expected.get(0).getIngredients().size(), "Ingredient count should match.");
		assertEquals(target.getName(), expected.get(0).getIngredients().get(0).getName(),
				"Ingredient names should match.");
		assertEquals(target.getType(), expected.get(0).getIngredients().get(0).getType(),
				"Ingredient types should match.");
	}

	@Test
	public void testFindsOneMatchWithMultipleRecipesButOneMatchExist() throws IOException {
		// Arrange
		Ingredient target = new Ingredient("Flour", "Batter"); // Adjusted to match your format
		ArrayList<Ingredient> targetIng = new ArrayList<>();
		Recipe targetRec = new Recipe("Target", targetIng);
		targetIng.add(target); // Assuming you want to add the ingredient "Flour" as the target

		Ingredient notTarget = new Ingredient("Cake", "atter"); // Adjusted to match your format
		ArrayList<Ingredient> notMatching = new ArrayList<>();
		notMatching.add(notTarget);
		Recipe noMatch = new Recipe("noMatch", notMatching);

		RecipeFileManager.writeRecipe(targetRec);
		RecipeFileManager.appendRecipe(noMatch);

		// Act
		List<Recipe> expected = RecipeFileLoader.recipesWithIngredient(target);

		// Assert
		assertEquals(1, expected.size(), "Expected one recipe to be found.");
		assertEquals(targetRec.getName(), expected.get(0).getName());
		assertEquals(targetRec.getIngredients().get(0).toString(), expected.get(0).getIngredients().get(0).toString());
	}
}