package edu.westga.dbaccess.dal;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import edu.westga.dbaccess.model.Customer;

/**
 * DAL for accessing Customer table
 * @author Rasheed Jones
 * @version 1.0
 */
public class CustomerDAL {
	
	/**
	 * Gets the list of customers;
	 * 
	 * @return the map of the customers
	 */
	public HashMap<Integer, Customer> getAllCustomers() throws SQLException {
		HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();
		String query = "select * from customer";
		try (Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING);
				PreparedStatement stmt = connection.prepareStatement(query)){ 
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customers.put(rs.getInt("memberId"), new Customer(rs.getInt("memberId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("address1"), rs.getString("state"), rs.getString("city"), rs.getString("zipcode"), rs.getString("phoneNumber"), rs.getDate("birthday"), rs.getDate("registrationDate")));
			}
			return customers;
		}
	}

	/**
	 * Gets a customer based on their memberId
	 * @param memberId the memberId to get the customer for
	 * @return a customer with the given memberId
	 * @throws SQLException
	 */
	public Customer getCustomerWithMemberId(int memberId) throws SQLException{
		String query = "select memberID, firstName, lastName, gender, address1, state, zipcode, city, phoneNumber, birthday, registrationDate from customer where memberId = ?";
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
		String query = "insert into customer (firstname, lastname, gender, address1, zipcode, state, city, phonenumber, birthday, registrationdate) values (?,?,?,?,?,?,?,?,?,?)";
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

	public ArrayList<Customer> getCustomerWithFullName(String firstName, String lastName) throws SQLException {
		String query = "select memberID, firstName, lastName, gender, address1, state, zipcode, city, phoneNumber, birthday, registrationDate from customer where firstName = ? and lastName = ?";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)){ 
		    
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
	
			ResultSet rs = stmt.executeQuery();
			ArrayList<Customer> customers = new ArrayList<Customer>();
			while(rs.next()) {
			customers.add(new Customer(rs.getInt("memberId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("address1"), rs.getString("state"), rs.getString("city"), rs.getString("zipcode"), rs.getString("phoneNumber"), rs.getDate("birthday"), rs.getDate("registrationDate")));
			}
			return customers;
		}
	}

	public ArrayList<Customer> getCustomerWithPhoneNumber(String phoneNumber) throws SQLException {
		String query = "select memberID, firstName, lastName, gender, address1, state, zipcode, city, phoneNumber, birthday, registrationDate from customer where phoneNumber = ?";
		try ( Connection connection = DriverManager.getConnection(ConnectionString.CONNECTION_STRING); 
				PreparedStatement stmt = connection.prepareStatement(query)){ 
		    
			stmt.setString(1, phoneNumber);

	
			ResultSet rs = stmt.executeQuery();
			ArrayList<Customer> customers = new ArrayList<Customer>();
			while(rs.next()) {
			customers.add(new Customer(rs.getInt("memberId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("address1"), rs.getString("state"), rs.getString("city"), rs.getString("zipcode"), rs.getString("phoneNumber"), rs.getDate("birthday"), rs.getDate("registrationDate")));
			}
			return customers;
		}
	}
}
