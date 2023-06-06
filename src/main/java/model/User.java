package model;

public abstract class User extends Person{
	/**
	 * provides constructor for child-classes
	 *
	 * @param firstName
	 * @param surname
	 */
	public User(String firstName, String surname) {
		super(firstName, surname);
	}
}
