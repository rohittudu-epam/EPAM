package com.epam.campus.bdd;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulated authentication service for BDD testing.
 */
public class AuthenticationService {

    private static final Map<String, String> REGISTERED_USERS = new HashMap<>();
    private String currentUser;

    static {
        REGISTERED_USERS.put("admin", "admin123");
        REGISTERED_USERS.put("buyer", "buyer123");
    }

    public String login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return "Username and password are required";
        }
        if (REGISTERED_USERS.containsKey(username) && REGISTERED_USERS.get(username).equals(password)) {
            currentUser = username;
            return "SUCCESS";
        }
        return "Invalid username or password";
    }

    public void logout() {
        currentUser = null;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public String register(String username, String email, String password) {
        if (username == null || username.isEmpty()) {
            return "Username is required";
        }
        if (REGISTERED_USERS.containsKey(username)) {
            return "Username already exists";
        }
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            return "Please enter a valid email address";
        }
        if (password == null || password.length() < 8) {
            return "Password must be at least 8 characters";
        }
        REGISTERED_USERS.put(username, password);
        return "Registration successful! Please log in.";
    }
}
