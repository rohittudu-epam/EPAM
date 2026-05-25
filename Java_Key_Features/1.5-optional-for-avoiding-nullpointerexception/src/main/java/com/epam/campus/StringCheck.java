package com.epam.campus;

import java.util.Optional;

/**
 * Utility class for checking if a string is null, empty, or blank.
 */
public class StringCheck {

    /**
     * Default constructor.
     */
    public StringCheck() {}

    /**
     * Returns an {@link Optional} containing the trimmed string if it is non-null and not blank;
     * otherwise, returns {@link Optional#empty()}.
     *
     * @param str the string to check
     * @return an {@link Optional} with the trimmed string if present, or empty if null/blank
     */
    public Optional<String> checkNullString(String str) {
        if (str == null || str.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(str.trim());
    }
}