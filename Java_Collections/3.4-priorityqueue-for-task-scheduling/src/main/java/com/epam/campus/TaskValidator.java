package com.epam.campus;

/**
 * Interface for validating task data.
 * Implementations of this interface provide methods to validate
 * task descriptions and priority levels.
 */
public interface TaskValidator {
    /**
     * Validates the task description.
     *
     * @param task The task to validate
     * @return The validated task
     * @throws IllegalArgumentException if task is null or empty
     */
    String taskValidation(String task);

    /**
     * Validates the priority level.
     *
     * @param priority The priority to validate
     * @return The validated priority
     * @throws IllegalArgumentException if priority is not between 1 and 5
     */
    int priorityValidation(int priority);
}
