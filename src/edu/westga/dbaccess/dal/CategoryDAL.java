package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import edu.westga.dbaccess.model.Category;

/**
* The Category dal
*
* @author Jamia Echols
* @version Fall 2021
**/
public class CategoryDAL {
	
	/**
	 * Gets a list of category
	 * 
	 * @return a list of category

	 * @throws SQLException
	 */
	 public HashMap<Integer, String> getCategory() throws SQLException {
		 HashMap<Integer, String> categories = new HashMap<Integer, String>();
		String query = "select categoryId, label from category";
			try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					categories.put(rs.getInt("categoryId"), rs.getString("label"));
				}

			}
			return categories;
		}
}
