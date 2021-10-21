package edu.westga.dbaccess.model;

/**
 * Employee class
 * 
 * @author Rasheed Jones
 * @version 1.0
 */
public class Employee {
	private String address1;
	private String address2;
	private int employeeId;
	private String firstName;
	private String lastName;
	private String password;
	private String phoneNumber;
	private String username;

	/**
	 * Constructor for employees 
	 *
	 * @param address1 the first portion of the employee's address
	 * @param address2 the second portion of the employee's address
	 * @param employeeId the employee's id
	 * @param fname the employee's first name
	 * @param lname the employees last name
	 * @param phoneNumber the employee's phone number
	 * @param password the employee's password
	 * @param username the employee's username
	 */
	public Employee(String address1, String address2, int employeeId, String fname, String lname, String phoneNumber, String password, String username) {
		this.address1 = address1;
		this.address2 = address2;
		this.employeeId = employeeId;
		this.firstName = fname;
		this.lastName = lname;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.username = username;
	}

	/**
	 * 
	 * @return the employee's address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * 
	 * @return the employee's address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * 
	 * @return the employee's Id
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * 
	 * @return the employee's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @return the employee's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @return the employee's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @return the employee's phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 
	 * @return this.username
	 */
	public String getUsername() {
		return username;
	}
}
