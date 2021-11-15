package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.westga.dbaccess.model.RentalItem;

/**
 * Rental item DAL
 * 
 * @author Jamia Echols
 * @version Fall 2021
 *
 */
public class RentalItemDAL {
	
	/**
	 * Creates new rental Item
	 * 
	 * @param transactionId the transaction id
	 * @param furnitureId the furniture id
	 * @param quantity the quantity
	 * @throws SQLException
	 */
	public void createRentalItem(int transactionId, int furnitureId, int quantity) throws SQLException {
		String query = "Insert one rental_item (transactionId, furnitureId, quantity) values (?,?,?)";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)) { 
			stmt.setInt(1, transactionId);
			stmt.setInt(2, furnitureId);
			stmt.setInt(3, quantity);
			
		}
	} 
	
	public List<RentalItem> rentalItems(int transactionId) throws SQLException {
		String query = "Select transactionId, furnitureId, quantity from rental_item";
		List<RentalItem> items = new ArrayList<RentalItem>();
		try( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				items.add(new RentalItem(rs.getInt("transactionId"), rs.getInt("furnitureId"), rs.getInt("quantity")));
			}
		}
		return items;
	}
}
