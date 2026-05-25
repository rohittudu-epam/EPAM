package com.epam.campus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.epam.campus.exception.UserNotFoundException;
import com.epam.campus.model.User;
import com.epam.campus.service.AuthenticationService;

/**
 * ------------------------------------------------------------
 * Application Entry Point
 * ------------------------------------------------------------
 *
 * This class demonstrates an Optional-based User Authentication
 * system using Java 8+ features.
 *
 * Key concepts demonstrated:
 *  - Java Optional usage
 *  - Exception handling
 *  - Functional-style operations (map, orElse, orElseGet)
 *  - Clean and safe null handling
 *
 * The program simulates authentication against a predefined
 * list of users.
 */
public class App {

    /**
     * Main method – program execution starts here.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        /**
         * ------------------------------------------------------------
         * Step 1: Create a list of users
         * ------------------------------------------------------------
         *
         * Arrays.asList() is used to quickly create an immutable list
         * of User objects.
         */
        List<User> users = Arrays.asList(
            new User("jack_ryan", "ryanjack83@gmail.com"),
            new User("captain_price", "johnpricemw@gmail.com"),
            new User("mactavish_john", "mctavishsoap@gmail.com")
        );

        /**
         * ------------------------------------------------------------
         * Step 2: Initialize AuthenticationService
         * ------------------------------------------------------------
         *
         * The service receives the user list and performs
         * authentication and lookup operations using Optional.
         */
        AuthenticationService authService = new AuthenticationService(users);

        System.out.println("Optional Based User Authentication System");
        System.out.println();

        /**
         * ------------------------------------------------------------
         * Test Case 1: Authenticate an existing user
         * ------------------------------------------------------------
         *
         * authenticate() returns Optional<User>.
         * - If user exists → Optional contains User
         * - If not → throws UserNotFoundException
         */
        System.out.println("Test Case 1: Authenticating existing user 'jack_ryan'");
        try {
            Optional<User> user = authService.authenticate("jack_ryan");

            // ifPresent executes only if user exists
            user.ifPresent(u ->
                System.out.println("Authentication Successful! " + u)
            );

        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        /**
         * ------------------------------------------------------------
         * Test Case 2: Authenticate a non-existing user
         * ------------------------------------------------------------
         *
         * This demonstrates exception handling when the user
         * does not exist.
         */
        System.out.println("Test Case 2: Authenticating non-existing user 'ray_jackson'");
        try {
            Optional<User> user = authService.authenticate("ray_jackson");
            user.ifPresent(u ->
                System.out.println("Authentication Successful! " + u)
            );
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        /**
         * ------------------------------------------------------------
         * Test Case 3: Authenticate another valid user
         * ------------------------------------------------------------
         */
        System.out.println("Test Case 3: Authenticating existing user 'captain_price'");
        try {
            Optional<User> user = authService.authenticate("captain_price");
            user.ifPresent(u ->
                System.out.println("Authentication Successful! " + u)
            );
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        /**
         * ------------------------------------------------------------
         * Test Case 4: Using Optional.orElse()
         * ------------------------------------------------------------
         *
         * orElse() returns:
         *  - the value if present
         *  - a default object if not present
         *
         * This avoids null checks completely.
         */
        System.out.println("Test Case 4: Using findUser with orElse for non-existing user");

        User defaultUser = new User("guest", "guest@example.com");

        User result = authService
                .findUser("unknown")
                .orElse(defaultUser);

        System.out.println("Result: " + result);
        System.out.println();

        /**
         * ------------------------------------------------------------
         * Test Case 5: Using map() and orElseGet()
         * ------------------------------------------------------------
         *
         * map() transforms the value inside Optional if present.
         * orElseGet() executes lazily (only when Optional is empty).
         */
        System.out.println("Test Case 5: Using orElseGet with Lambda");

        String message = authService
                .findUser("charles")
                .map(u -> "Welcome, " + u.getUsername() + "!")
                .orElseGet(() -> "Access Denied. User not found.");

        System.out.println(message);
    }
}
