package com.epam.campus;

/**
 * Represents an employee with a name, salary, and department.
 */
public class Employee implements EmployeeInterface {
    private final String name;
    private final double salary;
    private final String department;

    /**
     * Constructs an Employee object.
     *
     * @param name       the employee's name; must not be null
     * @param salary     the employee's salary
     * @param department the employee's department; must not be null
     * @throws IllegalArgumentException if name or department is null
     */
    public Employee(String name, double salary, String department) {
        if (name == null || department == null) {
            throw new IllegalArgumentException("Name and department must not be null.");
        }
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    /**
     * Returns a string representation of the Employee.
     *
     * @return a formatted string with employee details
     */
    @Override
    public String toString() {
        return String.format("Employee{name='%s', salary=%.2f, department='%s'}", name, salary, department);
    }
}