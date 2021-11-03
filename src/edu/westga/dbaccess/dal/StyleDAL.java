package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import edu.westga.dbaccess.model.Style;

/**
* The style dal
*
* @author Jamia Echols
* @version Fall 2021
**/
public class StyleDAL {
	
	/**
	 * Gets a list of style 
	 * 
	 * @return a list of style

	 * @throws SQLException
	 */
	 public HashMap<Integer, String> getStyles() throws SQLException {
		 HashMap<Integer, String> styles = new HashMap<Integer, String>();
		String query = "select styleId, label from style";
			try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					styles.put(rs.getInt("styleId"), rs.getString("label"));
				}

			}
			return styles;
		}
}