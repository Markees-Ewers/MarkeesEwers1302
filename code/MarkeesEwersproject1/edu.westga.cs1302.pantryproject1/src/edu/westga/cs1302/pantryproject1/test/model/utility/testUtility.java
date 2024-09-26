package edu.westga.cs1302.pantryproject1.test.model.utility;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantryproject1.model.FoodItem;
import edu.westga.cs1302.pantryproject1.model.PantryUtility;

class testUtility {

	 private List<FoodItem> pantry;

	    @BeforeEach
	    void setUp() {
	        // Initialize the pantry before each test
	        pantry = new ArrayList<>();
	    }

	    @Test
	    void testGetTotalQuantityWithEmptyPantry() {
	        // Test with an empty pantry (no items)
	        int totalQuantity = PantryUtility.getTotalQuantity(pantry);
	        assertEquals(0, totalQuantity); // Expect 0 because pantry is empty
	    }

	    @Test
	    void testGetTotalQuantityWithSingleItem() {
	        // Test with one item
	        FoodItem apple = new FoodItem("Apple", "Fruit");
	        apple.setQuantity(5);
	        pantry.add(apple); // Add item to pantry
	        int totalQuantity = PantryUtility.getTotalQuantity(pantry);
	        assertEquals(5, totalQuantity); // Expect 5 as the total quantity
	    }

	    @Test
	    void testGetTotalQuantityWithMultipleItems() {
	        // Test with multiple items
	        FoodItem apple = new FoodItem("Apple", "Fruit");
	        apple.setQuantity(5);
	        FoodItem banana = new FoodItem("Banana", "Fruit");
	        banana.setQuantity(3);
	        pantry.add(apple);  // Add first item
	        pantry.add(banana); // Add second item
	        int totalQuantity = PantryUtility.getTotalQuantity(pantry);
	        assertEquals(8, totalQuantity); // Expect total to be 5 + 3 = 8
	    }
}
