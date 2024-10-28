package edu.westga.cs1302.project2.test.model.RecipeFileManager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileManager;

class RecipeFileManagerTest {
	private final File testFile = new File("data/recipes.txt");

	@AfterEach
	void tearDown() {
		
		if (testFile.exists()) {
			testFile.delete();
		}
	}

	@Test
	void testWriteRecipeWithValidRecipe() throws IOException {

		Ingredient ing1 = new Ingredient("Cake", "dessert");
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(ing1);
		Recipe recipe = new Recipe("Chocolate Cake", ingredients);

		RecipeFileManager.writeRecipe(recipe);

		assertTrue(testFile.exists(), "The File 'recipes.txt' IS be created by the writeRecipe method.");

		if (testFile.exists()) {
			String expectedRecipeNameLine = "Chocolate Cake" + System.lineSeparator();
			String expectedIngredientLine = "Cake, ";

			try (Scanner scnr = new Scanner(testFile)) {
			
				assertTrue(scnr.hasNextLine());
				assertEquals(expectedRecipeNameLine.trim(), scnr.nextLine().trim());

				
				assertTrue(scnr.hasNextLine());
				assertEquals(expectedIngredientLine.trim(), scnr.nextLine().trim());
			}
		}
	}

	@Test
	void testWriteMultiple() throws IOException {

		Ingredient ing1 = new Ingredient("Cake", "dessert");
		Ingredient ing2 = new Ingredient("Flour", "seasoning");
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(ing1);
		ingredients.add(ing2);
		Recipe recipe = new Recipe("Chocolate Cake", ingredients);

		RecipeFileManager.writeRecipe(recipe);

		assertTrue(testFile.exists(), "The file 'recipes.txt' should be created by the writeRecipe method.");

		if (testFile.exists()) {
			String expectedRecipeNameLine = "Chocolate Cake" + System.lineSeparator();
			String expectedIngredientLine = "Cake, Flour,";

			try (Scanner scnr = new Scanner(testFile)) {
				
				assertTrue(scnr.hasNextLine());
				assertEquals(expectedRecipeNameLine.trim(), scnr.nextLine().trim());

				
				assertTrue(scnr.hasNextLine());
				assertEquals(expectedIngredientLine.trim(), scnr.nextLine().trim());
			}
		}
	}

	@Test
	void testNoIngredients() throws IOException {

		ArrayList<Ingredient> ingredients = new ArrayList<>();

		Recipe recipe = new Recipe("Chocolate Cake", ingredients);

		RecipeFileManager.writeRecipe(recipe);

		assertTrue(testFile.exists(), "The file 'recipes.txt' should be created by the writeRecipe method.");

		if (testFile.exists()) {
			String expectedRecipeNameLine = "Chocolate Cake" + System.lineSeparator();

			try (Scanner scnr = new Scanner(testFile)) {
				
				assertTrue(scnr.hasNextLine());
				assertEquals(expectedRecipeNameLine.trim(), scnr.nextLine().trim());

				assertFalse(scnr.hasNextLine());

			}
		}
	}

	@Test
	void testRecipeWithNullNameThrowsException() {
		// Arrange
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Sugar", "sweetener"));

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> new Recipe(null, ingredients),
				" IllegalArgumentException for null recipe name");
	}

	@Test
	void testRecipeWithEmptyNameThrowsException() {
		
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Sugar", "sweetener"));

		
		assertThrows(IllegalArgumentException.class, () -> new Recipe("", ingredients),
				" IllegalArgumentException for empty recipe name");
	}

	@Test
	void testWriteRecipeWithManyIngredients() throws IOException {
		
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			ingredients.add(new Ingredient("Ingredient" + i, "Type" + i));
		}
		Recipe recipe = new Recipe("Bulk Recipe", ingredients);

		
		RecipeFileManager.writeRecipe(recipe);

	
		assertTrue(testFile.exists(), "The file 'recipes.txt' should be created by the writeRecipe method.");
	}

}
