package edu.westga.dbaccess.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.westga.dbaccess.dal.CustomerDAL;
import edu.westga.dbaccess.model.Customer;

/**
 * The search controller
 * 
 * @author Rasheed Jones
 * @version Fall 2021
 *
 */
public class SearchController {

	private CustomerDAL customerDal;
	
	/**
	 * Initializes the search controller
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public SearchController() {
		this.customerDal = new CustomerDAL();
	}

	/**
	 * Gets the user by the member id
	 * 
	 * @param text the user id
	 * 
	 * @return a string containing the customer's member Id, first name, and last name
	 * 
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public Customer getbyMemberId(String text) throws NumberFormatException, SQLException {
		Customer customer = this.customerDal.getCustomerWithMemberId(Integer.parseInt(text));
		return customer;

	}

	/**
	 * Gets the customer's full name
	 * 
	 * @param first the first name
	 * @param last the last name
	 * 
	 * @return a list of customers with the given full name
	 * 
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public ArrayList<Customer> getByFullName(String first, String last) throws NumberFormatException, SQLException {
		ArrayList<Customer> customers = this.customerDal.getCustomerWithFullName(first, last);
		return customers;
	}

	/**
	 * Gets the customers phone number
	 * 
	 * @param text the phone number
	 * 
	 * @return a string containing the customer's member Id, first name, and last name
	 * 
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public ArrayList<Customer> getByPhoneNumber(String text) throws NumberFormatException, SQLException {
		ArrayList<Customer> customers = this.customerDal.getCustomerWithPhoneNumber(text);
		return customers;
	}

	/**
	 * Gets the customer by member id
	 * 
	 * @param memberId the member id
	 * 
	 * @return the customer
	 * 
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public Customer getCustomerbyMemberId(int memberId) throws NumberFormatException, SQLException {
		Customer customer = this.customerDal.getCustomerWithMemberId(memberId);
		return customer;

	}
}
