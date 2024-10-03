package edu.westga.cs1302.bill.test.model.bill_persistence_Manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class testSaveBill {

	private final File dataFile = new File(BillPersistenceManager.DATA_FILE);  // The actual data file

    @Test
    public void testSaveBillDataSuccessfully() throws IOException {
        // Create a bill and add some items
        Bill bill = new Bill();
        bill.setServerName("John Doe");
        bill.addItem(new BillItem("Pizza", 12.99));
        bill.addItem(new BillItem("Burger", 8.50));
        bill.addItem(new BillItem("Soda", 2.99));

        // Call saveBillData to write the bill to data.txt
        BillPersistenceManager.saveBillData(bill);

        // Read the content of the data.txt file and verify it
        String content = Files.readString(dataFile.toPath());

        String expectedContent = "Server Name: John Doe\n" +
                                 "Bill Items: \n" +
                                 "Pizza , 12.99\n" +
                                 "Burger , 8.5\n" +
                                 "Soda , 2.99\n";

        assertEquals(expectedContent, content, "The saved file content is incorrect.");
    }

    @Test
    public void testSaveBillDataWithNullBill() {
        // Test passing a null bill, expect IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            BillPersistenceManager.saveBillData(null);
        });

        assertEquals("Bill must not be null", exception.getMessage());
    }

    @Test
    public void testSaveBillDataWithEmptyBill() throws IOException {
        // Create an empty bill with a server name but no items
        Bill bill = new Bill();
        bill.setServerName("Jane Doe");

        // Call saveBillData to write the empty bill to data.txt
        BillPersistenceManager.saveBillData(bill);

        // Read the content of the data.txt file and verify it
        String content = Files.readString(dataFile.toPath());

        String expectedContent = "Server Name: Jane Doe\n" +
                                 "Bill Items: \n";

        assertEquals(expectedContent, content, "The saved file content for an empty bill is incorrect.");
    }
}