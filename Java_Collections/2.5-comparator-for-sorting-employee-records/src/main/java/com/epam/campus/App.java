package com.epam.campus;

/**
 * The {@code App} class serves as the entry point for the Employee Management application.
 * <p>
 * It demonstrates the usage of the {@link EmployeeManager} class by adding employees,
 * handling invalid input cases, listing employees, and sorting them by salary.
 * </p>
 */
public class App {

    /**
     * The main method that runs the Employee Management application.
     * <p>
     * It performs the following actions:
     * <ul>
     *     <li>Adds valid employees to the manager.</li>
     *     <li>Attempts to add employees with invalid data (age, salary, or name) and handles exceptions.</li>
     *     <li>Lists all valid employees.</li>
     *     <li>Sorts employees by salary in descending order and displays the sorted list.</li>
     * </ul>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();

        // Add valid employees
        try {
            manager.addEmployee("Alice", 30, 5000, "Developer");
            manager.addEmployee("Bob", 45, 12000, "Manager");
            manager.addEmployee("Charlie", 25, 3000, "Analyst");

        } catch (IllegalArgumentException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }

        // Try to add an employee with invalid age
        try {
            manager.addEmployee("David", 15, 4000, "Intern");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }

        // Try to add an employee with invalid salary
        try {
            manager.addEmployee("Eve", 28, 500, "Tester");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }

        // Try to add an employee with empty name
        try {
            manager.addEmployee("", 28, 4000, "Tester");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }

        // List all valid employees
        manager.listEmployees();
        manager.sortEmployees();

        // Sorting
        System.out.println("After Sorting..\n");

        // Displaying sorted list
        manager.listEmployees();
    }
}