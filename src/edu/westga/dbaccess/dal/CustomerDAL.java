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
 * @version 1.0
 */
public class CustomerDAL {

	/**
	 * Gets a customer based on their memberId
	 * @param memberId the memberId to get the customer for
	 * @return a customer with the given memberId
	 * @throws SQLException
	 */
	public Customer getCustomerWithMemberId(int memberId) throws SQLException{
		
		String query = "select memberID, name, gender, address1, address2, phoneNumber, birthday, registrationDate from customer where memberId = ?";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)){ 
		    
			stmt.setInt(1, memberId);
	
			ResultSet rs = stmt.executeQuery();
			Customer customer = null;
			while(rs.next()) {
			customer = new Customer(rs.getInt("memberId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("address1"), rs.getString("state"), rs.getString("city"), rs.getString("zipcode"), rs.getString("phoneNumber"), rs.getDate("birthday"), rs.getDate("registrationDate"));
			}
			return customer;
		}
	}
	
	
	/**
 * Registers a customer
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param address1
	 * @param zipcode
	 * @param state
	 * @param city
	 * @param phoneNumber
	 * @param birthday
	 * @param registrationDate
	 * @throws SQLException
	 */
	public void registerCustomer(String firstName, String lastName, String gender, String address1, String zipcode, String state, String city, String phoneNumber, Date birthday, Date registrationDate) throws SQLException {
		String query = "insert into customer (name, gender, address1, address2, phoneNumber, birthday, registrationDate) values (?,?,?,?,?,?,?)";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)) { 
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, gender);
			stmt.setString(4, address1);
			stmt.setString(5, zipcode);
			stmt.setString(6, state);
			stmt.setString(7, city);
			stmt.setString(8, phoneNumber);
			stmt.setDate(9, birthday);
			stmt.setDate(10, registrationDate);
			stmt.execute();
		}
	}
}
