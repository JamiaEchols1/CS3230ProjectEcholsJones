package edu.westga.dbaccess.view;

import java.io.IOException;
import java.sql.SQLException;

import edu.westga.dbaccess.dal.CustomerDAL;
import edu.westga.dbaccess.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    
    public LandingWindowCodeBehind() {
    	this.customerDal = new CustomerDAL();
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
    	this.customerComboBox.getItems().setAll(this.customerDal.getAllCustomers().values());
    	this.setupBindings();
    }
    
    private void setupBindings() throws SQLException {
    	 this.customerComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		 if (newValue != null) {
    			 this.shopButton.setDisable(false);
    		 } else {
    			 this.shopButton.setDisable(true);
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
			
			codeBehind.setCustomer(this.customerComboBox.getSelectionModel().getSelectedItem().getMemberID());
			
			codeBehind.setEmployee(Integer.parseInt(this.idLbl.getText()));
			
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

			System.out.println(getClass().getResource("edu\\westga\\devops\\view\\SearchWindow.fxml"));

			root = loader.load();

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

	 public void setTitle(String name, String username, int id) {
    	this.employeeNameLbl.setText(name);
    	this.usernameLbl.setText(username);   	
    	this.idLbl.setText(String.valueOf(id));
    }

}
