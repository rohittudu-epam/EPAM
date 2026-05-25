package com.epam.campus;

import java.util.Comparator;

/**
 * The {@code EmployeeComparator} class implements the {@link Comparator} interface
 * to provide custom sorting logic for {@link Employee} objects.
 * <p>
 * Employees are compared based on their salary in descending order,
 * meaning employees with higher salaries will appear before those with lower salaries
 * when sorted using this comparator.
 * </p>
 */
public class EmployeeComparator implements Comparator<Employee> {

    /**
     * Compares two {@link Employee} objects based on their salary.
     * <p>
     * The comparison is performed in descending order, so the employee with the higher salary
     * is considered "less than" the other for sorting purposes.
     * </p>
     *
     * @param e1 the first employee to compare
     * @param e2 the second employee to compare
     * @return a negative integer, zero, or a positive integer as the first argument has
     *         greater than, equal to, or less than the salary of the second argument
     */
    @Override
    public int compare(Employee e1, Employee e2) {        
        return Double.compare(e2.getSalary(), e1.getSalary());
    }
}