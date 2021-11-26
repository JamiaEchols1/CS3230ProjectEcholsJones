package edu.westga.dbaccess.view;
import java.sql.SQLException;

import edu.westga.dbaccess.dal.AdminQueryInterfaceDAL;
import edu.westga.dbaccess.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The admin Query window code behind
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
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
    
    private WindowGenerator newWindow;
    
    /**
     * Initializes the Admin query
     * 
     * @precondition none
     * @postcondition none
     * 
     */
    public AdminQueryWindowCodeBehind() {
    	this.adminQueryInterfacedal = new AdminQueryInterfaceDAL();
    	this.newWindow = new WindowGenerator();
    }

    @FXML
    void initialize() {
    	this.adminInformationLabel.setText(Employee.getEmployee().toString());	
    }
    
    @FXML
    void handleBackButtonClick(ActionEvent event) {
    	this.newWindow.generateWindow("Registration Window", "edu\\westga\\dbaccess\\view\\LandingWindow.fxml", event);
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