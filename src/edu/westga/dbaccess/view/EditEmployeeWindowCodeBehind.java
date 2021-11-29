package edu.westga.dbaccess.view;

import java.sql.Date;
import java.sql.SQLException;

import edu.westga.dbaccess.controller.EditEmployeeWindowController;
import edu.westga.dbaccess.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditEmployeeWindowCodeBehind {

    @FXML
    private Label firstNameLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Label lastNameLabel;

    @FXML
    private TextField lastNameTextBox;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressTextField;

    @FXML
    private Label cityLabel;

    @FXML
    private TextField cityTexfield;

    @FXML
    private Label zipCodeLabel;

    @FXML
    private TextField zipCodeTextField;

    @FXML
    private Label addressErrorLabel;

    @FXML
    private Label stateLabel;

    @FXML
    private ComboBox<String> stateComboBox;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label phoneNumberErrorLabel;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label employeeInformationLabel;
    
    @FXML
    private int employeeId;
    
    private EditEmployeeWindowController controller;
    
    public EditEmployeeWindowCodeBehind() {
    	this.controller = new EditEmployeeWindowController();
    }

    @FXML
    void handleCancelClick(ActionEvent event) {

    }

    @FXML
    void handleEditClick(ActionEvent event) throws SQLException {
    	this.controller.edit(this.employeeId, this.firstNameTextField.getText(),
		this.lastNameTextBox.getText(),
		this.addressTextField.getText(), this.zipCodeTextField.getText(),
		this.stateComboBox.getSelectionModel().getSelectedItem(), this.cityTexfield.getText(),
		this.phoneNumberTextField.getText(), this.usernameTextField.getText(), this.passwordTextField.getText());
    }
    
    @FXML
    void initialize() {
		this.stateComboBox.getItems().addAll("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
				"Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois",
				"Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Montana", "Nebraska", "Nevada",
				"New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
				"Oklahoma", "Oregon", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
				"Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah",
				"Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");
		this.setupListeners();
		this.setUp();
    }
    
	private void setUp() {
		this.firstNameTextField.setText(Employee.getEmployee().getFirstName());
		this.lastNameTextBox.setText(Employee.getEmployee().getLastName());
		this.zipCodeTextField.setText(Employee.getEmployee().getZipcode());
		this.stateComboBox.getSelectionModel().select(Employee.getEmployee().getState());
		this.cityTexfield.setText(Employee.getEmployee().getCity());
		this.addressTextField.setText(Employee.getEmployee().getAddress1());
		this.phoneNumberTextField.setText(Employee.getEmployee().getPhoneNumber());
		this.usernameTextField.setText(Employee.getEmployee().getUsername());
		this.passwordTextField.setText(Employee.getEmployee().getPassword());
		this.employeeId = Employee.getEmployee().getEmployeeId();

	}
	
    private void setupListeners() {
		this.nameValidation();
		this.addressValidation();		
		this.phoneNumberTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (this.phoneNumberTextField.getText().isEmpty() || this.phoneNumberTextField.getText().length() != 10) {
				this.phoneNumberErrorLabel.setVisible(true);
			} else {
				try {
					Integer.parseInt(this.phoneNumberTextField.getText());
					this.phoneNumberErrorLabel.setVisible(false);
				} catch (Exception exception) {
					this.phoneNumberErrorLabel.setVisible(true);
				}
			}
		});

	}

	private void nameValidation() {
		this.firstNameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (this.firstNameTextField.getText().isEmpty() || this.firstNameTextField.getText().length() < 1) {
				this.nameErrorLabel.setVisible(true);
			} else {
				this.nameErrorLabel.setVisible(false);
			}
		});

		this.lastNameTextBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (this.lastNameTextBox.getText().isEmpty() || this.lastNameTextBox.getText().length() < 1) {
				this.nameErrorLabel.setVisible(true);
			} else {
				this.nameErrorLabel.setVisible(false);
			}
		});
	}

	private void addressValidation() {
		this.addressTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (this.addressTextField.getText().isEmpty() || this.addressTextField.getText().length() < 1) {
				this.addressErrorLabel.setVisible(true);
			} else {
				this.addressErrorLabel.setVisible(false);
			}
		});
		this.cityTexfield.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (this.cityTexfield.getText().isEmpty() || this.cityTexfield.getText().length() < 1) {
				this.addressErrorLabel.setVisible(true);
			} else {
				this.addressErrorLabel.setVisible(false);
			}
		});
		this.zipCodeTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (this.zipCodeTextField.getText().isEmpty() || this.zipCodeTextField.getText().length() != 6) {
				this.addressErrorLabel.setVisible(true);
			} else {
				try {
					Integer.parseInt(this.zipCodeTextField.getText());
					this.addressErrorLabel.setVisible(false);
				} catch (Exception exception) {
					this.addressErrorLabel.setVisible(true);
				}
			}
		});
		this.stateComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				this.addressErrorLabel.setVisible(true);
			}
		});
	}

}
