package edu.westga.dbaccess.controller;

import java.sql.SQLException;

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
		Customer customer = this.customerDal.getCustomerWithFullName(first, last);
		return customer.getMemberID() + " " + customer.getFirstName() + " " + customer.getLastName();
	}

	public String getByPhoneNumber(String text) throws NumberFormatException, SQLException {
		Customer customer = this.customerDal.getCustomerWithPhoneNumber(text);
		return customer.getMemberID() + " " + customer.getFirstName() + " " + customer.getLastName();
	}

	public Customer getCustomerbyMemberId(String memberId) throws NumberFormatException, SQLException {
		Customer customer = this.customerDal.getCustomerWithMemberId(Integer.parseInt(memberId));
		return customer;
		
	}
}
