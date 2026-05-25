package com.epam.campus;


public class App {
    public static void main(String[] args) {
        System.out.println(
                "=====================================");
        System.out.println(
                "Divison program with Exception handling");
        System.out.println(
                "=====================================");

        // DivisionHandler object
        HandleZeroDivision divisionHandler = new HandleZeroDivision();

        try {
            // Calling performDivision Method
            divisionHandler.performDivision();
        } finally {

            // Ensuring proper closing
            divisionHandler.close();
        }
    }
}
