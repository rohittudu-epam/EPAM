package com.epam.campus.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO class representing a Bank Account entity for API request/response handling.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccountResponse {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("balance")
    private Double balance;
    
    @JsonProperty("user")
    private UserResponse user;

    // Default constructor
    public BankAccountResponse() {
    }

    // Constructor with required fields
    public BankAccountResponse(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    // Full constructor
    public BankAccountResponse(Long id, String name, Double balance, UserResponse user) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BankAccountResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}
