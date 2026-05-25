package com.epam.campus;

public abstract class Animal {
    // Attribute
    protected String name;

    // constructors
    public Animal(){
        this.name = "Unknown";
    }

    public Animal(String name){
        if (name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    // getter for name
    public String getName(){
        return this.name;
    }

    // abstract method
    protected abstract String makeSound();

    public void speak(){
        System.out.println(name + ": " + makeSound());
    }
}
