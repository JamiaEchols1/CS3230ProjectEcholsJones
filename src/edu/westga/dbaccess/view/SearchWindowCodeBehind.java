package edu.westga.dbaccess.view;

import java.io.IOException;
import java.sql.SQLException;

import edu.westga.dbaccess.controller.SearchController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The search code behind
 * 
 * @author Rasheed Jones
 * @version Fall 2021
 *
 */
public class SearchWindowCodeBehind {

	@FXML
	private Label searchLabel;

	@FXML
	private Label warningLabel;

	@FXML
	private Button searchButton;

	@FXML
	private Button backButton;

	@FXML
	private ListView<String> customerListView;

	@FXML
	private Button editButton;

	@FXML
	private ComboBox<String> modeComboBox;

	@FXML
	private TextField searchTextField;

	private SearchController controller;

	private WindowGenerator newWindow;

	@FXML
	void backButtonClick(ActionEvent event) {
		this.newWindow.generateWindow("Registration Window", "edu\\westga\\dbaccess\\view\\LandingWindow.fxml", event);
	}

	@FXML
	void editButtonClick(ActionEvent event) throws NumberFormatException, SQLException {
		if (this.customerListView.getSelectionModel().getSelectedItem() != null) {
			Parent root;
			try {
				String memberId = this.customerListView.getSelectionModel().getSelectedItem().split(" ")[0];

				FXMLLoader loader = new FXMLLoader(
						getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\EditMemberWindow.fxml"));

				root = loader.load();

				EditMemberWindowCodeBehind editWindow = loader.getController();

				editWindow.setUp(this.controller.getCustomerbyMemberId(memberId));

				Stage stage = new Stage();

				stage.setTitle("Edit Member Window");

				stage.setScene(new Scene(root));

				stage.show();

				Node node = ((Node) (event.getSource()));

				Stage thisStage = (Stage) node.getScene().getWindow();

				thisStage.close();

			} catch (IOException e) {

				e.printStackTrace();

			}
		}
	}

	/*
	 * private void addListeners() {
	 * this.searchTextField.textProperty().addListener((obs, oldText, newText) -> {
	 * if (this.modeComboBox.getSelectionModel().getSelectedItem() == "FullName") {
	 * Pattern pattern = Pattern.compile("*"); Matcher matcher =
	 * pattern.matcher(newText); boolean matchFound = matcher.find(); if(matchFound)
	 * { this.searchButton.setDisable(true);
	 * this.warningLabel.setText("Must be in format FirstName LastName"); } else {
	 * this.searchButton.setDisable(false); this.warningLabel.setText(""); } }
	 * 
	 * }); }
	 */

	@FXML
	void searchButtonClick(ActionEvent event) throws NumberFormatException, SQLException {
		this.customerListView.getItems().clear();
		if (this.modeComboBox.getSelectionModel().getSelectedItem() == "MemberID") {
			this.customerListView.getItems().add(this.controller.getbyMemberId(this.searchTextField.getText()));
		} else if (this.modeComboBox.getSelectionModel().getSelectedItem() == "FullName") {
			String first = this.searchTextField.getText().split(" ")[0];
			String last = this.searchTextField.getText().split(" ")[1];
			String[] customerArray = this.controller.getByFullName(first, last).split(",");
			for (String customer : customerArray) {
				if (!customer.equals("")) {
					this.customerListView.getItems().add(customer);
				}
			}

		} else if (this.modeComboBox.getSelectionModel().getSelectedItem() == "Phone") {
			String[] customerArray = this.controller.getByPhoneNumber(this.searchTextField.getText()).split(",");
			for (String customer : customerArray) {
				if (!customer.equals("")) {
					this.customerListView.getItems().add(customer);
				}
			}
		}
	}

	@FXML
	void initialize() {
		this.modeComboBox.getItems().addAll("MemberID", "FullName", "Phone");
		this.controller = new SearchController();
		this.newWindow = new WindowGenerator();
	}
}
