package edu.westga.dbaccess.model;

import java.util.Date;

/**
 * Customer class
 * 
 * @author Rasheed Jones
 * @version 1.0
 */
public class Customer {
	private int memberID;
	private String name;
	private String address1;
	private String address2;
	private String phoneNumber;
	private Date birthday;
	private Date registrationDate;
	private String gender;

	/**
	 * 
	 * Constructor for customers
	 * 
	 * @param id the customers id
	 * @param name the customers name
	 * @param gender the customers gender
	 * @param address1 the customers first address portion
	 * @param address2 the customer's state
	 * @param phoneNumber the customers phone number
	 * @param birthday the customers birthday
	 * @param registrationDate the customers registration date;
	 */
	public Customer(int id, String name, String gender, String address1, String address2, String phoneNumber, Date birthday, Date registrationDate) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id must be greater than 0");
		}
		if (name == null) {
			throw new IllegalArgumentException("Name must not be null");
		}
		if (gender == null) {
			throw new IllegalArgumentException("gender must not be null");
		}
		if (address1 == null) {
			throw new IllegalArgumentException("address1 must not be null");
		}
		if (address2 == null) {
			throw new IllegalArgumentException("address2 must not be null");
		}
		if (phoneNumber == null) {
			throw new IllegalArgumentException("phoneNumber must not be null");
		}
		if (birthday == null) {
			throw new IllegalArgumentException("birthday must not be null");
		}
		if (registrationDate == null) {
			throw new IllegalArgumentException("registrationDate must not be null");
		}
		this.memberID = id;
		this.name = name;
		this.gender = gender;
		this.address1 = address1;
		this.address2 = address2;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.registrationDate = registrationDate;

	}

	/**
	 * @return the memberID
	 */
	public int getMemberID() {
		return memberID;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}


	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}


	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * 
	 * @return return this.gender
	 */
	public String getGender() {
		return gender;
	}
}
