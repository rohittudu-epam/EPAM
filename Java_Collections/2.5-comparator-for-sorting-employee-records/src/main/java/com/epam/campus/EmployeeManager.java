package com.epam.campus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

/**
 * The {@code EmployeeManager} class provides functionality to manage a list of employees,
 * including adding, validating, listing, and sorting employee records.
 * <p>
 * It enforces constraints on employee age and salary, and ensures that name and designation
 * fields are not empty. Employees are stored in an {@link ArrayList}.
 * </p>
 */
public class EmployeeManager {
    /**
     * The minimum allowed age for an employee.
     */
    private final int MIN_AGE = 18;

    /**
     * The maximum allowed age for an employee.
     */
    private final int MAX_AGE = 90;

    /**
     * The minimum allowed salary for an employee.
     */
    private final double MIN_SALARY = 1000;

    /**
     * The maximum allowed salary for an employee.
     */
    private final double MAX_SALARY = 99999999;

    /**
     * The list that stores all employee records.
     */
    ArrayList<Employee> empList;

    /**
     * Constructs an empty {@code EmployeeManager}.
     */
    public EmployeeManager() {
        empList = new ArrayList<Employee>();
    }

    /**
     * Adds a new employee to the list after validating the input parameters.
     *
     * @param name         the name of the employee
     * @param age          the age of the employee
     * @param salary       the salary of the employee
     * @param designation  the designation of the employee
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public void addEmployee(String name, int age, double salary, String designation) {
        // Validate inputs first
        String validName = validateName(name);
        int validAge = validateAge(age);
        double validSalary = validateSalary(salary);
        String validDesignation = validateDesignation(designation);

        // Create and add employee
        Employee temp = new Employee(validName, validAge, validSalary, validDesignation);
        empList.add(temp);

        System.out.println("Added Employee:");
        employeeLogger(temp);
    }

    /**
     * Validates the employee's name.
     *
     * @param name the name to validate
     * @return the validated name
     * @throws IllegalArgumentException if the name is null or empty
     */
    public String validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can not be empty");
        }
        return name;
    }

    /**
     * Validates the employee's designation.
     *
     * @param designation the designation to validate
     * @return the validated designation
     * @throws IllegalArgumentException if the designation is null or empty
     */
    public String validateDesignation(String designation) {
        if (designation == null || designation.isEmpty()) {
            throw new IllegalArgumentException("Designation can not be empty");
        }
        return designation;
    }

    /**
     * Validates the employee's age.
     *
     * @param age the age to validate
     * @return the validated age
     * @throws IllegalArgumentException if the age is outside the allowed range
     */
    public int validateAge(int age) {
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new IllegalArgumentException(
                    "Invalid Age Value. Age should lie between " + MIN_AGE + " and " + MAX_AGE);
        }
        return age;
    }

    /**
     * Validates the employee's salary.
     *
     * @param salary the salary to validate
     * @return the validated salary
     * @throws IllegalArgumentException if the salary is outside the allowed range
     */
    public double validateSalary(double salary) {
        if (salary < MIN_SALARY || salary > MAX_SALARY) {
            throw new IllegalArgumentException(
                    "Invalid Salary Value. Salary Should lie between $" + MIN_SALARY + " and $" + MAX_SALARY);
        }
        return salary;
    }

    /**
     * Logs the details of a given employee to the standard output.
     *
     * @param emp the employee whose details are to be logged
     */
    public void employeeLogger(Employee emp) {
        System.out.println("Employee Details:");
        System.out.println("Name: " + emp.getName());
        System.out.println("Designation: " + emp.getDesgination());
        System.out.println("Age: " + emp.getAge());
        System.out.println("Salary: " + emp.getSalary());
        System.out.println();
    }

    /**
     * Prints the details of all employees in the list to the standard output.
     */
    public void listEmployees() {
        Iterator<Employee> it = empList.iterator();

        System.out.println("================Employee List==============");
        while (it.hasNext()) {
            employeeLogger(it.next());
        }
    }

    /**
     * Sorts the list of employees using the {@link EmployeeComparator}.
     */
    public void sortEmployees() {
        Collections.sort(empList, new EmployeeComparator());
    }
}