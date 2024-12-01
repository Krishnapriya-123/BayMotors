package com.baymotors.models;

import java.util.Date;

public class Supplier {
    private int id;
    private String name;
    private String contactPerson;
    private String contactNumber;
    private String email;
    private int manufacturerId;
    private String address;

    public Supplier(int id, String name, String contactPerson, String contactNumber, String email, int manufacturerId, String address) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.contactNumber = contactNumber;
        this.email = email;
        this.manufacturerId = manufacturerId;
        this.address = address;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | ContactPerson: " + contactPerson + 
               " | ContactNumber: " + contactNumber + " | Email: " + email + 
               " | ManufacturerID: " + manufacturerId;
    }
}