package com.epam.campus.service;

import com.epam.campus.model.User;
import com.epam.campus.exception.UserNotFoundException;

import java.util.Optional;

/**
 * Authentication contract.
 */
public interface AuthenticationServiceInterface {

    Optional<User> authenticate(String username) throws UserNotFoundException;

    Optional<User> findUser(String username);
}
