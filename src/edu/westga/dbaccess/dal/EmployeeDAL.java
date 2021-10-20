package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.westga.dbaccess.model.Employee;

/**
 * DAL for accessing employee table
 * @author lyang
 * @verison 1.0
 */
public class EmployeeDAL {

	public Employee getEmployeesByLoginCredentials(String username, String password) throws SQLException{
		
		Employee employee = null;
		String query = "select address1, address2, employeeId ";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)){ 
		    
			stmt.setString(1, username);
			stmt.setString(2, password);
	
			ResultSet rs = stmt.executeQuery();
			while (rs.first()) {
			}

        } 
		return employee;
	}
}
