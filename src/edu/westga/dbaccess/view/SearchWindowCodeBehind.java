package edu.westga.dbaccess.view;

import java.sql.SQLException;

import edu.westga.dbaccess.controller.SearchController;
import edu.westga.dbaccess.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private Button editButton;

	@FXML
	private ComboBox<String> modeComboBox;

	@FXML
	private TextField searchTextField;

	@FXML
	private TableView<Customer> searchTableView;

	private TableColumn<Customer, String> lastNameCol;
	private TableColumn<Customer, String> firstNameCol;
	private TableColumn<Customer, Integer> memberIdCol;

	private SearchController controller;

	private WindowGenerator newWindow;

	@FXML
	void backButtonClick(ActionEvent event) {
		this.newWindow.generateWindow("Registration Window", "edu\\westga\\dbaccess\\view\\LandingWindow.fxml", event);
	}

	@FXML
	void editButtonClick(ActionEvent event) throws NumberFormatException, SQLException {
		if (this.searchTableView.getSelectionModel().getSelectedItem() != null) {
			int memberId = this.searchTableView.getSelectionModel().getSelectedItem().getMemberID();
			Customer.setCustomer(this.controller.getCustomerbyMemberId(memberId));
			this.newWindow.generateWindow("Edit Member Window", "edu\\westga\\dbaccess\\view\\EditMemberWindow.fxml",
					event);
		}
	}

	@FXML
	void searchButtonClick(ActionEvent event) throws NumberFormatException, SQLException {
		if (this.modeComboBox.getSelectionModel().getSelectedItem() == "MemberID") {
			this.searchTableView.getItems().add(this.controller.getbyMemberId(this.searchTextField.getText()));
		} else if (this.modeComboBox.getSelectionModel().getSelectedItem() == "FullName") {
			String first = this.searchTextField.getText().split(" ")[0];
			String last = this.searchTextField.getText().split(" ")[1];
			ObservableList<Customer> data = FXCollections
					.observableArrayList(this.controller.getByFullName(first, last));
			this.searchTableView.setItems(data);
		}

		else if (this.modeComboBox.getSelectionModel().getSelectedItem() == "Phone") {
			ObservableList<Customer> data = FXCollections
					.observableArrayList(this.controller.getByPhoneNumber(this.searchTextField.getText()));
			this.searchTableView.setItems(data);
		}
	}

	@FXML
	void initialize() {
		this.modeComboBox.getItems().addAll("MemberID", "FullName", "Phone");
		this.controller = new SearchController();
		this.newWindow = new WindowGenerator();

		this.firstNameCol = new TableColumn<Customer, String>("First Name");
		this.lastNameCol = new TableColumn<Customer, String>("Last Name");
		this.memberIdCol = new TableColumn<Customer, Integer>("MemberID");
		this.firstNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		this.lastNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		this.memberIdCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("memberID"));
		searchTableView.getColumns().addAll(firstNameCol, lastNameCol, memberIdCol);
	}
}
