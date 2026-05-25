package com.epam.campus;

/**
 * Main application class to demonstrate TreeMap usage for sorted student data.
 * 
 * <p>This application showcases how TreeMap automatically maintains entries in sorted order.
 * Students are added in random order (7, 5, 8, 11, 3), but when listed, they appear
 * sorted by their IDs in ascending order (3, 5, 7, 8, 11).</p>
 * 
 * <p>TreeMap vs HashMap:
 * <ul>
 *   <li>HashMap: Fast O(1) operations, no guaranteed order</li>
 *   <li>TreeMap: Slower O(log n) operations, maintains sorted order</li>
 * </ul></p>
 */
public class App {
    /**
     * Main entry point of the application.
     * 
     * <p>Demonstrates TreeMap sorting behavior by:
     * 1. Creating a Student object (which uses TreeMap internally)
     * 2. Adding students with IDs in random order
     * 3. Displaying students in sorted order of IDs (TreeMap automatic sorting)
     * </p>
     * 
     * @param args command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Create a Student instance with an empty TreeMap
        Student std = new Student();

        // Add students in random order of IDs
        // These IDs will be automatically sorted by TreeMap: 7, 5, 8, 11, 3
        std.addStudent(7, "Ryan");
        std.addStudent(5, "Simon");
        std.addStudent(8, "John");
        std.addStudent(11, "Alex");
        std.addStudent(3, "Pierce");

        // Display all students
        // Output will show students sorted by ID in ascending order: 3, 5, 7, 8, 11
        System.out.println("===== Students sorted by ID (TreeMap Demonstration) =====");
        std.listStudents();
    }
}
