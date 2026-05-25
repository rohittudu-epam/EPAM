package com.epam.campus;


public class App {
    public static void main(String[] args) {

        // Creating File Reader object
        FileReader reader = new FileReader();

        // Sample file path
        String filePath = "./README.md";

        // readFile method call
        reader.readFile(filePath);
    }
}
