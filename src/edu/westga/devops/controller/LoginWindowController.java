package edu.westga.devops.controller;

import java.sql.SQLException;

import edu.westga.dbaccess.dal.EmployeeDAL;

public class LoginWindowController {
	private EmployeeDAL employeeDal;

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
		boolean login = false;
		try {
			login = this.employeeDal.validateEmployeeByLoginCredentials(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}
	
	/**
	 * 
	 * @return this.employeeDal
	 */
	public EmployeeDAL getEmployeeDAL() {
		return this.employeeDal;
	}

}
