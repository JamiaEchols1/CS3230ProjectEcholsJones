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
	private String zipcode;
	private String state;
	private String city;
	
	private static Employee employee = null;

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
	public Employee(String address1, String address2, int employeeId, String fname, String lname, String phoneNumber, String password, String username, String city, String zipcode, String state) {
		this.address1 = address1;
		this.address2 = address2;
		this.employeeId = employeeId;
		this.firstName = fname;
		this.lastName = lname;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
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

	/**
	 * 
	 * @return the employees full name
	 */
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", username=" + username + "]";
	}

	/**
	 * Sets the employee
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param employee
	 */
	public static void setEmployee(Employee employee) {
		Employee.employee = employee;
	}
	
	/**
	 * Gets the employee
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the employee
	 */
	public static Employee getEmployee() {
		return Employee.employee;
	}
	
	
	/**
	 * Gets the zipcode
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the zipcode
	 */
	public String getZipcode() {
		return this.zipcode;
	}
	
	/**
	 * Gets the state
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * Gets the city
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}
}
