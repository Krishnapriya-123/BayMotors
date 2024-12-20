package com.baymotors.models;

import java.util.Date;
import com.baymotors.util.Util;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    private String customerType;
    private Date registrationDate;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(int id, String firstName, String lastName, String email, String mobileNumber,
                    String address, String customerType, Date registrationDate) {
    
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.customerType = customerType;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        // Format the registration date if it's not null
        String registeredDate = (registrationDate != null) ? Util.formatDate(registrationDate) : "N/A";
        
        // Return the formatted string with "|" separator
        return "ID: " + id + " | " +
               "FirstName: " + firstName + " | " +
               "LastName: " + lastName + " | " +
               "MobileNumber: " + mobileNumber + " | " +
               "CustomerType: " + customerType + " | " +
               "registrationDate: " + registeredDate;
    }

}

