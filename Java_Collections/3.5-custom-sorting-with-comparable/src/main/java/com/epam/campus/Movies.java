package com.epam.campus;

/**
 * The {@code Movies} class represents a movie with a title, description, rating, and release date.
 * It implements the {@link Comparable} interface to allow sorting movies by their rating in descending order.
 * <p>
 * Example usage:
 * <pre>
 *     Movies movie = new Movies("Inception", "A mind-bending thriller", 5, "2010-07-16");
 * </pre>
 * </p>
 */
public class Movies implements Comparable<Movies> {
    /**
     * The title of the movie. Cannot be null or empty.
     */
    private String title;

    /**
     * The description of the movie. Cannot be null or empty.
     */
    private String description;

    /**
     * The rating of the movie, on a scale from 1 to 5.
     */
    private int rating;

    /**
     * The release date of the movie, as a string.
     */
    private String date;

    /**
     * Constructs a new {@code Movies} object with the specified title, description, rating, and date.
     *
     * @param title       the title of the movie; must not be null or empty
     * @param description the description of the movie; must not be null or empty
     * @param rating      the rating of the movie; must be between 1 and 5 (inclusive)
     * @param date        the release date of the movie; must not be null or empty
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Movies(String title, String description, int rating, String date) {
        setTitle(title);
        setDescription(description);
        setRating(rating);
        setDate(date);
    }

    /**
     * Compares this movie to another movie based on their ratings.
     * Movies with higher ratings are considered "less" to ensure descending order when sorting.
     *
     * @param m the other movie to compare to
     * @return a negative integer, zero, or a positive integer as this movie's rating
     *         is greater than, equal to, or less than the specified movie's rating
     */
    @Override
    public int compareTo(Movies m) {
        // Higher rating comes first
        return Integer.compare(m.getRating(), this.getRating());
    }

    /**
     * Returns the title of the movie.
     *
     * @return the movie title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the movie.
     *
     * @param title the new title; must not be null or empty
     * @throws IllegalArgumentException if the title is null or empty
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty.");
        }
        this.title = title.trim();
    }

    /**
     * Returns the description of the movie.
     *
     * @return the movie description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the movie.
     *
     * @param description the new description; must not be null or empty
     * @throws IllegalArgumentException if the description is null or empty
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description shouldn't be empty.");
        }
        this.description = description.trim();
    }

    /**
     * Returns the rating of the movie.
     *
     * @return the movie rating (1-5)
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating of the movie.
     *
     * @param rating the new rating; must be between 1 and 5 (inclusive)
     * @throws IllegalArgumentException if the rating is not between 1 and 5
     */
    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Invalid Input for Rating. Enter number between 1 & 5.");
        }
        this.rating = rating;
    }

    /**
     * Returns the release date of the movie.
     *
     * @return the movie release date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the release date of the movie.
     *
     * @param date the new release date; must not be null or empty
     * @throws IllegalArgumentException if the date is null or empty
     */
    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date can't be empty.");
        }
        // Optionally, add date format validation here
        this.date = date.trim();
    }
}