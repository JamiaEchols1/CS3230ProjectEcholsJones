package edu.westga.dbaccess.view;

import java.io.IOException;
import java.util.List;

import edu.westga.dbaccess.model.Employee;
import edu.westga.dbaccess.model.Item;
import edu.westga.dbaccess.model.RentalTransaction;
import edu.westga.dbaccess.utils.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * The Transaction Summary window
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class TransactionWindowCodeBehind {

    @FXML
    private TextArea transactionSummaryTextArea;

    @FXML
    private Button backToHomeButton;

    private Employee employee;

    @FXML
    void handleBackToHomeButtonClick(ActionEvent event) {
    	Parent root;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edu\\westga\\dbaccess\\view\\LandingWindow.fxml"));

			root = loader.load();

			LandingWindowCodeBehind  codeBehind = loader.getController();
			
			codeBehind.setEmployee(this.employee);

			Stage stage = new Stage();

			stage.setTitle("Home Window");

			stage.setScene(new Scene(root, 452, 440));

			stage.show();

			Node node = ((Node)(event.getSource()));

			Stage thisStage = (Stage) node.getScene().getWindow();

			thisStage.close();

		} catch (IOException e) {

            e.printStackTrace();

        }
    }
    
    /**
     * Sets the employee
     * 
     * @precondition employee != null
     * @postcondition none
     * 
     * @param employee the employee
     */
    public void setEmployee(Employee employee) {
    	if (employee == null) {
    		throw new IllegalArgumentException(UI.ErrorMessages.EMPLOYEE_NULL);
    	}
    	this.employee = employee;
    }
    
    /**
     * Sets the transaction text
     * 
     * @precondition none
     * @postcondition none
     * 
     * @param transaction the transaction
     * @param items the items in the transaction
     */
    public void setTransactionText(Object transaction, List<Item> items) {
    	String transactionSummary = transaction.toString() + System.lineSeparator();
    	for (Object item : items) {
    		transactionSummary += item.toString() + System.lineSeparator();
    	}
    	this.transactionSummaryTextArea.setText(transactionSummary);
    }

}

