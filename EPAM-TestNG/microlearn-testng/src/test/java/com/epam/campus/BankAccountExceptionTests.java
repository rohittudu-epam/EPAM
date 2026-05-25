package com.epam.campus;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BankAccountExceptionTests {
    
    @Test(priority = 1)
    public void testNormalDepositOperation() {
        BankAccount account = new BankAccount(100, "Test User");
        account.deposit(50);
        Assert.assertEquals(account.getBalance(), 150.0, "Deposit should work correctly.");
    }

    @Test(priority = 2)
    public void testNormalWithdrawalOperation() {
        BankAccount account = new BankAccount(100, "Test User");
        account.withdraw(30);
        Assert.assertEquals(account.getBalance(), 70.0, "Withdrawal should work correctly.");
    }

    @Test(priority = 3, expectedExceptions = IllegalArgumentException.class)
    public void testNegativeInitialBalance() {
        new BankAccount(-100, "Test User");
    }

    @Test(priority = 4, expectedExceptions = IllegalArgumentException.class)
    public void testNegativeDepositAmount() {
        BankAccount account = new BankAccount(100, "Test User");
        account.deposit(-50);
    }

    @Test(priority = 5, expectedExceptions = IllegalArgumentException.class)
    public void testZeroDepositAmount() {
        BankAccount account = new BankAccount(100, "Test User");
        account.deposit(0);
    }

    @Test(priority = 6, expectedExceptions = IllegalArgumentException.class)
    public void testNegativeWithdrawalAmount() {
        BankAccount account = new BankAccount(100, "Test User");
        account.withdraw(-50);
    }

    @Test(priority = 7, expectedExceptions = IllegalArgumentException.class)
    public void testZeroWithdrawalAmount() {
        BankAccount account = new BankAccount(100, "Test User");
        account.withdraw(0);
    }

    @Test(priority = 8, expectedExceptions = IllegalArgumentException.class)
    public void testInsufficientBalance() {
        BankAccount account = new BankAccount(50, "Test User");
        account.withdraw(100);
    }

    @Test(priority = 9, expectedExceptions = IllegalArgumentException.class)
    public void testCloseAccountWithPositiveBalance() {
        BankAccount account = new BankAccount(100, "Test User");
        account.close();
    }
}
