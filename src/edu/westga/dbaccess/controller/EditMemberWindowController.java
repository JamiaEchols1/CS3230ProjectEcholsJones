package edu.westga.dbaccess.controller;

import edu.westga.dbaccess.dal.CustomerDAL;

public class EditMemberWindowController {
	private CustomerDAL customerDal;
	
	public EditMemberWindowController() {
		this.customerDal = new CustomerDAL();
	}
}
