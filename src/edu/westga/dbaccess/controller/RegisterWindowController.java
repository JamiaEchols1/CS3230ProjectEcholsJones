package edu.westga.dbaccess.controller;

import java.sql.Date;
import java.sql.SQLException;

import edu.westga.dbaccess.dal.CustomerDAL;

public class RegisterWindowController {
	private CustomerDAL customerDal;
	
	/**
	 * Constructor for the register window controller
	 */
	public RegisterWindowController() {
		this.customerDal = new CustomerDAL();
	}

	/**
	 * Registers a customer
	 * @param name
	 * @param gender
	 * @param address1
	 * @param address2
	 * @param phoneNumber
	 * @param birthday
	 * @param registrationDate
	 * @throws SQLException
	 */
	public void registerCustomer(String name, String gender, String address1, String address2, String phoneNumber, Date birthday, Date registrationDate) throws SQLException {
		this.customerDal.registerCustomer(name, gender, address1, address2, phoneNumber, birthday, registrationDate);
	}
}
