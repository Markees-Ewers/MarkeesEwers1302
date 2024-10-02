package edu.westga.cs1302.bill.view;

import java.io.IOException;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The codebehind for the MainWindow of the application.
 *
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

	/** The bill. */
	private Bill bill;

	/** The name. */
	@FXML
	private TextField name;

	/** The amount. */
	@FXML
	private TextField amount;

	/** The receipt area. */
	@FXML
	private TextArea receiptArea;

	/** The server name. */
	@FXML
	private ComboBox<String> serverName;

	/** The load button. */
	@FXML

	private Button loadButton;

	/**
	 * Adds the item.
	 *
	 * @param event the event
	 */
	@FXML
	void addItem(ActionEvent event) {
		try {
			BillItem item = new BillItem(this.name.getText(), Double.parseDouble(this.amount.getText()));
			this.bill.addItem(item);
			this.name.clear();
			this.amount.clear();
			this.updateReceipt();
		} catch (NumberFormatException numError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add new bill item");
			alert.setContentText("Invalid amount provided, please correct and try again.");
			alert.showAndWait();
		} catch (IllegalArgumentException argError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add new bill item");
			alert.setContentText(argError.getMessage());
			alert.showAndWait();
		} catch (IllegalStateException full) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("bill list is full");
			alert.setContentText(full.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * Update receipt.
	 */
	private void updateReceipt() {
		  if (this.bill == null) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Error");
		        alert.setHeaderText("No Bill Data");
		        alert.setContentText("The bill has not been loaded or is invalid.");
		        alert.showAndWait();
		        return; 
		    }
		    this.receiptArea.setText(BillTextifier.getText(this.bill));
		}

	/**
	 * Select server.
	 *
	 * @param event the event
	 */
	@FXML
	void selectServer(ActionEvent event) {
		String name = this.serverName.getValue();
		if (name != null) {
			this.bill.setServerName(name);
			this.updateReceipt();
		}
	}

	/**
	 * Save bill data.
	 *
	 * @param event the event
	 */
	@FXML
	void saveBillData(ActionEvent event) {
		System.out.println("im working");
		try {
			BillPersistenceManager.saveBillData(this.bill);
		} catch (IOException ex) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}

	}

	/**
	 * Load bill from file.
	 *
	 * @param event the event
	 */
	@FXML
	void loadBillFromFile(ActionEvent event) {
		try {
			this.bill = BillPersistenceManager.loadBillData();
			this.updateReceipt();

		} catch (NumberFormatException ex) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		} catch (IllegalArgumentException ex) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}

	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		this.serverName.getItems().add("Bob");
		this.serverName.getItems().add("Alice");
		this.serverName.getItems().add("Trudy");
		this.receiptArea.setEditable(false);
	
		this.bill = new Bill();
		
		this.loadBillFromFile(null);
		this.updateReceipt();
	}
}
