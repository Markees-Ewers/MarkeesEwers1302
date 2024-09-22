package edu.westga.cs1302.pantryproject1.model;

public class FoodItem {
	public static final int STARTING_QUANTITY = 0; 
	
	private final String name;
	private final FoodType type;
	private int quantity;
	
	
	public FoodItem(String name,FoodType foodType ) {
		 if (name == null || name.isEmpty()) {
	            throw new IllegalArgumentException("Name cannot be null or empty");
	        }
	        if (foodType == null) {
	            throw new IllegalArgumentException("Food type cannot be null");
	        }
		this.name = name;
		this.type= foodType;
		this.quantity = FoodItem.STARTING_QUANTITY;
	}
	
	public String getName() {
		return name;
	}
	public FoodType getType() {
		return this.type;
	}
	public int getQuantity() {
		return quantity;
	}
	@Override
	public String toString() {
		return name + " - " + this.quantity;
	}

}
