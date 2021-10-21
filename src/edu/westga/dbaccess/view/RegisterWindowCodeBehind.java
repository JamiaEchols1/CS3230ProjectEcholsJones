package edu.westga.dbaccess.view;

import java.sql.Date;	
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import edu.westga.devops.controller.RegisterWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

	private String errorMessage;

    @FXML
    void clearBtnClick(ActionEvent event) {

    }

    @FXML
    void registerBtnClick(ActionEvent event) throws SQLException {
        if (!this.validateInput()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(this.errorMessage);
            alert.show();
        } else {
        	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Customer Updated");
            alert.show();
            this.controller.registerCustomer(this.fullNameTxtField.getText(), this.genderComboBox.getSelectionModel().getSelectedItem(), this.address1TxtField.getText(), this.addressComboBox.getSelectionModel().getSelectedItem(), this.phoneNumberTxtField.getText(), Date.valueOf(this.birthdayDatePicker.getValue()), Date.valueOf(LocalDate.now()));
        }
    } 
    
    private boolean validateInput() {
        if (this.fullNameTxtField.getText().isEmpty()) {
            this.errorMessage = "Full name cannot be empty.";
            return false;
        }
        if (this.address1TxtField.getText().isEmpty()) {
            this.errorMessage = "Address cannot be empty,";
            return false;
        }
        if (this.phoneNumberTxtField.getText().isEmpty()) {
            this.errorMessage = "Phone number cannot be empty.";
            return false;
        }
        if (this.phoneNumberTxtField.getText().length() != 10) {
            this.errorMessage = "Phone number must be 10 characters long.";
            return false;
        }
        if (Pattern.matches("[^0-9]", this.phoneNumberTxtField.getText())) {
            this.errorMessage = "Phone number must only contain integers.";
            return false;
        }
        if (this.birthdayDatePicker.getValue() == null) {
        	this.errorMessage = "Birthday must not be null.";
            return false;
        }
        if (this.genderComboBox.getSelectionModel().getSelectedItem() == null) {
        	this.errorMessage = "Please select a gender.";
            return false;
        }
        if (this.addressComboBox.getSelectionModel().getSelectedItem() == null) {
        	this.errorMessage = "Please select a state.";
            return false;
        }
        return true;
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
