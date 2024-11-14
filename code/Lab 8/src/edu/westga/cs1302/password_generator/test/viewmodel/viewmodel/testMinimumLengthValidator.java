package edu.westga.cs1302.password_generator.test.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

class testMinimumLengthValidator {
	private ViewModel vm;
	
	
	@Test
	void testErrorShouldShowIfNotANumberInMinimumDigitsTextField() {
	this.vm = new ViewModel();
	vm.getMinimumLength().set("a non Number");
	String error =  vm.getErrorText().getValue();
	String expected = "Minimum Length must only be a number";
	
	assertEquals(error, expected);
	}
	
	@Test
	void testErroShouldBeclearIfNumberIsInMinimumDigitsTextField() {
		this.vm = new ViewModel();
		vm.getMinimumLength().set("");
		String error =  vm.getErrorText().getValue();
		String expected = "";
		
		assertEquals(error, expected);
	}
	@Test
	void testErroShouldShowIfTextFieldContainsLettersAndNumbers() {
		this.vm = new ViewModel();
		vm.getMinimumLength().set("5five");
		String error =  vm.getErrorText().getValue();
		String expected =  "Minimum Length must only be a number";
		
		assertEquals(error, expected);
	}

}
