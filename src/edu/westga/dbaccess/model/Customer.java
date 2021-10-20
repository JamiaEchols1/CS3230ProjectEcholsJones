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

	public Customer(int id, String name, String address1, String address2, String phoneNumber, Date birthday, Date registrationDate) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id must be greater than 0");
		}
		if (name == null) {
			throw new IllegalArgumentException("Name must not be null");
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
		
		this.name = name;
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
	 * @param memberID the memberID to set
	 */
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
