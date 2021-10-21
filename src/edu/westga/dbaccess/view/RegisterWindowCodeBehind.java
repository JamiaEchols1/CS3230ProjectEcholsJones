package edu.westga.dbaccess.view;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import edu.westga.devops.controller.RegisterWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterWindowCodeBehind {

    @FXML
    private Label addressLbl;

    @FXML
    private TextField address1TxtField;

    @FXML
    private Label stateLbl;

    @FXML
    private ComboBox<String> addressComboBox;

    @FXML
    private Label birthdayLbl;

    @FXML
    private DatePicker birthdayDatePicker;

    @FXML
    private Label fullNameLbl;

    @FXML
    private TextField fullNameTxtField;

    @FXML
    private Label phoneNumberLbl;

    @FXML
    private TextField phoneNumberTxtField;

    @FXML
    private Label genderLbl;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private Button registerBtn;
    
    private RegisterWindowController controller;
    
    @FXML
    private Label employeeNameLbl;

    @FXML
    private Label usernameLbl;

    @FXML
    private Label idLbl;

    @FXML
    void clearBtnClick(ActionEvent event) {

    }

    @FXML
    void registerBtnClick(ActionEvent event) throws SQLException {
    	this.controller.registerCustomer(this.fullNameTxtField.getText(), this.genderComboBox.getSelectionModel().getSelectedItem(), this.address1TxtField.getText(), this.addressComboBox.getSelectionModel().getSelectedItem(), this.phoneNumberTxtField.getText(), Date.valueOf(this.birthdayDatePicker.getValue()), Date.valueOf(LocalDate.now()));
    }
    
    @FXML
    void initialize() {
    	this.addressComboBox.getItems().addAll("Alabama",
    			"Alaska",
    			"Arizona",
    			"Arkansas",
    			"California",
    			"Colorado",
    			"Connecticut",
    			"Delaware",
    			"District of Columbia",
    			"Florida",
    			"Georgia",
    			"Hawaii",
    			"Idaho",
    			"Illinois",
    			"Indiana",
    			"Iowa",
    			"Kansas",
    			"Kentucky",
    			"Louisiana",
    			"Maine",
    			"Montana",
    			"Nebraska",
    			"Nevada",
    			"New Hampshire",
    			"New Jersey",
    			"New Mexico",
    			"New York",
    			"North Carolina",
    			"North Dakota",
    			"Ohio",
    			"Oklahoma",
    			"Oregon",
    			"Maryland",
    			"Massachusetts",
    			"Michigan",
    			"Minnesota",
    			"Mississippi",
    			"Missouri",
    			"Pennsylvania",
    			"Rhode Island",
    			"South Carolina",
    			"South Dakota",
    			"Tennessee",
    			"Texas",
    			"Utah",
    			"Vermont",
    			"Virginia",
    			"Washington",
    			"West Virginia",
    			"Wisconsin",
    			"Wyoming");
    	this.genderComboBox.getItems().addAll("Male", "Female", "Other");
    	this.controller = new RegisterWindowController();
    }
    
    public void setTitle(String name, String username, int id) {
    	this.employeeNameLbl.setText(name);
    	this.usernameLbl.setText(username);   	
    	this.idLbl.setText(String.valueOf(id));
    }

}
