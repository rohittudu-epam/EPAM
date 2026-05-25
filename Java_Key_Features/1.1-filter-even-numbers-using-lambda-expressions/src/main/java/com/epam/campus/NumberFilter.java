package com.epam.campus;

import java.util.ArrayList;

/**
 * NumberFilter class provides functionality to store integers,
 * insert numbers, and print even numbers using Java Streams and Lambda Expressions.
 */
public class NumberFilter {
    /**
     * List to store integer numbers.
     */
    private ArrayList<Integer> numbers;

    /**
     * Constructs a new NumberFilter with an empty list.
     */
    public NumberFilter(){
        numbers = new ArrayList<Integer>();
    }

    /**
     * Inserts a number into the list.
     *
     * @param num the integer to be added
     */
    public void insertNumbers(int num){
        numbers.add(num);
    }

    /**
     * Prints all even numbers in the list to the standard output.
     * Uses Java Streams and Lambda Expressions for filtering and printing.
     */
    public void printEven(){
        numbers.stream()
               .filter((num) -> num % 2 == 0)
               .forEach((e) -> System.out.println(e));
    }
}