package com.epam.campus;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

public class BrowserSimulation {
    private String currentUrl;
    private ArrayDeque<String> backwardHistory;
    private ArrayDeque<String> forwardHistory;

    public BrowserSimulation(){
        backwardHistory = new ArrayDeque<>();
        forwardHistory = new ArrayDeque<>();
    }

    public void setCurrentUrl(String url){
        this.currentUrl = validateUrl(url);
    }

    public void visitURL(String url){
        if (!backwardHistory.isEmpty()){
            backwardHistory.push(currentUrl);
            forwardHistory.clear();
        }

        setCurrentUrl(validateUrl(url));
    }

    public String getCurrentUrl(){
        return this.currentUrl;
    }

    public void goForwardHistory(){
        if (forwardHistory.isEmpty()){
            throw new NoSuchElementException("No next page in history.");
        }
        
        backwardHistory.push(currentUrl);
        setCurrentUrl(validateUrl(forwardHistory.pop()));
    }

    public void goBackwardHistory(){
        if (backwardHistory.isEmpty()){
            throw new NoSuchElementException("No previous page in history.");
        }

        forwardHistory.push(currentUrl);
        setCurrentUrl(validateUrl(backwardHistory.pop()));
    }

    public String validateUrl(String url){
        if (url == null || url.isEmpty()){
            throw new IllegalArgumentException("Enter a Valid Url");
        }

        return url;
    }
}
