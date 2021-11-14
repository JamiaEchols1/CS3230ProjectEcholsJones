package edu.westga.dbaccess.view;

import edu.westga.dbaccess.model.Customer;
import edu.westga.dbaccess.model.Furniture;
import edu.westga.dbaccess.model.RentalTransaction;
import edu.westga.dbaccess.utils.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ReturnWindowCodeBehind {

    @FXML
    private ListView<Furniture> transactionListView;

    @FXML
    private ListView<Furniture> returnListView;

    @FXML
    private Button addToReturnButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button submitButton;

    @FXML
    private Label customerInformationLabel;

    @FXML
    private Label transactionNumberLabel;

	private int employeeId;

	private RentalTransaction transaction;

	private Customer customer;
	
	@FXML
	void initialize() {
		this.customerInformationLabel.setText(this.customerInformationLabel.getText() + " " + this.customer);
		this.transactionNumberLabel.setText(this.transactionNumberLabel.getText() + " " + this.transaction.getTransactionId());
	}

    @FXML
    void handleAddToReturnClick(ActionEvent event) {

    }

    @FXML
    void handleRemoveClick(ActionEvent event) {

    }

    @FXML
    void handleSubmitButtonClick(ActionEvent event) {

    }
    
    /**
     * Sets the employee id
     * 
     * @precondition employeeId >0
     * @postcondition employeeId = employeeId
     * 
     * @param employeeId the employeeId
     */
    public void setEmployee(int employeeId) {
    	if (employeeId < 0 ) {
    		throw new IllegalArgumentException(UI.ErrorMessages.ID_NEGATIVE);
    	}
    	this.employeeId = employeeId;
    }
    
    /**
     * Set the customerId
     * 
     * @precondition customerId > 0
     * 
     * @param customerId
     */
    public void setCustomer(Customer customer) {
    	if (customer == null) {
    		throw new IllegalArgumentException(UI.ErrorMessages.CUSTOMER_NULL);
    	}
    	this.customer = customer;
    }

    /**
     * Sets the transaction
     * 
     * @precondition transaction cannot be null
     * @postcondition none
     * 
     * @param transaction
     */
	public void setTransaction(RentalTransaction transaction) {
		if (transaction == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.NULL_TRANSACTION);
		}
		this.transaction = transaction;
	}

}
