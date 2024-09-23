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
 * @author me00070
 * @version fall 2024
 */
public class MainWindow {

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
			String name = this.foodNameTextField.getText();
			String type = this.foodTypeComboBox.getValue();

			FoodItem foodItem = new FoodItem(name, type); 
			this.foodItems.add(foodItem);
			
			this.foodNameTextField.clear();
			this.foodTypeComboBox.getSelectionModel().clearSelection();
			
		} catch (IllegalArgumentException ex) {
			Alert errorAlert = new Alert(AlertType.ERROR);
	        errorAlert.setTitle("Error Creating Name");
	        errorAlert.setHeaderText(null);
	        errorAlert.setContentText(ex.getMessage());
	        errorAlert.showAndWait();
	        this.foodNameTextField.clear();
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
		assert this.foodListView != null
				: "fx:id=\"foodListView\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.foodNameTextField != null
				: "fx:id=\"foodNameTextField\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.foodTypeComboBox != null
				: "fx:id=\"foodTypeComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'MainWindow.fxml'.";
		this.populateComboBox();
	}

}
