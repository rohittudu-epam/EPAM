package com.epam.campus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelStreamsProcessorTest {

    private final ParallelStreamsProcessor processor = new ParallelStreamsProcessor();

    @Test
    public void testCalculateSumOfEvenNumbers_NormalCase() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = processor.calculateSumOfEvenNumbers(numbers);
        assertEquals(12, result); // 2 + 4 + 6 = 12
    }

    @Test
    public void testCalculateSumOfEvenNumbers_EmptyList() {
        List<Integer> numbers = Collections.emptyList();
        int result = processor.calculateSumOfEvenNumbers(numbers);
        assertEquals(0, result);
    }

    @Test
    public void testCalculateSumOfEvenNumbers_AllOddNumbers() {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
        int result = processor.calculateSumOfEvenNumbers(numbers);
        assertEquals(0, result);
    }

    @Test
    public void testCalculateSumOfEvenNumbers_AllEvenNumbers() {
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
        int result = processor.calculateSumOfEvenNumbers(numbers);
        assertEquals(30, result); // 2 + 4 + 6 + 8 + 10 = 30
    }

    @Test
    public void testCalculateSumOfEvenNumbers_SingleEvenNumber() {
        List<Integer> numbers = Arrays.asList(2);
        int result = processor.calculateSumOfEvenNumbers(numbers);
        assertEquals(2, result);
    }

    @Test
    public void testCalculateSumOfEvenNumbers_SingleOddNumber() {
        List<Integer> numbers = Arrays.asList(1);
        int result = processor.calculateSumOfEvenNumbers(numbers);
        assertEquals(0, result);
    }

    @Test
    public void testCalculateSumOfEvenNumbers_LargeList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        int result = processor.calculateSumOfEvenNumbers(numbers);
        assertEquals(110, result); // Sum of even numbers from 1 to 20
    }

    @Test
    public void testCalculateSumOfEvenNumbers_NegativeNumbers() {
        List<Integer> numbers = Arrays.asList(-2, -4, -6, -8, -10);
        int result = processor.calculateSumOfEvenNumbers(numbers);
        assertEquals(-30, result); // -2 + -4 + -6 + -8 + -10 = -30
    }

    @Test
    public void testCalculateSumOfEvenNumbers_MixedPositiveAndNegativeNumbers() {
        List<Integer> numbers = Arrays.asList(-2, 4, -6, 8, -10);
        int result = processor.calculateSumOfEvenNumbers(numbers);
        assertEquals(-6, result); // -2 + 4 + -6 + 8 + -10 = -6
    }

    @Test
    public void testCalculateSumOfEvenNumbers_BoundaryCondition() {
        List<Integer> numbers = Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        int result = processor.calculateSumOfEvenNumbers(numbers);
        assertEquals(Integer.MIN_VALUE, result); // Only Integer.MIN_VALUE and 0 are even
    }
}
