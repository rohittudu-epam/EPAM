package com.epam.campus;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Manages employee-related operations such as insertion,
 * validation, and retrieval based on salary criteria.
 *
 * <p>
 * This class acts as a service layer responsible for:
 * </p>
 * <ul>
 *   <li>Validating employee data</li>
 *   <li>Storing employee objects</li>
 *   <li>Filtering employees using Java Streams</li>
 * </ul>
 *
 * <p>
 * It ensures that invalid employee data is not persisted
 * by performing validations before object creation.
 * </p>
 *
 * @author Rohit
 * @version 1.0
 */
public class EmployeeManager {

    /**
     * Internal list that stores all employee objects.
     */
    private ArrayList<Employee> employees;

    /**
     * Constructs an {@code EmployeeManager} instance
     * and initializes the employee list.
     */
    public EmployeeManager() {
        employees = new ArrayList<>();
    }

    /**
     * Inserts a new employee after validating input values.
     *
     * @param name        employee name (must not be null or blank)
     * @param designation employee designation (must not be null or blank)
     * @param salary      employee salary (must be greater than 500)
     *
     * @throws IllegalArgumentException if any validation fails
     */
    public void insertEmployees(String name, String designation, double salary) {
        String validName = validateName(name);
        String validDesignation = validateDesignation(designation);
        double validSalary = validateSalary(salary);

        employees.add(new Employee(validName, validDesignation, validSalary));
    }

    /**
     * Retrieves employees whose salary exceeds the specified value.
     *
     * @param salary salary threshold
     * @return list of employees earning more than the given salary
     */
    public ArrayList<Employee> getEmployeesBySalary(double salary) {
        return new ArrayList<>(
                employees.stream()
                        .filter(emp -> emp.getSalary() > salary)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Validates employee name.
     *
     * @param name employee name
     * @return validated name
     * @throws IllegalArgumentException if name is null or blank
     */
    public String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return name;
    }

    /**
     * Validates employee designation.
     *
     * @param designation employee designation
     * @return validated designation
     * @throws IllegalArgumentException if designation is null or blank
     */
    public String validateDesignation(String designation) {
        if (designation == null || designation.isBlank()) {
            throw new IllegalArgumentException("Designation is required");
        }
        return designation;
    }

    /**
     * Validates employee salary.
     *
     * @param salary employee salary
     * @return validated salary
     * @throws IllegalArgumentException if salary is less than 500
     */
    public double validateSalary(double salary) {
        if (salary < 500) {
            throw new IllegalArgumentException("Illegal salary value");
        }
        return salary;
    }
}
