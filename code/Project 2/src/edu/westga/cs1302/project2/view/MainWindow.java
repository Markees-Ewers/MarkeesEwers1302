package edu.westga.cs1302.project2.view;

import java.util.Collections;
import java.util.Comparator;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.NameComparator;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.TypeComparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private Button addRecipeButton;
	@FXML
	private TextField recipeTextField;
	@FXML
	private Button bringButton;
	@FXML
	private ComboBox<String> ingredientType;
	@FXML
	private ListView<Ingredient> ingredientsList;
	@FXML
	private TextField ingredientName;
	@FXML
	private ComboBox<Comparator<Ingredient>> sortComboBox;
	@FXML
	private Button sortButton;
	@FXML
	private ListView<Ingredient> recipeListView;

	@FXML
	void bringIngredients(ActionEvent event) {

		Ingredient item = this.ingredientsList.getSelectionModel().getSelectedItem();
		try {
			if (item != null) {
				this.recipeListView.getItems().add(item);
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add ingredient");
			alert.setContentText("Must select item form ingredients list");
			alert.showAndWait();
		}
	}

	@FXML
	void addIngredient(ActionEvent event) {
		try {
			this.ingredientsList.getItems()
					.add(new Ingredient(this.ingredientName.getText(), this.ingredientType.getValue()));
			this.ingredientName.clear();
			this.ingredientType.getSelectionModel().clearSelection();
			this.sortIngredientsList(event);
		} catch (IllegalArgumentException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add ingredient");
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	void removeIngredient(ActionEvent event) {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null) {
			this.ingredientsList.getItems().remove(selectedIngredient);
			this.sortIngredientsList(event);
		}
	}

	private void populateCompareComboBox() {
		NameComparator name = new NameComparator();
		TypeComparator type = new TypeComparator();

		this.sortComboBox.getItems().add(name);
		this.sortComboBox.getItems().add(type);
		this.sortComboBox.getSelectionModel().select(0);
	}

	@FXML
	void sortIngredientsList(ActionEvent event) {

		Comparator<Ingredient> selectedComparator = (Comparator<Ingredient>) this.sortComboBox.getSelectionModel()
				.getSelectedItem();

		Collections.sort(this.ingredientsList.getItems(), selectedComparator);

		this.ingredientsList.setItems(FXCollections.observableArrayList(this.ingredientsList.getItems()));
	}

	@FXML
	void addRecipe(ActionEvent event) {

	}

	@FXML
	void initialize() {
		this.ingredientType.getItems().add("Vegetable");
		this.ingredientType.getItems().add("Meat");
		this.ingredientType.getItems().add("Bread");
		this.ingredientType.getItems().add("Fruit");
		this.ingredientType.getItems().add("Spice");

		this.populateCompareComboBox();

	}
}
