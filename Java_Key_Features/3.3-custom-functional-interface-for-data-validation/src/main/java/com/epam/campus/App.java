package com.epam.campus;

import java.util.Arrays;
import java.util.List;

/**
 * ------------------------------------------------------------
 * App (Application Entry Point)
 * ------------------------------------------------------------
 *
 * Demonstrates:
 *  - CustomEmailValidator usage
 *  - Single email validation
 *  - Bulk email validation using streams
 *  - Safe handling of valid, invalid, empty, and null inputs
 *
 * NOTE:
 * Arrays.asList() is used instead of List.of()
 * because List.of() does NOT allow null elements.
 */
public class App {

    /**
     * Program execution starts here.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("=== Email Validation Demo ===\n");

        /**
         * ------------------------------------------------------------
         * Step 1: Create validator implementation
         * ------------------------------------------------------------
         *
         * CustomEmailValidator is a functional interface,
         * allowing different validation strategies.
         */
        CustomEmailValidator validator = new NewValidator();

        /**
         * ------------------------------------------------------------
         * Step 2: Validate a single email
         * ------------------------------------------------------------
         */
        String singleEmail = "user@example.com";
        boolean isValidSingle = validator.validate(singleEmail);

        System.out.println("Single Email Validation:");
        System.out.println("Email: " + singleEmail);
        System.out.println("Valid: " + isValidSingle);
        System.out.println();

        /**
         * ------------------------------------------------------------
         * Step 3: Prepare list of emails for bulk validation
         * ------------------------------------------------------------
         *
         * Arrays.asList() is used because it allows null values,
         * which helps demonstrate edge-case handling.
         */
        List<String> emails = Arrays.asList(
                "admin@gmail.com",
                "invalid-email",
                "",
                null,
                "support@company.org"
        );

        /**
         * ------------------------------------------------------------
         * Step 4: Perform bulk validation
         * ------------------------------------------------------------
         *
         * EmailValidator orchestrates validation logic
         * using the provided CustomEmailValidator.
         */
        EmailValidator service = new EmailValidator();
        List<Boolean> results = service.validateEmails(emails, validator);

        /**
         * ------------------------------------------------------------
         * Step 5: Display validation results
         * ------------------------------------------------------------
         *
         * Each email maps to a Boolean result
         * in the same index order.
         */
        System.out.println("Bulk Email Validation Results:");

        for (int i = 0; i < emails.size(); i++) {
            System.out.println(
                    "Email: " + emails.get(i) +
                    " -> Valid: " + results.get(i)
            );
        }

        System.out.println("\n=== Validation Completed ===");
    }
}
