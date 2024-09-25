package edu.westga.cs1302.pantryproject1.test.model.fooditem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantryproject1.model.FoodItem;

class testSetQuantity {


    private FoodItem item;

    @BeforeEach
    void setUp() {
        this.item = new FoodItem("Apple", "Fruit");
    }

    @Test
    void testSetQuantityValid() {
        this.item.setQuantity(5);
        assertEquals(5, this.item.getQuantity());
    }

    @Test
    void testSetQuantityBelowOneThrowsException() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            this.item.setQuantity(0);
        });
        assertEquals("Quantity cannot be be below 1", exception.getMessage());
    }

    @Test
    void testSetQuantityNegativeThrowsException() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            this.item.setQuantity(-10);
        });
        assertEquals("Quantity cannot be be below 1", exception.getMessage());
    }

    @Test
    void testSetQuantityOneIsValid() {
        this.item.setQuantity(1);
        assertEquals(1, this.item.getQuantity());
    }
}