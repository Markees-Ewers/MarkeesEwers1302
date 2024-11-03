package edu.westga.cs1302.project2.test.model.TypeComparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.TypeComparator;

public class TypeComparatorTest {

	@Test
	public void testCompareShouldReturnNegativeWhenFirstTypeIsAlphabeticallyBefore() {

		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Carrot", "Vegetable");
		Comparator<Ingredient> typeComparator = new TypeComparator();

		int result = typeComparator.compare(ingredient1, ingredient2);

		assertTrue(result < 0, "Fruit should be before Vegetable");
	}

	@Test
	public void testCompareShouldReturnPositiveWhenFirstTypeIsAlphabeticallyAfter() {

		Ingredient ingredient1 = new Ingredient("Apple", "Vegetable");
		Ingredient ingredient2 = new Ingredient("Banana", "Fruit");
		Comparator<Ingredient> typeComparator = new TypeComparator();

		int result = typeComparator.compare(ingredient1, ingredient2);

		assertTrue(result > 0, "Vegetable should be after Fruit");
	}

	@Test
	public void testCompareShouldReturnZeroWhenTypesAreTheSame() {

		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Banana", "Fruit");
		Comparator<Ingredient> typeComparator = new TypeComparator();

		int result = typeComparator.compare(ingredient1, ingredient2);

		assertEquals(0, result, "Both types are 'Fruit', so comparison should return 0");
	}

	@Test
	public void testCompareShouldHandleCaseSensitivity() {

		Ingredient ingredient1 = new Ingredient("Apple", "fruit");
		Ingredient ingredient2 = new Ingredient("Banana", "Fruit");
		Comparator<Ingredient> typeComparator = new TypeComparator();

		int result = typeComparator.compare(ingredient1, ingredient2);

		assertTrue(result > 0, "Lowercase 'fruit' should come after uppercase 'Fruit'");
	}

	@Test
	public void testCompareShouldHandleSingleCharacterTypes() {

		Ingredient ingredient1 = new Ingredient("Apple", "A");
		Ingredient ingredient2 = new Ingredient("Banana", "B");
		Comparator<Ingredient> typeComparator = new TypeComparator();

		int result = typeComparator.compare(ingredient1, ingredient2);

		assertTrue(result < 0, "'A' should be before 'B'");
	}

	@Test
	public void testCompareShouldHandleVeryLongTypes() {

		Ingredient ingredient1 = new Ingredient("Apple", "A".repeat(1000));
		Ingredient ingredient2 = new Ingredient("Banana", "B".repeat(1000));
		Comparator<Ingredient> typeComparator = new TypeComparator();

		int result = typeComparator.compare(ingredient1, ingredient2);

		assertTrue(result < 0, "A long string of 'A's should be before a long string of 'B's");
	}

	@Test
	public void testCompareShouldHandleNumbersInTypes() {

		Ingredient ingredient1 = new Ingredient("Apple", "Type1");
		Ingredient ingredient2 = new Ingredient("Banana", "Type2");

		Comparator<Ingredient> typeComparator = new TypeComparator();

		int result = typeComparator.compare(ingredient1, ingredient2);

		assertTrue(result < 0, "'Type1' should be before 'Type2'");
	}
}