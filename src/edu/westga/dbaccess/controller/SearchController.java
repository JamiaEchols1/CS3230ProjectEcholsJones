package edu.westga.dbaccess.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.westga.dbaccess.dal.CustomerDAL;
import edu.westga.dbaccess.model.Customer;

public class SearchController {

	private CustomerDAL customerDal;

	public SearchController() {
		this.customerDal = new CustomerDAL();
	}

	public String getbyMemberId(String text) throws NumberFormatException, SQLException {
		Customer customer = this.customerDal.getCustomerWithMemberId(Integer.parseInt(text));
		System.out.println(customer.getMemberID() + " " + customer.getFirstName() + " " + customer.getLastName());
		return customer.getMemberID() + " " + customer.getFirstName() + " " + customer.getLastName();

	}

	public String getByFullName(String first, String last) throws NumberFormatException, SQLException {
		ArrayList<Customer> customers = this.customerDal.getCustomerWithFullName(first, last);
		String customerString = "";
		for (Customer customer : customers) {
			customerString += customer.getMemberID() + " " + customer.getFirstName() + " " + customer.getLastName()
					+ ",";
		}
		return customerString;
	}

	public String getByPhoneNumber(String text) throws NumberFormatException, SQLException {
		ArrayList<Customer> customers = this.customerDal.getCustomerWithPhoneNumber(text);
		String customerString = "";
		for (Customer customer : customers) {
			customerString += customer.getMemberID() + " " + customer.getFirstName() + " " + customer.getLastName()
					+ ",";
		}
		return customerString;
	}

	public Customer getCustomerbyMemberId(String memberId) throws NumberFormatException, SQLException {
		Customer customer = this.customerDal.getCustomerWithMemberId(Integer.parseInt(memberId));
		return customer;

	}
}
