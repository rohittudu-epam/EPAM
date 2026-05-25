package com.epam.campus;

public abstract class Payment {
    
    protected String type;
    protected double amount;
    protected String status;

    public Payment(String type) {
        this.type = type;
        this.status = "initiated";
    }

    // Abstract method for payment processing
    public abstract void processPayment();

    public void setAmount(double amount){
        if (amount <= 0){
            throw new IllegalArgumentException("The Amount should be greater than 0");
        }

        this.amount = amount;
    }

    public double getAmount(){
        return amount;
    }

    public String getStatus(){
        return status;
    }

    protected void setStatus(String status){
        this.status = status;
    }

    public String getType(){
        return type;
    }
}
