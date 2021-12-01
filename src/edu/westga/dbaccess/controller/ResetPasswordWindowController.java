package edu.westga.dbaccess.controller;

import edu.westga.dbaccess.dal.EmployeeDAL;

/**
 * The reset password window controller
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class ResetPasswordWindowController {
	
	private EmployeeDAL employeeDal;
	
	/**
	 * Initialize the reset password controller
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public ResetPasswordWindowController() {
		this.employeeDal = new EmployeeDAL();
	}

	/**
	 * Resets the employees password
	 * 
	 * @param employeeId the employee's id
	 * @param newPassword the new password
	 * @throws Exception 
	 */
	public void resetPassword(int employeeId, String password, String newPassword) throws Exception {
		this.employeeDal.resetPassword(employeeId, password, newPassword);
	}
}
