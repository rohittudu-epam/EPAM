package com.epam.campus;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BankAccountTransactionTests {
    
    private BankAccount bankAccount;

    @BeforeMethod
    public void setUp() {
        bankAccount = new BankAccount(1000, "John Doe");
    }

    @AfterMethod
    public void tearDown() {
        bankAccount = null;
    }

    @Test
    public void testDepositOperation() {
        bankAccount.deposit(500);
        Assert.assertEquals(bankAccount.getBalance(), 1500.0, "Balance should be 1500 after depositing 500.");
    }

    @Test
    public void testWithdrawalOperation() {
        bankAccount.withdraw(300);
        Assert.assertEquals(bankAccount.getBalance(), 700.0, "Balance should be 700 after withdrawing 300.");
    }

    @Test
    public void testMultipleDepositsAndWithdrawals() {
        bankAccount.deposit(200);
        Assert.assertEquals(bankAccount.getBalance(), 1200.0, "Balance should be 1200 after first deposit.");
        
        bankAccount.withdraw(100);
        Assert.assertEquals(bankAccount.getBalance(), 1100.0, "Balance should be 1100 after withdrawal.");
        
        bankAccount.deposit(150);
        Assert.assertEquals(bankAccount.getBalance(), 1250.0, "Balance should be 1250 after second deposit.");
    }

    @Test
    public void testAccountBalanceAfterMultipleTransactions() {
        bankAccount.deposit(100);
        bankAccount.deposit(200);
        bankAccount.withdraw(50);
        Assert.assertEquals(bankAccount.getBalance(), 1250.0, "Balance should be 1250 after all transactions.");
    }
}
