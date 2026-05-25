package com.epam.campus.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO class representing an Operation entity for API request/response handling.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationResponse {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("date")
    private String date;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("amount")
    private Double amount;
    
    @JsonProperty("bankAccount")
    private BankAccountResponse bankAccount;

    // Default constructor
    public OperationResponse() {
    }

    // Constructor with required fields
    public OperationResponse(String date, String description, Double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    // Full constructor
    public OperationResponse(Long id, String date, String description, Double amount, BankAccountResponse bankAccount) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.bankAccount = bankAccount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BankAccountResponse getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountResponse bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "OperationResponse{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", bankAccount=" + bankAccount +
                '}';
    }
}
