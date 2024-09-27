package edu.westga.cs1302.bill.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Supports saving and loading bill data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillPersistenceManager {

	public static final String DATA_FILE = "data.txt";

	/**
	 * Save the bill!
	 * 
	 * Writes all bill data to DATA_FILE
	 * 
	 * @precondition bill != null
	 * @postcondition none
	 * 
	 * @param bill the bill to save
	 * @throws IOException
	 */
	public static void saveBillData(Bill bill) throws IOException {
		if (bill == null) {
			throw new IllegalArgumentException("Bill must not be null");
		}
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Server Name: " + bill.getServerName() + "\n");

			writer.write("Bill Items: \n");

			for (BillItem item : bill.getItems()) {
				writer.write(item.getName() + " , " + item.getAmount() + "\n");
			}
		}
	}

	/**
	 * Load the bill!
	 * 
	 * Reads from DATA_FILE File is assumed to use the same format as saveBillData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param filePath where the bill will be saved to
	 * 
	 * @return the bill loaded
	 */
	public static Bill loadBillData(String filePath) {
		Bill bill = new Bill(); 
		String serverName = null;
		int lineCount = 1;

		try (Scanner scanner = new Scanner(new File(filePath))) {
			while (scanner.hasNextLine()) {
				lineCount++;
				String line = scanner.nextLine();

				if (line.startsWith("Server Name: ")) {

					serverName = line.substring("Server Name: ".length()).trim();
				} else if (line.startsWith("Bill Items:")) {

					continue;
				} else {
					// Split the item line into name and amount
					String[] parts = line.split(",");
					if (parts.length == 2) {
						String itemName = parts[0].trim();
						double itemAmount = Double.parseDouble(parts[1].trim());
						bill.addItem(new BillItem(itemName, itemAmount));
					} else {
						System.out.println("error on line " + lineCount);
					}
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + ex.getMessage());
		} catch (NumberFormatException ex) {
			System.err.println("Error parsing the amount: " + ex.getMessage());
		}

		// Set the server name if it was read
		if (serverName != null) {
			bill.setServerName(serverName);
		}

		return bill; 
	}

}
