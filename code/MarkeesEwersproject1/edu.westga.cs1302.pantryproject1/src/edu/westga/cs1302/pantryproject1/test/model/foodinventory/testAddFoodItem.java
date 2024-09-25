package edu.westga.cs1302.pantryproject1.test.model.foodinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantryproject1.model.FoodInventory;
import edu.westga.cs1302.pantryproject1.model.FoodItem;

class testAddFoodItem {

	private FoodInventory inventory;

	@BeforeEach
	void setUp() {
		this.inventory = new FoodInventory();
	}

	@Test
	void testAddFoodItemValidDoesNotThrowException() {
		FoodItem apple = new FoodItem("Apple", "Fruit");
		assertDoesNotThrow(() -> this.inventory.addFoodItem(apple)); 
	}

	@Test
	void testAddFoodItemNullThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			this.inventory.addFoodItem(null);
		});
		assertEquals("Food item cannot be null", exception.getMessage());
	}
}