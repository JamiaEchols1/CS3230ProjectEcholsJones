package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.westga.dbaccess.model.Item;

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

	/**
	 * Gets the items from a transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param transactionId the transaction id
	 * 
	 * @return items from the transaction
	 * 
	 * @throws SQLException
	 */
	public List<Item> getItems(int transactionId) throws SQLException {
		List<Item> items = new ArrayList<Item>();
		String query = "Select returnId, furnitureId, quantity from return_item where returnId=?";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)) { 
			stmt.setInt(1, transactionId);;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				items.add(new Item(rs.getInt("returnId"), rs.getInt("furnitureId"), rs.getInt("quantity")));
			}
		}
		return items;
	}
}
