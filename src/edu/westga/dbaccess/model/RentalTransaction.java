package edu.westga.dbaccess.model;

import java.sql.Date;

/**
 * The Rental Transaction
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class RentalTransaction {
	private int transactionId;
	private Date dueDate;
	private Date transactionDate;
	private int customerId;
	private int employeeeId;
	
	/**
	 * Creates a new rental transaction
	 * 
	 * @precondition none
	 * @postcondition getTransactionId() == transactionId && getDueDate() == dueDate && getTransactionDate() == transactionDate && getCustomerID() == customerId && getEmployyeeId() == employeeId
	 * 
	 * @param transactionId the transaction Id
	 * @param dueDate the due date
	 * @param transactionDate the transaction date
	 * @param customerId the customer Id
	 * @param employeeId the employee id
	 */
	public RentalTransaction(int transactionId, Date dueDate, Date transactionDate, int customerId, int employeeId) {
		this.transactionId = transactionId;
		this.dueDate = dueDate;
		this.transactionDate = transactionDate;
		this.customerId = customerId;
		this.employeeeId = employeeId;
	}
	
	/**
	 * Gets the transaction Id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the transactionId
	 */
	public int getTransactionId() {
		return this.transactionId;
	}

	/**
	 * Gets the due date
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return this.dueDate;
	}

	/**
	 * Gets the transaction date
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return this.transactionDate;
	}

	/**
	 * Gets the customer id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the customerId
	 */
	public int getCustomerId() {
		return this.customerId;
	}

	/**
	 * Gets the employee Id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the employeeeId
	 */
	public int getEmployeeeId() {
		return this.employeeeId;
	}

}
