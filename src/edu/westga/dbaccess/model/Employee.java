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

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getUsername() {
		return username;
	}
}
