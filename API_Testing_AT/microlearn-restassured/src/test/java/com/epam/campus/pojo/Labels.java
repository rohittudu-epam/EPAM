package com.epam.campus.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Labels {

    @JsonProperty("id")
    private int id;

    @JsonProperty("label")
    private String label;

    public Labels() {
    }

    public Labels(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Labels{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
