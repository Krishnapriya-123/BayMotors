package com.baymotors.models;

/**
 * Represents the Vehicle entity corresponding to the VEHICLE table in the database.
 */
public class Vehicle {

    private int id;
    private String registrationNumber; // Matches registration_number in the database
    private String make;
    private String model;
    private int year;
    private String color;
    private int customerId; // Matches customer_id in the database

    // Default constructor
    public Vehicle() {
    }

    // Parameterized constructor
    public Vehicle(int id, String registrationNumber, String make, String model, int year, String color, int customerId) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.customerId = customerId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // toString Method
    @Override
    public String toString() {
        return "id:" + id + " | " +
               "registrationNumber:'" + registrationNumber + "' | " +
               "make:'" + make + "' | " +
               "model:'" + model + "' | " +
               "year:" + year + " | " +
               "color:'" + color + "' | " +
               "customerId:" + customerId;
    }

}
