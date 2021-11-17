package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.westga.dbaccess.model.RentalTransaction;

/**
 * Rental Transaction DAL
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class RentalTransactionDAL {

	/**
	 * Creates a rental transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param dueDate the due date
	 * @param transactionDate the transaction date
	 * @param customerId the customerId
	 * @param employeeId the employeeId
	 * 
	 * @throws SQLException
	 * 
	 */
	public void createRentalTransaction(int transactionId, Date dueDate, Date transactionDate, int customerId, int employeeId, String rentalItems) throws SQLException {
		//String query = "insert into rental_transaction (transactionId, dueDate, transactionDate, customerId, employeeId) values (?,?,?,?,?)";
		String query = "CALL uspCreateRentalTransaction(?,?,?,?,?,?)";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
					PreparedStatement stmt = connection.prepareStatement(query)) { 
				stmt.setInt(1, transactionId);
				stmt.setDate(2, dueDate);
				stmt.setDate(3, transactionDate);
				stmt.setInt(4, customerId);
				stmt.setInt(5, employeeId);
				stmt.setString(6, rentalItems);
				stmt.execute();
		}
	}
	
	/**
	 * Gets the size of the table
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the size of the table
	 * 
	 * @throws SQLException
	 */
	public int getSizeOfTable() throws SQLException {
		String idQuery = "select * from rental_transaction";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(idQuery)) { 

			ResultSet rs = stmt.executeQuery();
			
			int size = 0;
			while (rs.next()) {
				size++;
			}
			return size;
		}
	}
	
	/**
	 * Gets the customers transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param customerId the customer id
	 * 
	 * @return the list of transactions
	 * 
	 * @throws SQLException
	 */
	public List<RentalTransaction> getCustomersTransactions(int customerId) throws SQLException {
		String query = "select transactionId, dueDate, transactionDate, customerId, employeeId from rental_transaction where customerId=?";
		List<RentalTransaction> transactions = new ArrayList<RentalTransaction>();
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {
			
			stmt.setInt(1, customerId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				transactions.add(new RentalTransaction(rs.getInt("transactionId"), rs.getDate("dueDate"), rs.getDate("transactionDate"), rs.getInt("customerId"), rs.getInt("employeeId")));
			}
		}
		return transactions;
	}

	/**
	 * Gets all transactions
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return list of transactions
	 * 
	 * @throws SQLException
	 */
	public List<RentalTransaction> getAllTransactions() throws SQLException {
		String query = "select transactionId, dueDate, transactionDate, customerId, employeeId from rental_transaction";
		List<RentalTransaction> transactions = new ArrayList<RentalTransaction>();
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {
	
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				transactions.add(new RentalTransaction(rs.getInt("transactionId"), rs.getDate("dueDate"), rs.getDate("transactionDate"), rs.getInt("customerId"), rs.getInt("employeeId")));
			}
		}
		return transactions;
	}

	/**
	 * Gets the employee's transactions
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param employeeId the employee id
	 * 
	 * @return list of transactions
	 * 
	 * @throws SQLException
	 */
	public List<RentalTransaction> getEmployeesTransactions(int employeeId) throws SQLException {
		String query = "select transactionId, dueDate, transactionDate, customerId, employeeId from rental_transaction where employeeId=?";
		List<RentalTransaction> transactions = new ArrayList<RentalTransaction>();
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {
			
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				transactions.add(new RentalTransaction(rs.getInt("transactionId"), rs.getDate("dueDate"), rs.getDate("transactionDate"), rs.getInt("customerId"), rs.getInt("employeeId")));
			}
		}
		return transactions;
	}
}
