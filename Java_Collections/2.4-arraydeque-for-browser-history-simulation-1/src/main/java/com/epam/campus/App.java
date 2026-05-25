package com.epam.campus;


/**
 * App is the main entry point for the browser history simulation demonstration.
 * It creates a BrowserSimulation instance and demonstrates the back and forward
 * navigation functionality by visiting multiple URLs and navigating through history.
 */
public class App {
    /**
     * Main method that demonstrates the browser simulation functionality.
     * 
     * The demo includes:
     * - Visiting 5 different URLs in sequence
     * - Navigating back through the history twice
     * - Navigating forward through the history twice
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        BrowserSimulation browser = new BrowserSimulation();

        browser.visitUrl("leetcode.com");
        browser.visitUrl("github.com");
        browser.visitUrl("microsoft.com");
        browser.visitUrl("ubisoft.com");
        browser.visitUrl("rockstargames.com");

        browser.goBackHistory();
        browser.goBackHistory();

        browser.goForwardHistory();
        browser.goForwardHistory();
    }
}
