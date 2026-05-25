package com.epam.campus;

public class Person {
    private String name;
    private int age;
    private String address;

    // Person Constructor
    public Person(String name, int age, String address) {
        setName(name);
        setAge(age);
        setAddress(address);
    }

    // Getters and Setters
    public void setName(String name){
        // Validation check for name
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be Blank");
        }
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age){
        // Validation Check for age
        if (age <= 0){
            throw new IllegalArgumentException("Age cannot be less than or equal to 0");
        }
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public void setAddress(String address){
        // Validation check for address
        if (address == null || address.isBlank()){
            throw new IllegalArgumentException("Address cannot be empty");
        }
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    // GetPersonDetails Method
    public void getPersonDetails(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
    }
}
