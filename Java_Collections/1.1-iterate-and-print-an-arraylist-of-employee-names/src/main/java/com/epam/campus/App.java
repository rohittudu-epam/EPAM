package com.epam.campus;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        // ArrayList object for Employee Names
        ArrayList<String> employeeNames = new ArrayList<>();

        // Scanner Object
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Number of Employees: ");

        // Input for No of Employees
        int noOfEmployees = Integer.parseInt(input.nextLine());
        // input.nextLine();

        // Iterating and Adding to ArrayList
        for (int i = 0; i < noOfEmployees; i++){
            employeeNames.add(input.nextLine());
        }

        // Method to Print With Iterator
        IterateAndPrint.printIterator(employeeNames);

        // Method to Print With For-Each-Loop
        IterateAndPrint.printForEach(employeeNames);

        input.close();
    }
}
