package com.epam.campus.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO class representing an Authentication response payload.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse {
    
    @JsonProperty("id_token")
    private String idToken;

    // Default constructor
    public AuthResponse() {
    }

    // Constructor with token
    public AuthResponse(String idToken) {
        this.idToken = idToken;
    }

    // Getters and Setters
    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "idToken='" + idToken + '\'' +
                '}';
    }
}
