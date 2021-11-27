package edu.westga.dbaccess.view;

import edu.westga.dbaccess.controller.LoginWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * The login window code behind
 * 
 * @author Rasheed Jones
 * @version Fall 2021
 *
 */
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
    
    private WindowGenerator newWindow;
    
    @FXML
    void loginBtnClick(ActionEvent event) {
    	try {
    		this.controller.login(this.userIdTextField.getText(), this.passwordTxtField.getText());
    	
    		this.loginFailedLbl.setText("good!");
    		
    		this.newWindow.generateWindow("Registration Window", "edu\\westga\\dbaccess\\view\\LandingWindow.fxml", event);
    		
    	} catch (Exception e) {
    		this.loginFailedLbl.setText("Invalid login!");
    	}
    }
    
    @FXML
    void initialize() {
    	this.controller = new LoginWindowController();
    	this.newWindow = new WindowGenerator();
    }

}
