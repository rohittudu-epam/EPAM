package com.epam.campus;

/**
 * Exception thrown when the input size exceeds the maximum allowed limit.
 * 
 * This is a custom exception used for more specific error handling when the number
 * of strings to process exceeds predefined limits. It helps distinguish between
 * different types of validation errors.
 */
public class InvalidSizeException extends IllegalArgumentException {
    
    /**
     * Constructs an InvalidSizeException with a default message.
     */
    public InvalidSizeException() {
        super("Input size exceeds the maximum allowed limit");
    }
    
    /**
     * Constructs an InvalidSizeException with a custom message.
     * 
     * @param message the detail message including the limit information
     */
    public InvalidSizeException(String message) {
        super(message);
    }
    
    /**
     * Constructs an InvalidSizeException with a custom message and cause.
     * 
     * @param message the detail message
     * @param cause the cause (which is saved for later retrieval)
     */
    public InvalidSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
