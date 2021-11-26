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
import javafx.scene.control.DatePicker;
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
    private DatePicker dueDateDatePicker;

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
    private TextField quantityTextField;
    
    @FXML
    private Button backButton;
    
    @FXML
    private ListView<Integer> quantityListView;
    
    private double cartCost;
    
    private WindowGenerator newWindow;
    
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
        this.customerId = 0;
        this.cartCost = 0.0;
        this.newWindow = new WindowGenerator();
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
    	this.newWindow.generateWindow("Registration Window", "edu\\westga\\dbaccess\\view\\LandingWindow.fxml", event);
	}
    
    @FXML
    void handleAddToCartButtonClick(ActionEvent event) throws SQLException {
    	try {
    		Furniture furniture = this.furnitureListView.getSelectionModel().getSelectedItem();
    		if (this.quantityTextField.getText().isEmpty()) {
    			throw new IllegalArgumentException("Must enter quantity");
    		}
    		if (Integer.parseInt(this.quantityTextField.getText()) > furniture.getQuantity()) {
    			throw new IllegalArgumentException("Quantity unavailable. Must choose at most " + furniture.getQuantity());
    		}
    		this.rentalCart.put(furniture, Integer.parseInt(this.quantityTextField.getText()));
    		this.setCartCost();
    		furniture.setQuantity(furniture.getQuantity() - Integer.parseInt(this.quantityTextField.getText()));
        	this.quantityListView.getItems().setAll(this.rentalCart.values());
    		this.cartListView.getItems().setAll(this.rentalCart.entrySet());
        	this.furnitureListView.getSelectionModel().clearSelection();
    	} catch (Exception exception) {
    		Alert alert = new Alert(AlertType.ERROR, exception.getMessage());
            alert.show();
    	}
    }
    
    private void setCartCost() {
		for (Entry<Furniture, Integer> item : this.rentalCart.entrySet()) {
			this.cartCost += (item.getValue() * item.getKey().getPrice());
	    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	    	String moneyString = formatter.format(this.cartCost);
	    	this.costLabel.setText( "Total: " + moneyString);
		}
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
    		furniture.getKey().setQuantity(furniture.getKey().getQuantity() + 1);
    	}
    	
    	this.setCartCost();
    	this.cartListView.getItems().setAll(this.rentalCart.entrySet());
    	this.quantityListView.getItems().setAll(this.rentalCart.values());
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
    	try {
    		
    	if (this.dueDateDatePicker.getValue().compareTo(LocalDate.now()) < 0) {
    		throw new IllegalArgumentException("Date must be after today.");
    	}
    	int transactionId = this.transactionDal.getSizeOfTable() + 1;
    	this.transactionDal.createRentalTransaction(transactionId, java.sql.Date.valueOf(this.dueDateDatePicker.getValue()), java.sql.Date.valueOf(LocalDate.now()), this.customerId, Employee.getEmployee().getEmployeeId(), this.createRentalItems(transactionId));
    	List<Item> items = this.itemsDal.rentalItems(transactionId);
  
    	Parent root;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\TransactionSummaryWindow.fxml"));

			root = loader.load();

			TransactionWindowCodeBehind codeBehind = loader.getController();
			
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
    	} catch (Exception exception) {
    		Alert alert = new Alert(AlertType.ERROR, exception.getMessage());
            alert.show();
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
