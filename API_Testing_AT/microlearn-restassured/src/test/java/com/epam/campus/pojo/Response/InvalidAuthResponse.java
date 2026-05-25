package com.epam.campus.pojo.Response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvalidAuthResponse {

    @JsonProperty("detail")
    private String detail;

    @JsonProperty("instance")
    private String instance;

    @JsonProperty("status")
    private int status;

    @JsonProperty("title")
    private String title;

    @JsonProperty("type")
    private String type;

    @JsonProperty("message")
    private String message;

    @JsonProperty("path")
    private String path;

    public InvalidAuthResponse() {}

    public InvalidAuthResponse(String detail, String instance, int status,
                               String title, String type, String message, String path) {
        this.detail = detail;
        this.instance = instance;
        this.status = status;
        this.title = title;
        this.type = type;
        this.message = message;
        this.path = path;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
