package com.epam.campus;

/**
 * Main application class that demonstrates the Library system.
 * Creates a library, adds books to it, and displays all books in insertion order.
 */
public class App {
    /**
     * Main entry point of the application.
     * Demonstrates the use of LinkedHashSet through the Library class.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new library
        Library library = new Library();
        
        // Add books to the library
        library.addBooks("The Great Gatsby");
        library.addBooks("To Kill a Mockingbird");
        library.addBooks("1984");
        library.addBooks("Pride and Prejudice");
        library.addBooks("The Catcher in the Rye");
        
        // List all books in insertion order (LinkedHashSet maintains order)
        library.listBooks();
    }
}
