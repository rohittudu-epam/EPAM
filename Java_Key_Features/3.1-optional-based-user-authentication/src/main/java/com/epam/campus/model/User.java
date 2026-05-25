package com.epam.campus.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * User domain model with validation.
 */
public class User {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private final String username;
    private final String email;

    public User(String username, String email) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }

        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{username=`" + username + "`, email=`" + email + "`}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
