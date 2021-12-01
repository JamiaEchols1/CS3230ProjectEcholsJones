package edu.westga.dbaccess.view;

import edu.westga.dbaccess.controller.ResetPasswordWindowController;
import edu.westga.dbaccess.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class ResetPasswordWindowCodeBehind {

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField retypePaasswordField;

    @FXML
    private Button enterButton;

    @FXML
    private Button closeButton;
    
    private WindowGenerator newWindow;
    
    private ResetPasswordWindowController controller;
    
    @FXML
    void initialize() {
    	this.newWindow = new WindowGenerator();
    	this.controller = new ResetPasswordWindowController();
    }

    @FXML
    void handleCloseButtonClick(ActionEvent event) {
    	this.newWindow.generateWindow("Edit Profile", "edu\\westga\\dbaccess\\view\\EditEmployeeWindow.fxml", event);
    }

    @FXML
    void handleEnterButtonClick(ActionEvent event) {
    	try {
    		if (this.currentPasswordField.getText().isEmpty()) {
    			throw new IllegalArgumentException("Must enter current password");
    		}
    		if (this.newPasswordField.getText().isEmpty()) {
    			throw new IllegalArgumentException("Must enter new password");
    		}
    		if (this.retypePaasswordField.getText().isEmpty()) {
    			throw new IllegalArgumentException("Must retype new password");
    		}
    		if (!this.retypePaasswordField.getText().equals(this.newPasswordField.getText())) {
    			throw new IllegalArgumentException("Passwords must match");
    		}
    		this.controller.resetPassword(Employee.getEmployee().getEmployeeId(), this.currentPasswordField.getText(), this.newPasswordField.getText());
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Password Changed!");
			alert.show();
    	} catch (Exception exception) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(exception.getMessage());
			alert.show();
    	}
    }

}
