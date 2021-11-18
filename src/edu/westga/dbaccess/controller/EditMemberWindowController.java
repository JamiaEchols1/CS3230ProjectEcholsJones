package edu.westga.dbaccess.controller;

import java.sql.Date;
import java.sql.SQLException;

import edu.westga.dbaccess.dal.CustomerDAL;

public class EditMemberWindowController {
	private CustomerDAL customerDal;
	
	public EditMemberWindowController() {
		this.customerDal = new CustomerDAL();
	}

	public void editCustomer(int memberID, String firstName, String lastName, String zipcode, String state, String city, String gender, String address1, String phoneNumber, Date birthday) throws SQLException {
		this.customerDal.editCustomer(memberID, firstName, lastName, zipcode, state, city, gender, address1, phoneNumber, birthday);
		// TODO Auto-generated method stub
		
	}
}
