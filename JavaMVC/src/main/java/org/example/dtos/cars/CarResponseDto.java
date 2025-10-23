package org.example.dtos.cars;

import org.example.dtos.auth.UserResponseDto;

public class CarResponseDto {
    private Long id;
    private String make;
    private String model;
    private int year;
    private UserResponseDto owner;
    private String createdAt; // String instead of LocalDateTime for JSON
    private String updatedAt;

    public CarResponseDto() {}

    public CarResponseDto(Long id, String make, String model, int year, UserResponseDto owner, String createdAt, String updatedAt) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public UserResponseDto getOwnerId() { return owner; }
    public void setOwnerId(UserResponseDto owner) { this.owner = owner; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
