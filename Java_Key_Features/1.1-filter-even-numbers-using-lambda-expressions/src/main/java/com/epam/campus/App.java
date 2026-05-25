package com.epam.campus;

/**
 * The App class demonstrates the usage of the NumberFilter class.
 * It inserts a set of integers and prints the even numbers from the list.
 */
public class App {
    /**
     * The main method is the entry point of the application.
     * It creates an instance of NumberFilter, inserts several integers,
     * and prints all even numbers in the list.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        NumberFilter nf = new NumberFilter();

        nf.insertNumbers(5);
        nf.insertNumbers(10);
        nf.insertNumbers(3);
        nf.insertNumbers(7);
        nf.insertNumbers(48);
        nf.insertNumbers(1);
        nf.insertNumbers(33);
        nf.insertNumbers(22);
        nf.insertNumbers(12);
        nf.insertNumbers(19);
        nf.insertNumbers(32);

        nf.printEven();
    }
}
