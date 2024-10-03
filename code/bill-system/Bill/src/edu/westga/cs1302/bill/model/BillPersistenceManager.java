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
	 * 
	 * @return the bill loaded
	 */
	public static Bill loadBillData() {
		Bill bill = new Bill();
		String serverName = null;
		int lineCount = 1;

		try (Scanner scanner = new Scanner(new File(DATA_FILE))) {
			System.out.println("Starting to read the file...");

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println("Reading line " + lineCount + ": " + line);

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
						System.out.println("Adding item: " + itemName + " with amount: " + itemAmount); 
																										
						bill.addItem(new BillItem(itemName, itemAmount));
						lineCount++;
					} else {
						System.out.println("Error on line " + lineCount + ": " + line); 
					}
				}
			}
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("File not found: " + ex.getMessage());
		} catch (NumberFormatException ex) {
			throw new NumberFormatException("Error with amount on line: " + lineCount);
		}

		if (serverName != null) {
			bill.setServerName(serverName);
			System.out.println("Server name set: " + serverName);
		} else {
			System.out.println("No server name found.");
		}

		System.out.println("Returning bill...");
		return bill; 
	}

}
