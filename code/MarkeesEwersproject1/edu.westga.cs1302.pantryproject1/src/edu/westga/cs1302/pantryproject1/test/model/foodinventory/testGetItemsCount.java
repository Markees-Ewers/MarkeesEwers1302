package edu.westga.cs1302.pantryproject1.test.model.foodinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantryproject1.model.FoodInventory;
import edu.westga.cs1302.pantryproject1.model.FoodItem;

class testGetItemsCount {

	 private FoodInventory inventory;

	    @BeforeEach
	    void setUp() {
	        this.inventory = new FoodInventory();
	    }

	    @Test
	    void testGetItemCountWhenEmpty() {
	        assertEquals(0, this.inventory.getItemCount()); 
	    }

	    @Test
	    void testGetItemCountAfterAddingItems() {
	        this.inventory.addFoodItem(new FoodItem("Apple", "Fruit"));
	        this.inventory.addFoodItem(new FoodItem("Banana", "Fruit"));
	        assertEquals(2, this.inventory.getItemCount()); 
	    }

	    @Test
	    void testGetItemCountAfterRemovingItem() {
	        this.inventory.addFoodItem(new FoodItem("Apple", "Fruit"));
	        this.inventory.addFoodItem(new FoodItem("Banana", "Fruit"));
	        this.inventory.removeFoodItem("Apple");
	        assertEquals(1, this.inventory.getItemCount()); 
	    }

	    @Test
	    void testGetItemCountAfterRemovingNonExistentItem() {
	        this.inventory.addFoodItem(new FoodItem("Apple", "Fruit"));
	        this.inventory.removeFoodItem("Banana"); 
	        assertEquals(1, this.inventory.getItemCount()); 
	    }
	}
