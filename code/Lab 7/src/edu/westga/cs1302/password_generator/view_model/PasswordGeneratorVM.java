package edu.westga.cs1302.password_generator.view_model;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PasswordGeneratorVM {
	
	private PasswordGenerator passwordGenerator;
	
	private IntegerProperty minLength;
	private BooleanProperty mustHaveAtLeastOneDigit;
	private BooleanProperty mustHaveOneUpperCase;
	private BooleanProperty mustHaveAtLeastOneLowerCase;
	
	public PasswordGeneratorVM(long seed) {
		this.passwordGenerator = new PasswordGenerator(seed);
		this.minLength = new SimpleIntegerProperty(passwordGenerator.getMinimumLength());
		this.mustHaveAtLeastOneDigit = new SimpleBooleanProperty(passwordGenerator.getMustHaveAtLeastOneDigit());
		this.mustHaveAtLeastOneLowerCase = new SimpleBooleanProperty(passwordGenerator.getMustHaveAtLeastOneLowerCaseLetter());
		this.mustHaveOneUpperCase = new SimpleBooleanProperty(passwordGenerator.getMustHaveAtLeastOneUpperCaseLetter());
		}
	
	
	public IntegerProperty minimumLengthPropery() {
		return this.minLength;
		
	}
	public BooleanProperty mustHaveAtLeastOneDigit() {
		return this.mustHaveAtLeastOneDigit;
	}
	public BooleanProperty mustHaveOneUpperCase() {
		return this.mustHaveOneUpperCase;
	}
	
	public BooleanProperty mustHaveAtLeastOneLowerCase(){
		return this.mustHaveAtLeastOneLowerCase;
		
	}
}

