package edu.westga.cs1302.pantryproject1.test.model.foodinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantryproject1.model.FoodInventory;
import edu.westga.cs1302.pantryproject1.model.FoodItem;

class testFindFoodItem {

	 private FoodInventory inventory;

	    @BeforeEach
	    void setUp() {
	        this.inventory = new FoodInventory();
	        this.inventory.addFoodItem(new FoodItem("Apple", "Fruit"));
	        this.inventory.addFoodItem(new FoodItem("Banana", "Fruit"));
	    }

	    @Test
	    void testFindFoodItemValid() {
	        FoodItem item = this.inventory.findFoodItem("Apple");
	        assertNotNull(item); 
	        assertEquals("Apple", item.getName()); 
	        assertEquals("Fruit", item.getType()); 
	    }

	    @Test
	    void testFindFoodItemNotInList() {
	        FoodItem item = this.inventory.findFoodItem("Orange");
	        assertNull(item); 
	    }

	    @Test
	    void testFindFoodItemCaseInsensitive() {
	        FoodItem item = this.inventory.findFoodItem("banana"); 
	        assertNotNull(item); 
	        assertEquals("Banana", item.getName()); 
	    }

	    @Test
	    void testFindFoodItemNullThrowsException() {
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            this.inventory.findFoodItem(null);
	        });
	        assertEquals("Name cannot be null", exception.getMessage());
	    }
	}
