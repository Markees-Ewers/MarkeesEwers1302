package edu.westga.cs1302.project2.test.model.NameComparator;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.Test;


import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.NameComparator;

public class NameComparatorTest {

	@Test
	public void testCompareShouldReturnNegativeWhenFirstNameIsAlphabeticallyBefore() {
	
		Ingredient ingredient1 = new Ingredient("Apple", "AnyType");
		Ingredient ingredient2 = new Ingredient("Banana", "AnyType");
		Comparator<Ingredient> nameComparator = new NameComparator();

		
		int result = nameComparator.compare(ingredient1, ingredient2);

		
		assertTrue(result < 0, "Apple should be before Banana");
	}

	@Test
	public void testCompareShouldReturnPositiveWhenFirstNameIsAlphabeticallyAfter() {
		
		Ingredient ingredient1 = new Ingredient("Banana", "AnyType");
		Ingredient ingredient2 = new Ingredient("Apple", "AnyType");
		Comparator<Ingredient> nameComparator = new NameComparator();

	
		int result = nameComparator.compare(ingredient1, ingredient2);

	
		assertTrue(result > 0, "Banana should be after Apple");
	}

	@Test
	public void testCompareShouldReturnZeroWhenNamesAreTheSame() {
		
		Ingredient ingredient1 = new Ingredient("Apple", "AnyType");
		Ingredient ingredient2 = new Ingredient("Apple", "AnotherType");
		Comparator<Ingredient> nameComparator = new NameComparator();

		
		int result = nameComparator.compare(ingredient1, ingredient2);

	
		assertEquals(0, result, "Both names are 'Apple', so comparison should return 0");
	}

	@Test
	public void testCompareShouldHandleCaseSensitivity() {
		
		Ingredient ingredient1 = new Ingredient("apple", "AnyType");
		Ingredient ingredient2 = new Ingredient("Apple", "AnyType");
		Comparator<Ingredient> nameComparator = new NameComparator();

		
		int result = nameComparator.compare(ingredient1, ingredient2);

		
		assertTrue(result > 0, "Lowercase 'apple' should come after uppercase 'Apple'");
	}

	

	@Test
	public void testCompareShouldHandleSingleCharacterNames() {
		
		Ingredient ingredient1 = new Ingredient("A", "AnyType");
		Ingredient ingredient2 = new Ingredient("B", "AnyType");
		Comparator<Ingredient> nameComparator = new NameComparator();

		
		int result = nameComparator.compare(ingredient1, ingredient2);

		
		assertTrue(result < 0, "'A' should be before 'B'");
	}

	@Test
	public void testCompareShouldHandleVeryLongNames() {
		
		Ingredient ingredient1 = new Ingredient("A".repeat(1000), "AnyType");
		Ingredient ingredient2 = new Ingredient("B".repeat(1000), "AnyType");
		NameComparator nameComparator = new NameComparator();

	
		int result = nameComparator.compare(ingredient1, ingredient2);

		
		assertTrue(result < 0, "A long string of 'A's should be before a long string of 'B's");
	}

	@Test
	public void testCompareShouldHandleNumbersInNames() {
		
		Ingredient ingredient1 = new Ingredient("Apple1", "AnyType");
		Ingredient ingredient2 = new Ingredient("Apple2", "AnyType");
		Comparator<Ingredient> nameComparator = new NameComparator();

		
		int result = nameComparator.compare(ingredient1, ingredient2);

		
		assertTrue(result < 0, "'Apple1' should be before 'Apple2'");
	}
}