package edu.westga.cs1302.password_generator.viewmodel;

import java.util.Random;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

// TODO: Auto-generated Javadoc
/** Manages utilizing the model and makes properties available to bind the UI elements.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class ViewModel {
	
	/** The minimum length. */
	private StringProperty minimumLength;
	
	/** The require digits. */
	private BooleanProperty requireDigits;
	
	/** The require lowercase. */
	private BooleanProperty requireLowercase;
	
	/** The require uppercase. */
	private BooleanProperty requireUppercase;
	
	/** The password. */
	private StringProperty password;
	
	/** The error text. */
	private StringProperty errorText;
	
	/** The past passwords. */
	private ListProperty<String> pastPasswords;
	
    /** The generator. */
    private PasswordGenerator generator;
	
	/**
	 *  Initialize the properties for the viewmodel.
	 */
	public ViewModel() {
		this.minimumLength = new SimpleStringProperty("1");
		this.requireDigits = new SimpleBooleanProperty(false);
		this.requireLowercase = new SimpleBooleanProperty(false);
		this.requireUppercase = new SimpleBooleanProperty(false);
		
		this.password = new SimpleStringProperty("");
		this.errorText = new SimpleStringProperty("");
		this.minimumLengthValidator();
		this.pastPasswords = new SimpleListProperty<>(FXCollections.observableArrayList());

        Random randomNumberGenerator = new Random();
        this.generator = new PasswordGenerator(randomNumberGenerator.nextLong());
     
	}
	
	/**
	 *  Return the minimum length property.
	 *
	 * @return the minimum length property
	 */
	public StringProperty getMinimumLength() {
		return this.minimumLength;
	}

	/**
	 *  Return the require digits property.
	 *
	 * @return the require digits property
	 */
	public BooleanProperty getRequireDigits() {
		return this.requireDigits;
	}

	/**
	 *  Return the require upper case property.
	 *
	 * @return the require upper case property
	 */
	public BooleanProperty getRequireUppercase() {
		return this.requireUppercase;
	}

	/**
	 *  Return the require lower case property.
	 *
	 * @return the require lower case property
	 */
	public BooleanProperty getRequireLowercase() {
		return this.requireLowercase;
	}

	/**
	 *  Return the password property.
	 *
	 * @return the password property
	 */
	public StringProperty getPassword() {
		return this.password;
	}

	/**
	 *  Return the error text property.
	 *
	 * @return the error text property
	 */
	public StringProperty getErrorText() {
		return this.errorText;
	}

    /**
     * Past passwords property.
     *
     * @return the list property
     */
    public ListProperty<String> pastPasswordsProperty() {
        return this.pastPasswords;
    }

    /**
     * Adds the password to history.
     *
     * @param password the password
     */
    public void addPasswordToHistory(String password) {
        this.pastPasswords.add(password);
    }

	/** Generates a password using the minimum length, require digit, require lower case, and require upper case property values.
	 * 
	 * If a password is successfully generated, the error text property is set to empty string and the password property is set to the password generated.
	 * 
	 * If an error is encountered, the password property is set to empty, and the error text property is populated with a message describing the problem.
	 */
	public void generatePassword() {
    	int minimumLength = -1;
    	this.password.setValue("");
    	
    	try {
    		minimumLength = Integer.parseInt(this.minimumLength.getValue());
    	} catch (NumberFormatException numberError) {
    		this.errorText.setValue("Invalid Minimum Length: must be a positive integer, but was " + this.minimumLength.getValue());
    		return;
    	}
    	
    	try {
    		this.generator.setMinimumLength(minimumLength);
    	} catch (IllegalArgumentException invalidLengthError) {
    		this.errorText.setValue("Invalid Minimum Length: " + invalidLengthError.getMessage());
    		return;
    	}
    	
    	this.generator.setMustHaveAtLeastOneDigit(this.requireDigits.getValue());
    	this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.requireLowercase.getValue());
    	this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.requireUppercase.getValue());
    	
    	String password = this.generator.generatePassword();
    	
    	this.password.setValue(password);
    	this.pastPasswords.add(password);
    }
	
	private void minimumLengthValidator() {
		this.minimumLength.addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
			this.errorText.setValue("Minimum Length must only be a number");
			} else {
				this.errorText.set("");
			}

		});
	}
}
