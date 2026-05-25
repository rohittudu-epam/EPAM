package com.epam.campus;

/**
 * Main application class demonstrating LinkedList-based to-do list functionality.
 * 
 * This class provides a demonstration of:
 * - Creating and managing a to-do list
 * - Adding multiple tasks
 * - Marking tasks as completed
 * - Displaying remaining tasks
 * - Removing completed tasks
 * 
 * @author EPAM Campus
 * @version 1.0
 */
public class App {
    /**
     * Main method that demonstrates the to-do list application.
     * Creates a TodoList, adds sample tasks, marks some as completed,
     * displays remaining tasks, and removes completed ones.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        
        // Add tasks
        System.out.println("Adding tasks to the to-do list...");
        todoList.addTask(new TodoItem("Complete Java assignment"));
        todoList.addTask(new TodoItem("Review LinkedList concepts"));
        todoList.addTask(new TodoItem("Practice coding problems"));
        todoList.addTask(new TodoItem("Submit project report"));
        
        // Display remaining tasks
        todoList.displayRemainingTasks();
        
        // Mark some tasks as completed
        System.out.println("\nMarking task at index 0 as completed...");
        todoList.markCompleted(0);
        System.out.println("Marking task at index 2 as completed...");
        todoList.markCompleted(2);
        
        // Display remaining tasks
        todoList.displayRemainingTasks();
        
        // Remove completed tasks
        System.out.println("\nRemoving completed tasks...");
        todoList.removeCompletedTasks();
        
        // Display final remaining tasks
        todoList.displayRemainingTasks();
    }
}
