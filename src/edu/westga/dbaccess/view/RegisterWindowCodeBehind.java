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
<<<<<<< HEAD
 * @author
=======
 * @author Rasheed Jones
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
<<<<<<< HEAD
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
=======
		} catch (IOException exception) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(exception.getMessage());
			alert.show();
>>>>>>> branch 'master' of https://github.com/JamiaEchols1/CS3230ProjectEcholsJones.git
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
		
		this.genderComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue == null) {
					throw new IllegalArgumentException();
				}
				this.genderErrorLabel.setVisible(false);
			} catch (Exception exception) {
				this.genderErrorLabel.setVisible(true);
			}
		});
		this.phoneNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue.isEmpty() || newValue.length() != 10) {
					throw new IllegalArgumentException();
				}
				Integer.parseInt(newValue);
				this.phoneNumberErrorLabel.setVisible(false);
			} catch (Exception exception) {
				this.phoneNumberErrorLabel.setVisible(true);
			}
		});

	}

	private void nameValidation() throws Exception {
		this.firstNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue.isEmpty() || newValue.length() < 1) {
					throw new IllegalArgumentException();
				}
				this.nameErrorLabel.setVisible(false);
			} catch (Exception exception) {
				this.nameErrorLabel.setVisible(true);
			}
		});
		this.lastNameTextBox.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue.isEmpty() || newValue.length() < 1) {
					throw new IllegalArgumentException();
				} 
				this.nameErrorLabel.setVisible(false);
			} catch (Exception exception) {
				this.nameErrorLabel.setVisible(true);
			}
		});
	}
	
	private void addressValidation() throws Exception {
		this.addressTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue.isEmpty() || newValue.length() < 1) {
					throw new IllegalArgumentException();
				}
				this.addressErrorLabel.setVisible(false);
			} catch (Exception exception) {
				this.addressErrorLabel.setVisible(true);
			}
		});
		this.cityTexfield.textProperty().addListener((observable, oldValue, newValue) -> {
			try {	
				if (newValue.isEmpty() || newValue.length() < 1) {
					throw new IllegalArgumentException();
				}
				this.addressErrorLabel.setVisible(false);
			} catch (Exception exception) {
				this.addressErrorLabel.setVisible(true);
			}
		});
		this.zipCodeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue.isEmpty() || newValue.length() != 6) {
					throw new IllegalArgumentException();
				}
				Integer.parseInt(newValue);this.addressErrorLabel.setVisible(false);
			} catch (Exception exception) {
				this.addressErrorLabel.setVisible(true);	
			}
		});
		this.stateComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		try {
			if (newValue == null) {
				throw new IllegalArgumentException();
			}
			this.addressErrorLabel.setVisible(false);
		} catch (Exception exception) {
			this.addressErrorLabel.setVisible(true);
		}
		});
	}

}
