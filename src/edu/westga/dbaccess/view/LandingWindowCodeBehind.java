package edu.westga.dbaccess.view;


import java.io.IOException;
import java.sql.SQLException;

import edu.westga.dbaccess.dal.CustomerDAL;
import edu.westga.dbaccess.dal.RentalTransactionDAL;
import edu.westga.dbaccess.model.Customer;
import edu.westga.dbaccess.model.Employee;
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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The landing page code behind
 * 
 * @author Rasheed Jones
 * @version Fall 2021
 */
public class LandingWindowCodeBehind {

    @FXML
    private Label employeeNameLbl;

    @FXML
    private Label usernameLbl;

    @FXML
    private Label idLbl;

    @FXML
    private Button registerBtn;
    
    @FXML
    private ComboBox<Customer> customerComboBox;
    
    @FXML
    private Button shopButton;
    
    @FXML
    private Button searchBtn;
    
    private CustomerDAL customerDal;
    
    private RentalTransactionDAL rentalDal;

    @FXML
    private ComboBox<RentalTransaction> transactionComboBox;

    @FXML
    private Button returnFurnitureButton;
    
    @FXML
    private Button openQueryInterfaceButton;
   
    private RentalTransaction transaction;
    
    private WindowGenerator newWindow;
    
    public LandingWindowCodeBehind() {
    	this.customerDal = new CustomerDAL();
    	this.rentalDal = new RentalTransactionDAL();
    	this.newWindow = new WindowGenerator();
    }

    @FXML
    void handleReturnFurnitureClick(ActionEvent event) {
    	Parent root;

		try{

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\ReturnWindow.fxml"));

			loader.setController(new ReturnWindowCodeBehind());
			ReturnWindowCodeBehind codeBehind = loader.getController();
					
			codeBehind.setCustomer(this.customerComboBox.getSelectionModel().getSelectedItem());
						
			codeBehind.setTransaction(this.transaction);
			
			root = loader.load();

			Stage stage = new Stage();

			stage.setTitle("Return Window");

			stage.setScene(new Scene(root));

			stage.show();

			Node node = ((Node)(event.getSource()));

			Stage thisStage = (Stage) node.getScene().getWindow();

			thisStage.close();

		} catch (IOException e) {

            e.printStackTrace();

        }
    }
    
    @FXML
    void registerBtnClick(ActionEvent event) {
    	this.newWindow.generateWindow("Registration Window", "edu\\westga\\dbaccess\\view\\RegisterWindow.fxml", event);
    }
    
    @FXML
    void initialize() throws SQLException {
    	this.shopButton.setDisable(true);
    	this.returnFurnitureButton.setDisable(true);
    	this.customerComboBox.getItems().setAll(this.customerDal.getAllCustomers().values());
    	this.employeeNameLbl.setText(Employee.getEmployee().getFullName());
    	this.usernameLbl.setText(Employee.getEmployee().getUsername());   	
    	this.idLbl.setText(String.valueOf(Employee.getEmployee().getEmployeeId()));
    	this.setupBindings();
    }
    
    private void setupBindings() throws SQLException {
    	 this.customerComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		 if (newValue != null) {
    			 this.shopButton.setDisable(false);
    			 try {
					this.transactionComboBox.getItems().setAll(this.rentalDal.getCustomersTransactions(this.customerComboBox.getSelectionModel().getSelectedItem().getMemberID()));
				} catch (SQLException e) {
	    			Alert alert = new Alert(AlertType.ERROR, UI.ErrorMessages.FURNITURE_SOLD_OUT);
	                alert.show();
				}
    		 } else {
    			 this.shopButton.setDisable(true);
    		 }
    	 });
    	 this.transactionComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		 if (newValue != null) {
    			 this.returnFurnitureButton.setDisable(false);
    			 this.transaction = this.transactionComboBox.getValue();
    		 } else {
    			 this.returnFurnitureButton.setDisable(true);
    		 }
    	});
    	 
    }
    
    @FXML
    void handleShopClick(ActionEvent event) {
    	Parent root;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\FurnitureShopWindow.fxml"));

			root = loader.load();
			
			FurnitureShopCodeBehind codeBehind = loader.getController();
			
			codeBehind.setCustomer(this.customerComboBox.getSelectionModel().getSelectedItem());
			
			Stage stage = new Stage();

			stage.setTitle("Search Window");

			stage.setScene(new Scene(root));

			stage.show();

			Node node = ((Node)(event.getSource()));

			Stage thisStage = (Stage) node.getScene().getWindow();

			thisStage.close();

		} catch (IOException e) {

            e.printStackTrace();

        }
    }
    
    @FXML
    void searchBtnClick(ActionEvent event) {
    	this.newWindow.generateWindow("Search Window", "edu\\westga\\dbaccess\\view\\SearchWindow.fxml", event);
    }
    
    @FXML
    void handleOpenQueryInterfaceButton(ActionEvent event) {
    	this.newWindow.generateWindow("Admin Query Window", "edu\\westga\\dbaccess\\view\\AdminQueryWindow.fxml", event);
	}
}
