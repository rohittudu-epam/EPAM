package com.epam.campus;

/**
 * Represents an Employee entity in the system.
 *
 * <p>
 * This class encapsulates basic employee-related information such as
 * name, designation, and salary. It follows the principles of
 * encapsulation by keeping fields private and exposing controlled
 * access via getters and setters.
 * </p>
 *
 * <p>
 * Validation is expected to be handled externally (for example, in
 * {@link EmployeeManager}) before creating an instance of this class.
 * </p>
 *
 * @author Rohit
 * @version 1.0
 */
public class Employee {

    /**
     * Name of the employee.
     */
    private String name;

    /**
     * Designation or role of the employee.
     */
    private String designation;

    /**
     * Salary of the employee.
     */
    private double salary;

    /**
     * Constructs an {@code Employee} object with the provided details.
     *
     * @param name        the name of the employee
     * @param designation the designation of the employee
     * @param salary      the salary of the employee
     */
    public Employee(String name, String designation, double salary) {
        setName(name);
        setDesignation(designation);
        setSalary(salary);
    }

    /**
     * Returns the name of the employee.
     *
     * @return employee name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the employee.
     *
     * @param name employee name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the designation of the employee.
     *
     * @return employee designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the designation of the employee.
     *
     * @param designation employee designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Returns the salary of the employee.
     *
     * @return employee salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the salary of the employee.
     *
     * @param salary employee salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Returns formatted employee details.
     *
     * <p>
     * This method is useful for displaying employee information
     * in a readable format on the console or logs.
     * </p>
     *
     * @return formatted string containing employee details
     */
    public String getDetails() {
        return "Name: " + getName() + "\n"
                + "Designation: " + getDesignation() + "\n"
                + "Salary: " + getSalary() + "\n";
    }
}
