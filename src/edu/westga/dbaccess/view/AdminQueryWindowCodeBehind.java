package edu.westga.dbaccess.view;
import java.io.IOException;
import java.sql.SQLException;

import edu.westga.dbaccess.dal.AdminQueryInterfaceDAL;
import edu.westga.dbaccess.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminQueryWindowCodeBehind {

    @FXML
    private TextField queryTextField;

    @FXML
    private Button submitButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextArea resultsTextArea;

    @FXML
    private Button backButton;

    @FXML
    private Label adminInformationLabel;
    
    private AdminQueryInterfaceDAL adminQueryInterfacedal;
    
    /**
     * Initializes the Admin query
     * 
     * @precondition none
     * @postcondition none
     * 
     */
    public AdminQueryWindowCodeBehind() {
    	this.adminQueryInterfacedal = new AdminQueryInterfaceDAL();
    	this.adminInformationLabel.setText(Employee.getEmployee().toString());
    }

    @FXML
    void handleBackButtonClick(ActionEvent event) {
 		Parent root;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\LandingWindow.fxml"));

			System.out.println(getClass().getResource("edu\\westga\\devops\\view\\LandingWindow.fxml"));

			root = loader.load();

			Stage stage = new Stage();

			stage.setTitle("Registration Window");

			stage.setScene(new Scene(root, 452, 440));

			stage.show();

			Node node = ((Node)(event.getSource()));

			Stage thisStage = (Stage) node.getScene().getWindow();

			thisStage.close();

		} catch (IOException e) {

            e.printStackTrace();

        }
    }

    @FXML
    void handleClearButtonCLick(ActionEvent event) {
    	this.queryTextField.setText("");
    }

    @FXML
    void handleSubmitButton(ActionEvent event) {
    	try {
    		this.resultsTextArea.setText(this.adminQueryInterfacedal.runQuery(this.queryTextField.getText()));
    	} catch (SQLException exception) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText(exception.getMessage());
    		alert.show();
    	}
    }
}