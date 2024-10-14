package edu.westga.cs1302.bill.model;

import java.util.Comparator;

/**
 * The Class AscendingComparator.
 * 
 * @author me00070
 * @version fall 2024
 */
public class AscendingComparator implements Comparator<BillItem> {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(BillItem o1, BillItem o2) {
		if (o1.getAmount() < o2.getAmount()) {
			return -1;
		}
		if (o1.getAmount() > o2.getAmount()) {
			return 1;
		}
		return 0;
	}

}
