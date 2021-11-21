package edu.westga.dbaccess.view;

import java.io.IOException;

import edu.westga.dbaccess.controller.LoginWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    @FXML
    void loginBtnClick(ActionEvent event) {
    	try {
    		this.controller.login(this.userIdTextField.getText(), this.passwordTxtField.getText());
    	
    		this.loginFailedLbl.setText("good!");
    		Parent root;

    		try {

    			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\LandingWindow.fxml"));

    			System.out.println(getClass().getResource("edu\\westga\\devops\\view\\LandingWindow.fxml"));

    			root = loader.load();

    			LandingWindowCodeBehind landingWindow = loader.getController();
    			
    			landingWindow.setTitle(this.controller.getEmployee().getFullName(), this.controller.getEmployee().getUsername(), this.controller.getEmployee().getEmployeeId());
    			
    			landingWindow.setEmployee(this.controller.getEmployee());;
    			
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
    	} catch (Exception e) {
    		this.loginFailedLbl.setText("Invalid login!");
    	}
    }
    
    @FXML
    void initialize() {
    	this.controller = new LoginWindowController();
    }

}
