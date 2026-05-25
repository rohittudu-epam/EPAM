package com.epam.campus;

import java.util.LinkedList;

/**
 * Manages a list of to-do items using LinkedList data structure.
 * 
 * This class provides functionality to:
 * - Add new tasks to the list
 * - Mark tasks as completed
 * - Display remaining (unfinished) tasks
 * - Remove completed tasks from the list
 * - Validate task indices
 * 
 * @author EPAM Campus
 * @version 1.0
 */
public class TodoList {
    LinkedList<TodoItem> list;

    /**
     * Constructs an empty TodoList with a LinkedList data structure.
     */
    TodoList() {
        list = new LinkedList<>();
    }

    /**
     * Adds a new task to the to-do list.
     * Performs validation to ensure the TodoItem and its properties are valid.
     * 
     * @param item the TodoItem to add (must not be null)
     * @throws IllegalArgumentException if item is null or has invalid properties
     */
    // Method to add Tasks
    public void addTask(TodoItem item) {
        if (item == null) {
            throw new IllegalArgumentException("TodoItem cannot be null");
        }
        if (item.getTask() == null || item.getTask().isEmpty()) {
            throw new IllegalArgumentException("TodoItem must have a valid task");
        }
        if (item.getStatus() == null || item.getStatus().isEmpty()) {
            throw new IllegalArgumentException("TodoItem must have a valid status");
        }
        list.add(item);

        System.out.println("Task Added: " + item.getTask());
    }

    /**
     * Marks a task at the given index as completed.
     * Updates the task status from 'assigned' to 'completed'.
     * 
     * @param taskIndex the index of the task to mark as completed
     * @throws IllegalArgumentException if the index is out of bounds
     */
    // Method to Mark tasks Completed
    public void markCompleted(int taskIndex) {
        if (!isValidIndex(taskIndex)) {
            throw new IllegalArgumentException("Invalid Index");
        }
        TodoItem item = list.get(taskIndex);
        item.setStatus("completed");
    }

    /**
     * Displays all tasks with 'assigned' status (remaining tasks).
     * Tasks are displayed in a numbered format with their status.
     * If no tasks remain, displays a message indicating completion of all tasks.
     */
    // Method to display remaining tasks
    public void displayRemainingTasks() {
        if(list.isEmpty()) {
            System.out.println("No tasks in the list");
            return;
        }
        System.out.println("\n=== Remaining Tasks ===");
        int count = 0;
        for(int i = 0; i < list.size(); i++) {
            TodoItem item = list.get(i);
            if(item.getStatus().equals("assigned")) {
                System.out.println((count + 1) + ". " + item.getTask() + " [" + item.getStatus() + "]");
                count++;
            }
        }
        if(count == 0) {
            System.out.println("No remaining tasks!");
        }
    }

    /**
     * Removes all tasks marked as 'completed' from the to-do list.
     * This operation reduces the list size permanently.
     */
    // Method to remove completed Tasks
    public void removeCompletedTasks() {
        list.removeIf(item -> item.getStatus().equals("completed"));
        System.out.println("Completed tasks have been removed");
    }

    /**
     * Validates whether the given index is within the bounds of the task list.
     * 
     * @param index the index to validate
     * @return true if index is valid (0 <= index < list size), false otherwise
     */
    public boolean isValidIndex(int index) {
        return index < list.size() && index >= 0;
    }
}
