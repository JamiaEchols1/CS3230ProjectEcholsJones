package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.westga.dbaccess.model.Employee;

/**
 * DAL for accessing employee table
 * 
 * @author Rasheed Jones
 * @verison 1.0
 */
public class EmployeeDAL {

	public boolean validateEmployeeByLoginCredentials(String username, String password) throws SQLException {

		Employee employee = null;
		String query = "select address1, address2, employeeId, firstName, lastName, password, phoneNumber, username from employee when username=? and ";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			while (rs.first()) {
				employee = new Employee(rs.getString("address1"), rs.getString("address2"), rs.getInt("employeeId"),
						rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"),
						rs.getString("phoneNumber"), rs.getString("username"));
			}

		}
		return employee != null;
	}
}
