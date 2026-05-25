package com.epam.campus.service;

import com.epam.campus.exception.UserNotFoundException;
import com.epam.campus.model.User;

import java.util.*;

/**
 * Map-based authentication service for fast lookup.
 */
public class AuthenticationService implements AuthenticationServiceInterface {

    /**
     * Stores users indexed by username for fast lookup.
     */
    private final Map<String, User> userStore;

    public AuthenticationService(List<User> users) {

        if (users == null) {
            throw new IllegalArgumentException("User list cannot be null");
        }

        this.userStore = new HashMap<>();

        for (User user : users) {
            userStore.put(user.getUsername(), user);
        }
    }

    /**
     * Authenticate user by username.
     */
    @Override
    public Optional<User> authenticate(String username)
            throws UserNotFoundException {

        validateUsername(username);

        User user = userStore.get(username);

        if (user == null) {
            throw new UserNotFoundException(
                    "User `" + username + "` not found."
            );
        }

        return Optional.of(user);
    }

    /**
     * Safe lookup without exceptions.
     */
    @Override
    public Optional<User> findUser(String username) {

        if (username == null || username.isBlank()) {
            return Optional.empty();
        }

        return Optional.ofNullable(userStore.get(username));
    }

    /**
     * Shared username validation logic.
     */
    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "Username cannot be null or blank"
            );
        }
    }
}
