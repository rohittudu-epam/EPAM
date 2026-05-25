package com.epam.campus;

/**
 * Represents a task with a description and priority level.
 * Priority levels are validated to be between 1 and 5, where 5 is the highest priority.
 */
public class Task implements TaskValidator {
    private String task;
    private int priority;

    /**
     * Constructs a Task with the given description and priority.
     *
     * @param task The task description
     * @param priority The priority level (1-5)
     * @throws IllegalArgumentException if task is null/empty or priority is invalid
     */
    public Task(String task, int priority) {
        setTask(task);
        setPriority(priority);
    }

    public void setTask(String task) {
        this.task = taskValidation(task);
    }

    /**
     * Sets the priority level after validation.
     *
     * @param priority The priority level (1-5)
     * @throws IllegalArgumentException if priority is not between 1 and 5
     */
    public void setPriority(int priority) {
        this.priority = priorityValidation(priority);
    /**
     * Returns the priority level of this task.
     *
     * @return The priority level (1-5)
     */
    }

    public String getTask() {
        return this.task;
    }

    public int getPriority() {
        return this.priority;
    }

    /**
     * Validates the task description.
     *
     * @param task The task to validate
     * @return The validated task
     * @throws IllegalArgumentException if task is null or empty
     */
    public String taskValidation(String task) {
        if (task == null || task.trim().isEmpty()) {
            throw new IllegalArgumentException("Task cannot be empty.");
        }

        return task.trim();
    }

    public int priorityValidation(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Invalid Priority value. Set Between 1 to 5");
        }

        return priority;
    }

}
