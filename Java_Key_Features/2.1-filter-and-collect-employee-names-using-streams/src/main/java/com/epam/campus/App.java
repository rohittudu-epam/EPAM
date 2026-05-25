package com.epam.campus;

import java.util.ArrayList;

/**
 * Entry point of the Employee Management application.
 *
 * <p>
 * This class acts as a driver program to demonstrate the usage of
 * {@link EmployeeManager}. It performs the following responsibilities:
 * </p>
 * <ul>
 *   <li>Creates an instance of {@link EmployeeManager}</li>
 *   <li>Inserts sample employee data</li>
 *   <li>Fetches employees whose salary exceeds a given threshold</li>
 *   <li>Displays employee details on the console</li>
 * </ul>
 *
 * <p>
 * Any validation-related issues are handled via {@link IllegalArgumentException}.
 * </p>
 *
 * @author Rohit
 * @version 1.0
 */
public class App {

    /**
     * Main method – application execution starts here.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {

        // Manager responsible for employee operations
        EmployeeManager manager = new EmployeeManager();

        try {
            /**
             * Insert sample employees into the system.
             * Validation of name, designation, and salary
             * is handled internally by EmployeeManager.
             */
            manager.insertEmployees("Rohit", "Software Engineer", 75000);
            manager.insertEmployees("Anita", "Senior Developer", 95000);
            manager.insertEmployees("Vikram", "Tester", 45000); // Below salary threshold
            manager.insertEmployees("Neha", "Manager", 120000);

            /**
             * Retrieve employees whose salary is greater than 50,000.
             */
            ArrayList<Employee> highSalaryEmployees =
                    manager.getEmployeesBySalary(50_000);

            // Display filtered employee details
            System.out.println("Employees with salary greater than 50,000:");
            for (Employee emp : highSalaryEmployees) {
                System.out.println(emp.getDetails());
            }

        } catch (IllegalArgumentException e) {
            /**
             * Handles validation failures such as:
             * - Invalid name
             * - Invalid designation
             * - Illegal salary value
             */
            System.out.println("Error: " + e.getMessage());
        }
    }
}
