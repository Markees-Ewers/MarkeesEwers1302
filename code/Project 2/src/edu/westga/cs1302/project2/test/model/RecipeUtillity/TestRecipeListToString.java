package edu.westga.cs1302.project2.test.model.RecipeUtillity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.UtilityRecipe;

public class TestRecipeListToString {
	@Test
	public void testRecipeListToStringWithMultipleRecipes() {

		List<Recipe> recipeList = new ArrayList<>();

		ArrayList<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(new Ingredient("Flour", "Dry"));
		ingredients1.add(new Ingredient("Sugar", "Dry"));
		recipeList.add(new Recipe("Chocolate Cake", ingredients1));

		ArrayList<Ingredient> ingredients2 = new ArrayList<>();
		ingredients2.add(new Ingredient("Tomato", "Vegetable"));
		ingredients2.add(new Ingredient("Cheese", "Dairy"));
		recipeList.add(new Recipe("Pizza", ingredients2));

		ArrayList<Ingredient> ingredients3 = new ArrayList<>();
		ingredients3.add(new Ingredient("Rice", "Grain"));
		ingredients3.add(new Ingredient("Chicken", "Protein"));
		recipeList.add(new Recipe("Chicken Rice", ingredients3));

		String expectedOutput = "Chocolate Cake" + System.lineSeparator() + System.lineSeparator() + "Pizza"
				+ System.lineSeparator() + System.lineSeparator() + "Chicken Rice" + System.lineSeparator()
				+ System.lineSeparator();

		String result = UtilityRecipe.recipeListToString(recipeList);

		assertEquals(expectedOutput, result, "The recipeListToString method did not return the expected output.");
	}

	@Test
	public void testRecipeListToStringWithEmptyList() {

		List<Recipe> emptyRecipeList = new ArrayList<>();

		String expectedOutput = "";

		String result = UtilityRecipe.recipeListToString(emptyRecipeList);

		assertEquals(expectedOutput, result, "The recipeListToString method did not handle an empty list correctly.");
	}

	@Test
	public void testRecipeListToStringWithSingleRecipe() {

		List<Recipe> singleRecipeList = new ArrayList<>();

		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Eggs", "Wet"));
		ingredients.add(new Ingredient("Flour", "Dry"));
		singleRecipeList.add(new Recipe("Pancakes", ingredients));

		String expectedOutput = "Pancakes" + System.lineSeparator() + System.lineSeparator();

		String result = UtilityRecipe.recipeListToString(singleRecipeList);

		assertEquals(expectedOutput, result, "The recipeListToString method did not handle a single recipe correctly.");
	}
}
