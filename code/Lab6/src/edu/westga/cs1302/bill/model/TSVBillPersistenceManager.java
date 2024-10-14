package edu.westga.cs1302.bill.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Class TSVBillPersistenceManager.
 * 
 * @author me00070
 * 
 * @version spring 2024
 */
public class TSVBillPersistenceManager extends BillPersistenceManager {

	/**
	 * Save bill data.
	 *
	 * @param bill the bill
	 * @throws IOException              Signals that an I/O exception has occurred.
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	@Override
	public void saveBillData(Bill bill) throws IOException, IllegalArgumentException {
		if (bill == null) {
			throw new IllegalArgumentException("Must provide a valid bill");
		}
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write(bill.getServerName() + System.lineSeparator());
			for (BillItem item : bill.getItems()) {
				writer.write(item.getName() + "\t" + item.getAmount() + System.lineSeparator());
			}
		}
	}

	/**
	 * Load bill data.
	 *
	 * @return the bill
	 */
	@Override
	public Bill loadBillData() {
		Bill bill = new Bill();
		File inputFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			bill.setServerName(reader.nextLine());
			while (reader.hasNextLine()) {
				String[] itemData = reader.nextLine().strip().split("\t");
				bill.addItem(new BillItem(itemData[0], Double.parseDouble(itemData[1])));
			}
		} catch (Exception error) {
			bill = new Bill();
		}
		return bill;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Tab Seperator";
	}

}
