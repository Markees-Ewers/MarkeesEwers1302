package edu.westga.cs1302.bill.test.model.bill_persistence_Manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class testLoadBill {

	  private static final String DATA_FILE = "data.txt";  

	    @BeforeEach
	    public void setup() throws IOException {
	      
	        try (FileWriter writer = new FileWriter(DATA_FILE)) {
	            writer.write("Server Name: John Doe\n");
	            writer.write("Bill Items:\n");
	            writer.write("Pizza, 12.99\n");
	            writer.write("Burger, 8.50\n");
	            writer.write("Soda, 2.99\n");
	        }
	    }

	    @Test
	    public void testLoadBillDataSuccessfully() throws IOException {
	      
	        Bill bill = BillPersistenceManager.loadBillData();

	      
	        assertEquals("John Doe", bill.getServerName(), "Server name should be John Doe");

	        
	        assertEquals(3, bill.getItems().length, "There should be 3 items in the bill");

	        BillItem[] items = bill.getItems();

	        BillItem item1 = items[0];
	        assertEquals("Pizza", item1.getName(), "First item should be Pizza");
	        assertEquals(12.99, item1.getAmount(), 0.01, "First item amount should be 12.99");

	        BillItem item2 = items[1];
	        assertEquals("Burger", item2.getName(), "Second item should be Burger");
	        assertEquals(8.50, item2.getAmount(), 0.01, "Second item amount should be 8.50");

	        BillItem item3 = items[2];
	        assertEquals("Soda", item3.getName(), "Third item should be Soda");
	        assertEquals(2.99, item3.getAmount(), 0.01, "Third item amount should be 2.99");
	    }

	    @Test
	    public void testLoadBillDataFileNotFound() {
	        // Delete the data.txt file to simulate file not found
	        File file = new File(DATA_FILE);
	        if (file.exists()) {
	            file.delete();
	        }

	       
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            BillPersistenceManager.loadBillData();
	        });

	      
	        assertTrue(exception.getMessage().startsWith("File not found: data.txt"));
	    }
	    @Test
	    public void testLoadBillDataMalformedFile() throws IOException {
	     
	        try (FileWriter writer = new FileWriter(DATA_FILE)) {
	            writer.write("Server Name: John Doe\n");
	            writer.write("Bill Items:\n");
	            writer.write("Pizza\n");  
	        }

	        Bill bill = BillPersistenceManager.loadBillData();
       
	        assertEquals("John Doe", bill.getServerName(), "Server name should be John Doe");

	        assertEquals(0, bill.getItems().length, "There should be no items in the bill due to malformed data");
	    }
	}