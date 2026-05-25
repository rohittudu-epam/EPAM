package com.epam.campus;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Comprehensive unit tests for the StringUtils duplicate detection functionality.
 * 
 * This test suite covers:
 * - Basic duplicate detection scenarios
 * - Edge cases (empty strings, null inputs, single elements)
 * - Multiple duplicates of the same string
 * - No duplicates scenario
 * - Large collections
 * - Hash collision handling
 */
@DisplayName("StringUtils Duplicate Detection Tests")
public class AppTest {

    private StringUtils stringUtils;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        stringUtils = new StringUtils();
        // Capture console output for assertions
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    // Clean up System.out after each test
    private void restoreOutput() {
        System.setOut(originalOut);
    }

    // Helper method to get the captured output
    private String getCapturedOutput() {
        return outputStream.toString().trim();
    }

    // ============== Basic Duplicate Detection Tests ==============

    @Test
    @DisplayName("Should detect a single duplicate")
    public void testSingleDuplicate() {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("apple");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("apple", getCapturedOutput());
    }

    @Test
    @DisplayName("Should detect multiple different duplicates")
    public void testMultipleDifferentDuplicates() {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("apple");
        list.add("banana");
        list.add("cherry");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        String output = getCapturedOutput();
        assertTrue(output.contains("apple"), "Output should contain 'apple'");
        assertTrue(output.contains("banana"), "Output should contain 'banana'");
    }

    @Test
    @DisplayName("Should handle string appearing more than twice")
    public void testStringAppearingMultipleTimes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("test");
        list.add("test");
        list.add("test");
        list.add("test");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        // Should print "test" only once even though it appears 4 times
        String output = getCapturedOutput();
        int count = output.split("\n").length;
        assertEquals(1, count, "Duplicate 'test' should appear only once in output");
    }

    // ============== Edge Cases ==============

    @Test
    @DisplayName("Should throw EmptyListException when list is null")
    public void testNullListThrowsException() {
        EmptyListException exception = assertThrows(EmptyListException.class, () -> {
            stringUtils.identifyDuplicates(null);
        });
        assertEquals("List of Strings is Empty", exception.getMessage());
        restoreOutput();
    }

    @Test
    @DisplayName("Should throw EmptyListException when list is empty")
    public void testEmptyListThrowsException() {
        ArrayList<String> list = new ArrayList<>();

        EmptyListException exception = assertThrows(EmptyListException.class, () -> {
            stringUtils.identifyDuplicates(list);
        });
        assertEquals("List of Strings is Empty", exception.getMessage());
        restoreOutput();
    }

    @Test
    @DisplayName("Should handle single element list without duplicates")
    public void testSingleElementNoDuplicate() {
        ArrayList<String> list = new ArrayList<>();
        list.add("single");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("", getCapturedOutput());
    }

    @Test
    @DisplayName("Should handle list with no duplicates")
    public void testNoDuplicates() {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("", getCapturedOutput());
    }

    // ============== Empty String Handling ==============

    @Test
    @DisplayName("Should skip empty strings in duplicate detection")
    public void testEmptyStringsAreSkipped() {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("");
        list.add("apple");
        list.add("");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("apple", getCapturedOutput());
    }

    @Test
    @DisplayName("Should handle list with only empty strings")
    public void testOnlyEmptyStrings() {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("", getCapturedOutput());
    }

    // ============== Case Sensitivity ==============

    @Test
    @DisplayName("Should treat strings with different cases as different strings")
    public void testCaseSensitivity() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("apple");
        list.add("APPLE");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("", getCapturedOutput());
    }

    @Test
    @DisplayName("Should detect duplicates with exact case matching")
    public void testExactCaseMatching() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Test");
        list.add("test");
        list.add("Test");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("Test", getCapturedOutput());
    }

    // ============== Whitespace Handling ==============

    @Test
    @DisplayName("Should treat different whitespace as different strings")
    public void testWhitespaceHandling() {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("hello ");
        list.add(" hello");
        list.add("hello");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("hello", getCapturedOutput());
    }

    // ============== Large Collection Tests ==============

    @Test
    @DisplayName("Should handle large collections efficiently")
    public void testLargeCollection() {
        ArrayList<String> list = new ArrayList<>();
        
        // Add 1000 strings with some duplicates
        for (int i = 0; i < 500; i++) {
            list.add("string" + (i % 100)); // Creates 100 different strings, each repeated 5 times
        }

        // Should not throw any exceptions
        assertDoesNotThrow(() -> stringUtils.identifyDuplicates(list));
        restoreOutput();
    }

    // ============== Special Characters ==============

    @Test
    @DisplayName("Should handle strings with special characters")
    public void testSpecialCharacters() {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello@world!");
        list.add("test#123");
        list.add("hello@world!");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("hello@world!", getCapturedOutput());
    }

    @Test
    @DisplayName("Should handle strings with numbers")
    public void testNumericStrings() {
        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("123");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("123", getCapturedOutput());
    }

    // ============== Unicode and International Characters ==============

    @Test
    @DisplayName("Should handle Unicode characters correctly")
    public void testUnicodeCharacters() {
        ArrayList<String> list = new ArrayList<>();
        list.add("café");
        list.add("naïve");
        list.add("café");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("café", getCapturedOutput());
    }

    // ============== Hash Collision Edge Case ==============

    @Test
    @DisplayName("Should correctly handle potential hash collisions")
    public void testPotentialHashCollisions() {
        ArrayList<String> list = new ArrayList<>();
        // Strings that may have hash collisions in some Java implementations
        // but should still be correctly identified as different or same strings
        list.add("Aa");
        list.add("BB");
        list.add("Aa");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        assertEquals("Aa", getCapturedOutput());
    }

    // ============== Order Independence ==============

    @Test
    @DisplayName("Should detect duplicates regardless of their order")
    public void testOrderIndependence() {
        ArrayList<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("second");
        list.add("first");

        stringUtils.identifyDuplicates(list);
        restoreOutput();

        String output = getCapturedOutput();
        assertTrue(output.contains("first"), "Should detect 'first' as duplicate");
        assertTrue(output.contains("second"), "Should detect 'second' as duplicate");
    }
}

