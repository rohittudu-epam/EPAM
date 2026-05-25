package com.epam.campus.pojo.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    public UserResponse() {
    }

    // Constructor with Required Fields
    public UserResponse(String login, String firstName, String lastName, String email, String langKey){
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.langKey = langKey;
    }

    // Full Constructor
    public UserResponse(Long id, String login, String firstName, String lastName, String email,
                        String imageUrl, Boolean activated, String langKey, List<String> authorities) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.activated = activated;
        this.langKey = langKey;
        this.authorities = authorities;
    }

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
                ", imageUrl='" + imageUrl + '\'' +
                ", activated=" + activated +
                ", langKey='" + langKey + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
