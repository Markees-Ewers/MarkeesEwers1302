package edu.westga.cs1302.project2.test.model.RecipeUtillity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.UtilityRecipe;

public class TestUtilityRecipe {

	@Test
	public void testRecipeToString() {
		// Create an ArrayList of ingredients
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Flour", "Dry"));
		ingredients.add(new Ingredient("Sugar", "Dry"));
		ingredients.add(new Ingredient("Butter", "Wet"));
		ingredients.add(new Ingredient("Eggs", "Wet"));

		Recipe testRecipe = new Recipe("Chocolate Cake", ingredients);

		String expectedOutput = "Chocolate Cake" + System.lineSeparator() + "Flour-Dry, " + "Sugar-Dry, "
				+ "Butter-Wet, " + "Eggs-Wet, " + System.lineSeparator();

		String result = UtilityRecipe.recipeToString(testRecipe);

		assertEquals(expectedOutput, result, "The recipeToString method did not return the expected output.");
	}
}