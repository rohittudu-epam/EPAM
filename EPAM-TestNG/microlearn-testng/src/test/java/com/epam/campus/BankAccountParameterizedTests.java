package com.epam.campus;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BankAccountParameterizedTests {
    
    @Parameters({"initialBalance", "withdrawalAmount", "expectedBalance"})
    @Test
    public void testWithdrawalWithXMLParameters(
            @Optional("1000") double initialBalance, 
            @Optional("100") double withdrawalAmount, 
            @Optional("900") double expectedBalance) {
        BankAccount account = new BankAccount(initialBalance, "Test User");
        account.withdraw(withdrawalAmount);
        Assert.assertEquals(account.getBalance(), expectedBalance, 
            "Expected balance " + expectedBalance + " after withdrawing " + withdrawalAmount);
    }

    @Parameters({"initialBalance", "holderName"})
    @Test
    public void testAccountInitializationWithXMLParameters(
            @Optional("500") double initialBalance, 
            @Optional("Test User") String holderName) {
        BankAccount account = new BankAccount(initialBalance, holderName);
        Assert.assertEquals(account.getBalance(), initialBalance, "Balance should match initial amount");
        Assert.assertEquals(account.getHolderName(), holderName, "Holder name should match");
        Assert.assertTrue(account.isActive(), "Account should be active");
    }

    @Parameters({"initialBalance", "depositAmount", "expectedBalance"})
    @Test
    public void testDepositWithXMLParameters(
            @Optional("800") double initialBalance, 
            @Optional("200") double depositAmount, 
            @Optional("1000") double expectedBalance) {
        BankAccount account = new BankAccount(initialBalance, "Test User");
        account.deposit(depositAmount);
        Assert.assertEquals(account.getBalance(), expectedBalance, 
            "Expected balance " + expectedBalance + " after depositing " + depositAmount);
    }
}
