package com.epam.campus.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO class representing an Authentication request payload.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthRequest {
    
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("rememberMe")
    private Boolean rememberMe;

    // Default constructor
    public AuthRequest() {
    }

    // Constructor with all fields
    public AuthRequest(String username, String password, Boolean rememberMe) {
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
