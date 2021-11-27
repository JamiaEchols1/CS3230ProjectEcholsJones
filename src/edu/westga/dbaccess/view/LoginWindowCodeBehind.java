package edu.westga.dbaccess.view;

import edu.westga.dbaccess.controller.LoginWindowController;
import edu.westga.dbaccess.dal.AdminDAL;
import edu.westga.dbaccess.dal.EmployeeDAL;
import edu.westga.dbaccess.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
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
	private RadioButton employeeRadioButton;
	
	@FXML
	private RadioButton adminRadioButton;

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
    
    private AdminDAL adminDal;
    
    private EmployeeDAL employeeDal;
    
    @FXML
    void loginBtnClick(ActionEvent event) {
    	
    	try {
    		if (this.employeeRadioButton.isSelected()) {
    		this.controller.login(this.userIdTextField.getText(), this.passwordTxtField.getText());
    	
    		this.loginFailedLbl.setText("good!");
    		
    		this.newWindow.generateWindow("Registration Window", "edu\\westga\\dbaccess\\view\\LandingWindow.fxml", event);
    		} else {
    			int employeeId = this.adminDal.getAdminByLoginCredentials(this.userIdTextField.getText(), this.passwordTxtField.getText());
    			Employee.setEmployee(this.employeeDal.getEmployeeByEmployeeId(employeeId));
    			this.newWindow.generateWindow("Admin Query Window", "edu\\westga\\dbaccess\\view\\AdminQueryWindow.fxml",
    					event);
    		}
    	} catch (Exception e) {
    		this.loginFailedLbl.setText("Invalid login!");
    	}
    }
    
    @FXML
    void initialize() {
    	this.controller = new LoginWindowController();
    	this.newWindow = new WindowGenerator();
    	this.adminDal = new AdminDAL();
    	this.employeeDal = new EmployeeDAL();
    	this.employeeRadioButton.setSelected(true);
    	this.setupBindings();
    }
    
    private void setupBindings() {
    	this.employeeRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
    		this.employeeRadioButton.setSelected(newValue);
    		this.adminRadioButton.setSelected(!newValue);
    	});
    	this.adminRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
    		this.employeeRadioButton.setSelected(!newValue);
    		this.adminRadioButton.setSelected(newValue);
    	});
    	
    }

}
