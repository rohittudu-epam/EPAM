package com.epam.campus.pojo.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccountResponse {

    @JsonProperty
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("balance")
    private double balance;

    @JsonProperty("user")
    private UserResponse user;


    public BankAccountResponse() {
    }

    public BankAccountResponse(String name, Double balance){
        this.name = name;
        this.balance = balance;
    }

    public BankAccountResponse(Long id, String name, double balance, UserResponse user) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.user = user;
    }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
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
