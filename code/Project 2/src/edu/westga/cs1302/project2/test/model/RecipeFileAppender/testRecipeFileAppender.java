package edu.westga.cs1302.project2.test.model.RecipeFileAppender;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;

import edu.westga.cs1302.project2.model.RecipeFileManager;

class testRecipeFileAppender {
	private final File testFile = new File("data/Recipes.txt");

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
		assertThrows(IllegalStateException.class, () -> RecipeFileManager.appendRecipe(recipe),
				"Expected an IllegalStateException when appending a duplicate recipe.");
	}

	@Test
	void testAppendNewRecipe() throws IOException {

		Ingredient ing1 = new Ingredient("Cake", "dessert");
		Ingredient ing2 = new Ingredient("fire", "ice");
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ArrayList<Ingredient> newIng = new ArrayList<>();
		ingredients.add(ing1);
		Recipe recipe = new Recipe("Chocolate Cake", ingredients);
		newIng.add(ing2);
		Recipe newRecipe = new Recipe("append", newIng);

		RecipeFileManager.writeRecipe(recipe);
		RecipeFileManager.appendRecipe(newRecipe);
		try (Scanner scnr = new Scanner(this.testFile)) {
			assertTrue(scnr.hasNext());
			String line = scnr.nextLine();
			assertEquals(line, "Chocolate Cake", "Name line should be equal to chocalate cake");
			line = scnr.nextLine();
			assertEquals("Cake-dessert, ", line);
			line = scnr.nextLine();
			assertEquals("append", line);
			line = scnr.nextLine();
			assertEquals("fire-ice, ", line);
		}
	}
}
