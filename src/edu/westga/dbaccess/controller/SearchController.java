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
	public String getbyMemberId(String text) throws NumberFormatException, SQLException {
		Customer customer = this.customerDal.getCustomerWithMemberId(Integer.parseInt(text));
		System.out.println(customer.getMemberID() + " " + customer.getFirstName() + " " + customer.getLastName());
		return customer.getMemberID() + " " + customer.getFirstName() + " " + customer.getLastName();

	}

	/**
	 * Gets the customer's full name
	 * 
	 * @param first the first name
	 * @param last the last name
	 * 
	 * @return a string containing the customer's member Id, first name, and last name
	 * 
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public String getByFullName(String first, String last) throws NumberFormatException, SQLException {
		ArrayList<Customer> customers = this.customerDal.getCustomerWithFullName(first, last);
		String customerString = "";
		for (Customer customer : customers) {
			customerString += customer.getMemberID() + " " + customer.getFirstName() + " " + customer.getLastName()
					+ ",";
		}
		return customerString;
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
	public String getByPhoneNumber(String text) throws NumberFormatException, SQLException {
		ArrayList<Customer> customers = this.customerDal.getCustomerWithPhoneNumber(text);
		String customerString = "";
		for (Customer customer : customers) {
			customerString += customer.getMemberID() + " " + customer.getFirstName() + " " + customer.getLastName()
					+ ",";
		}
		return customerString;
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
	public Customer getCustomerbyMemberId(String memberId) throws NumberFormatException, SQLException {
		Customer customer = this.customerDal.getCustomerWithMemberId(Integer.parseInt(memberId));
		return customer;

	}
}
