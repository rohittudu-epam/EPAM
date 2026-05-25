package com.epam.campus;

import java.util.Collections;
import java.util.List;

/**
 * ------------------------------------------------------------
 * EmailValidator
 * ------------------------------------------------------------
 *
 * Service class responsible for validating one or more email
 * addresses using a pluggable {@link CustomEmailValidator}.
 *
 * This class demonstrates:
 * - Functional interfaces
 * - Strategy pattern via dependency injection
 * - Stream-based bulk processing
 * - Safe handling of null and empty inputs
 *
 * The validation logic itself is delegated to the
 * CustomEmailValidator implementation.
 */
public class EmailValidator {

    /**
     * ------------------------------------------------------------
     * Bulk Email Validation
     * ------------------------------------------------------------
     *
     * Validates a list of email addresses using the provided
     * CustomEmailValidator.
     *
     * Behavior:
     * - Each email is validated independently
     * - The order of results matches the input order
     * - Invalid or null emails result in `false`
     *
     * Edge case handling:
     * - Throws IllegalArgumentException if validator is null
     * - Returns an empty list if input emails list is null or empty
     *
     * @param emails    list of email addresses to validate
     * @param validator implementation of CustomEmailValidator
     * @return list of Boolean values representing validation results
     * @throws IllegalArgumentException if validator is null
     */
    public List<Boolean> validateEmails(
            List<String> emails,
            CustomEmailValidator validator) {

        // Validator dependency must be provided
        if (validator == null) {
            throw new IllegalArgumentException("CustomEmailValidator cannot be null");
        }

        // Defensive check for null or empty input list
        if (emails == null || emails.isEmpty()) {
            return Collections.emptyList();
        }

        // Validate each email using the provided validator
        return emails.stream()
                .map(validator::validate)
                .toList();
    }
}
