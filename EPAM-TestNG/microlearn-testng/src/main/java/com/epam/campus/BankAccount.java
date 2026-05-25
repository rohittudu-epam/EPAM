package com.epam.campus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BankAccount {

    private double balance;
    private boolean isActive;
    private String holderName;

    // Constructor to initialize a bank account
    public BankAccount(double initialBalance, String holderName) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
        this.isActive = true;
        this.holderName = holderName;
        List<String> list = new ArrayList<>();
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        HashMap<String, Integer> map = new HashMap<>();
        map.values().stream().mapToInt(Integer::intValue).average();
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }

    // Method to close the account
    public void close() {
        if (balance > 0) {
            throw new IllegalArgumentException("Cannot close account with positive balance");
        }
        isActive = false;
    }

    // Method to check if the account is active
    public boolean isActive() {
        return isActive;
    }

    // Getter for the account holder's name
    public String getHolderName() {
        return holderName;
    }
}
