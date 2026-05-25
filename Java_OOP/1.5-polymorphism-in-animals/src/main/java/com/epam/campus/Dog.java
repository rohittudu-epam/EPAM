package com.epam.campus;

public class Dog extends Animal {
    @Override
    public String makeSound() {
        return "bark";
    }

    // constructors
    public Dog() {
        super("Dog");
    }

    public Dog(String name){
        super(name);
    }
}
