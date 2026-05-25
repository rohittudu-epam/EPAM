package com.epam.campus;

/**
 * Represents a Book in the Library system.
 * Each book has a title and an availability status.
 */
public class Book {
    /** The title of the book */
    String title;
    
    /** The availability status of the book (available/issued) */
    String availability;

    /**
     * Constructs a Book with the specified title.
     * The book is initially set to "available" status.
     * 
     * @param title the title of the book
     * @throws IllegalArgumentException if title is null or blank
     */
    public Book(String title){
        setTitle(title);

        // Availability -> available or issued
        setAvailability("available");
    }

    /**
     * Sets the title of the book.
     * 
     * @param title the title to set
     * @throws IllegalArgumentException if title is null or blank
     */
    public void setTitle(String title){
        if (title == null || title.isBlank()){
            throw new IllegalArgumentException("Invalid Input for book Title");
        }
        this.title = title;
    }

    /**
     * Sets the availability status of the book.
     * 
     * @param available the availability status (e.g., "available" or "issued")
     * @throws IllegalArgumentException if available is null or blank
     */
    public void setAvailability(String available){
        if (available == null || available.isBlank()){
            throw new IllegalArgumentException("Invalid Input for Availability");
        }
        this.availability = available;
    }

    /**
     * Returns a formatted string representation of the book details.
     * 
     * @return a string containing the book title and availability status
     */
    public String getDetails(){
        return ("Book: " + title + " | Availability: " + availability);
    }
}
