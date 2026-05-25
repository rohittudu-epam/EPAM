package com.epam.campus;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Vehicle[] vehicles = {
            new Bike(),
            new Car(),
            new Truck()
        };

        for (Vehicle v: vehicles){
            v.move();
        }
    }
}
