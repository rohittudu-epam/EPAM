package com.epam.campus;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    @Test
    public void testGroupEmployeesByDepartment_NormalCase() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of(
            new Employee("Alice", 50000, "HR"),
            new Employee("Bob", 60000, "IT"),
            new Employee("Charlie", 70000, "Finance"),
            new Employee("David", 55000, "HR")
        );

        Map<String, List<Employee>> grouped = employeeService.groupEmployeesByDepartment(employees);

        assertNotNull(grouped);
        assertEquals(2, grouped.get("HR").size());
        assertEquals(1, grouped.get("IT").size());
        assertEquals(1, grouped.get("Finance").size());
    }

    @Test
    public void testGroupEmployeesByDepartment_EmptyList() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of();

        Map<String, List<Employee>> grouped = employeeService.groupEmployeesByDepartment(employees);

        assertNotNull(grouped);
        assertTrue(grouped.isEmpty());
    }

    @Test
    public void testGroupEmployeesByDepartment_SingleDepartment() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of(
            new Employee("Alice", 50000, "HR"),
            new Employee("David", 55000, "HR")
        );

        Map<String, List<Employee>> grouped = employeeService.groupEmployeesByDepartment(employees);

        assertNotNull(grouped);
        assertEquals(2, grouped.get("HR").size());
    }

    @Test
    public void testGroupEmployeesByDepartment_InvalidInput_NullList() {
        EmployeeService employeeService = new EmployeeService();

        Map<String, List<Employee>> grouped = employeeService.groupEmployeesByDepartment(null);

        assertNull(grouped);
    }

    @Test
    public void testGroupEmployeesByDepartment_BoundaryCondition_EmptyDepartment() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of(
            new Employee("Alice", 50000, ""),
            new Employee("Bob", 60000, "")
        );

        Map<String, List<Employee>> grouped = employeeService.groupEmployeesByDepartment(employees);

        assertNotNull(grouped);
        assertEquals(2, grouped.get("").size());
    }

    @Test
    public void testGroupEmployeesByDepartment_BoundaryCondition_SpecialCharactersInDepartment() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of(
            new Employee("Alice", 50000, "@HR"),
            new Employee("Bob", 60000, "@HR")
        );

        Map<String, List<Employee>> grouped = employeeService.groupEmployeesByDepartment(employees);

        assertNotNull(grouped);
        assertEquals(2, grouped.get("@HR").size());
    }

    @Test
    public void testGroupEmployeesByDepartment_BoundaryCondition_NumericDepartment() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of(
            new Employee("Alice", 50000, "123"),
            new Employee("Bob", 60000, "123")
        );

        Map<String, List<Employee>> grouped = employeeService.groupEmployeesByDepartment(employees);

        assertNotNull(grouped);
        assertEquals(2, grouped.get("123").size());
    }

    @Test
    public void testGroupEmployeesByDepartment_MixedCaseDepartments() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of(
            new Employee("Alice", 50000, "HR"),
            new Employee("Bob", 60000, "hr"),
            new Employee("Charlie", 70000, "HR")
        );

        Map<String, List<Employee>> grouped = employeeService.groupEmployeesByDepartment(employees);

        assertNotNull(grouped);
        assertEquals(2, grouped.get("HR").size());
        assertEquals(1, grouped.get("hr").size());
    }

    @Test
    public void testGroupEmployeesByDepartment_DuplicateEmployees() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of(
            new Employee("Alice", 50000, "HR"),
            new Employee("Alice", 50000, "HR")
        );

        Map<String, List<Employee>> grouped = employeeService.groupEmployeesByDepartment(employees);

        assertNotNull(grouped);
        assertEquals(2, grouped.get("HR").size());
    }

    @Test
    public void testGroupEmployeesByDepartment_MultipleDepartments() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = List.of(
            new Employee("Alice", 50000, "HR"),
            new Employee("Bob", 60000, "IT"),
            new Employee("Charlie", 70000, "Finance"),
            new Employee("David", 55000, "HR"),
            new Employee("Eve", 65000, "IT")
        );

        Map<String, List<Employee>> grouped = employeeService.groupEmployeesByDepartment(employees);

        assertNotNull(grouped);
        assertEquals(2, grouped.get("HR").size());
        assertEquals(2, grouped.get("IT").size());
        assertEquals(1, grouped.get("Finance").size());
    }
}