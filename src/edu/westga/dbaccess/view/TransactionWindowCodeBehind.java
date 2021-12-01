package edu.westga.dbaccess.view;

import java.util.List;

import edu.westga.dbaccess.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * The Transaction Summary window
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class TransactionWindowCodeBehind {

    @FXML
    private TextArea transactionSummaryTextArea;

    @FXML
    private Button backToHomeButton;
    
    private WindowGenerator newWindow;

    @FXML
    void initialize() {
    	this.newWindow = new WindowGenerator();
    }

    @FXML
    void handleBackToHomeButtonClick(ActionEvent event) {
    	this.newWindow.generateWindow("Landing Window", "edu\\westga\\dbaccess\\view\\LandingWindow.fxml", event);
    }
    
    /**
     * Sets the transaction text
     * 
     * @precondition none
     * @postcondition none
     * 
     * @param transaction the transaction
     * @param items the items in the transaction
     */
    public void setTransactionText(Object transaction, List<Item> items) {
    	String transactionSummary = transaction.toString() + System.lineSeparator();
    	for (Object item : items) {
    		transactionSummary += item.toString() + System.lineSeparator();
    	}
    	this.transactionSummaryTextArea.setText(transactionSummary);
    }

}

