package com.epam.campus;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
       Person p1 = new Person("Alan", 41, "12th floor, Sky Tower, New york city");
       Person p2 = new Person("Max Verstappen", 28, "North Holland, Netherlands");

       p1.getPersonDetails();
       p2.getPersonDetails();
    }
}
