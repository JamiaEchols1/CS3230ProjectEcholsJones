package edu.westga.dbaccess.model;

import java.sql.Date;
import edu.westga.dbaccess.utils.UI;

/**
 * Customer class
 * 
 * @author Rasheed Jones
 * @version 1.0
 */
public class Customer {
	private int memberID;
	private String firstName;
	private String lastName;
	private String address1;
	private String zipcode;
	private String state;
	private String city;
	private String phoneNumber;
	private Date birthday;
	private Date registrationDate;
	private String gender;

	/**
	 * 
	 * Constructor for customers
	 * 
	 * @param id               the customers id
	 * @param firstName        the first name of the customer
	 * @param lastName         the last name of the customer
	 * @param gender           the customers gender
	 * @param address1         the customers first address portion
	 * @param state            the customers state
	 * @param zipcode          the customers zipcode
	 * @param city             the customers city
	 * @param phoneNumber      the customers phone number
	 * @param birthday         the customers birthday
	 * @param registrationDate the customers registration date;
	 */
	public Customer(int id, String firstName, String lastName, String gender, String address1, String state,
			String city, String zipcode, String phoneNumber, Date birthday, Date registrationDate) {
		if (id <= 0) {
			throw new IllegalArgumentException(UI.ErrorMessages.ID_NULL);
		}
		if (firstName == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.FIRSTNAME_NULL);
		}
		if (lastName == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.LASTNAME_NULL);
		}
		if (gender == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.GENDER_NULL);
		}
		if (address1 == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.ADDRESS_NULL);
		}
		if (state == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.STATE_NULL);
		}
		if (phoneNumber == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.PHONENUMBER_NULL);
		}
		if (birthday == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.BIRTHDAY_NULL);
		}
		if (registrationDate == null) {
			throw new IllegalArgumentException(UI.ErrorMessages.REGISTATIONDATE_NULL);
		}
		this.memberID = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address1 = address1;
		this.state = state;
		this.zipcode = zipcode;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.registrationDate = registrationDate;
	}

	/**
	 * Gets the member Id
	 * 
	 * @return the memberID
	 */
	public int getMemberID() {
		return this.memberID;
	}

	/**
	 * Gets the firstname
	 * 
	 * @return the firstname
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Gets the last name
	 * 
	 * @return the lastname
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Gets the address
	 * 
	 * @return the address1
	 */
	public String getAddress1() {
		return this.address1;
	}

	/**
	 * gets the phonenumber
	 * 
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
	 * Returns the gender
	 * 
	 * @return return this.gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Returns the zipcode
	 * 
	 * @return the zipcode
	 */
	public String getZipcode() {
		return this.zipcode;
	}

	/**
	 * Returns the state
	 * 
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * Returns the city
	 * 
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	@Override
	public String toString() {
		return "Customer [memberID=" + memberID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", registrationDate=" + registrationDate + "]";
	}

}
