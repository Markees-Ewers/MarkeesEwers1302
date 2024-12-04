package edu.westga.cs1302.project3.model;

public class Task {

	public String name;
	public String description;

	public Task(String name, String description) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be blank or null ");
		}
		if (description == null || name.isBlank()) {
			throw new IllegalArgumentException("Description cannot be blank or null");
		}

		this.name = name;
		this.description = description;
	}

}
