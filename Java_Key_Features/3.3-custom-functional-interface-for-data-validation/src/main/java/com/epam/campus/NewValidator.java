package com.epam.campus;

import java.util.regex.Pattern;

/**
 * ------------------------------------------------------------
 * NewValidator
 * ------------------------------------------------------------
 *
 * Concrete implementation of {@link CustomEmailValidator}.
 *
 * This validator performs email validation using:
 *  - Null safety checks
 *  - Blank / empty string checks
 *  - Regular expression pattern matching
 *
 * Design goals:
 *  - Fail-safe (never throws exceptions)
 *  - Easy to read and maintain
 *  - Suitable for most enterprise/business use cases
 *
 * Note:
 * This validator checks only the *format* of the email.
 * It does NOT verify domain existence or mailbox validity.
 */
public class NewValidator implements CustomEmailValidator {

    /**
     * Precompiled regular expression for basic email validation.
     *
     * This pattern allows:
     *  - Alphanumeric characters and common symbols in the local part
     *  - A single '@' separator
     *  - Valid domain characters
     *
     * Regex is compiled once for performance efficiency.
     */
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    /**
     * Validates the given email address.
     *
     * Validation rules:
     *  1. Email must not be null
     *  2. Email must not be empty or blank
     *  3. Email must match the defined regex pattern
     *
     * @param email email address to validate
     * @return true if the email is valid, false otherwise
     */
    @Override
    public boolean validate(String email) {

        // Defensive null check
        if (email == null) {
            return false;
        }

        // Reject empty or whitespace-only values
        if (email.isBlank()) {
            return false;
        }

        // Regex-based format validation
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
