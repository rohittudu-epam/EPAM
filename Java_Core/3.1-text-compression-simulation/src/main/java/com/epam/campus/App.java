package com.epam.campus;

/**
 * Application entry point demonstrating text compression functionality.
 * 
 * This application showcases the TextCompressor implementation using
 * run-length encoding for string compression.
 */
public class App {
    public static void main(String[] args) {
        // Create an instance of TextCompressor (better OOP practice)
        Compressor compressor = new TextCompressor();

        // Sample test cases
        String a1 = "aabbbcc"; // Output -> a2b3c2
        System.out.println(compressor.compress(a1));

        String a2 = "a"; // Output -> a1
        System.out.println(compressor.compress(a2));

        String a3 = "111223"; // Output -> 132231
        System.out.println(compressor.compress(a3));

        String a4 = "@@@###$$"; // Output -> @3#3$2
        System.out.println(compressor.compress(a4));
    }
}
