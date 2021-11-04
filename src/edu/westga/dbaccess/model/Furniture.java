package edu.westga.dbaccess.model;

import edu.westga.dbaccess.utils.UI;

/**
* The furniture class
* 
*@author Jamia Echols
*@version Fall 2021
**/
public class Furniture {

	private int furnitureId;
	private int styleId;
	private int categoryId;
	private int quantity;
	private double price;

	/**
	* Creates a furniture
	*
	*@precondition furnitureId > 0 and price >= 0.0 and styleId > 0 and categoryId > 0 and quantity > 0
	*@postcondition getFurnitureId() == furnitureId and getPrice() == price and getStyleId() == styleId 
	*				and getCategoryId() == categoryId and getQuantity() == quantity
	*
	*@throws IllegalArgumentException if preconditions arent met.
	*/
	public Furniture(int furnitureId, double price, int styleId, int categoryId, int quantity) {
		if (furnitureId <= 0) {
			throw new IllegalArgumentException(UI.ErrorMessages.FURNITUREID_NEGATIVE);
		}
		if (price < 0.0) {
			throw new IllegalArgumentException(UI.ErrorMessages.PRICE_NEGATIVE);
		}
		if (styleId <= 0) {
			throw new IllegalArgumentException(UI.ErrorMessages.STYLE_NEGATIVE);
		}
		if (categoryId <= 0) {
			throw new IllegalArgumentException(UI.ErrorMessages.CATEGORY_NEGATIVE);
		}
		if (quantity < 0) {
			throw new IllegalArgumentException(UI.ErrorMessages.QUANTITY_NEGATIVE);
		}
		this.furnitureId = furnitureId;
		this.price = price;
		this.styleId = styleId;
		this.categoryId = categoryId;
		this.quantity = quantity;
	}

	/**
	* Gets the Furniture
	*
	*@precondition none
	*@postcondition none
	*
	*@return the furniture id
	**/
	public int getFurnitureId() {
		return this.furnitureId;
	}

	/**
	* Gets the price
	*
	*@precondition none
	*@postcondition nonw
	*
	*@return the price
	**/
	public double getPrice() {
		return this.price;
	}

	/**
	* Gets the style id
	*
	* @precondition none
	* @postcondition none
	*
	* @return the style
	**/
	public int getStyleId() {
		return this.styleId;
	}

	/**
	* Get the category id
	*
	* @precondtition none
	* @posticondition none
	*
	* @return the category
	**/
	public int getCategoryId() {
		return this.categoryId;
	}

	/**
	* Gets the quantity
	*
	* @precondition none
	* @posticondition none
	*
	* @return the quantity
	**/
	public int getQuantity() {
		return this.quantity;
	}
	
	@Override
	public String toString() {
		return "Furniture Id: " + this.furnitureId + " Price: " + this.price + " Category ID: " + this.categoryId + " Style ID: " + this.styleId;
	}
}