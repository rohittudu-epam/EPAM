package com.epam.campus;

/**
 * Utility class for string operations including reversal and address formatting.
 */
public class StringUtils {
    /**
     * Reverses the input string after stripping leading/trailing whitespace.
     *
     * @param input takes a string (cannot be null or blank)
     * @return reversed String without any whitespaces
     * @throws IllegalArgumentException if input is null or blank after stripping
     */
    public static String stringReverse(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input String should not be empty");
        }

        String trimmed = input.strip();
        return new StringBuilder(trimmed).reverse().toString();
    }

    public static String formatAddress(String street, String city, String state, String postalCode) {
        // Comprehensive validation for required street parameter
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }

        StringBuilder sb = new StringBuilder();

        // Add street (always required and already validated)
        String trimmedStreet = street.strip();
        sb.append(trimmedStreet);

        // Add city if provided and not blank
        if (city != null && !city.isBlank()) {
            String trimmedCity = city.strip();
            sb.append(", ").append(trimmedCity);
        }

        // Add state if provided and not blank
        if (state != null && !state.isBlank()) {
            String trimmedState = state.strip();
            sb.append(", ").append(trimmedState);
        }

        // Add postal code if provided and not blank
        if (postalCode != null && !postalCode.isBlank()) {
            String trimmedPostalCode = postalCode.strip();
            sb.append(", ").append(trimmedPostalCode);
        }

        return sb.toString();
    }
}
