package com.epam.campus;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

/**
 * BrowserSimulation simulates a web browser's navigation functionality.
 * It maintains the current URL being visited and tracks the history of previously visited URLs
 * (back history) and forward navigation (forward history).
 * 
 * This class uses ArrayDeque data structures to efficiently manage the browser's back and forward
 * navigation stacks, allowing O(1) push and pop operations for quick history traversal.
 */
public class BrowserSimulation {
    String currentUrl;
    private ArrayDeque<String> backHistory;
    private ArrayDeque<String> forwardHistory;

    /**
     * Constructs a BrowserSimulation object with empty back and forward history deques.
     */
    public BrowserSimulation() {
        backHistory = new ArrayDeque<>();
        forwardHistory = new ArrayDeque<>();
    }

    /**
     * Sets the current URL to the specified URL after validation.
     * Prints the current page URL to the console.
     * 
     * @param url the URL to set as current
     * @throws IllegalArgumentException if the URL is null or empty
     */
    public void setCurrentUrl(String url) {

        this.currentUrl = validateUrl(url);
        System.out.println("Current Page: " + currentUrl);
    }

    /**
     * Retrieves the URL that is currently being visited.
     * 
     * @return the current URL
     */
    public String getCurrentUrl(){
        return currentUrl;
    }

    /**
     * Visits a new URL by pushing the current URL to back history
     * and setting the new URL as current. This simulates clicking a link.
     * 
     * @param url the URL to visit
     * @throws IllegalArgumentException if the URL is null or empty
     */
    public void visitUrl(String url) {

        if (currentUrl != null) {
            backHistory.push(currentUrl);
        }

        setCurrentUrl(validateUrl(url));
    }
    
    /**
     * Navigates to the previous URL in the browser history.
     * The current URL is moved to forward history, and the previous URL becomes current.
     * This simulates clicking the back button.
     * 
     * @throws NoSuchElementException if the back history is empty
     */
    public void goBackHistory(){
        if (backHistory.isEmpty()){
            throw new NoSuchElementException("No previous page in History");
        }
        
        System.out.println("Visiting Previous Page: ");
        forwardHistory.push(currentUrl);
        setCurrentUrl(backHistory.pop());
        
    }
    
    /**
     * Navigates to the next URL in the forward history.
     * The current URL is moved to back history, and the next URL becomes current.
     * This simulates clicking the forward button.
     * 
     * @throws NoSuchElementException if the forward history is empty
     */
    public void goForwardHistory(){
        if (forwardHistory.isEmpty()){
            throw new NoSuchElementException("No next page in History");
        }
        
        System.out.println("Visiting Next page: ");
        backHistory.push(currentUrl);
        setCurrentUrl(forwardHistory.pop());
        
        System.out.println("Visiting: " + currentUrl);
    }
    
    /**
     * Validates the provided URL to ensure it is not null or empty.
     * 
     * @param url the URL to validate
     * @return the validated URL
     * @throws IllegalArgumentException if the URL is null or empty
     */
    public String validateUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL is empty. Enter a valid url");
        }
    
        return url;
    }
}
