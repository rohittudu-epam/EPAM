package com.epam.campus;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BankAccountInitializationTests {
    
    @Test
    public void testInitializationWithZeroBalance() {
        BankAccount account = new BankAccount(0, "Amit Datta");
        Assert.assertEquals(account.getBalance(), 0.0, "Balance should be zero.");
    }

    @Test
    public void testInitializationWithPositiveBalance100() {
        BankAccount account = new BankAccount(100, "John Doe");
        Assert.assertEquals(account.getBalance(), 100.0, "Balance should be 100.");
    }

    @Test
    public void testInitializationWithPositiveBalance500() {
        BankAccount account = new BankAccount(500, "Jane Smith");
        Assert.assertEquals(account.getBalance(), 500.0, "Balance should be 500.");
    }

    @Test
    public void testDepositSingleAmount() {
        BankAccount account = new BankAccount(100, "Amit Datta");
        account.deposit(50);
        Assert.assertEquals(account.getBalance(), 150.0, "Balance should be 150 after depositing 50.");
    }

    @Test
    public void testDepositMultipleAmounts() {
        BankAccount account = new BankAccount(100, "Amit Datta");
        account.deposit(50);
        account.deposit(25);
        account.deposit(75);
        Assert.assertEquals(account.getBalance(), 250.0, "Balance should be 250 after multiple deposits.");
    }

    @Test
    public void testWithdrawalLessThanBalance() {
        BankAccount account = new BankAccount(200, "Amit Datta");
        account.withdraw(50);
        Assert.assertEquals(account.getBalance(), 150.0, "Balance should be 150 after withdrawing 50.");
    }

    @Test
    public void testWithdrawalEqualToBalance() {
        BankAccount account = new BankAccount(100, "Amit Datta");
        account.withdraw(100);
        Assert.assertEquals(account.getBalance(), 0.0, "Balance should be 0 after withdrawing entire balance.");
    }
}
