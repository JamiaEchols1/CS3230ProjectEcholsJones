package edu.westga.dbaccess.view;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import edu.westga.dbaccess.dal.CategoryDAL;
import edu.westga.dbaccess.dal.FurnitureDAL;
import edu.westga.dbaccess.dal.RentalTransactionDAL;
import edu.westga.dbaccess.dal.StyleDAL;
import edu.westga.dbaccess.dal.RentalItemDAL;
import edu.westga.dbaccess.model.Customer;
import edu.westga.dbaccess.model.Employee;
import edu.westga.dbaccess.model.Furniture;
import edu.westga.dbaccess.model.Item;
import edu.westga.dbaccess.utils.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The furniture shop window code behing
 * 
 * @author Jamia Echols
 * @version Fall 2021
 */
public class FurnitureShopCodeBehind {

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> styleFilterComboBox;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ListView<Furniture> furnitureListView;

    @FXML
    private Label customerInformationLabel;
    
    private FurnitureDAL furnitureDal;
    
    private CategoryDAL categoryDal;
    
    private StyleDAL styleDal;
    
    private RentalItemDAL itemsDal;
    
    private RentalTransactionDAL transactionDal;

    private HashMap<Integer, String> styles;
    
    private HashMap<Integer, String> categories;

    private HashMap<Furniture, Integer> rentalCart;
    
    private int employeeId;
    
    private Employee employee;
    
    private int customerId;
    
    @FXML
    private Button searchCategoryButton;

    @FXML
    private Button addToCartButton;

    @FXML
    private Button checkoutButton;

    @FXML
    private Button searchStyleButton;

    @FXML
    private ListView<Entry<Furniture, Integer>> cartListView;

    @FXML
    private Button removeButton;

    @FXML
    private Label costLabel;
    
    @FXML
    private Button backButton;
    
    private double cartCost;
    
    /**
     * Initializes the Furniture search code behind;
     * 
     * @precondition none
     * 
     */
    public FurnitureShopCodeBehind () {
        this.furnitureDal = new FurnitureDAL();
        this.categoryDal = new CategoryDAL();
        this.styleDal = new StyleDAL();
        this.itemsDal = new RentalItemDAL();
        this.transactionDal = new RentalTransactionDAL();
        this.styles = new HashMap<Integer, String>();
        this.categories = new HashMap<Integer, String>();
        this.rentalCart = new HashMap<Furniture, Integer>();
        this.employeeId = 0;
        this.customerId = 0;
        this.cartCost = 0.0;
    }

    @FXML
    void initialize() throws SQLException {
    	this.furnitureListView.getItems().setAll(this.furnitureDal.getAllFurniture());
    	this.styleFilterComboBox.getItems().setAll(this.styleDal.getStyles().values());
    	this.categoryComboBox.getItems().setAll(this.categoryDal.getCategory().values());
    	this.styles = this.styleDal.getStyles();
    	this.categories = this.categoryDal.getCategory();
    }

