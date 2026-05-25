package com.epam.campus;

import java.util.HashMap;

/**
 * Manages student grades using a HashMap data structure.
 * Provides functionality to add and retrieve student grades with validation.
 */
public class StudentGrades {
    private static final int MIN_GRADE = 0;
    private static final int MAX_GRADE = 100;
    
    HashMap<String, Integer> student;

    /**
     * Constructs a new StudentGrades object with an empty HashMap.
     */
    StudentGrades(){
        student = new HashMap<>();
    }

    /**
     * Adds or updates a student's grade.
     * 
     * @param name the name of the student (cannot be null or empty)
     * @param grade the student's grade (must be between 0 and 100)
     * @throws IllegalArgumentException if name is null or empty, or grade is out of valid range
     */
    public void addStudentGrade(String name, int grade){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        if (grade < MIN_GRADE || grade > MAX_GRADE) {
            throw new IllegalArgumentException("Grade must be between " + MIN_GRADE + " and " + MAX_GRADE);
        }
        student.put(name, (Integer)grade);
    }

    /**
     * Retrieves a student's grade by name.
     * 
     * @param name the name of the student (cannot be null or empty)
     * @return a string with the student's name and grade
     * @throws IllegalArgumentException if name is null or empty
     * @throws IllegalArgumentException if student name does not exist in the records
     */
    public String getStudentGrade(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        if (!student.containsKey(name)) {
            throw new IllegalArgumentException("Student '" + name + "' not found in records");
        }
        int grade = student.get(name);
        String out = "Grade of " + name + " is: " + grade;

        return out;
    }
}
