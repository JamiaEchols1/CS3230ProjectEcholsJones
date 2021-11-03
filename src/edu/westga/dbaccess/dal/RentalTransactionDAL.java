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
	 * @return the transaction Id
	 */
	public int createRentalTransaction(Date dueDate, Date transactionDate, int customerId, int employeeId) throws SQLException {
		String query = "insert onto rental_transaction (dueDate, transactionDate, customerId, employeeId values (?,?,?,?)";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
					PreparedStatement stmt = connection.prepareStatement(query)) { 
				stmt.setDate(1, dueDate);
				stmt.setDate(2, transactionDate);
				stmt.setInt(3, customerId);
				stmt.setInt(4, employeeId);
				ResultSet rs = stmt.executeQuery();
				
				return rs.getInt("transactionId");
			}
	}
}
