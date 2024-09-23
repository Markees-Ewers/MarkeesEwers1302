package edu.westga.cs1302.pantryproject1.model;

import java.util.ArrayList;

/**
 * The Class FoodInventory.
 * 
 * @author me00070
 * @version fall 2024
 */
public class FoodInventory {
	
	/** The food items. */
	private ArrayList<FoodItem> foodItems;

	/**
	 * Instantiates a new food inventory.
	 */
	public FoodInventory() {
		this.foodItems = new ArrayList<>();
	}

	/**
	 * Adds the food item.
	 *
	 * @param item the item
	 */
	public void addFoodItem(FoodItem item) {
		if (item == null) {
			throw new IllegalArgumentException("Food item cannot be null");
		}
		this.foodItems.add(item);
	}

	/**
	 * Removes the food item.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean removeFoodItem(String name) {
		return this.foodItems.removeIf(item -> item.getName().equalsIgnoreCase(name));
	}

	/**
	 * Find food item.
	 *
	 * @param name the name
	 * @return the food item
	 */
	public FoodItem findFoodItem(String name) {
		for (FoodItem item : this.foodItems) {
			if (item.getName().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Gets the item count.
	 *
	 * @return the item count
	 */
	public int getItemCount() {
		return this.foodItems.size();
	}
}
