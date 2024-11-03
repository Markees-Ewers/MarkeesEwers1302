package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/**
 * The Class TypeComparator.
 * 
 * @author me00070
 * @version fall 2024
 */
public class TypeComparator implements Comparator<Ingredient> {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		return o1.getType().compareTo(o2.getType());
	}

	@Override
	public String toString() {
		return "By Type";
	}
}