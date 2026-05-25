package com.epam.campus;

/**
 * Demo application for StudentGrades HashMap functionality.
 */
public class App {
    public static void main(String[] args) {
        // Create a new StudentGrades instance
        StudentGrades grades = new StudentGrades();
        
        System.out.println("=== Student Grades Management System ===\n");
        
        // Add some student grades
        System.out.println("Adding student grades...");
        grades.addStudentGrade("Alice", 95);
        System.out.println("✓ Added Alice with grade 95");
        
        grades.addStudentGrade("Bob", 87);
        System.out.println("✓ Added Bob with grade 87");
        
        grades.addStudentGrade("Charlie", 78);
        System.out.println("✓ Added Charlie with grade 78");
        
        grades.addStudentGrade("Diana", 92);
        System.out.println("✓ Added Diana with grade 92\n");
        
        // Retrieve and display grades
        System.out.println("Retrieving student grades...");
        System.out.println(grades.getStudentGrade("Alice"));
        System.out.println(grades.getStudentGrade("Bob"));
        System.out.println(grades.getStudentGrade("Charlie"));
        System.out.println(grades.getStudentGrade("Diana"));
        System.out.println();
        
        // Update a grade
        System.out.println("Updating Charlie's grade to 85...");
        grades.addStudentGrade("Charlie", 85);
        System.out.println(grades.getStudentGrade("Charlie"));
        System.out.println();
        
        // Demonstrate validation - invalid grade
        System.out.println("Testing validation (invalid grade)...");
        try {
            grades.addStudentGrade("Eve", 150);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        System.out.println();
        
        // Demonstrate validation - null name
        System.out.println("Testing validation (null name)...");
        try {
            grades.addStudentGrade(null, 88);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        System.out.println();
        
        // Demonstrate validation - empty name
        System.out.println("Testing validation (empty name)...");
        try {
            grades.addStudentGrade("", 88);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        System.out.println();
        
        // Demonstrate validation - student not found
        System.out.println("Testing validation (student not found)...");
        try {
            grades.getStudentGrade("Frank");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}
