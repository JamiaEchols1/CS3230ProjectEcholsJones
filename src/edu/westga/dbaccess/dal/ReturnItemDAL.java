package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The class return item dal
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class ReturnItemDAL {

	/**
	 * Creates a new return item
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param returnId 
	 * @param furnitureId
	 * @param rentalId
	 * @param date
	 * @param quantity
	 * 
	 * @throws SQLException 
	 */
	public void createReturnItem(int returnId, int furnitureId, int rentalId, Date returnDate, Integer quantity) throws SQLException {
		String query = "Insert one return_item (rentalId, furnitureId, returnId, returnDate) values (?,?,?,?)";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)) { 
			stmt.setInt(1, returnId);
			stmt.setInt(2, furnitureId);
			stmt.setInt(3, rentalId);
			stmt.setDate(4, returnDate);
			stmt.setInt(5, quantity);
			
		}
	}

}
