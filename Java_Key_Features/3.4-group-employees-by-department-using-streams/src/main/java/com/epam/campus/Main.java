package com.epam.campus;

import java.util.List;

/**
 * The entry point for the Employee grouping application.
 * <p>
 * Demonstrates the usage of {@link EmployeeService} to group employees by their department.
 * </p>
 */
public class Main {

    /**
     * The main method that executes the example.
     * <p>
     * Creates a list of {@link Employee} objects, uses {@link EmployeeService} to group them
     * by department, and prints the resulting map to the console.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of(
            new Employee("Alice", 50000, "HR"),
            new Employee("Bob", 60000, "IT"),
            new Employee("Charlie", 70000, "Finance")
        );

        // Call the method to group employees by department and print the result.
        System.out.println(employeeService.groupEmployeesByDepartment(employees));
    }
}