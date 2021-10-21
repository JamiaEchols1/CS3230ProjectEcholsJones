package edu.westga.devops.controller;

import java.sql.SQLException;

import edu.westga.dbaccess.dal.EmployeeDAL;

public class LoginWindowController {
	private EmployeeDAL employeeDal;

	public LoginWindowController() {
		this.employeeDal = new EmployeeDAL();
	}

	public boolean login(String username, String password) {
		boolean login = false;
		try {
			login = this.employeeDal.validateEmployeeByLoginCredentials(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}

}
