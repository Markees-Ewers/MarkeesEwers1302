package edu.westga.cs1302.pantryproject1.test.model.foodinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantryproject1.model.FoodInventory;
import edu.westga.cs1302.pantryproject1.model.FoodItem;

class testRemoveFoodItem {

	private FoodInventory inventory;

    @BeforeEach
    void setUp() {
        this.inventory = new FoodInventory();
        this.inventory.addFoodItem(new FoodItem("Apple", "Fruit"));
        this.inventory.addFoodItem(new FoodItem("Banana", "Fruit"));
    }

    @Test
    void testRemoveFoodItemValid() {
        boolean result = this.inventory.removeFoodItem("Apple");
        assertTrue(result); 
    }

    @Test
    void testRemoveFoodItemNotInList() {
        boolean result = this.inventory.removeFoodItem("Orange");
        assertFalse(result); 
    }

    @Test
    void testRemoveFoodItemCaseInsensitive() {
        boolean result = this.inventory.removeFoodItem("banana");
        assertTrue(result); 
    }

    @Test
    void testRemoveFoodItemNullThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.inventory.removeFoodItem(null);
        });
        assertEquals("Name cannot be null", exception.getMessage());
    }
}