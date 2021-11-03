package edu.westga.dbaccess.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    private HashMap<Integer, Integer> furnitureIds;
    
    private int employeeId;
    
    private int customerId;
    
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
        this.furnitureIds = new HashMap<Integer, Integer>();
        this.employeeId = 0;
        this.customerId = 0;
    }

    @FXML
    void initialize() throws SQLException {
    	this.furnitureListView.getItems().setAll(this.furnitureDal.getAllFurniture());
    	this.styleFilterComboBox.getItems().setAll(this.styleDal.getStyles().values());
    	this.categoryComboBox.getItems().setAll(this.categoryDal.getCategory().values());
    }

    @FXML
    void handleSearchButtonClick(ActionEvent event) throws SQLException {
    	this.furnitureListView.getItems().setAll(this.furnitureDal.getFurnitureByCriteria(this.getStyleId(this.styleFilterComboBox.getSelectionModel().getSelectedItem()), this.getCategoryId(this.categoryComboBox.getSelectionModel().getSelectedItem())));
    }
    
    @FXML
    void handleAddToCartButtonClick(ActionEvent event) throws SQLException {
    	for (Furniture furniture: this.furnitureListView.getSelectionModel().getSelectedItems()) {
    		if (!this.furnitureIds.containsKey(furniture.getFurnitureId())) {
    			this.furnitureIds.put(furniture.getFurnitureId(),1);
    		} else {
    			int quantity = this.furnitureIds.get(furniture.getFurnitureId());
    			quantity++;
    			this.furnitureIds.put(furniture.getFurnitureId(), quantity);
    		}
    	}
    	this.furnitureListView.getSelectionModel().clearSelection();
    }
    
    @FXML
    void handleCheckoutButtonClick(ActionEvent event) throws SQLException {
    	int transactionId = this.transactionDal.createRentalTransaction(java.sql.Date.valueOf(LocalDate.now().plusDays(60)), java.sql.Date.valueOf(LocalDate.now()), this.customerId, this.employeeId);
    	for (int furnitureId : this.furnitureIds.keySet()) {
    		this.itemDal.createRentalItem(transactionId, furnitureId, this.furnitureIds.get(furnitureId));
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
