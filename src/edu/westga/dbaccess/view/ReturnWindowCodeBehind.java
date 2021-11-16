package edu.westga.dbaccess.view;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import edu.westga.dbaccess.dal.FurnitureDAL;
import edu.westga.dbaccess.dal.RentalItemDAL;
import edu.westga.dbaccess.dal.ReturnItemDAL;
import edu.westga.dbaccess.dal.ReturnTransactionDAL;
import edu.westga.dbaccess.model.Customer;
import edu.westga.dbaccess.model.Furniture;
import edu.westga.dbaccess.model.RentalItem;
import edu.westga.dbaccess.model.RentalTransaction;
import edu.westga.dbaccess.utils.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Class return window code behind
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class ReturnWindowCodeBehind {

    @FXML
    private ListView<Furniture> transactionListView;

    @FXML
    private ListView<Entry<Furniture, Integer>> returnListView;

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
	
	private ReturnTransactionDAL transactionDal;
	
	private ReturnItemDAL itemDal;

	private Customer customer;
	
	private HashMap<Furniture, Integer> returnCart;
	

	public ReturnWindowCodeBehind() {
		this.rentalDal = new RentalItemDAL();
		this.furnitureDal = new FurnitureDAL();
		this.transactionDal = new ReturnTransactionDAL();
		this.itemDal = new ReturnItemDAL();
		this.returnCart = new HashMap<Furniture, Integer>();
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
			for (int i = 0; i < item.getQuantity(); i++) {
				furniture.add(this.furnitureDal.getFurnitureById(item.getFurnitureId()));
			}
		}
		
		return furniture;
	}
	
    @FXML
    void handleAddToReturnClick(ActionEvent event) {
    	Furniture furniture =this.transactionListView.getSelectionModel().getSelectedItem();
    			if (!this.returnCart.containsKey(furniture)) {
					this.returnCart.put(furniture, 1);
    			} else {
    				int quantity = this.returnCart.get(furniture);
    				quantity++;
    				this.returnCart.put(furniture, quantity);
    			}
    			furniture.setQuantity(furniture.getQuantity()+1);
    			this.transactionListView.getItems().remove(furniture);
    	
    	this.returnListView.getItems().setAll(this.returnCart.entrySet());
    	this.transactionListView.getSelectionModel().clearSelection();
    }

    @FXML
    void handleRemoveClick(ActionEvent event) {

    	for (Entry<Furniture, Integer> furniture: this.returnListView.getSelectionModel().getSelectedItems()) {
    		if (this.returnCart.get(furniture.getKey()) > 1) {
    			this.returnCart.put(furniture.getKey(), this.returnCart.get(furniture.getKey())-1);
    		} else {
    			this.returnCart.remove(furniture.getKey());
    		}
    		this.transactionListView.getItems().add(furniture.getKey());
    	}
    	this.returnListView.getItems().setAll(this.returnCart.entrySet());
    }

    @FXML
    void handleSubmitButtonClick(ActionEvent event) throws SQLException {
    	int transactionId = this.transactionDal.getSizeOfTable() + 1;
    	this.transactionDal.createReturnTransaction(transactionId, java.sql.Date.valueOf(LocalDate.now()), this.customer.getMemberID(), this.employeeId);
    	for (Entry<Furniture, Integer> furniture: this.returnListView.getItems()) {
    		this.itemDal.createReturnItem(transactionId, furniture.getKey().getFurnitureId(), this.transaction.getTransactionId(), java.sql.Date.valueOf(LocalDate.now()), furniture.getValue());
    	}
    		Parent root;

    		try {

    			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\LandingWindow.fxml"));

    			System.out.println(getClass().getResource("edu\\westga\\devops\\view\\LandingWindow.fxml"));

    			root = loader.load();


    			Stage stage = new Stage();

    			stage.setTitle("Registration Window");

    			stage.setScene(new Scene(root, 452, 440));

    			stage.show();

    			Node node = ((Node)(event.getSource()));

    			Stage thisStage = (Stage) node.getScene().getWindow();

    			thisStage.close();

    		} catch (IOException e) {

                e.printStackTrace();

            }
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
