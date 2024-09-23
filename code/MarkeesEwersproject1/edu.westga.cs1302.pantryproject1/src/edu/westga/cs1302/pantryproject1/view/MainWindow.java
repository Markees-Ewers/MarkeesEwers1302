package edu.westga.cs1302.pantryproject1.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs1302.pantryproject1.model.FoodItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The Class MainWindow.
 * 
 * @author me00070
 * @version fall 2024
 */
public class MainWindow {

	@FXML
	private TextField amountTextField;

	@FXML
	private Button decrementButton;

	@FXML
	private Button incrementButton;

	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The add food button. */
	@FXML
	private Button addFoodButton;

	/** The food list view. */
	@FXML
	private ListView<FoodItem> foodListView;

	/** The food name text field. */
	@FXML
	private TextField foodNameTextField;

	/** The food type combo box. */
	@FXML
	private ComboBox<String> foodTypeComboBox;

	/** The pane. */
	@FXML
	private AnchorPane pane;

	/** The food items. */
	private ObservableList<FoodItem> foodItems;

	/**
	 * Adds the food.
	 *
	 * @param event the event
	 */
	@FXML
	void addFood(ActionEvent event) {
		try {
			if (Integer.parseInt(this.amountTextField.getText()) < 1) {
				throw new IllegalArgumentException("Quantity must be greater than 0");
			}
			String name = this.foodNameTextField.getText();
			String type = this.foodTypeComboBox.getValue();
			FoodItem foodItem = new FoodItem(name, type);
			foodItem.setQuantity(Integer.parseInt(this.amountTextField.getText()));
			this.foodItems.add(foodItem);

			this.amountTextField.clear();
			this.foodNameTextField.clear();
			this.foodTypeComboBox.getSelectionModel().clearSelection();

		} catch (NumberFormatException ex) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Error Creating Name");
			errorAlert.setHeaderText(null);
			errorAlert.setContentText("Amount must be a number");
			System.out.println("1");

			errorAlert.showAndWait();
			this.foodNameTextField.clear();
		} catch (IllegalArgumentException ex) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Error Creating Name");
			errorAlert.setHeaderText(null);
			errorAlert.setContentText("Name cannot be empty");
			System.out.println("2");

			errorAlert.showAndWait();
			this.foodNameTextField.clear();
		}

	}

	@FXML
	void decrementCurrentSelection(ActionEvent event) {

		try {
			FoodItem currentItem = this.foodListView.getSelectionModel().getSelectedItem();
			if (currentItem.getQuantity() <= 1) {
				this.foodItems.remove(currentItem);
				this.foodListView.refresh();
			}
			currentItem.decrementQuantity();

			this.foodListView.refresh();
		} catch (NullPointerException ex) {
			this.foodItems.remove(this.foodListView.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	void incrementCurrentSelection(ActionEvent event) {
		try {
			FoodItem currentItem = this.foodListView.getSelectionModel().getSelectedItem();

			currentItem.incrementQuantity();
			this.foodListView.refresh();
		} catch (NullPointerException ex) {
			// this just catches the exception to make sure that there is nothing inside of
			// console
		}
	}

	/**
	 * Populate combo box.
	 */
	private void populateComboBox() {
		// This is the Array that gets me 5 extra points yay!!!!
		String[] foodTypes = { "Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient" };

		for (String curr : foodTypes) {
			this.foodTypeComboBox.getItems().addAll(curr);
		}
	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		System.out.println("Initializing...");

		// Initialize the ObservableList for the ListView
		this.foodItems = FXCollections.observableArrayList();

		// Bind the ObservableList to the ListView
		this.foodListView.setItems(this.foodItems);

		// Populate the ComboBox
		this.populateComboBox();

		assert this.addFoodButton != null
				: "fx:id=\"addFoodButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.amountTextField != null
				: "fx:id=\"amountTextField\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.decrementButton != null
				: "fx:id=\"decrementButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.foodListView != null
				: "fx:id=\"foodListView\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.foodNameTextField != null
				: "fx:id=\"foodNameTextField\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.foodTypeComboBox != null
				: "fx:id=\"foodTypeComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.incrementButton != null
				: "fx:id=\"incrementButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'MainWindow.fxml'.";

	}
}
