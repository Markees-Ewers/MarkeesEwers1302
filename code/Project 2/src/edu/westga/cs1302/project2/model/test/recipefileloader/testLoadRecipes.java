package edu.westga.cs1302.project2.model.test.recipefileloader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileLoader;
import edu.westga.cs1302.project2.model.RecipeFileManager;

public class testLoadRecipes {
	private File testFile = new File("data/recipes.txt");

	@AfterEach
	void tearDown() {

		if (testFile.exists()) {
			testFile.delete();
		}
	}

	@Test
	public void testFindsTwoRecipe() throws IOException {
		// Arrange
		Ingredient target = new Ingredient("Flour", "Batter"); // Adjusted to match your format
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(target); // Assuming you want to add the ingredient "Flour" as the target
		Recipe recipe = new Recipe("Chocolate Cake", ingredients);
		Recipe recipe2 = new Recipe("IceCream", ingredients);

		// Write the recipe to a file
		RecipeFileManager.writeRecipe(recipe); // Ensure you pass the testFile here
		RecipeFileManager.appendRecipe(recipe2);
		List<Recipe> expected = RecipeFileLoader.loadRecipes();
		assertEquals(expected.size(), 2);
	}

	@Test
	public void testFindsOneRecipe() throws IOException {
		// Arrange
		Ingredient target = new Ingredient("Flour", "Batter"); // Adjusted to match your format
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(target); // Assuming you want to add the ingredient "Flour" as the target
		Recipe recipe = new Recipe("Chocolate Cake", ingredients);

		// Write the recipe to a file
		RecipeFileManager.writeRecipe(recipe); // Ensure you pass the testFile here

		List<Recipe> expected = RecipeFileLoader.loadRecipes();
		assertEquals(expected.size(), 1);
	}

	@Test
	public void testFindsThreeRecipe() throws IOException {
		// Arrange
		Ingredient target = new Ingredient("Flour", "Batter"); // Adjusted to match your format
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(target); // Assuming you want to add the ingredient "Flour" as the target
		Recipe recipe = new Recipe("Chocolate Cake", ingredients);
		Recipe recipe2 = new Recipe("IceCream", ingredients);
		Recipe recipe3 = new Recipe("fislh", ingredients);

		// Write the recipe to a file
		RecipeFileManager.writeRecipe(recipe); // Ensure you pass the testFile here
		RecipeFileManager.appendRecipe(recipe2);
		RecipeFileManager.appendRecipe(recipe3);
		List<Recipe> expected = RecipeFileLoader.loadRecipes();
		assertEquals(expected.size(), 3);
	}

	@Test
	public void testFindsNoRecipe() throws IOException {

		List<Recipe> expected = RecipeFileLoader.loadRecipes();
		assertEquals(expected.size(), 0);
	}

}
