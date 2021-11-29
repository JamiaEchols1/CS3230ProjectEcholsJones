package edu.westga.dbaccess.view;

import java.sql.SQLException;

import edu.westga.dbaccess.controller.SearchController;
import edu.westga.dbaccess.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
			String memberId = this.customerListView.getSelectionModel().getSelectedItem().split(" ")[0];
			Customer.setCustomer(this.controller.getCustomerbyMemberId(memberId));
			this.newWindow.generateWindow("Edit Member Window", "edu\\westga\\dbaccess\\view\\EditMemberWindow.fxml", event);
		}
	}

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
