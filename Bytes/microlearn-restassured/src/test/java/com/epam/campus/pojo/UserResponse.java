package com.epam.campus.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO class representing a User entity for API request/response handling.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("login")
    private String login;
    
    @JsonProperty("firstName")
    private String firstName;
    
    @JsonProperty("lastName")
    private String lastName;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("imageUrl")
    private String imageUrl;
    
    @JsonProperty("activated")
    private Boolean activated;
    
    @JsonProperty("langKey")
    private String langKey;
    
    @JsonProperty("authorities")
    private List<String> authorities;

    // Default constructor
    public UserResponse() {
    }

    // Constructor with required fields for registration
    public UserResponse(String login, String firstName, String lastName, String email, String langKey) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.langKey = langKey;
    }

    // Full constructor
    public UserResponse(Long id, String login, String firstName, String lastName, 
                       String email, Boolean activated, String langKey, List<String> authorities) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.langKey = langKey;
        this.authorities = authorities;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", activated=" + activated +
                ", langKey='" + langKey + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
