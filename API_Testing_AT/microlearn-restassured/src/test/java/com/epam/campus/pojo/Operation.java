package com.epam.campus.pojo;

import java.util.List;

import com.epam.campus.pojo.Response.BankAccountResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Operation {

    @JsonProperty("id")
    private int id;

    @JsonProperty("date")
    private String date;

    @JsonProperty("description")
    private String description;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("bankAccount")
    private BankAccountResponse bankAccount;

    @JsonProperty("labels")
    private List<Labels> labels;

    public Operation() {
    }

    public Operation(int id, String date, String description, double amount,
                     BankAccountResponse bankAccount, List<Labels> labels) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.bankAccount = bankAccount;
        this.labels = labels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BankAccountResponse getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountResponse bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<Labels> getLabels() {
        return labels;
    }

    public void setLabels(List<Labels> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", bankAccount=" + bankAccount +
                ", labels=" + labels +
                '}';
    }
}
