package edu.westga.dbaccess.model;

import edu.westga.dbaccess.utils.UI;

/**
 * The class category
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class Category {

	private int categoryId;
	private String label;
	
	/**
	 * Creates a new category
	 * 
	 * @precondition categoryId cannot be negative and label cannot be null and label cannot be empty
	 * @postcondition getCategoryId() == categoryId and getLabel() == label
	 * 
	 * @param categoryId the category id
	 * @param label the label
	 */
	public Category(int categoryId, String label) {
		if (categoryId < 0) {
			throw new IllegalArgumentException(UI.ErrorMessages.CATEGORY_NEGATIVE);
		}
		if (label == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.LABEL_NULL);
		}
		if (label.isEmpty()) {
			throw new IllegalArgumentException(UI.ErrorMessages.LABEL_EMPTY);
		}
		this.categoryId = categoryId;
		this.label = label;
	}
	
	/**
	 * Get the Category Id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the category id
	 */
	public int getCategoryId() {
		return this.categoryId;
	}
	
	/**
	 * Gets the label
	 * 
	 *  @precondition none
	 *  @postcondition none
	 *  
	 * @return the label
	 */
	public String getLabel() {
		return this.label;
	}
}
