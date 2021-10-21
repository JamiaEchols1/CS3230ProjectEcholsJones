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

	private String name;
	private String username;
	private int id;

	/**
	 * Validates an employee by their username and password.
	 * @param username the employee's username
	 * @param password the employee's password
	 * @return
	 * @throws SQLException
	 */
	public boolean validateEmployeeByLoginCredentials(String username, String password) throws SQLException {

		Employee employee = null;
		String query = "select address1, address2, employeeId, firstName, lastName, password, phoneNumber, username from employee where username=? and password=?";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				employee = new Employee(rs.getString("address1"), rs.getString("address2"), rs.getInt("employeeId"),
						rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"),
						rs.getString("phoneNumber"), rs.getString("username"));
			}

		}
		if (employee != null) {
			this.name = employee.getFirstName() + " " + employee.getLastName();
			this.username = employee.getUsername();
			this.id = employee.getEmployeeId();
			
		}
		return employee != null;
	}

	/**
	 * @return this.name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 *@return this.username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @return this.id
	 */
	public int getId() {
		return id;
	}
}
