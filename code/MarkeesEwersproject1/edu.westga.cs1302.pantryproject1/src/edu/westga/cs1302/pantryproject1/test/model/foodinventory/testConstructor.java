package edu.westga.cs1302.pantryproject1.test.model.foodinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantryproject1.model.FoodInventory;

class testConstructor {

	 private FoodInventory inventory;

	    @BeforeEach
	    void setUp() {
	        this.inventory = new FoodInventory();
	    }

	    @Test
	    void testConstructorInitializesEmptyFoodItems() {
	       
	        assertNotNull(this.inventory);
	       
	    }
	}