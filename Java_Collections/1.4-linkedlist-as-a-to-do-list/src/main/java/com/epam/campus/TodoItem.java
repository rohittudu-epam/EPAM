package com.epam.campus;

/**
 * Represents a single to-do item with a task description and completion status.
 * 
 * This class encapsulates a task and its status (assigned or completed).
 * It provides validation to ensure data integrity:
 * - Tasks must be non-empty and not exceed 500 characters
 * - Status must be either 'assigned' or 'completed'
 * 
 * @author EPAM Campus
 * @version 1.0
 */
public class TodoItem {
    String task;
    String status;

    /**
     * Constructs a TodoItem with the given task and sets status to 'assigned' by default.
     * 
     * @param task the task description (must not be null or empty)
     * @throws IllegalArgumentException if task is null or empty
     */
    TodoItem(String task){
        setTask(task);

        // Status(assigned, completed)
        setStatus("assigned");
    }

    /**
     * Sets the task description with validation.
     * Trims whitespace and validates that task is not empty and not exceeding 500 characters.
     * 
     * @param task the task description to set
     * @throws IllegalArgumentException if task is null, empty, or exceeds 500 characters
     */
    public void setTask(String task){
        if(task == null || task.trim().isEmpty()){
            throw new IllegalArgumentException("Task cannot be null or empty");
        }
        if(task.length() > 500){
            throw new IllegalArgumentException("Task cannot exceed 500 characters");
        }
        this.task = task.trim();
    }

    /**
     * Sets the status of the task with validation.
     * Accepts only 'assigned' or 'completed' (case-insensitive).
     * 
     * @param status the status to set ('assigned' or 'completed')
     * @throws IllegalArgumentException if status is invalid
     */
    public void setStatus(String status){
        if(status == null || status.trim().isEmpty()){
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        String trimmedStatus = status.trim().toLowerCase();
        if(!trimmedStatus.equals("assigned") && !trimmedStatus.equals("completed")){
            throw new IllegalArgumentException("Status must be either 'assigned' or 'completed'");
        }
        this.status = trimmedStatus;
    }

    /**
     * Retrieves the task description.
     * 
     * @return the task description
     */
    public String getTask(){
        return task;
    }

    /**
     * Retrieves the status of the task.
     * 
     * @return the status ('assigned' or 'completed')
     */
    public String getStatus(){
        return status;
    }
}
