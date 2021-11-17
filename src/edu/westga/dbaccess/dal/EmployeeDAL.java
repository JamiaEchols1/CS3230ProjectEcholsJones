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

	/**
	 * Validates an employee by their username and password.
	 * 
	 * @param username the employee's username
	 * @param password the employee's password
	 * @return
	 * @throws SQLException
	 */
	public Employee getEmployeeByLoginCredentials(String username, String password) throws SQLException {

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

		return employee;
	}

	/**
	 * Gets the employee by the employee Id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param employeeId the employeeId
	 * 
	 * @return the employee
	 * 
	 * @throws SQLException
	 */
	public Employee getEmployeeByEmployeeId(int employeeId) throws SQLException {
		Employee employee = null;
		String query = "select address1, address2, employeeId, firstName, lastName, password, phoneNumber, username from employee where employeeId=?";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, employeeId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				employee = new Employee(rs.getString("address1"), rs.getString("address2"), rs.getInt("employeeId"),
						rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"),
						rs.getString("phoneNumber"), rs.getString("username"));
			}

		}

		return employee;
	}
}
