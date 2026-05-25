package com.epam.campus.pojo.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidAuthResponse {

    @JsonProperty("idToken")
    private String idToken;

    public ValidAuthResponse() {
    }

    public ValidAuthResponse(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @Override
    public String toString() {
        return "ValidAuthResponse{" +
                "idToken='" + idToken + '\'' +
                '}';
    }
}
