package edu.westga.dbaccess.view;
import java.sql.SQLException;

import edu.westga.dbaccess.dal.AdminQueryInterfaceDAL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private Label adminInformaationLabel;
    
    private AdminQueryInterfaceDAL adminQueryInterfacedal;
    
    public AdminQueryWindowCodeBehind() {
    	this.adminQueryInterfacedal = new AdminQueryInterfaceDAL();
    }

    @FXML
    void handleBackButtonClick(ActionEvent event) {

    }

    @FXML
    void handleClearButtonCLick(ActionEvent event) {

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