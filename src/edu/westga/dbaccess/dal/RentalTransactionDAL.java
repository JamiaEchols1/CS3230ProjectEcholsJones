package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public void createRentalTransaction(int transactionId, Date dueDate, Date transactionDate, int customerId, int employeeId) throws SQLException {
		String query = "insert into rental_transaction (transactionId, dueDate, transactionDate, customerId, employeeId) values (?,?,?,?,?)";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
					PreparedStatement stmt = connection.prepareStatement(query)) { 
				stmt.setInt(1, transactionId);
				stmt.setDate(2, dueDate);
				stmt.setDate(3, transactionDate);
				stmt.setInt(4, customerId);
				stmt.setInt(5, employeeId);
				stmt.execute();
		}
	}
	
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
}
