package edu.westga.dbaccess.controller;

import java.sql.SQLException;

import edu.westga.dbaccess.dal.EmployeeDAL;
import edu.westga.dbaccess.model.Employee;

/**
 * The edit employee window controller
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class EditEmployeeWindowController {
	
	private EmployeeDAL employeeDal;
	
	/**
	 * Initializes a new edit employee window controller
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public EditEmployeeWindowController() {
		this.employeeDal = new EmployeeDAL();
	}
	
	/**
	 * Edits the employee
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param employeeID
	 * @param firstName
	 * @param lastName
	 * @param address1
	 * @param zipcode
	 * @param state
	 * @param city
	 * @param phoneNumber
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public Employee edit(int employeeID, String firstName, String lastName, String address1,
				String zipcode, String state, String city, String phoneNumber, String username, String password) throws SQLException {
			
		this.employeeDal.editEmployee(employeeID, firstName, lastName, address1, zipcode, state, city, phoneNumber, username, password);
		Employee.setEmployee(this.employeeDal.getEmployeeByEmployeeId(employeeID));
		return Employee.getEmployee();
	}

}
