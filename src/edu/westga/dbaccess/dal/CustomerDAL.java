package edu.westga.dbaccess.dal;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import edu.westga.dbaccess.model.Customer;

/**
 * DAL for accessing Customer table
 * @author Rasheed Jones
 * @verison 1.0
 */
public class CustomerDAL {

	public Customer getCustomerWithUsername(int memberId) throws SQLException{
		
		String query = "select memberID, name, gender, address1, address2, phoneNumber, birthday, registrationDate from customer where memberId = ?";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)){ 
		    
			stmt.setInt(1, memberId);
	
			ResultSet rs = stmt.executeQuery();
			Customer customer = null;
			while(rs.next()) {
			customer = new Customer(rs.getInt("memberId"), rs.getString("name"), rs.getString("gender"), rs.getString("address1"), rs.getString("address2"), rs.getString("phoneNumber"), rs.getDate("birthday"), rs.getDate("registrationDate"));
			}
			return customer;
		}
	}
	
	
	public void registerCustomer(String name, String gender, String address1, String address2, String phoneNumber, Date birthday, Date registrationDate) throws SQLException {
		String query = "insert into customer (name, gender, address1, address2, phoneNumber, birthday, registrationDate) values (?,?,?,?,?,?,?)";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)){ 
			stmt.setString(1, name);
			stmt.setString(2, gender);
			stmt.setString(3, address1);
			stmt.setString(4, address2);
			stmt.setString(5, phoneNumber);
			stmt.setDate(6, birthday);
			stmt.setDate(7, registrationDate);
			stmt.execute();
		}
	}
}
