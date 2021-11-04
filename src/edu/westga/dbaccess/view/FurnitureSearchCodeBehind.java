package edu.westga.dbaccess.view;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.westga.dbaccess.dal.CategoryDAL;
import edu.westga.dbaccess.dal.FurnitureDAL;
import edu.westga.dbaccess.dal.RentalItemDAL;
import edu.westga.dbaccess.dal.RentalTransactionDAL;
import edu.westga.dbaccess.dal.StyleDAL;
import edu.westga.dbaccess.model.Furniture;
import edu.westga.dbaccess.model.Style;
import edu.westga.dbaccess.utils.UI;
import edu.westga.dbaccess.model.Category;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class FurnitureSearchCodeBehind {

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

    private FurnitureDAL furnitureDal;
    
    private CategoryDAL categoryDal;
    
    private StyleDAL styleDal;
    
    private RentalTransactionDAL transactionDal;
    
    private RentalItemDAL itemDal;

    private List<Style> styles;
    
    private List<Category> categories;

    private HashMap<Furniture, Integer> rentalCart;
    
    private int employeeId;
    
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
    private ListView<Furniture> cartListView;

    @FXML
    private Button removeButton;

    @FXML
    private Label costLabel;
    
    private double cartCost;
    
    /**
     * Initializes the Furniture search code behind;
     * 
     * @precondition none
     * 
     */
    public FurnitureSearchCodeBehind () {
        this.furnitureDal = new FurnitureDAL();
        this.categoryDal = new CategoryDAL();
        this.styleDal = new StyleDAL();
        this.transactionDal = new RentalTransactionDAL();
        this.itemDal = new RentalItemDAL();
        this.styles = new ArrayList<Style>();
        this.categories = new ArrayList<Category>();
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
    }

    @FXML
    void handleAddToCartButtonClick(ActionEvent event) throws SQLException {
    	for (Furniture furniture: this.furnitureListView.getSelectionModel().getSelectedItems()) {
    		if (!this.rentalCart.containsKey(furniture)) {
    			this.rentalCart.put(furniture,1);
    			this.cartCost += furniture.getPrice();
    		} else {
    			int quantity = this.rentalCart.get(furniture);
    			quantity++;
    			this.rentalCart.put(furniture, quantity);
    			this.cartCost += furniture.getPrice();
    		}
    	}
    	this.costLabel.setText( "Total: " + this.cartCost);
    	this.cartListView.getItems().setAll(this.rentalCart.keySet());
    	this.furnitureListView.getSelectionModel().clearSelection();
    }
    
    @FXML
    void handleRemoveButtonClick(ActionEvent event) {
    	for (Furniture furniture: this.cartListView.getSelectionModel().getSelectedItems()) {
    		if (this.rentalCart.get(furniture) > 1) {
    			this.rentalCart.put(furniture, this.rentalCart.get(furniture)-1);
    			this.cartCost -= furniture.getPrice();
    		} else {
    			this.rentalCart.remove(furniture);
    			this.cartCost -= furniture.getPrice();
    		}
    	}
    	this.costLabel.setText( "Total: " + this.cartCost);
    	this.cartListView.getItems().setAll(this.rentalCart.keySet());
    	this.furnitureListView.getSelectionModel().clearSelection();
    
    }

    @FXML
    void handleSearchCategoryButtonClick(ActionEvent event) throws SQLException {
     	this.furnitureListView.getItems().setAll(this.furnitureDal.getFurnitureByCategory(this.getCategoryId(this.categoryComboBox.getSelectionModel().getSelectedItem())));
        
    }

    @FXML
    void handleSearchStyleButtonClick(ActionEvent event) throws SQLException{
     	this.furnitureListView.getItems().setAll(this.furnitureDal.getFurnitureByStyle(this.getStyleId(this.styleFilterComboBox.getSelectionModel().getSelectedItem())));
    }
    
    @FXML
    void handleCheckoutButtonClick(ActionEvent event) throws SQLException {
    	int transactionId = this.transactionDal.getSizeOfTable() + 1;
    	this.transactionDal.createRentalTransaction(transactionId, java.sql.Date.valueOf(LocalDate.now().plusDays(60)), java.sql.Date.valueOf(LocalDate.now()), this.customerId, this.employeeId);
    	for (Furniture furniture: this.rentalCart.keySet()) {
    		this.itemDal.createRentalItem(transactionId, furniture.getFurnitureId(), this.rentalCart.get(furniture));
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
    
    private int getStyleId(String styleLabel) {
    	for (Style style : this.styles) {
    		if (style.getLabel() == styleLabel) {
    			return style.getStyleId();
    		}
    	}
    	return 0;
    }
    
    private int getCategoryId(String categoryLabel) {
    	for (Category category : this.categories) {
    		if (category.getLabel() == categoryLabel) {
    			return category.getCategoryId();
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
    public void setCustomer(int customerId) {
    	if (customerId < 0) {
    		throw new IllegalArgumentException(UI.ErrorMessages.ID_NEGATIVE);
    	}
    	this.customerId = customerId;
    }

}
