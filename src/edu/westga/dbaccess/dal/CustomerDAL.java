package edu.westga.dbaccess.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.westga.dbaccess.model.Customer;

/**
 * DAL for accessing Customer table
 * @author Rasheed Jones
 * @verison 1.0
 */
public class CustomerDAL {

	public Customer getCustomerWithUsername(String username, String password) throws SQLException{
		
		String query = "select memberID, name, address1, address2, phoneNumber, birthday, registrationDate, username, password from customer where username = ? and password = ?";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)){ 
		    
			stmt.setString(1, username);
			stmt.setString(2, password);
	
			ResultSet rs = stmt.executeQuery();
			Customer customer = null;
			while(rs.next()) {
			customer = new Customer(rs.getInt("memberId"), rs.getString("name"), rs.getString("address1"), rs.getString("address2"), rs.getString("phoneNumber"), rs.getDate("birthday"), rs.getDate("registrationDate"));
			}
			return customer;
        } 
		
	}
}
