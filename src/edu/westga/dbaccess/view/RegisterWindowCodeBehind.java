package edu.westga.dbaccess.view;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import edu.westga.dbaccess.controller.RegisterWindowController;
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
<<<<<<< HEAD
 * <<<<<<< HEAD
 * 
 * @author =======
=======
>>>>>>> branch 'master' of https://github.com/JamiaEchols1/CS3230ProjectEcholsJones.git
 * @author Rasheed Jones
<<<<<<< HEAD
 * @version Fall 2021 >>>>>>> branch 'master' of
 *          https://github.com/JamiaEchols1/CS3230ProjectEcholsJones.git
=======
 * @version Fall 2021
>>>>>>> branch 'master' of https://github.com/JamiaEchols1/CS3230ProjectEcholsJones.git
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
<<<<<<< HEAD
		try {
			this.nameValidation();
			this.nameErrorLabel.setVisible(false);
		} catch (Exception exception) {
			this.nameErrorLabel.setVisible(true);
		}
		try {
			this.addressValidation();
			this.addressErrorLabel.setVisible(false);
		} catch (Exception exception) {
			this.addressErrorLabel.setVisible(true);
		}

=======
		this.nameValidation();
		this.addressValidation();
	
>>>>>>> branch 'master' of https://github.com/JamiaEchols1/CS3230ProjectEcholsJones.git
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
<<<<<<< HEAD
		this.lastNameTextBox.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue.isEmpty() || newValue.length() < 1) {
					throw new IllegalArgumentException();
				}
				this.nameErrorLabel.setVisible(false);
			} catch (Exception exception) {
				this.nameErrorLabel.setVisible(true);
			}
=======
		this.lastNameTextBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
				if (this.lastNameTextBox.getText().isEmpty() || this.lastNameTextBox.getText().length() < 1) {
					this.nameErrorLabel.setVisible(true);
				} else {
					this.nameErrorLabel.setVisible(false);
				}
>>>>>>> branch 'master' of https://github.com/JamiaEchols1/CS3230ProjectEcholsJones.git
		});
	}
<<<<<<< HEAD

	private void addressValidation() throws Exception {
		this.addressTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue.isEmpty() || newValue.length() < 1) {
					throw new IllegalArgumentException();
=======
	
	private void addressValidation() {
		this.addressTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
				if (this.addressTextField.getText().isEmpty() || this.addressTextField.getText().length() < 1) {
					this.addressErrorLabel.setVisible(true);
				} else {
					this.addressErrorLabel.setVisible(false);
>>>>>>> branch 'master' of https://github.com/JamiaEchols1/CS3230ProjectEcholsJones.git
				}
		});
<<<<<<< HEAD
		this.cityTexfield.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue.isEmpty() || newValue.length() < 1) {
					throw new IllegalArgumentException();
=======
		this.cityTexfield.focusedProperty().addListener((observable, oldValue, newValue) -> {
				if (this.cityTexfield.getText().isEmpty() || this.cityTexfield.getText().length() < 1) {
					this.addressErrorLabel.setVisible(true);
				} else {
					this.addressErrorLabel.setVisible(false);
>>>>>>> branch 'master' of https://github.com/JamiaEchols1/CS3230ProjectEcholsJones.git
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
<<<<<<< HEAD
				Integer.parseInt(newValue);
				this.addressErrorLabel.setVisible(false);
			} catch (Exception exception) {
				this.addressErrorLabel.setVisible(true);
			}
=======
			
>>>>>>> branch 'master' of https://github.com/JamiaEchols1/CS3230ProjectEcholsJones.git
		});
		this.stateComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
<<<<<<< HEAD
			try {
				if (newValue == null) {
					throw new IllegalArgumentException();
				}
				this.addressErrorLabel.setVisible(false);
			} catch (Exception exception) {
=======
			if (newValue == null) {
>>>>>>> branch 'master' of https://github.com/JamiaEchols1/CS3230ProjectEcholsJones.git
				this.addressErrorLabel.setVisible(true);
			}
		});
	}

}
