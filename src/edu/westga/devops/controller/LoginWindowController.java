package edu.westga.devops.controller;

import java.sql.SQLException;

import edu.westga.dbaccess.dal.EmployeeDAL;
import edu.westga.dbaccess.model.Employee;

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
