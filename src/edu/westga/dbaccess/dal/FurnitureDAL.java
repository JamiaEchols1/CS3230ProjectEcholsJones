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
		String query = "select  furnitureId, price, styleId, categoryId, quantity from furniture";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
			PreparedStatement stmt = connection.prepareStatement(query)) {
				
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				furniture.add(new Furniture(rs.getInt("furnitureId"), rs.getDouble("price"), rs.getInt("styleId"),
					rs.getInt("categoryId"), rs.getInt("quantity")));
			}
		}
		return furniture;
	}
	
	/**
	 * Gets a list of furniture by their style and category
	 * 
	 * @param styleId the style Ids
	 * @param categoryId the category ids 
	 *
	 * @return a hashset of furniture

	 * @throws SQLException
	 */
	 public HashSet<Furniture> getFurnitureByCriteria(int styleId, int categoryId) throws SQLException {
		HashSet<Furniture> furniture = new HashSet<Furniture>();
		String filterQuery = "select furnitureId, price, styleId, categoryId, quantity from furniture where styleId=? and categoryId=?";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(filterQuery)) {

				stmt.setLong(1, styleId);
				stmt.setLong(2, categoryId);

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					furniture.add(new Furniture(rs.getInt("furnitureId"), rs.getDouble("price"), rs.getInt("styleId"),
						rs.getInt("categoryId"), rs.getInt("quantity")));
				}

			}
		return furniture;
	 }
}