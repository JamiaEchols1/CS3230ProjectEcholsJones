package edu.westga.dbaccess.controller;

import java.sql.Date;
import java.sql.SQLException;

import edu.westga.dbaccess.dal.CustomerDAL;

/**
 * The class editmember window controller
 * 
 * @author Rasheed Jones
 * @version Fall 2021
 */
public class EditMemberWindowController {
	private CustomerDAL customerDal;
	
	/**
	 * Initializes the EditMemberWindowController
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public EditMemberWindowController() {
		this.customerDal = new CustomerDAL();
	}

	/**
	 * Edit the customer
	 * 
	 * @param memberID the member id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param zipcode the zipcode
	 * @param state the state
	 * @param city the city
	 * @param gender the gender
	 * @param address1 the address
	 * @param phoneNumber the phone number
	 * @param birthday the birthday
	 * 
	 * @throws SQLException
	 */
	public void editCustomer(int memberID, String firstName, String lastName, String zipcode, String state, String city, String gender, String address1, String phoneNumber, Date birthday) throws SQLException {
		this.customerDal.editCustomer(memberID, firstName, lastName, zipcode, state, city, gender, address1, phoneNumber, birthday);
		// TODO Auto-generated method stub
		
	}
}
