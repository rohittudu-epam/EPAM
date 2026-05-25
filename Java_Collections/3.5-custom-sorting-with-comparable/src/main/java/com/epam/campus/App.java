package com.epam.campus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Example implementation of Movies class with custom sorting
 */
public class App {
    public static void main(String[] args) {
        // Create a list of movies
        List<Movies> moviesList = new ArrayList<>();
        
        // Add movies using the constructor with date parameter
        moviesList.add(new Movies("The Shawshank Redemption", "Two imprisoned men bond over a number of years.", 5, "1994-10-14"));
        moviesList.add(new Movies("The Dark Knight", "Batman faces the Joker, a criminal mastermind.", 5, "2008-07-18"));
        moviesList.add(new Movies("Inception", "A thief who steals corporate secrets through dream-sharing.", 4, "2010-07-16"));
        moviesList.add(new Movies("Pulp Fiction", "The lives of two mob hitmen, a boxer, and a gangster.", 5, "1994-10-14"));
        moviesList.add(new Movies("Forrest Gump", "The presidencies of Kennedy and Johnson unfold through the perspective of an Alabama man.", 4, "1994-07-06"));
        
        // Display original movies
        System.out.println("=== Original Movies ===");
        displayMovies(moviesList);
        
        // Sort movies by rating using comparable
        Collections.sort(moviesList);
        
        // Display sorted movies
        System.out.println("\n=== Movies Sorted by Rating (Highest to Lowest) ===");
        displayMovies(moviesList);
        
        // Demonstrate getter/setter with validation
        System.out.println("\n=== Demonstrating Getter/Setter ===");
        Movies movie = moviesList.get(0);
        System.out.println("Movie: " + movie.getTitle());
        System.out.println("Date: " + movie.getDate());
        System.out.println("Rating: " + movie.getRating());
        
        // Try to set invalid date (uncomment to see validation error)
        // movie.setDate(""); // This will throw IllegalArgumentException
        
        // Try to set invalid rating (uncomment to see validation error)
        // movie.setRating(10); // This will throw IllegalArgumentException
    }
    
    /**
     * Helper method to display movies
     */
    private static void displayMovies(List<Movies> movies) {
        for (Movies movie : movies) {
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Description: " + movie.getDescription());
            System.out.println("Rating: " + movie.getRating() + "/5");
            System.out.println("Date: " + movie.getDate());
            System.out.println();
        }
    }
}
