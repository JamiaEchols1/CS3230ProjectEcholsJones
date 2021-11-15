package edu.westga.dbaccess.model;

public class RentalItem {
	
	private int transactionId;
	private int furnitureId;
	private int quantity;
	
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

	
}
