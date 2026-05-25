package com.epam.campus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class FileReader {

    /**
     * Reads a file from the given file path with comprehensive error handling.
     * 
     * @param filePath Takes path of the file that need to be read
     * @throws IllegalArgumentException if the file path is invalid or empty
     * @throws FileNotFoundException if the file is not found at the path given
     * @throws IOException if an error occurs during an I/O operation
     */

    public void readFile(String filePath){

        // Validation check for File Path (must not be null or empty)
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("No path provided");
        }

        // Validate the file path format
        try {
            Paths.get(filePath);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Invalid file path format: " + filePath, e);
        }

        // Check if file exists and is actually a file (not a directory)
        File file = new File(filePath);

        if (!file.isFile()) {
            throw new IllegalArgumentException("Path points to a directory, not a file: " + filePath);
        }

        // Check file accessibility
        if (!file.canRead()) {
            throw new SecurityException("No read permission for file: " + filePath);
        }

        BufferedReader reader = null;

        // Try block for BufferedReader
        try {
            // Create BufferedReader object
            reader = new BufferedReader(new java.io.FileReader(filePath));
            String line;
            int lineCount = 0;

            System.out.println("File Content: ");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                lineCount++;
            }

            System.out.println("File read successfully (" + lineCount + " lines)");
        } 
        // Catch block if File is not found
        catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filePath);
            System.err.println("Please check if the path is correct and the file exists.");
            System.err.println("Details: " + e.getMessage());
        } 
        // Catch block for security/permission issues
        catch (SecurityException e) {
            System.err.println("Error: Permission denied - Cannot read file: " + filePath);
            System.err.println("Details: " + e.getMessage());
        }
        // Catch block if any Exception Occurs during I/O operation
        catch (IOException e) {
            System.err.println("Error: I/O error occurred while reading the file.");
            System.err.println("File path: " + filePath);
            System.err.println("Details: " + e.getMessage());
        } 
        // Catch block for unexpected runtime exceptions
        catch (Exception e) {
            System.err.println("Error: Unexpected error occurred while reading the file.");
            System.err.println("File path: " + filePath);
            System.err.println("Error type: " + e.getClass().getSimpleName());
            System.err.println("Details: " + e.getMessage());
        } 
        finally {
            // Ensure reader is properly closed
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("File stream closed successfully");
                } 
                // Catch block if any exception occurs during closing BufferedReader
                catch (IOException e) {
                    System.err.println("Warning: Error occurred while closing file stream.");
                    System.err.println("Details: " + e.getMessage());
                }
            }
        }
    }
    
}
