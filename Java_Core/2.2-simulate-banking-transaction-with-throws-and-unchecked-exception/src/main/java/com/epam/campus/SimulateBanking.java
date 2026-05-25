package com.epam.campus;

public class SimulateBanking implements Banking {

    // Simulate Banking attributes
    private String accountHolder;
    private double balance;


    /**
     * This is a simple Banking simulation
     * @param accountHolder String parameter takes Account Holder name
     * @param balance Double parameter takes Account balance
     * @throws IllegalArgumentException if accountHolder is null or empty
     */
    public SimulateBanking(String accountHolder, double balance) throws IllegalArgumentException {
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder name cannot be null or empty");
        }
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    /**
     * @param amount -> takes amount to be withdrawn from the account
     */
    @Override
    public void withdraw(double amount) throws IllegalArgumentException {
        validateAmount(amount);
        validateSufficientBalance(amount);

        // Balance update -> subtraction of amount
        balance -= amount;

        logWithdrawal(amount);
    }


    /**
     * @param amount -> takes amount to be deposited in the account
     */
    @Override
    public void deposit(double amount) throws IllegalArgumentException {
        validateAmount(amount);

        // Balance update -> addition of amount
        balance += amount;

        logDeposit(amount);
    }

    /**
     * Validates that the amount is positive
     * @param amount the amount to validate
     * @throws IllegalArgumentException if amount is not positive
     */
    private void validateAmount(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Amount Entered");
        }
    }

    /**
     * Validates that there is sufficient balance for withdrawal
     * @param amount the amount to check against current balance
     * @throws IllegalArgumentException if balance is insufficient
     */
    private void validateSufficientBalance(double amount) throws IllegalArgumentException {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance to complete the transaction.");
        }
    }

    /**
     * Logs withdrawal transaction details
     * @param amount the amount withdrawn
     */
    private void logWithdrawal(double amount) {
        System.out.println(String.format("Amount Withdrawn: %.2f", amount));
        System.out.println(String.format("Remaining Balance: %.2f\n", balance));
    }

    /**
     * Logs deposit transaction details
     * @param amount the amount deposited
     */
    private void logDeposit(double amount) {
        System.out.println(String.format("Amount Deposited: %.2f", amount));
        System.out.println(String.format("Current Balance: %.2f\n", balance));
    }

    /**
     * Gets the current account balance
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the account holder name
     * @return the account holder name
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    /**
     * Logs the current balance to console
     */
    private void logBalance() {
        System.out.println(String.format("Current balance: %.2f\n", balance));
    }

    /**
     * Logs the account holder information to console
     */
    private void logAccountHolder() {
        System.out.println("Account Holder: " + accountHolder);
    }
}
