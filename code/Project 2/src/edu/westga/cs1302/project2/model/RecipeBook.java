package edu.westga.cs1302.project2.model;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class RecipeBook.
 * @author me00070
 * @version fall 2024
 */
public class RecipeBook  {
	
	/** The recipe book. */
	private ArrayList<Recipe> recipeBook;
	
	/**
	 * Instantiates a new recipe book.
	 */
	public RecipeBook() {
		this.recipeBook = new ArrayList<Recipe>();
		
	}

	/**
	 * Gets the recipe book.
	 *
	 * @return the recipe book
	 */
	public ArrayList<Recipe> getRecipeBook() {
		return this.recipeBook;
	}

	/**
	 * Sets the recipe book.
	 *
	 * @param recipeBook the new recipe book
	 */
	public void setRecipeBook(ArrayList<Recipe> recipeBook) {
		this.recipeBook = recipeBook;
	}
	

}
