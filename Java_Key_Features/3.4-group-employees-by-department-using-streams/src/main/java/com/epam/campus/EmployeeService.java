package com.epam.campus;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class for handling operations related to Employee entities.
 * <p>
 * Provides utility methods for processing and organizing employee data,
 * such as grouping employees by their department.
 * </p>
 */
public class EmployeeService {

    /**
     * Groups a list of employees by their department.
     * <p>
     * This method processes the provided list of {@link EmployeeInterface} objects
     * and organizes
     * them into a map, where each key is a department name and the corresponding
     * value
     * is a list of employees who belong to that department.
     * </p>
     *
     * @param employees the list of {@link EmployeeInterface} objects to be grouped;
     *                  must not be null
     * @return a {@link Map} where the key is the department name (as a
     *         {@link String})
     *         and the value is a {@link List} of employees in that department;
     *         if the input list is empty, returns an empty map
     * @throws IllegalArgumentException if the employees list is null
     */
    public Map<String, List<Employee>> groupEmployeesByDepartment(List<Employee> employees) {
        if (employees == null) {
            throw new IllegalArgumentException("Employee list must not be null.");
        }
        return employees.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.getDepartment() != null)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}