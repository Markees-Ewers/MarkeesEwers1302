package edu.westga.cs1302.pantryproject1.test.model.fooditem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantryproject1.model.FoodItem;

class testConstructor {

	@Test
	void testConstructorValidInputs() {
		FoodItem testItem = new FoodItem("Banana", "Fruit");
		assertEquals("Banana", testItem.getName());
		assertEquals("Fruit", testItem.getType());
		assertEquals(FoodItem.STARTING_QUANTITY, testItem.getQuantity());
	}

	@Test
	void testConstructorNullNameThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new FoodItem(null, "Fruit");
		});
		assertEquals("Name cannot be null or empty", exception.getMessage());
	}

	@Test
	void testConstructorEmptyNameThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new FoodItem("", "Fruit");
		});
		assertEquals("Name cannot be null or empty", exception.getMessage());
	}

	@Test
	void testConstructorNullTypeThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new FoodItem("Apple", null);
		});
		assertEquals("Food type cannot be null", exception.getMessage());
	}
}