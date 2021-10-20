package edu.westga.dbaccess.view;

import edu.westga.devops.controller.LoginWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginWindowCodeBehind {

    @FXML
    private VBox loginWindow;

    @FXML
    private TextField userIdTextField;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    private Button loginBtn;

    @FXML
    private Label loginFailedLbl;
    
    private LoginWindowController controller;

    @FXML
    void loginBtnClick(ActionEvent event) {
    	if (this.controller.login(this.userIdTextField.getText(), this.passwordTxtField.getText())) {
    		this.loginFailedLbl.setText("good!");
    	} else {
    		this.loginFailedLbl.setText("No good!");
    	}
    }

}