    @FXML
    void handleBackButtonClick(ActionEvent event) {
    	Parent root;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\LandingWindow.fxml"));

			root = loader.load();

			LandingWindowCodeBehind landingWindow = loader.getController();
			
			landingWindow.setTitle(this.employee.getFullName(), this.employee.getUsername(), this.employee.getEmployeeId());
			
			landingWindow.setEmployee(this.employee);
			
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
    
    @FXML
    void handleAddToCartButtonClick(ActionEvent event) throws SQLException {
    	for (Furniture furniture: this.furnitureListView.getSelectionModel().getSelectedItems()) {
    		if (furniture.getQuantity() > 0) {
    			if (!this.rentalCart.containsKey(furniture)) {
					this.rentalCart.put(furniture, 1);
    				this.cartCost += furniture.getPrice();
    			} else {
    				int quantity = this.rentalCart.get(furniture);
    				quantity++;
    				this.rentalCart.put(furniture, quantity);
    				this.cartCost += furniture.getPrice();
    			}
    			furniture.setQuantity(furniture.getQuantity()-1);
    		} else {
    			Alert alert = new Alert(AlertType.ERROR, UI.ErrorMessages.FURNITURE_SOLD_OUT);
                alert.show();
    		}
    	}
    	this.costLabel.setText( "Total: " + this.cartCost);
    	this.cartListView.getItems().setAll(this.rentalCart.entrySet());
    	this.furnitureListView.getSelectionModel().clearSelection();
    }
    
    @FXML
    void handleRemoveButtonClick(ActionEvent event) {
    	for (Entry<Furniture, Integer> furniture: this.cartListView.getSelectionModel().getSelectedItems()) {
    		if (this.rentalCart.get(furniture.getKey()) > 1) {
    			this.rentalCart.put(furniture.getKey(), this.rentalCart.get(furniture.getKey())-1);
    			this.cartCost -= furniture.getKey().getPrice();
    		} else {
    			this.rentalCart.remove(furniture.getKey());
    			this.cartCost -= furniture.getKey().getPrice();
    		}
    	}
    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
    	String moneyString = formatter.format(this.cartCost);
    	this.costLabel.setText( "Total: " + moneyString);
    	this.cartListView.getItems().setAll(this.rentalCart.entrySet());
    	this.cartListView.getSelectionModel().clearSelection();
    
    }

    @FXML
    void handleSearchCategoryButtonClick(ActionEvent event) throws SQLException {
     	this.furnitureListView.getItems().setAll(this.furnitureDal.getFurnitureByCategory(this.getCategoryId(this.categoryComboBox.getSelectionModel().getSelectedItem().toString())));        
    }

    @FXML
    void handleSearchStyleButtonClick(ActionEvent event) throws SQLException{
     	this.furnitureListView.getItems().setAll(this.furnitureDal.getFurnitureByStyle(this.getStyleId(this.styleFilterComboBox.getSelectionModel().getSelectedItem().toString())));
    }
    
    private String createRentalItems(int transactionId) {
    	String rentalItems = "";
    	for (Entry<Furniture, Integer> furniture : this.rentalCart.entrySet()) {
    		rentalItems += ",(" + transactionId + "," + furniture.getKey().getFurnitureId() + "," + furniture.getValue() + ")";
    	}
    	return rentalItems.replaceFirst(",", "");
    }
    
    @FXML
    void handleCheckoutButtonClick(ActionEvent event) throws SQLException {
    	int transactionId = this.transactionDal.getSizeOfTable() + 1;
    	this.transactionDal.createRentalTransaction(transactionId, java.sql.Date.valueOf(LocalDate.now().plusDays(60)), java.sql.Date.valueOf(LocalDate.now()), this.customerId, this.employeeId, this.createRentalItems(transactionId));
    	List<Item> items = this.itemsDal.rentalItems(transactionId);
    	Parent root;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\TransactionSummaryWindow.fxml"));

			root = loader.load();

			TransactionWindowCodeBehind codeBehind = loader.getController();
	
			codeBehind.setEmployee(this.employee);
			
			codeBehind.setTransactionText((Object) this.transactionDal.getRentalTransaction(transactionId), items);
			
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
    
    private int getStyleId(String styleLabel) {
    	for (int styleId : this.styles.keySet()) {
    		if (this.styles.get(styleId).equals(styleLabel)) {
    			return styleId;
    		}
    	}
    	return 0;
    }
    
    private int getCategoryId(String categoryLabel) {
    	for (int categoryId : this.categories.keySet()) {
    		if (this.categories.get(categoryId).equals(categoryLabel)) {
    			return categoryId;
    		}
    	}
    	return 0;
    }
    
    /**
     * Sets the employee id
     * 
     * @precondition employeeId >0
     * @postcondition employeeId = employeeId
     * 
     * @param employeeId the employeeId
     */
    public void setEmployee(Employee employee) {
    	if (employee == null) {
    		throw new IllegalArgumentException(UI.ErrorMessages.EMPLOYEE_NULL);
    	}
    	this.employeeId = employee.getEmployeeId();
    	this.employee = employee;
    	
    }
    
    /**
     * Set the customer
     * 
     * @precondition customer != null
     * 
     * @param customer the customer
     */
    public void setCustomer(Customer customer) {
    	if (customer == null) {
    		throw new IllegalArgumentException(UI.ErrorMessages.CUSTOMER_NULL);
    	}
    	this.customerId = customer.getMemberID();
    	this.customerInformationLabel.setText(customer.toString());
    }

}
