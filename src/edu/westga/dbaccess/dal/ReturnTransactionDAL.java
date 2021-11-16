package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public void createReturnTransaction(int transactionId, Date date, int memberID, int employeeId) throws SQLException {
		String query = "insert into return_transaction (transactionId, returnDate, customerId, employeeId) values (?,?,?,?)";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
					PreparedStatement stmt = connection.prepareStatement(query)) { 
				stmt.setInt(1, transactionId);
				stmt.setDate(2, date);
				stmt.setInt(4, memberID);
				stmt.setInt(5, employeeId);
				stmt.execute();
		}
	}

}
