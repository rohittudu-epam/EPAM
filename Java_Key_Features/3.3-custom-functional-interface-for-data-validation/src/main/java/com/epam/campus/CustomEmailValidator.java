package com.epam.campus;

/**
 * ------------------------------------------------------------
 * CustomEmailValidator
 * ------------------------------------------------------------
 *
 * A custom functional interface for validating email addresses.
 *
 * This interface defines a single abstract method, making it
 * compatible with:
 *  - Lambda expressions
 *  - Method references
 *
 * Primary use cases:
 *  - Plug-and-play email validation strategies
 *  - Clean separation of validation logic
 *  - Easy testing and mocking
 *
 * Example usage with lambda:
 * <pre>
 * CustomEmailValidator validator =
 *     email -> email != null && email.contains("@");
 * </pre>
 */
@FunctionalInterface
public interface CustomEmailValidator {

    /**
     * Validates the given email address.
     *
     * Implementations should:
     *  - Return true if the email is valid
     *  - Return false if the email is invalid or null
     *  - Avoid throwing exceptions for invalid input
     *
     * @param email email address to validate
     * @return true if valid, false otherwise
     */
    boolean validate(String email);
}
