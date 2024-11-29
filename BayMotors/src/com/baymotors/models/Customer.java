package com.baymotors.models;

import java.util.Date;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    private boolean isRegistered;
    private Date registrationDate;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(int id, String firstName, String lastName, String email, String mobileNumber,
                    String address, boolean isRegistered, Date registrationDate) {
    
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.isRegistered = isRegistered;
        this.registrationDate = registrationDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
	public String toString() {
		return "ID: " + id + " FirstName: " + firstName + " LastName: " + lastName + " MobileNumber: " + mobileNumber + " IsRegistered: " + isRegistered + " registrationDate: ";
	}
}

