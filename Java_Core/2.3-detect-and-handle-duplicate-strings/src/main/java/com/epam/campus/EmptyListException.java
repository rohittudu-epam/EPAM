package com.epam.campus;

/**
 * Exception thrown when a required list of strings is null or empty.
 * 
 * This is a custom exception used for more specific error handling in the duplicate
 * string detection workflow. It helps distinguish between different types of input
 * validation errors.
 */
public class EmptyListException extends IllegalArgumentException {
    
    /**
     * Constructs an EmptyListException with a default message.
     */
    public EmptyListException() {
        super("List of Strings is Empty");
    }
    
    /**
     * Constructs an EmptyListException with a custom message.
     * 
     * @param message the detail message
     */
    public EmptyListException(String message) {
        super(message);
    }
    
    /**
     * Constructs an EmptyListException with a custom message and cause.
     * 
     * @param message the detail message
     * @param cause the cause (which is saved for later retrieval)
     */
    public EmptyListException(String message, Throwable cause) {
        super(message, cause);
    }
}
