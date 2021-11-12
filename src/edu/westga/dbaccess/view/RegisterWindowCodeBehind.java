package edu.westga.dbaccess.view;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import edu.westga.dbaccess.controller.RegisterWindowController;
import edu.westga.dbaccess.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The register window
 * 
 * @author Rasheed Jones
 * @version Fall 2021
 *
 */
public class RegisterWindowCodeBehind {

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
	private TextField addressTextField;

	@FXML
	private Label cityyLabel;

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
	private Button registerButton;

	@FXML
	private Button cancelButton;

	private RegisterWindowController controller;

	@FXML
	void handleRegisterClick(ActionEvent event) throws SQLException {
		try {
			this.controller.registerCustomer(this.firstNameTextField.getText(), this.lastNameTextBox.getText(),
					this.zipCodeTextField.getText(), this.cityTexfield.getText(),
					this.genderComboBox.getSelectionModel().getSelectedItem(), this.addressTextField.getText(),
					this.stateComboBox.getSelectionModel().getSelectedItem(), this.phoneNumberTextField.getText(),
					Date.valueOf(this.birthdateDatePicker.getValue()), Date.valueOf(LocalDate.now()));
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
	void handleCancelClick(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(
				getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\LandingWindow.fxml"));

		Parent root;
		try {
			root = loader.load();

			LandingWindowCodeBehind landingWindow = loader.getController();

			Stage stage = new Stage();

			stage.setTitle("Registration Window");

			stage.setScene(new Scene(root));

			stage.show();

			Node node = ((Node) (event.getSource()));

			Stage thisStage = (Stage) node.getScene().getWindow();

			thisStage.close();

		} catch (IOException exception) {
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
		this.controller = new RegisterWindowController();
		this.setupListeners();
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
		this.phoneNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue.isEmpty() || newValue.length() != 10) {
					this.phoneNumberErrorLabel.setVisible(true);
				} else {
					try {
						Integer.parseInt(newValue);
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
		this.zipCodeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (this.zipCodeTextField.getText().isEmpty() || this.zipCodeTextField.getText().length() != 6) {
					this.addressErrorLabel.setVisible(true);
				} else {
					try {
						Integer.parseInt(newValue);
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
