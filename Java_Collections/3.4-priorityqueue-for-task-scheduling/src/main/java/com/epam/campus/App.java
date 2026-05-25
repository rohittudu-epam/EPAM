package com.epam.campus;

/**
 * Task Scheduling Application using PriorityQueue.
 * Demonstrates the use of PriorityQueue to manage and process tasks
 * based on their priority levels (1-5, where 5 is highest priority).
 */
public class App {
    /**
     * Main entry point for the Task Scheduling application.
     * Creates a task manager, inserts multiple tasks with different priorities,
     * and processes them in descending order of priority.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            ManageTask taskManager = new ManageTask();

            taskManager.insertTask("Place Order: Laptops", 5);
            taskManager.insertTask("Check Email", 2);
            taskManager.insertTask("Prepare Report", 4);
            taskManager.insertTask("Schedule Meeting", 3);
            taskManager.insertTask("Bug Fix", 1);

            taskManager.taskProcessor();

        } catch (Exception e) {
            System.out.println("An Error Occured: " + e.getMessage());
        }
    }
}
