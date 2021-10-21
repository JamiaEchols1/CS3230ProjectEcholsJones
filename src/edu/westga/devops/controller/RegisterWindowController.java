package edu.westga.devops.controller;

import java.sql.Date;
import java.sql.SQLException;

import edu.westga.dbaccess.dal.CustomerDAL;

public class RegisterWindowController {
	private CustomerDAL customerDal;
	
	public RegisterWindowController() {
		this.customerDal = new CustomerDAL();
	}
	
	public void registerCustomer(String name, String gender, String address1, String address2, String phoneNumber, Date birthday, Date registrationDate) throws SQLException {
		this.customerDal.registerCustomer(name, gender, address1, address2, phoneNumber, birthday, registrationDate);
	}
}
