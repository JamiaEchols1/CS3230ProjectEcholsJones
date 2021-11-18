package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.westga.dbaccess.model.ReturnTransaction;

/**
 * The return transaction dal
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class ReturnTransactionDAL {

	/**
	 * Gets the size of the table
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the size of the table
	 * @throws SQLException 
	 */
	public int getSizeOfTable() throws SQLException {
		String idQuery = "select * from return_transaction";
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
	 * Creates a return transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param transactionId the transaction id
	 * @param date date of transaction
	 * @param memberID the member id
	 * @param employeeId the employee id
	 * 
	 * @throws SQLException 
	 */
	public void createReturnTransaction(int transactionId, Date returnDate, int memberID, int employeeId, String returnItem) throws SQLException {
		String query = "CALL uspCreateReturnTransaction(?,?,?,?,?)";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
					PreparedStatement stmt = connection.prepareStatement(query)) { 
				stmt.setInt(1, transactionId);
				stmt.setDate(2, returnDate);
				stmt.setInt(3, memberID);
				stmt.setInt(4, employeeId);
				stmt.setString(5, returnItem);
				stmt.execute();
		}
	}

	/**
	 * Gets all the return transactions
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a list of return transactions 
	 * 
	 * @throws SQLException
	 */
	public List<ReturnTransaction> getAllTransactions() throws SQLException {
		List<ReturnTransaction> transactions = new ArrayList<ReturnTransaction>();
		String query = "select transactionId, returnDate, customerId, employeeId from return_transaction";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
					PreparedStatement stmt = connection.prepareStatement(query)) { 
				
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				transactions.add(new ReturnTransaction(rs.getInt("transactionId"), rs.getDate("returnDate"), rs.getInt("customerId"), rs.getInt("employeeId")));
			}
			return transactions;
		}
	}
	
	/**
	 * Gets the customers return transactions
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param customerId the customers id
	 * 
	 * @return a list of transactions
	 * 
	 * @throws SQLException
	 */
	public List<ReturnTransaction> getCustomersTransactions(int customerId) throws SQLException {
		List<ReturnTransaction> transactions = new ArrayList<ReturnTransaction>();
		String query = "select transactionId, returnDate, customerId, employeeId from return_transaction where customerId=?";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
					PreparedStatement stmt = connection.prepareStatement(query)) { 
				
			stmt.setInt(1, customerId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				transactions.add(new ReturnTransaction(rs.getInt("transactionId"), rs.getDate("returnDate"), rs.getInt("customerId"), rs.getInt("employeeId")));
			}
			return transactions;
		}
	}

	/**
	 * Gets the employees transactions
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param employeeId the employee id
	 * 
	 * @return the list of transactions
	 * 
	 * @throws SQLException
	 */
	public List<ReturnTransaction> getEmployeesTransactions(int employeeId) throws SQLException {
		List<ReturnTransaction> transactions = new ArrayList<ReturnTransaction>();
		String query = "select transactionId, returnDate, customerId, employeeId from return_transaction where employeeId=?";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
					PreparedStatement stmt = connection.prepareStatement(query)) { 
				
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				transactions.add(new ReturnTransaction(rs.getInt("transactionId"), rs.getDate("returnDate"), rs.getInt("customerId"), rs.getInt("employeeId")));
			}
			return transactions;
		}
	}

}
