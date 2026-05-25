package com.epam.campus;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Age Validation Program");
        System.out.println("This program validates if a user is 18 years or older.");
        System.out.println();

        // Create an instance of validate UserAge()
        ValidateUserAge validator = new ValidateUserAge();

        // Check user's age
        validator.checkUserAge();

        System.out.println();
        System.out.println("Program Ended");
    }
}
