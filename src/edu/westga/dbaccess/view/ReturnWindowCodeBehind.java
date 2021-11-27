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
import edu.westga.dbaccess.dal.RentalTransactionDAL;
import edu.westga.dbaccess.dal.ReturnTransactionDAL;
import edu.westga.dbaccess.model.Customer;
import edu.westga.dbaccess.model.Employee;
import edu.westga.dbaccess.model.Furniture;
import edu.westga.dbaccess.model.Item;
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
import javafx.scene.control.ComboBox;
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
    private ComboBox<RentalTransaction> transactionComboBox;

    @FXML
    private ListView<Furniture> transactionListView;

    @FXML
    private ListView<Entry<Furniture, int[]>> returnListView;

    @FXML
    private Button addToReturnButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button submitButton;
    
    @FXML
    private Label finesLabel;

    @FXML
    private Label customerInformationLabel;

    @FXML
    private Label transactionNumberLabel;

	private RentalTransaction transaction;
	
	private RentalItemDAL rentalDal;
	
	private FurnitureDAL furnitureDal;
	
	private ReturnTransactionDAL transactionDal;

	private Customer customer;
	
	private HashMap<Furniture, int[]> returnCart;
	
	private double fineCost;
	
	private WindowGenerator newWindow;
	
	private RentalTransactionDAL rentalTransactionDal;
	
	/**
	 * Initializes the return window code behind
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public ReturnWindowCodeBehind() {
		this.rentalDal = new RentalItemDAL();
		this.furnitureDal = new FurnitureDAL();
		this.rentalTransactionDal = new RentalTransactionDAL();
		this.transactionDal = new ReturnTransactionDAL();
		this.returnCart = new HashMap<Furniture, int[]>();
		this.newWindow = new WindowGenerator();
		this.fineCost = 0.0;
	}
	
	@FXML
	void initialize() throws SQLException {
		this.customerInformationLabel.setText(this.customerInformationLabel.getText() + " " + this.customer);
		this.transactionComboBox.getItems().setAll(this.rentalTransactionDal.getCustomersTransactions(this.customer.getMemberID()));
   	 	this.transactionComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
   	 		if (newValue != null) {
   	 			try {
   	 				this.transaction = newValue;
   	 				this.transactionListView.getItems().setAll(this.getFurniture(newValue));
   	 			} catch (SQLException e) {
   	 				e.printStackTrace();
   	 			}
   	 		} 
   	 	});
	}

	private List<Furniture> getFurniture(RentalTransaction transaction) throws SQLException {
		List<Item> items = this.rentalDal.rentalItems(transaction.getTransactionId());
		List<Furniture> furniture = new ArrayList<Furniture>();
		
		for (Item item : items) {
			for (int i = 0; i < item.getQuantity(); i++) {
				furniture.add(this.furnitureDal.getFurnitureById(item.getFurnitureId()));
			}
		}
		
		return furniture;
	}
	
    @FXML
    void handleAddToReturnClick(ActionEvent event) {
    	Furniture furniture = this.transactionListView.getSelectionModel().getSelectedItem();
    			
    	if (furniture != null) {
    		if (!this.returnCart.containsKey(furniture)) {
					this.returnCart.put(furniture, new int[] {this.transaction.getTransactionId(), 1});
    			} else {
    				int quantity = this.returnCart.get(furniture)[1];
    				quantity++;
    				
    				this.returnCart.put(furniture, new int[] {this.transaction.getTransactionId(), quantity});
    			}
    			furniture.setQuantity(furniture.getQuantity()+1);
    			this.transactionListView.getItems().remove(furniture);
    	
    			this.returnListView.getItems().setAll(this.returnCart.entrySet());
    			this.transactionListView.getSelectionModel().clearSelection();
    		if (LocalDate.now().compareTo(this.transaction.getDueDate().toLocalDate()) > 0) {
    			this.fineCost += furniture.getPrice();
    			this.finesLabel.setText("Fines: $" + this.fineCost);
    		}
    	}
    }
    
    @FXML
    void handleRemoveClick(ActionEvent event) {
    	for (Entry<Furniture, int[]> furniture: this.returnListView.getSelectionModel().getSelectedItems()) {
    		if (this.returnCart.get(furniture.getKey())[1] > 1) {
    			this.returnCart.put(furniture.getKey(), new int[] {this.returnCart.get(furniture.getKey())[0],this.returnCart.get(furniture.getKey())[1] -1});
    		} else {
    			this.returnCart.remove(furniture.getKey());
    		}
    		this.transactionListView.getItems().add(furniture.getKey());
    		if (LocalDate.now().compareTo(this.transaction.getDueDate().toLocalDate()) > 0) {
    			this.fineCost -= furniture.getKey().getPrice();
    			this.finesLabel.setText("Fines: $" + this.fineCost);
    		}
    	}
    	this.returnListView.getItems().setAll(this.returnCart.entrySet());
    }

    @FXML
    void handleBackButtonClick(ActionEvent event) {
    	this.newWindow.generateWindow("Registration Window", "edu\\westga\\dbaccess\\view\\LandingWindow.fxml", event);
    }
    
    @FXML
    void handleSubmitButtonClick(ActionEvent event) throws SQLException {
    	int transactionId = this.transactionDal.getSizeOfTable() + 1;
    	String returnItems = "";
    	LocalDate date = LocalDate.now();
    	java.sql.Date sqlDate = java.sql.Date.valueOf(date.toString());
    	for (Entry<Furniture, int[]> furniture: this.returnListView.getItems()) {
    		returnItems += ",(" + furniture.getValue()[0] + "," + transactionId + ",'" + sqlDate.toString() + "'," + furniture.getKey().getFurnitureId() + "," + furniture.getValue()[1] + ")";
    	}
    	this.transactionDal.createReturnTransaction(transactionId, sqlDate, this.customer.getMemberID(), Employee.getEmployee().getEmployeeId(), returnItems.replaceFirst(",", ""));
    	
    	Parent root;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\TransactionSummaryWindow.fxml"));

			root = loader.load();

			TransactionWindowCodeBehind codeBehind = loader.getController();
			
			List<Item> items = this.rentalDal.rentalItems(transactionId);
			
			codeBehind.setTransactionText((Object) this.transactionDal.getReturnTransaction(transactionId), items);
			
			Stage stage = new Stage();

			stage.setTitle("Summary Window");

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

}
