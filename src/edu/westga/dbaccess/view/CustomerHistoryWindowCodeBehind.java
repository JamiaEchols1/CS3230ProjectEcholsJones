package edu.westga.dbaccess.view;

import java.sql.SQLException;

import edu.westga.dbaccess.dal.RentalTransactionDAL;
import edu.westga.dbaccess.dal.ReturnTransactionDAL;
import edu.westga.dbaccess.model.Customer;
import edu.westga.dbaccess.model.RentalTransaction;
import edu.westga.dbaccess.model.ReturnTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Customer History Window code Behind
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class CustomerHistoryWindowCodeBehind {

    @FXML
    private TextArea historyTextArea;

    @FXML
    private Button backToHomeButton;

    @FXML
    private Label customerInformationLabel;
    
    private RentalTransactionDAL rentalDAL;
    
    private ReturnTransactionDAL returnDAL;
    
    private WindowGenerator newWindow;

    @FXML
    void handleBackToHomeButtonClick(ActionEvent event) {
    	this.newWindow.generateWindow("Landing Window", "edu\\westga\\dbaccess\\view\\LandingWindow.fxml", event);
        
    }
    
    @FXML
    void initialize() throws SQLException {
    	this.rentalDAL = new RentalTransactionDAL();
    	this.returnDAL = new ReturnTransactionDAL();
    	this.newWindow = new WindowGenerator();
    	this.customerInformationLabel.setText(Customer.getCustomer().toString());
    	this.historyTextArea.setText(this.transactionHistory());
    }
    
    private String transactionHistory() throws SQLException {
    	String report = "Rental Transactions:" + System.lineSeparator();
    	for (RentalTransaction transaction: this.rentalDAL.getCustomersTransactions(Customer.getCustomer().getMemberID())) {
    		report += transaction.toString() + System.lineSeparator();
    	}
    	report += System.lineSeparator() + "Return Transactions:" + System.lineSeparator();
    	for (ReturnTransaction transaction : this.returnDAL.getCustomersTransactions(Customer.getCustomer().getMemberID())) {
    		report += transaction.toString() + System.lineSeparator();
    	}
    	return report;
    }
}