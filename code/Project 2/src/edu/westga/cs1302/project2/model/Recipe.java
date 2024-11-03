package edu.westga.cs1302.project2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Recipe.
 * @author me00070
 * @version spring 2024
 */
public class Recipe {
	private String name;

	/** The Ingredients. */
	private ArrayList<Ingredient> ingredients;

	/**
	 * Instantiates a new recipe.
	 *@param ingredients to be added
	 * @param name the name
	 */
	public Recipe(String name, ArrayList<Ingredient> ingredients) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty or null");
		}
		this.name = name;
		this.ingredients = ingredients;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the ingredients.
	 *
	 * @return the ingredients
	 */
	public ArrayList<Ingredient> getIngredients() {
		return this.ingredients;
	}

	/**
	 * Sets the ingredients.
	 *
	 * @param ingredients the new ingredients
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Sets the list of ingredients. This replaces all current ingredients.
	 * 
	 * @param ingredients the list of ingredients to set
	 */
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = new ArrayList<>(ingredients);
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

}
