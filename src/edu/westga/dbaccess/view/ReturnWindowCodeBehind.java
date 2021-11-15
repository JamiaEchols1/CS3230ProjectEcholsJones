package edu.westga.dbaccess.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.westga.dbaccess.dal.FurnitureDAL;
import edu.westga.dbaccess.dal.RentalItemDAL;
import edu.westga.dbaccess.model.Customer;
import edu.westga.dbaccess.model.Furniture;
import edu.westga.dbaccess.model.RentalItem;
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
	
	private RentalItemDAL rentalDal;
	
	private FurnitureDAL furnitureDal;

	private Customer customer;
	

	public ReturnWindowCodeBehind() {
		this.rentalDal = new RentalItemDAL();
		this.furnitureDal = new FurnitureDAL();
	}
	
	@FXML
	void initialize() throws SQLException {
		this.customerInformationLabel.setText(this.customerInformationLabel.getText() + " " + this.customer);
		this.transactionNumberLabel.setText(this.transactionNumberLabel.getText() + " " + this.transaction.getTransactionId());
		this.transactionListView.getItems().setAll(this.getFurniture());
	}

	private List<Furniture> getFurniture() throws SQLException {
		List<RentalItem> items = this.rentalDal.rentalItems(this.transaction.getTransactionId());
		List<Furniture> furniture = new ArrayList<Furniture>();
		
		for (RentalItem item : items) {
			furniture.add(this.furnitureDal.getFurniture(item.getFurnitureId()));
		}
		
		return furniture;
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
