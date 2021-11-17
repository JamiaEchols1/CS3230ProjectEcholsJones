package edu.westga.dbaccess.model;

import java.sql.Date;

/**
 * Return transaction class
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class ReturnTransaction {
	private int transactionId;
	private Date date;
	private int memberId;
	private int customerId;
	
	/**
	 * Creates a new rental Transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param transactionId the transaction id
	 * @param date the date
	 * @param memberId the memberId
	 * @param customerId the customerId
	 */
	public ReturnTransaction(int transactionId, Date date, int memberId, int customerId) {
		this.transactionId = transactionId;
		this.date = date;
		this.memberId = memberId;
		this.customerId = customerId;
	}

	/**
	 * Gets the transaction Id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the transaction id
	 */
	public int getTransactionId() {
		return this.transactionId;
	}

	/**
	 * Gets the date
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * Gets the memberId
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the memberId
	 */
	public int getMemberId() {
		return this.memberId;
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
	
	
}
