package com.epam.campus.pojo.Request;

import com.epam.campus.pojo.Response.UserResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestBankAccount {

    @JsonProperty("name")
    protected String name;

    @JsonProperty("balance")
    protected double balance;

    @JsonProperty("user")
    protected UserResponse user;


    public RequestBankAccount() {
    }

    public RequestBankAccount(String name, double balance, UserResponse user) {
        this.name = name;
        this.balance = balance;
        this.user = user;
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
        return "RequestBankAccount{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}
