package edu.westga.cs1302.project2.model.test.recipefileloader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileLoader;
import edu.westga.cs1302.project2.model.RecipeFileManager;

public class testRecipeToString {
	private final File testFile = new File("data/Recipes.txt");

	@BeforeEach
	void setUp() throws IOException {

		testFile.getParentFile().mkdirs();

		if (!testFile.exists()) {
			testFile.createNewFile();
		}
	}

	@Test
	public void testFindsOneRecipe() throws IOException {

		Ingredient target = new Ingredient("Flour", "Batter");
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(target);
		Recipe recipe = new Recipe("Chocolate Cake", ingredients);

		RecipeFileManager.writeRecipe(recipe);

		

		List<Recipe> expected = RecipeFileLoader.recipesWithIngredient(target);

		System.out.println("Expected recipes count: " + expected.size());
		for (Recipe r : expected) {
			System.out.println("Recipe found: " + r.getName());
		}

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

		Ingredient target = new Ingredient("Flour", "Batter");
		ArrayList<Ingredient> targetIng = new ArrayList<>();
		Recipe targetRec = new Recipe("Target", targetIng);
		targetIng.add(target);

		Ingredient notTarget = new Ingredient("Cake", "atter");
		ArrayList<Ingredient> notMatching = new ArrayList<>();
		notMatching.add(notTarget);
		Recipe noMatch = new Recipe("noMatch", notMatching);

		RecipeFileManager.writeRecipe(targetRec);
		RecipeFileManager.appendRecipe(noMatch);

		List<Recipe> expected = RecipeFileLoader.recipesWithIngredient(target);

		assertEquals(1, expected.size(), "Expected one recipe to be found.");
		assertEquals(targetRec.getName(), expected.get(0).getName());
		assertEquals(targetRec.getIngredients().get(0).toString(), expected.get(0).getIngredients().get(0).toString());
	}
}