package edu.westga.dbaccess.view;

import java.io.IOException;

import edu.westga.devops.controller.LoginWindowController;
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
    		Parent root;

    		try{

    			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\RegisterWindow.fxml"));

    			System.out.println(getClass().getResource("edu\\westga\\devops\\view\\MainWindow.fxml"));

    			root = loader.load();

    			RegisterWindowCodeBehind registerWindow = loader.getController();

    			Stage stage = new Stage();

    			stage.setTitle("Main Window");

    			stage.setScene(new Scene(root, 272, 440));

    			stage.show();

    			Node node = ((Node)(event.getSource()));

    			Stage thisStage = (Stage) node.getScene().getWindow();

    			thisStage.close();

    		} catch (IOException e) {

                e.printStackTrace();

            }
    	} else {
    		this.loginFailedLbl.setText("No good!");
    	}
    }
    
    @FXML
    void initialize() {
    	this.controller = new LoginWindowController();
    }

}
