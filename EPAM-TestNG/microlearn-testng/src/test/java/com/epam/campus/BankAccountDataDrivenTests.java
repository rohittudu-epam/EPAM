package com.epam.campus;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BankAccountDataDrivenTests {
    
    @DataProvider(name = "depositDataProvider")
    public Object[][] depositTestData() {
        return new Object[][] {
            { 100, 50, 150 },      // initial balance, deposit amount, expected balance
            { 200, 100, 300 },
            { 50, 25, 75 },
            { 1000, 500, 1500 },
            { 0, 10, 10 },
            { 500, 250, 750 },
            { 75, 25, 100 },
            { 999, 1, 1000 }
        };
    }

    @Test(dataProvider = "depositDataProvider")
    public void testDepositWithVariousAmounts(double initialBalance, double depositAmount, double expectedBalance) {
        BankAccount account = new BankAccount(initialBalance, "Test User");
        account.deposit(depositAmount);
        Assert.assertEquals(account.getBalance(), expectedBalance, 
            "Expected balance " + expectedBalance + " after depositing " + depositAmount);
    }

    @DataProvider(name = "multipleDepositDataProvider")
    public Object[][] multipleDepositTestData() {
        return new Object[][] {
            { 100, new double[]{50, 30}, 180 },     // initial, deposits array, expected total
            { 200, new double[]{100, 50, 25}, 375 },
            { 0, new double[]{100, 200, 300}, 600 }
        };
    }

    @Test(dataProvider = "multipleDepositDataProvider")
    public void testMultipleDeposits(double initialBalance, double[] depositAmounts, double expectedBalance) {
        BankAccount account = new BankAccount(initialBalance, "Test User");
        for (double amount : depositAmounts) {
            account.deposit(amount);
        }
        Assert.assertEquals(account.getBalance(), expectedBalance, 
            "Expected balance " + expectedBalance + " after multiple deposits");
    }
}
