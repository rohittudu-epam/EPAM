package com.epam.campus;

import java.util.TreeMap;
import java.util.Map;

/**
 * Student class that uses TreeMap to manage student records.
 * 
 * <p>TreeMap is a sorted map implementation that automatically sorts entries by keys.
 * Keys are sorted in their natural ordering (ascending order for integers).
 * This class demonstrates how TreeMap maintains sorted order as students are added.</p>
 * 
 * <p>Key characteristics of TreeMap:
 * <ul>
 *   <li>Implements NavigableMap interface</li>
 *   <li>Sorts entries based on keys in ascending order by default</li>
 *   <li>Time complexity: O(log n) for add, remove, and lookup operations</li>
 *   <li>Not synchronized - external synchronization needed for concurrent access</li>
 *   <li>Does not allow null keys (but allows null values)</li>
 * </ul></p>
 */
public class Student {
    /**
     * TreeMap to store student records where:
     * - Key: Student ID (Integer) - sorted in ascending order
     * - Value: Student Name (String)
     */
    TreeMap<Integer, String> students;

    /**
     * Constructor that initializes the TreeMap.
     * TreeMap automatically maintains sorted order of keys.
     */
    Student(){
        students = new TreeMap<Integer, String>();
    }

    /**
     * Adds a new student to the TreeMap.
     * 
     * @param id the unique student ID (Integer) - used as the key
     * @param name the student's name (String) - used as the value
     * 
     * @throws IllegalArgumentException if id is null or name is null/empty
     * 
     * <p>The TreeMap automatically inserts the entry in sorted order by ID.
     * If the ID already exists, the previous value is replaced.</p>
     */
    public void addStudent(Integer id, String name){
        // Validate ID is not null
        if (id == null){
            throw new IllegalArgumentException("ID Cannot be null");
        }

        // Validate Name is not null or empty
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or Empty");
        }
        
        // TreeMap.put() automatically maintains sorted order
        students.put(id, name);
    }

    /**
     * Displays all students in sorted order of their IDs.
     * 
     * <p>Demonstrates that TreeMap maintains entries in sorted order.
     * The iteration occurs in ascending order of keys (student IDs).</p>
     */
    public void listStudents(){
        // TreeMap entrySet() returns entries in sorted order of keys
        for (Map.Entry<Integer, String> entry: students.entrySet()){
            int key = entry.getKey();
            String value = entry.getValue();
            
            System.out.println("Key is: " + key + " and Value is: " + value);
        }
    }
}
