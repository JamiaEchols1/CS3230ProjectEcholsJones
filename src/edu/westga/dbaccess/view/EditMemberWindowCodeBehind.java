package edu.westga.dbaccess.view;

import java.sql.Date;
import java.time.LocalDate;

import edu.westga.dbaccess.controller.EditMemberWindowController;
import edu.westga.dbaccess.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Member edit window
 * 
 * @author Rasheed Jones
 * @version Fall 2021
 *
 */
public class EditMemberWindowCodeBehind {

	@FXML
	private Label nameErrorLabel;

	@FXML
	private Label cityLabel;

	@FXML
	private Label addressErrorLabel;

	@FXML
	private Label birthdayErrorLabel;

	@FXML
	private Label phoneNumberErrorLabel;

	@FXML
	private Label genderErrorLabel;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Label firstNameLabel;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private Label lastNameLabel;

	@FXML
	private TextField lastNameTextBox;

	@FXML
	private Label addressLabel;
	
	@FXML
	private Label customerInformationLabel;

	@FXML
	private TextField addressTextField;

	@FXML
	private TextField cityTexfield;

	@FXML
	private Label zipCodeLabel;

	@FXML
	private TextField zipCodeTextField;

	@FXML
	private Label stateLabel;

	@FXML
	private ComboBox<String> stateComboBox;

	@FXML
	private Label dateOfBirthLabel;

	@FXML
	private DatePicker birthdateDatePicker;

	@FXML
	private Label phoneNumberLabel;

	@FXML
	private TextField phoneNumberTextField;

	@FXML
	private Label genderLabel;

	@FXML
	private ComboBox<String> genderComboBox;

	@FXML
	private Button editButton;

	@FXML
	private Button cancelButton;

	private EditMemberWindowController controller;

	private int memberID;

	private WindowGenerator newWindow;

	@FXML
	void handleCancelClick(ActionEvent event) {
		this.newWindow.generateWindow("Search Window", "edu\\westga\\dbaccess\\view\\SearchWindow.fxml", event);
	}

	@FXML
	void handleEditClick(ActionEvent event) {
		try {
			this.controller.editCustomer(this.memberID, this.firstNameTextField.getText(),
					this.lastNameTextBox.getText(), this.genderComboBox.getSelectionModel().getSelectedItem(),
					this.addressTextField.getText(), this.zipCodeTextField.getText(),
					this.stateComboBox.getSelectionModel().getSelectedItem(), this.cityTexfield.getText(),
					this.phoneNumberTextField.getText(), Date.valueOf(this.birthdateDatePicker.getValue()));
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Customer Updated");
			alert.show();
		} catch (Exception exception) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(exception.getMessage());
			alert.show();
		}
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
		this.genderComboBox.getItems().addAll("Male", "Female", "Other");
		this.controller = new EditMemberWindowController();
		this.newWindow = new WindowGenerator();
		this.customerInformationLabel.setText(Customer.getCustomer().toString());
		this.setUp(Customer.getCustomer());
		this.setupListeners();
	}

	public void setUp(Customer customer) {
		this.firstNameTextField.setText(customer.getFirstName());
		this.lastNameTextBox.setText(customer.getLastName());
		this.zipCodeTextField.setText(customer.getZipcode());
		this.stateComboBox.getSelectionModel().select(customer.getState());
		this.genderComboBox.getSelectionModel().select(customer.getGender());
		this.cityTexfield.setText(customer.getCity());
		this.addressTextField.setText(customer.getAddress1());
		this.phoneNumberTextField.setText(customer.getPhoneNumber());
		LocalDate date = customer.getBirthday().toLocalDate();
		this.birthdateDatePicker.setValue(date);
		this.memberID = customer.getMemberID();

	}

	private void setupListeners() {
		this.nameValidation();
		this.addressValidation();
		this.genderComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				this.genderErrorLabel.setVisible(true);
			} else {
				this.genderErrorLabel.setVisible(false);
			}
		});
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
