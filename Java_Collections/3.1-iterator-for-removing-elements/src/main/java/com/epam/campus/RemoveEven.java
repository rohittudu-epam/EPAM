package com.epam.campus;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * RemoveEven class demonstrates the use of Iterator to safely remove elements
 * from a collection while iterating over it.
 * 
 * This class provides functionality to remove all even numbers from an ArrayList
 * using an Iterator, which is the proper way to modify a collection during iteration.
 * Attempting to remove elements using the ArrayList.remove() method directly during
 * iteration would throw a ConcurrentModificationException.
 */
public class RemoveEven {
    
    /**
     * Removes all even numbers from the provided ArrayList using an Iterator.
     * 
     * The method performs the following steps:
     * 1. Creates an Iterator over the ArrayList
     * 2. Iterates through each element
     * 3. Checks if the current element is even (divisible by 2)
     * 4. If even, safely removes it using Iterator.remove()
     * 5. Prints all remaining (odd) numbers to the console
     * 
     * @param nums an ArrayList of Integer values to process
     *             The ArrayList is modified in-place; all even numbers are removed.
     * 
     * @example
     * ArrayList<Integer> numbers = new ArrayList<>();
     * numbers.add(1); numbers.add(2); numbers.add(3);
     * RemoveEven remover = new RemoveEven();
     * remover.removeEvenIterator(numbers);
     * // Output: 1, 3
     */
    public void removeEvenIterator(ArrayList<Integer> nums){
        // Create an iterator to safely traverse and modify the list
        Iterator<Integer> numsIter = nums.iterator();

        // Remove all even numbers
        while (numsIter.hasNext()){
            // Get the next element and check if it's even
            if (numsIter.next() % 2 == 0){
                // Safe removal using iterator's remove() method
                numsIter.remove();
            }
        }

        // Create a new iterator to print the remaining odd numbers
        Iterator<Integer> numsIterNew = nums.iterator();

        // Display all remaining elements (odd numbers only)
        while (numsIterNew.hasNext()){
            System.out.println(numsIterNew.next());
        }
    }
}
