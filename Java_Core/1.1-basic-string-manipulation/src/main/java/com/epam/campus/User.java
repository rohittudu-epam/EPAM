package com.epam.campus;

import java.util.Arrays;

public class User {
    // User Class Attributes
    private String firstName;
    private String lastName;

    public User(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full Name Shouldn't be Empty");
        }
        // trimming and Splitting
        String[] parts = fullName.trim().split("[ ]");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Full Name must contain First Name and a Last Name");
        }

        // String[] full_name = fullName.stripLeading().stripTrailing().split("[ ]");
        setFirstName(parts[0]);
        setLastName(String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)));
    };

    // Setter for Names
    public void setFirstName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("First Name should not be empty");
        }
        this.firstName = name;
    }

    public void setLastName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Last Name should not be empty");
        }
        this.lastName = name;
    }

    // getter for Full Name in UpperCase
    public String getUpperCase() {
        return firstName.toUpperCase() + " " + lastName.toUpperCase();
    }
}
