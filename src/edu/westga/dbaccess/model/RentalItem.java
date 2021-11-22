package edu.westga.dbaccess.model;

/**
 * The class rental Item
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class RentalItem {
	
	private int transactionId;
	private int furnitureId;
	private int quantity;
	
	/**
	 * Creates a new rental Item
	 * 
	 * @param transactionId the transaction id
	 * @param furnitureId the furniture id
	 * @param quantity the quantity
	 */
	public RentalItem(int transactionId, int furnitureId, int quantity) {
		this.transactionId = transactionId;
		this.furnitureId = furnitureId;
		this.quantity = quantity;
	}
	
	/**
	 * Gets the transaction Id
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the transaction Id
	 */
	public int getTransactionId() {
		return this.transactionId;
	}

	/**
	 * Gets the furniture Id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the furniture id
	 */
	public int getFurnitureId() {
		return this.furnitureId;
	}

	/**
	 * Gets the quantity
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public String toString() {
		return "RentalItem [transactionId=" + transactionId + ", furnitureId=" + furnitureId + ", quantity=" + quantity
				+ "]";
	}

}
