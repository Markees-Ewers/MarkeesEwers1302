package edu.westga.cs1302.pantryproject1.model;

/**
 * The Class FoodItem.
 * 
 * @version Fall 2024
 * @author me00070@my.westga.edu
 */
public class FoodItem {
	
	/** The Constant STARTING_QUANTITY. */
	public static final int STARTING_QUANTITY = 0; 
	
	/** The name. */
	private final String name;
	
	/** The type. */
	private final String type;
	
	/** The quantity. */
	private int quantity;

	/**
	 * Instantiates a new food item.
	 *
	 * @param name the name
	 * @param foodType the food type
	 */
	public FoodItem(String name, String foodType) {
		 if (name == null || name.isEmpty()) {
	            throw new IllegalArgumentException("Name cannot be null or empty");
	        }
	        if (foodType == null) {
	            throw new IllegalArgumentException("Food type cannot be null");
	        }
		this.name = name;
		this.type = foodType;
		this.quantity = FoodItem.STARTING_QUANTITY;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.name + " - " + this.quantity;
	}

}
