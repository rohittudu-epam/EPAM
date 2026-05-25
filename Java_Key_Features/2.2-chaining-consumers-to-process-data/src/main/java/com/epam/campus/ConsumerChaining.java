package com.epam.campus;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * The {@code ConsumerChaining} class demonstrates the use of Java's {@link Consumer}
 * interface and consumer chaining with the Stream API.
 * <p>
 * It maintains a list of strings and provides methods to insert new strings and
 * display each string along with its length using chained consumers.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     ConsumerChaining cc = new ConsumerChaining();
 *     cc.insertString("Example");
 *     cc.displayStringsWithLength();
 * </pre>
 * </p>
 *
 * @author Your Name
 */
public class ConsumerChaining {
    /**
     * The list that stores all inserted strings.
     */
    private ArrayList<String> listOfStrings;

    /**
     * Constructs a new {@code ConsumerChaining} instance with an empty list of strings.
     */
    public ConsumerChaining(){
        listOfStrings = new ArrayList<String>();
    }

    /**
     * Inserts a non-null, non-empty string into the list.
     * <p>
     * If the provided string is {@code null} or empty, an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param str the string to be added to the list
     * @throws IllegalArgumentException if {@code str} is {@code null} or empty
     */
    public void insertString(String str){
        if (str == null || str.isEmpty()){
            throw new IllegalArgumentException("String must not be empty.");
        }
        
        listOfStrings.add(str);
        System.out.println("String Added: `" + str + "`");
    }

    /**
     * Displays all strings in the list along with their lengths.
     * <p>
     * This method uses two {@link Consumer} instances: one to print the string,
     * and another to print its length. The consumers are chained using {@code andThen}
     * and applied to each string in the list via the Stream API.
     * </p>
     */
    public void displayStringsWithLength(){
        System.out.println("Displaying all the Strings in the list: \n");
        Consumer<String> c1 = s -> System.out.println("String: " + s);
        Consumer<String> c2 = s -> System.out.println("Length: " + s.length() + "\n");

        Consumer<String> stringChain = c1.andThen(c2);

        listOfStrings.stream().forEach(item -> stringChain.accept(item));
    }
}