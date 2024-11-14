package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

	@FXML
	private CheckBox mustIncludeDigits;
	@FXML
	private CheckBox mustIncludeLowerCaseLetters;
	@FXML
	private CheckBox mustIncludeUpperCaseLetters;
	@FXML
	private TextField minimumLength;
	@FXML
	private TextArea output;
	@FXML
	private Label errorTextLabel;
	@FXML
	private Button generatePasswordButton;
	@FXML
	private ListView<String> passwordListView;

	private ViewModel vm;

	@FXML
	void initialize() {
		this.vm = new ViewModel();
		this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
		this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
		this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());
		this.minimumLength.setText(this.vm.getMinimumLength().getValue());
		this.vm.getMinimumLength().bind(this.minimumLength.textProperty());
		this.passwordListView.itemsProperty().bind(this.vm.pastPasswordsProperty());

		this.errorTextLabel.textProperty().bind(this.vm.getErrorText());

		this.generatePasswordButton.setOnAction((event) -> {
			this.vm.generatePassword();
		});
		this.minimumLengthValidator();
	}

	private void minimumLengthValidator() {
		this.minimumLength.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) { 
				this.vm.getErrorText().setValue("Minimum Length must only be a number");
			} else {
				this.vm.getErrorText().set("");
			}

		});
	}

}
