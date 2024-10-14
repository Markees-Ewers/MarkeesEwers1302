package edu.westga.cs1302.bill.view;

import java.io.IOException;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;
import edu.westga.cs1302.bill.model.CSVBillPersistenceManager;
import edu.westga.cs1302.bill.model.TSVBillPersistenceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The codebehind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	private Bill bill;

	@FXML
	private ComboBox<BillPersistenceManager> billSeperatorComboBox;

	@FXML
	private TextField name;
	@FXML
	private TextField amount;
	@FXML
	private TextArea receiptArea;
	@FXML
	private ComboBox<String> serverName;

	@FXML
	void addItem(ActionEvent event) {
		try {
			BillItem item = new BillItem(this.name.getText(), Double.parseDouble(this.amount.getText()));
			this.bill.addItem(item);
			this.name.clear();
			this.amount.clear();
			this.updateReceipt();
		} catch (NumberFormatException numError) {
			this.displayErrorPopup("Invalid amount provided, please correct and try again.");
		} catch (IllegalArgumentException argError) {
			this.displayErrorPopup("Unable to add new bill item");
		}
	}

	private void updateReceipt() {
		this.receiptArea.setText(BillTextifier.getText(this.bill));
	}

	@FXML
	void selectServer(ActionEvent event) {
		String name = this.serverName.getValue();
		if (name != null) {
			this.bill.setServerName(name);
			this.updateReceipt();
		}
	}
	
	void loadBill() {
		
			CSVBillPersistenceManager csv = new CSVBillPersistenceManager();
			this.bill = csv.loadBillData();
			if (this.bill.getItems().length == 0) {
				TSVBillPersistenceManager tsv = new TSVBillPersistenceManager();
				this.bill = tsv.loadBillData();
				this.updateReceipt();
				if (this.bill.getItems().length == 0) {
					this.displayErrorPopup("Previous Bill was not able to load");
					this.updateReceipt();
				}
			}
		
	}

	@FXML
	void saveBillData(ActionEvent event) {
		try {
			this.billSeperatorComboBox.getSelectionModel().getSelectedItem().saveBillData(this.bill);
		} catch (IllegalArgumentException ex) {
		this.displayErrorPopup(ex.getMessage());
		} catch (IOException ex) {
			this.displayErrorPopup("Couldnt save Bill");
		}
	}

	private void displayErrorPopup(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	void initialize() {
		
		this.serverName.getItems().add("Bob");
		this.serverName.getItems().add("Alice");
		this.serverName.getItems().add("Trudy");
		
		this.billSeperatorComboBox.getItems().add(new CSVBillPersistenceManager());
		this.billSeperatorComboBox.getItems().add(new TSVBillPersistenceManager());
		this.billSeperatorComboBox.setValue(this.billSeperatorComboBox.getItems().get(0));
		
		this.bill = new Bill();
		this.loadBill();
		this.updateReceipt();
	}
}
