package com.epam.campus.pojo.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class RegisterRequest {

    @JsonProperty("login")
    private String login;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("langKey")
    private String langKey;

    public RegisterRequest() {
    }

    public RegisterRequest(String login, String email, String password, String langKey) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.langKey = langKey;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", langKey='" + langKey + '\'' +
                '}';
    }
}
