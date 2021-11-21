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
    
    private Employee employee;
    
    public LandingWindowCodeBehind() {
    	this.customerDal = new CustomerDAL();
    	this.rentalDal = new RentalTransactionDAL();
    }

    @FXML
    void handleReturnFurnitureClick(ActionEvent event) {
    	Parent root;

		try{

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\ReturnWindow.fxml"));

			loader.setController(new ReturnWindowCodeBehind());
			ReturnWindowCodeBehind codeBehind = loader.getController();
					
			codeBehind.setCustomer(this.customerComboBox.getSelectionModel().getSelectedItem());
			
			codeBehind.setEmployee(Integer.parseInt(this.idLbl.getText()));
			
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
    	Parent root;

		try{

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\RegisterWindow.fxml"));

			System.out.println(getClass().getResource("edu\\westga\\devops\\view\\LandingWindow.fxml"));

			root = loader.load();

			Stage stage = new Stage();

			stage.setTitle("Registration Window");

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
    void initialize() throws SQLException {
    	this.shopButton.setDisable(true);
    	this.returnFurnitureButton.setDisable(true);
    	this.customerComboBox.getItems().setAll(this.customerDal.getAllCustomers().values());
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
			
			codeBehind.setEmployee(this.employee);
			
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
    	Parent root;

		try{

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\SearchWindow.fxml"));

			root = loader.load();
			
			SearchWindowCodeBehind codeBehind = loader.getController();
			codeBehind.setEmployee(this.employee);

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
    void handleOpenQueryInterfaceButton(ActionEvent event) {
    	Parent root;

		try{

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\AdminQueryWindow.fxml"));

			root = loader.load();
			
			AdminQueryWindowCodeBehind codeBehind = loader.getController();
			
			codeBehind.setAdminInformation(this.employee);

			Stage stage = new Stage();

			stage.setTitle("Admin Query Window");

			stage.setScene(new Scene(root));

			stage.show();

			Node node = ((Node)(event.getSource()));

			Stage thisStage = (Stage) node.getScene().getWindow();

			thisStage.close();

		} catch (IOException e) {

            e.printStackTrace();

        }
    }

	 public void setTitle(String name, String username, int id) {
    	this.employeeNameLbl.setText(name);
    	this.usernameLbl.setText(username);   	
    	this.idLbl.setText(String.valueOf(id));
    }

	 /**
	  * Sets the employee
	  * 
	  * @param employee the employee
	  */
	 public void setEmployee(Employee employee) {
		 this.employee = employee;
	 }
}
