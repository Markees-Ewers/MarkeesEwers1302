package edu.westga.cs1302.project2.test.model.RecipeFileAppender;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileAppender;
import edu.westga.cs1302.project2.model.RecipeFileManager;

class testRecipeFileAppender {
	private final File testFile = new File("data/recipes.txt");

	@AfterEach
	void tearDown() {

		if (testFile.exists()) {
			testFile.delete();
		}
	}

	@Test
	void testAppendSameRecipe() throws IOException {

		Ingredient ing1 = new Ingredient("Cake", "dessert");

		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(ing1);
		Recipe recipe = new Recipe("Chocolate Cake", ingredients);
		
		RecipeFileManager.writeRecipe(recipe);
		assertThrows(IllegalStateException.class, () -> RecipeFileAppender.appendRecipe(recipe),
				"Expected an IllegalStateException when appending a duplicate recipe.");
	}
	@Test
	void testAppendNewRecipe() throws IOException {

		Ingredient ing1 = new Ingredient("Cake", "dessert");

		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(ing1);
		Recipe recipe = new Recipe("Chocolate Cake", ingredients);
		
		RecipeFileManager.writeRecipe(recipe);
		assertThrows(IllegalStateException.class, () -> RecipeFileAppender.appendRecipe(recipe),
				"Expected an IllegalStateException when appending a duplicate recipe.");
	}
}
