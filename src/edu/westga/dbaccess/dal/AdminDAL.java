package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * The admin Query Interface DAL
 * 
 * @author Jamia Echols
 * @Version Fall 2021
 *
 */
public class AdminDAL {

	/**
	 * Runs the enter query
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param query the query
	 * 
	 * @return the result of the query
	 * 
	 * @throws SQLException
	 */
	public String runQuery(String query) throws SQLException {
		String results = "";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					results += rsmd.getColumnName(i) + ": " + rs.getObject(i) + " | ";
				}
				results += System.lineSeparator();
			}

		}
		return results;
	}

	/**
	 * Validates an admin by their username and password.
	 * 
	 * @param username the admin's username
	 * @param password the admin's password
	 * 
	 * @return the employeeId
	 * @throws SQLException
	 */
	public int getAdminByLoginCredentials(String username, String password) throws SQLException {

		int employeeId = 0;
		String query = "select employee from admin where username=? and password=?";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				employeeId = rs.getInt("employee");
			}

		}

		return employeeId;
	}

}
