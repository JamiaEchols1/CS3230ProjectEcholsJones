package edu.westga.dbaccess.controller;

import java.sql.SQLException;

import edu.westga.dbaccess.dal.EmployeeDAL;
import edu.westga.dbaccess.model.Employee;

/**
 * The login window controller
 * 
 * @author Rasheed Jones
 * @version Fall 2021
 *
 */
public class LoginWindowController {
	private EmployeeDAL employeeDal;
	private Employee login;

	/**
	 * Constructor for the login window controller
	 */
	public LoginWindowController() {
		this.employeeDal = new EmployeeDAL();
	}

	/**
	 * Logs an employee into the system
	 * @param username the employee's username
	 * @param password the employee's password
	 * @return true if the login was successful, false otherwise
	 */
	public boolean login(String username, String password) {
		try {
			this.login = this.employeeDal.getEmployeeByLoginCredentials(username, password);
			Employee.setEmployee(this.login);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return !this.login.equals(null);
	}
	
	/**
	 * 
	 * @return this.employeeDal
	 */
	public Employee getEmployee() {
		return this.login;
	}

}
