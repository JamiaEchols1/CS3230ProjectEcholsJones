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
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param address1
	 * @param zipcode
	 * @param state
	 * @param city
	 * @param phoneNumber
	 * @param birthday
	 * @param registrationDate
	 * @throws SQLException
	 */
	public void registerCustomer(String firstName, String lastName, String zipcode, String state, String city, String gender, String address1, String phoneNumber, Date birthday, Date registrationDate) throws SQLException {
		this.customerDal.registerCustomer(firstName, lastName, zipcode, state, city, gender, address1, phoneNumber, birthday, registrationDate);
	}
}
