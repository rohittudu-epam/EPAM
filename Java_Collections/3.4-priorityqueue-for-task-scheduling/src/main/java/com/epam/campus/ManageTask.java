package com.epam.campus;

import java.util.PriorityQueue;

/**
 * Manages a collection of tasks and processes them based on priority.
 * Uses a PriorityQueue to automatically order tasks by priority level,
 * ensuring higher priority tasks are processed first.
 */
public class ManageTask {
    private PriorityQueue<Task> tasks;

    /**
     * Initializes the task manager with an empty priority queue.
     * Tasks are ordered by priority in descending order (5 to 1).
     */
    public ManageTask() {
        tasks = new PriorityQueue<Task>(new TaskComparator());
        System.out.println("===========Task Manager Initiated=============\n");
    }

    /**
     * Inserts a new task with the specified priority into the task queue.
     *
     * @param task The task description
     * @param priority The priority level (1-5)
     * @throws IllegalArgumentException if task is invalid or priority is out of range
     */
    public void insertTask(String task, int priority) {
        if (task == null) {
            throw new IllegalArgumentException("Task description cannot be null.");
        }
        tasks.add(new Task(task, priority));

        System.out.println("Added Task");
        System.out.println("Priority: " + priority + " - " + task + "\n");
    }

    /**
     * Processes all tasks in the queue in priority order (highest to lowest).
     * Each task is removed from the queue and processed before moving to the next.
     */
    public void taskProcessor() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to process.\n");
            return;
        }

        System.out.println("==========Processing Tasks==============\n");
        while (!tasks.isEmpty()) {
            processTask(tasks.remove());
        }

        System.out.println("==========Tasks Processed================\n");
    }

    /**
     * Processes a single task by displaying its priority and description.
     *
     * @param t The task to process
     * @throws IllegalArgumentException if task is null
     */
    public void processTask(Task t) {
        if (t == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        System.out.println("Priority: " + t.getPriority());
        System.out.println("Processing Task: " + t.getTask() + "\n");
        System.out.println("Task Completed\n");
    }
}
