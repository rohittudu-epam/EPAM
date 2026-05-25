package com.epam.campus;

// Banking interface for Simulate banking
public interface Banking {
    public void deposit(double amount) throws IllegalArgumentException;
    public void withdraw(double amount) throws IllegalArgumentException;
}
