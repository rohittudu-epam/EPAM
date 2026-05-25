package com.epam.campus;

/**
 * The {@code Employee} class represents an employee with a name, age, salary, and designation.
 * <p>
 * It provides getter and setter methods for each field, allowing encapsulated access and modification.
 * </p>
 */
public class Employee {
    /**
     * The name of the employee.
     */
    private String name;

    /**
     * The age of the employee.
     */
    private int age;

    /**
     * The salary of the employee.
     */
    private double salary;

    /**
     * The designation of the employee.
     */
    private String desgination;

    /**
     * Constructs a new {@code Employee} with the specified name, age, salary, and designation.
     *
     * @param name         the name of the employee
     * @param age          the age of the employee
     * @param salary       the salary of the employee
     * @param designation  the designation of the employee
     */
    public Employee(String name, int age, double salary, String designation){
        setName(name);
        setAge(age);
        setSalary(salary);
        setDesgination(designation);
    }

    /**
     * Returns the name of the employee.
     *
     * @return the employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the employee.
     *
     * @param name the employee's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the age of the employee.
     *
     * @return the employee's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the employee.
     *
     * @param age the employee's age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the salary of the employee.
     *
     * @return the employee's salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the salary of the employee.
     *
     * @param salary the employee's salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Returns the designation of the employee.
     *
     * @return the employee's designation
     */
    public String getDesgination() {
        return desgination;
    }

    /**
     * Sets the designation of the employee.
     *
     * @param desgination the employee's designation
     */
    public void setDesgination(String desgination) {
        this.desgination = desgination;
    }
}