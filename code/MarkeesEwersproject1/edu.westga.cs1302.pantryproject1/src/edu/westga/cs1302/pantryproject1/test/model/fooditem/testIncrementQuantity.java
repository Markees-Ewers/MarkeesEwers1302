package edu.westga.cs1302.pantryproject1.test.model.fooditem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantryproject1.model.FoodItem;

class testIncrementQuantity {

	  private FoodItem item;

	    @BeforeEach
	    void setUp() {
	        this.item = new FoodItem("Apple", "Fruit");
	    }

	    @Test
	    void testIncrementQuantityFromStartingValue() {
	        assertEquals(FoodItem.STARTING_QUANTITY, this.item.getQuantity()); // Verify initial quantity
	        this.item.incrementQuantity();
	        assertEquals(1, this.item.getQuantity()); // Verify increment from 0 to 1
	    }

	    @Test
	    void testIncrementQuantityMultipleTimes() {
	        assertEquals(FoodItem.STARTING_QUANTITY, this.item.getQuantity()); // Verify initial quantity
	        this.item.incrementQuantity();
	        this.item.incrementQuantity();
	        this.item.incrementQuantity();
	        assertEquals(3, this.item.getQuantity()); // Verify quantity after multiple increments
	    }

	    @Test
	    void testIncrementQuantityAfterSettingValue() {
	        this.item.setQuantity(10);
	        this.item.incrementQuantity();
	        assertEquals(11, this.item.getQuantity()); // Verify increment from 10 to 11
	    }
	}
