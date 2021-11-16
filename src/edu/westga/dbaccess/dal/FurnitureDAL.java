package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import edu.westga.dbaccess.model.Furniture;

/**
* The furniture dal
*
* @author Jamia Echols
* @version Fall 2021
**/
public class FurnitureDAL {
	
	/**
	 * Gets all the furniture items
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a hashset of furniture items
	 * 
	 * @throws SQLException
	 */
	public HashSet<Furniture> getAllFurniture() throws SQLException {
		HashSet<Furniture> furniture = new HashSet<Furniture>();
		String query = "select  furnitureId, daily_rental_rate, styleId, categoryId, quantity from furniture";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
			PreparedStatement stmt = connection.prepareStatement(query)) {
				
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				furniture.add(new Furniture(rs.getInt("furnitureId"), rs.getDouble("daily_rental_rate"), rs.getInt("styleId"),
					rs.getInt("categoryId"), rs.getInt("quantity")));
			}
		}
		return furniture;
	}
	
	/**
	 * Gets a list of furniture by their style
	 * 
	 * @param styleId the style Ids
	 *
	 * @return a hashset of furniture

	 * @throws SQLException
	 */
	 public HashSet<Furniture> getFurnitureByStyle(int styleId) throws SQLException {
		HashSet<Furniture> furniture = new HashSet<Furniture>();
		String filterQuery = "select furnitureId, daily_rental_rate, styleId, categoryId, quantity from furniture where styleId=?";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(filterQuery)) {

				stmt.setInt(1, styleId);

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					furniture.add(new Furniture(rs.getInt("furnitureId"), rs.getDouble("daily_rental_rate"), rs.getInt("styleId"),
						rs.getInt("categoryId"), rs.getInt("quantity")));
				}

			}
		return furniture;
	 }
	 
		/**
		 * Gets a list of furniture by their category
		 * 
		 * @param categoryId the category ids 
		 *
		 * @return a hashset of furniture

		 * @throws SQLException
		 */
		 public HashSet<Furniture> getFurnitureByCategory(int categoryId) throws SQLException {
			 HashSet<Furniture> furniture = new HashSet<Furniture>();
			String filterQuery = "select furnitureId, daily_rental_rate, styleId, categoryId, quantity from furniture where categoryId=?";
			try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
					PreparedStatement stmt = connection.prepareStatement(filterQuery)) {

					stmt.setInt(1, categoryId);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						furniture.add(new Furniture(rs.getInt("furnitureId"), rs.getDouble("daily_rental_rate"), rs.getInt("styleId"),
							rs.getInt("categoryId"), rs.getInt("quantity")));
					}

				}
			return furniture;
		 }
		 
			/**
			 * Gets a furniture by its Id
			 * 
			 * @param furnitureId the furniture id
			 *
			 * @return a furniture

			 * @throws SQLException
			 */
			 public Furniture getFurnitureById(int furnitureId) throws SQLException {
				String filterQuery = "select furnitureId, daily_rental_rate, styleId, categoryId, quantity from furniture where furnitureId=?";
				Furniture furniture = null;
				try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
						PreparedStatement stmt = connection.prepareStatement(filterQuery)) {

						stmt.setInt(1, furnitureId);

						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							furniture = new Furniture(rs.getInt("furnitureId"), rs.getDouble("daily_rental_rate"), rs.getInt("styleId"),
								rs.getInt("categoryId"), rs.getInt("quantity"));
						}
						return furniture;
					}
			 }
}