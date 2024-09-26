package edu.westga.cs1302.pantryproject1.model;

import java.util.List;

/**
 * The Class PantryUtility.
 * 
 * @author me00070
 * @version fall 2024
 */
public class PantryUtility {

	/**
	 * Gets the total quantity.
	 *
	 * @param pantry the pantry
	 * @return the total quantity
	 */
	public static int getTotalQuantity(List<FoodItem> pantry) {
		int totalQuantity = 0;

		for (FoodItem item : pantry) {
			totalQuantity += item.getQuantity();
		}

		return totalQuantity;
	}
}