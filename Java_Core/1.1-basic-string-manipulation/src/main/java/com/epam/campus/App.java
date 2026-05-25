package com.epam.campus;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Scanner for User Input
        Scanner scan = new Scanner(System.in);
        String fullName = scan.nextLine();

        // User Class Object
        User u1 = new User(fullName);
        System.out.println(u1.getUpperCase());

        demonstrateStringImmutability();

        scan.close();
    }

    private static void demonstrateStringImmutability() {

        String s1 = "Ghost";

        // Attempting to concatenate (creates a new object, but reference not updated)
        s1.concat(" Recon");

        // Original string remains unchanged
        System.out.println("s1 after concat (no reassignment): " + s1);

        String st1 = "One";

        // Concatenation with reassignment to a new reference
        String st2 = st1.concat(" Time");

        // Content of both strings
        System.out.println("st1 value: " + st1);
        System.out.println("st2 value: " + st2);

        // Reference comparison (checks memory location)
        System.out.println("st1 == st2 : " + (st1 == st2));

        // Identity hash codes represent object identity in memory
        System.out.println("st1 identity hash code: " + System.identityHashCode(st1));
        System.out.println("st2 identity hash code: " + System.identityHashCode(st2));
    }
}
