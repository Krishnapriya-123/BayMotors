package com.baymotors.models;


import java.util.Date;

public class Task {
    private int id;
    private int vehicleId; // Logical foreign key to Vehicle
    private int mechanicId; // Logical foreign key to Employee
    private String description;
    private String status; // Enum-like: 'PENDING', 'IN PROGRESS', 'COMPLETED', 'CANCELLED'
    private String priority; // Enum-like: 'Low', 'Medium', 'High'
    private Date completedAt;
    private int rating; // Between 0 and 10
    private String feedback;

    // Default constructor
    public Task() {
    }

    // Parameterized constructor
    public Task(int id, int vehicleId, int mechanicId, String description, String status,
                String priority, Date completedAt, int rating, String feedback) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.mechanicId = mechanicId;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.completedAt = completedAt;
        this.rating = rating;
        this.feedback = feedback;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(int mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + 
               "VehicleID: " + vehicleId + " | " + 
               "MechanicID: " + mechanicId + " | " + 
               "Description: " + description + " | " + 
               "Status: " + status + " | " + 
               "Priority: " + priority + " | " + 
               "CompletedAt: " + completedAt + " | " + 
               "Rating: " + rating + " | " + 
               "Feedback: " + feedback;
    }

}

