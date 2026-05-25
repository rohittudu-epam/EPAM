package com.epam.campus;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BankAccountAdvancedControlTests {
    
    private BankAccount bankAccount;

    @BeforeMethod
    public void setUp() {
        bankAccount = new BankAccount(1000, "John Doe");
    }

    @Test
    public void testInitializeAccount() {
        Assert.assertTrue(bankAccount.isActive(), "Account should be active.");
        Assert.assertEquals(bankAccount.getBalance(), 1000.0, "Initial balance should be 1000.");
    }

    @Test(dependsOnMethods = {"testInitializeAccount"})
    public void testDepositAfterInitialization() {
        bankAccount.deposit(500);
        Assert.assertEquals(bankAccount.getBalance(), 1500.0, "Balance should be 1500 after deposit.");
    }

    @Test(dependsOnMethods = {"testDepositAfterInitialization"})
    public void testWithdrawalAfterDeposit() {
        bankAccount.deposit(500);
        bankAccount.withdraw(200);
        Assert.assertEquals(bankAccount.getBalance(), 1300.0, "Balance should be 1300 after withdrawal.");
    }

    @Test(enabled = false)
    public void testDisabledTest() {
        // This test is intentionally disabled to demonstrate the @enabled = false attribute
        Assert.fail("This test should not run");
    }

    @Test
    public void testMultipleTransactions() {
        bankAccount.deposit(100);
        bankAccount.deposit(200);
        bankAccount.withdraw(150);
        Assert.assertEquals(bankAccount.getBalance(), 1150.0, "Balance should be 1150 after multiple transactions.");
    }

    @Test(dependsOnMethods = {"testMultipleTransactions"})
    public void testFinalBalanceCheck() {
        // This test depends on testMultipleTransactions
        bankAccount.deposit(100);
        bankAccount.deposit(200);
        bankAccount.withdraw(150);
        bankAccount.withdraw(50);
        Assert.assertEquals(bankAccount.getBalance(), 1100.0, "Balance should be 1100.");
    }

    @Test(enabled = true)
    public void testEnabledTest() {
        // This test is explicitly enabled to demonstrate the @enabled = true attribute
        Assert.assertEquals(bankAccount.getBalance(), 1000.0, "Initial balance should be 1000.");
    }
}
