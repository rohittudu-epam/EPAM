package com.epam.campus;

import java.util.LinkedHashSet;
import java.util.Iterator;

/**
 * Represents a Library that manages a collection of books.
 * Uses LinkedHashSet to maintain the insertion order of books.
 */
public class Library {
    /** LinkedHashSet to store books while maintaining insertion order */
    LinkedHashSet<Book> books;

    /**
     * Constructs an empty Library.
     */
    public Library(){
        books = new LinkedHashSet<>();
    }

    /**
     * Adds a new book with the specified title to the library.
     * 
     * @param title the title of the book to add
     * @throws IllegalArgumentException if title is null or blank
     */
    public void addBooks(String title){
        if (title == null || title.isBlank()){
            throw new IllegalArgumentException("Book Title Cannot be empty");
        }
        books.add(new Book(title));
        System.out.println("Book Added: " + title);
    }

    /**
     * Displays all books in the library in their insertion order.
     * Uses an Iterator to traverse the LinkedHashSet.
     */
    public void listBooks(){
        Iterator<Book> it = books.iterator();

        System.out.println("\nBooks Available: \n");
        while (it.hasNext()){
            Book book = it.next();
            System.out.println(book.getDetails());
        }

    }
}
