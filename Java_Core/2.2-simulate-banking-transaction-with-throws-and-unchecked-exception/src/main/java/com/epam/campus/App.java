package com.epam.campus;


public class App {
    public static void main(String[] args) {

        // Simulate banking object for Demonstration
        SimulateBanking sm = new SimulateBanking("Ryan", 225800);

        // Calling withdraw method
        sm.withdraw(4440);

        // Calling deposit method
        sm.deposit(33980);

        // Display account information using returned values
        System.out.println("Account Holder: " + sm.getAccountHolder());
        System.out.println(String.format("Final Balance: %.2f\n", sm.getBalance()));
    }
}
