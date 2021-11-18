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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SearchWindowCodeBehind {

	@FXML
	private Label searchLabel;

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

	private SearchController controller;

	@FXML
	void backButtonClick(ActionEvent event) {

	}
	
	@FXML
	private TextField searchTextField;

	@FXML
	void editButtonClick(ActionEvent event) throws NumberFormatException, SQLException {
		if (this.customerListView.getSelectionModel().getSelectedItem() != null ) {
			Parent root;
			try{
				String memberId = this.customerListView.getSelectionModel().getSelectedItem().split(" ")[0];

				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\EditMemberWindow.fxml"));

				System.out.println(getClass().getResource("edu\\westga\\devops\\view\\LandingWindow.fxml"));

				root = loader.load();
				
				EditMemberWindowCodeBehind editWindow = loader.getController();
				
				editWindow.setUp(this.controller.getCustomerbyMemberId(memberId));

				Stage stage = new Stage();

				stage.setTitle("Edit Member Window");

				stage.setScene(new Scene(root));

				stage.show();

				Node node = ((Node)(event.getSource()));

				Stage thisStage = (Stage) node.getScene().getWindow();

				thisStage.close();

			} catch (IOException e) {

	            e.printStackTrace();

	        }
		}
	}

	@FXML
	void checkRegex(MouseEvent event) {

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
			for(String customer : customerArray) {
				if (!customer.equals("")) {
					this.customerListView.getItems().add(customer);
				}
			}

		} else if (this.modeComboBox.getSelectionModel().getSelectedItem() == "Phone") {
			String[] customerArray = this.controller.getByPhoneNumber(this.searchTextField.getText()).split(",");
			for(String customer : customerArray) {
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
	}

}
