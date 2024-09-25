package edu.westga.cs1302.pantryproject1.test.model.fooditem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantryproject1.model.FoodItem;

class testDecrementQuantity {

	private FoodItem item;

	@BeforeEach
	void setUp() {
		this.item = new FoodItem("Apple", "Fruit");
	}

	@Test
	void testDecrementQuantityFromStartingValue() {
		assertEquals(FoodItem.STARTING_QUANTITY, this.item.getQuantity()); // Verify initial quantity
		this.item.decrementQuantity();
		assertEquals(-1, this.item.getQuantity());
	}

	@Test
	void testDecrementQuantityAfterIncrement() {
		this.item.incrementQuantity();
		assertEquals(1, this.item.getQuantity());

		this.item.decrementQuantity();
		assertEquals(0, this.item.getQuantity());
	}

	@Test
	void testDecrementQuantityMultipleTimes() {
		this.item.setQuantity(3);
		this.item.decrementQuantity();
		this.item.decrementQuantity(); 
		this.item.decrementQuantity(); 
		assertEquals(0, this.item.getQuantity());
	}

	@Test
	void testDecrementQuantityBelowZero() {
		this.item.setQuantity(1);
		this.item.decrementQuantity(); 
		this.item.decrementQuantity(); 
		this.item.decrementQuantity();
		assertEquals(-2, this.item.getQuantity()); 
	}
}