package edu.westga.dbaccess.model;

import java.sql.SQLException;

import edu.westga.dbaccess.dal.CustomerDAL;
import edu.westga.dbaccess.dal.EmployeeDAL;
import edu.westga.dbaccess.dal.FurnitureDAL;
import edu.westga.dbaccess.dal.RentalTransactionDAL;
import edu.westga.dbaccess.dal.ReturnTransactionDAL;

/**
 * Generates report for admin
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class ReportGenerator {
	private RentalTransactionDAL rentalTransactionDal;
	private ReturnTransactionDAL returnTransactionDal;
	private CustomerDAL customerDal;
	private FurnitureDAL furnitureDal;
	private EmployeeDAL employeeDal;
	
	/**
	 * Creates new report generator
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public ReportGenerator() {
		this.rentalTransactionDal = new RentalTransactionDAL();
		this.returnTransactionDal = new ReturnTransactionDAL();
		this.customerDal = new CustomerDAL();
		this.furnitureDal = new FurnitureDAL();
		this.employeeDal = new EmployeeDAL();
	}

	/**
	 * Generate full report
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the full report
	 * @throws SQLException 
	 */
	public String generateFullReport() throws SQLException {
		String report = "";
		report += "Rental Transactions:" + System.lineSeparator();
		for (RentalTransaction rentalTransaction: this.rentalTransactionDal.getAllTransactions()) {
			report += rentalTransaction.toString() + System.lineSeparator();
		}
		report += "Return Transactions:" + System.lineSeparator();
		for (ReturnTransaction returnTransaction: this.returnTransactionDal.getAllTransactions()) {
			report += returnTransaction.toString() + System.lineSeparator();
		}
		report += "Inventory:" + System.lineSeparator();
		for (Furniture furniture : this.furnitureDal.getAllFurniture()) {
			report += furniture.toString() + System.lineSeparator();
		}
		report += "Members:" + System.lineSeparator();
		for (Customer customer : this.customerDal.getAllCustomers().values()) {
			report += customer.toString() + System.lineSeparator();
		}	
		return report;
	}
	
	/**
	 * Generates a report using customer id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param customerId the customer id
	 * 
	 * @return full report from customer id
	 * @throws SQLException 
	 */
	public String generateMemberReport(int customerId) throws SQLException {
		String report = "Member: " + this.customerDal.getCustomerWithMemberId(customerId) + System.lineSeparator();
		report += "Rental Transactions:" + System.lineSeparator();
		for (RentalTransaction rentalTransaction: this.rentalTransactionDal.getCustomersTransactions(customerId)) {
			report += rentalTransaction.toString() + System.lineSeparator();
		}
		report += "Return Transactions:" + System.lineSeparator();
		for (ReturnTransaction returnTransaction: this.returnTransactionDal.getCustomersTransactions(customerId)) {
			report += returnTransaction.toString() + System.lineSeparator();
		}
		return report;
	}
	
	/**
	 * Generates the inventory report
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the inventory report
	 *
	 * @throws SQLException
	 */
	public String generateInventoryReport() throws SQLException {
		String report = "";
		report += "Inventory:" + System.lineSeparator();
		for (Furniture furniture : this.furnitureDal.getAllFurniture()) {
			report += furniture.toString() + System.lineSeparator();
		}
		return report;
	}
	
	/**
	 * Generate report by employee id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param employeeId the employee id
	 * 
	 * @return the report
	 * @throws SQLException 
	 */
	public String generateReportByEmployee(int employeeId) throws SQLException {
		String report = "Employee: " + this.employeeDal.getEmployeeByEmployeeId(employeeId) + System.lineSeparator();
		report += "Rental Transactions:" + System.lineSeparator();
		for (RentalTransaction rentalTransaction: this.rentalTransactionDal.getEmployeesTransactions(employeeId)) {
			report += rentalTransaction.toString() + System.lineSeparator();
		}
		report += "Return Transactions:" + System.lineSeparator();
		for (ReturnTransaction returnTransaction: this.returnTransactionDal.getEmployeesTransactions(employeeId)) {
			report += returnTransaction.toString() + System.lineSeparator();
		}
		return report;
	}
}
