package com.epam.campus;

public class Cat extends Animal {
    @Override
    public String makeSound() {
        return "meows";
    }

    // Constructors
    public Cat() {
        super("Cat");
    }

    public Cat(String name){
        super(name);
    }
}
