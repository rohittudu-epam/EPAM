package com.epam.campus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Comprehensive unit tests for TextCompressor implementation.
 * 
 * This test suite covers:
 * - Normal compression cases
 * - Edge cases (single character, repeated sequences)
 * - Special characters and digits
 * - Input validation and error handling
 */
@DisplayName("TextCompressor Tests")
public class AppTest {

    private Compressor compressor;

    /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {
        compressor = new TextCompressor();
    }

    // ==================== Normal Compression Cases ====================

    /**
     * Test basic compression with mixed character sequences.
     */
    @Test
    @DisplayName("Should compress basic string 'aabbbcc' to 'a2b3c2'")
    public void testBasicCompression() {
        String input = "aabbbcc";
        String expected = "a2b3c2";
        assertEquals(expected, compressor.compress(input));
    }

    /**
     * Test compression with single character.
     */
    @Test
    @DisplayName("Should compress single character 'a' to 'a1'")
    public void testSingleCharacterCompression() {
        String input = "a";
        String expected = "a1";
        assertEquals(expected, compressor.compress(input));
    }

    /**
     * Test compression with numeric characters.
     */
    @Test
    @DisplayName("Should compress numeric string '111223' to '132231'")
    public void testNumericCompression() {
        String input = "111223";
        String expected = "132231";
        assertEquals(expected, compressor.compress(input));
    }

    /**
     * Test compression with special characters.
     */
    @Test
    @DisplayName("Should compress special chars '@@@###$$' to '@3#3$2'")
    public void testSpecialCharactersCompression() {
        String input = "@@@###$$";
        String expected = "@3#3$2";
        assertEquals(expected, compressor.compress(input));
    }

    // ==================== Edge Cases ====================

    /**
     * Test compression with all identical characters.
     */
    @Test
    @DisplayName("Should compress string with all identical chars 'aaaa' to 'a4'")
    public void testAllIdenticalCharacters() {
        String input = "aaaa";
        String expected = "a4";
        assertEquals(expected, compressor.compress(input));
    }

    /**
     * Test compression with alternating characters.
     */
    @Test
    @DisplayName("Should handle alternating characters 'abab' to 'a1b1a1b1'")
    public void testAlternatingCharacters() {
        String input = "abab";
        String expected = "a1b1a1b1";
        assertEquals(expected, compressor.compress(input));
    }

    /**
     * Test compression with two character sequence.
     */
    @Test
    @DisplayName("Should compress 'aabb' to 'a2b2'")
    public void testTwoCharacterSequence() {
        String input = "aabb";
        String expected = "a2b2";
        assertEquals(expected, compressor.compress(input));
    }

    /**
     * Test compression with mixed alphanumeric and special characters.
     */
    @Test
    @DisplayName("Should handle mixed char types 'aaa111###' to 'a313#3'")
    public void testMixedCharacterTypes() {
        String input = "aaa111###";
        String expected = "a313#3";
        assertEquals(expected, compressor.compress(input));
    }

    /**
     * Test compression with spaces.
     */
    @Test
    @DisplayName("Should handle spaces 'aa  bb' to 'a2 2b2'")
    public void testCompressionWithSpaces() {
        String input = "aa  bb";
        String expected = "a2 2b2";
        assertEquals(expected, compressor.compress(input));
    }

    /**
     * Test compression with long repeated sequence.
     */
    @Test
    @DisplayName("Should handle long sequences 'aaaaaaaaaa' to 'a10'")
    public void testLongRepeatedSequence() {
        String input = "aaaaaaaaaa";
        String expected = "a10";
        assertEquals(expected, compressor.compress(input));
    }

    // ==================== Error Handling & Validation ====================

    /**
     * Test that null input throws IllegalArgumentException.
     */
    @Test
    @DisplayName("Should throw IllegalArgumentException for null input")
    public void testNullInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            compressor.compress(null);
        });
    }

    /**
     * Test that empty string throws IllegalArgumentException.
     */
    @Test
    @DisplayName("Should throw IllegalArgumentException for empty string")
    public void testEmptyStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            compressor.compress("");
        });
    }

    /**
     * Test that string with control characters throws IllegalArgumentException.
     */
    @Test
    @DisplayName("Should throw IllegalArgumentException for control characters")
    public void testControlCharacterThrowsException() {
        String input = "aaa\u0000bbb"; // Contains null control character
        assertThrows(IllegalArgumentException.class, () -> {
            compressor.compress(input);
        });
    }

    /**
     * Test that tabs are allowed (common whitespace).
     */
    @Test
    @DisplayName("Should allow tabs in input")
    public void testTabCharactersAllowed() {
        String input = "aa\tbb";
        String expected = "a2\t1b2";
        assertEquals(expected, compressor.compress(input));
    }

    // ==================== Compressor Interface Tests ====================

    /**
     * Test that TextCompressor properly implements Compressor interface.
     */
    @Test
    @DisplayName("TextCompressor should implement Compressor interface")
    public void testImplementsCompressorInterface() {
        assertTrue(compressor instanceof Compressor);
    }

    /**
     * Test that compression is deterministic (same input produces same output).
     */
    @Test
    @DisplayName("Compression should be deterministic")
    public void testCompressionDeterministic() {
        String input = "aabbcc";
        String result1 = compressor.compress(input);
        String result2 = compressor.compress(input);
        assertEquals(result1, result2);
    }
}
