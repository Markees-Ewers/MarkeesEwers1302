package edu.westga.cs1302.project3.model;

/**
 * a single task
 * 
 * @version fall 2024
 * @author me00070
 * 
 *         The Class Task.
 */
public class Task {

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/**
	 * Instantiates a new task.
	 * 
	 * @precondition name != null || name != empty
	 * 
	 * @preconditon description !+ null || name != empty
	 * @precondition name !contain "-"
	 * @precondition description 1contain "-"
	 * @param name        the name
	 * @param description the description
	 */
	public Task(String name, String description) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be blank or null ");
		}
		if (name.contains("-")) {
			throw new IllegalArgumentException("name cannot contain: -");
		}
		if (description.contains("-")) {
			throw new IllegalArgumentException("description cannot contain: -");
		}
		if (description == null || description.isBlank()) {
			throw new IllegalArgumentException("Description cannot be blank or null");
		}

		this.name = name;
		this.description = description;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the description.
	 * 
	 * @precondition name != null || blank
	 * @precondition name !contain description
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		if (description == null || description.isBlank()) {
			throw new IllegalArgumentException("Description cannot be blank or null");
		}
		if (description.contains("-")) {
			throw new IllegalArgumentException("description cannot contain: -");
		}
		this.description = description;
	}

	/**
	 * Sets the name.
	 * 
	 * @precondition name != null || name != blank
	 * @precondition name !contain "-"
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be blank or null ");
		}
		if (name.contains("-")) {
			throw new IllegalArgumentException("name cannot contain: -");
		}
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {

		return this.description;
	}

	@Override
	public String toString() {
		return this.name + ":   " + this.description;

	}

}
