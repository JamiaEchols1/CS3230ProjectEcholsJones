package edu.westga.dbaccess.model;

import edu.westga.dbaccess.utils.UI;

/**
 * The class Style
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class Style {

	private int styleId;
	private String label;
	
	/**
	 * Creates a new style
	 * 
	 * @precondition styleId cannot be negative and label cannot be null and label cannot be empty
	 * @postcondition getStyleId() == styleId and getLabel() == label
	 * 
	 * @param styleId the style id
	 * @param label the label
	 */
	public Style(int styleId, String label) {
		if (styleId < 0) {
			throw new IllegalArgumentException(UI.ErrorMessages.STYLE_NEGATIVE);
		}
		if (label == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.LABEL_NULL);
		}
		if (label.isEmpty()) {
			throw new IllegalArgumentException(UI.ErrorMessages.LABEL_EMPTY);
		}
		this.styleId = styleId;
		this.label = label;
	}
	
	/**
	 * Get the style Id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the style id
	 */
	public int getStyleId() {
		return this.styleId;
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
