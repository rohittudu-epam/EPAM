package com.epam.campus;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * ListOfStrings is a utility class that manages a list of strings and provides
 * methods to insert strings and check for strings longer than a specified length.
 *
 * <p>
 * Demonstrates the use of the Predicate functional interface for conditional checks.
 * </p>
 *
 * @author YourName
 */
public class ListOfStrings {
    /**
     * Internal list to store strings.
     */
    private ArrayList<String> listOfStrings;

    /**
     * Constructs an empty ListOfStrings.
     */
    public ListOfStrings(){
        this.listOfStrings = new ArrayList<>();
    }

    /**
     * Inserts a non-null, non-empty string into the list.
     *
     * @param str the string to insert
     * @throws IllegalArgumentException if the string is null or empty
     */
    public void insertString(String str){
        if (str == null || str.isEmpty()){
            throw new IllegalArgumentException("String is Empty");
        }
        listOfStrings.add(str);
    }

    /**
     * Checks if the list contains any string with a length greater than 5.
     * Uses the Predicate functional interface for the condition.
     *
     * @return true if any string in the list is longer than 5 characters, false otherwise
     */
    public boolean containsStringLongerThan5(){
        Predicate<String> pd = str -> str.length() > 5;
        for (String s: listOfStrings){
            if (pd.test(s)) return true;
        }
        return false;
    }
}