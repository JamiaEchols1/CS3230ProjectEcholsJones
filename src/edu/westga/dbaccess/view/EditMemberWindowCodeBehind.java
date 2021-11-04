package edu.westga.dbaccess.view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import edu.westga.dbaccess.controller.EditMemberWindowController;
import edu.westga.dbaccess.controller.RegisterWindowController;
import edu.westga.dbaccess.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditMemberWindowCodeBehind {

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

	@FXML
	void handleCancelClick(ActionEvent event) {

	}

	@FXML
	void handleEditClick(ActionEvent event) {

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

	}

}
