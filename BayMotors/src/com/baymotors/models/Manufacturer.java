package com.baymotors.models;

public class Manufacturer {
    private int id;
    private String name;
    private String country;

    public Manufacturer(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Country: " + country;
    }
}