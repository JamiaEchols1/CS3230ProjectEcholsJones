package edu.westga.devops.controller;

import java.sql.SQLException;

import edu.westga.dbaccess.dal.EmployeeDAL;

public class LoginWindowController {
EmployeeDAL dal;
public LoginWindowController() {
	this.dal = new EmployeeDAL();
}

public boolean login(String username, String password) {
	boolean login = false;
	try {
		login = this.dal.validateEmployeeByLoginCredentials(username, password);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return login;
}

}
