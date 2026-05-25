package com.epam.campus;

import java.util.List;
import java.util.Spliterator;

/**
 * Advanced Spliterator demonstrations with multiple splitting levels and error handling.
 */
public class ArrayListSplit {

    public void splitArrayList(List<String> products) {
        try {
            if (products == null || products.isEmpty()) {
                System.out.println("List is empty");
                return;
            }
            
            Spliterator<String> firstSplit = products.spliterator();
            Spliterator<String> secondSplit = firstSplit.trySplit();

            System.out.println("First part:");
            firstSplit.forEachRemaining(System.out::println);
            
            if (secondSplit != null) {
                System.out.println("\nSecond part:");
                secondSplit.forEachRemaining(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void splitArrayListMultipleLevels(List<String> products) {
        try {
            if (products == null || products.isEmpty()) {
                System.out.println("List is empty");
                return;
            }
            System.out.println("=== Multi-Level Splitting (Total: " + products.size() + ") ===");
            splitRecursively(products.spliterator(), 0);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void splitRecursively(Spliterator<String> spliterator, int level) {
        if (spliterator == null || spliterator.estimateSize() == 0) return;

        String indent = "  ".repeat(level);
        System.out.println(indent + "Level " + level + " (" + spliterator.estimateSize() + " items):");
        
        Spliterator<String> rightSplit = spliterator.trySplit();
        spliterator.forEachRemaining(item -> System.out.println(indent + "  - " + item));

        if (rightSplit != null && rightSplit.estimateSize() > 0) {
            splitRecursively(rightSplit, level + 1);
        }
    }

    public void demonstrateTryAdvance(List<String> products) {
        try {
            if (products == null || products.isEmpty()) {
                System.out.println("List is empty");
                return;
            }
            System.out.println("=== Try Advance (First 3 items) ===");
            Spliterator<String> spliterator = products.spliterator();
            
            for (int i = 0; i < 3 && spliterator.tryAdvance(item -> System.out.println(item)); i++);
            
            System.out.println("Remaining:");
            spliterator.forEachRemaining(item -> System.out.println("  - " + item));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void demonstrateSpliteratorCharacteristics(List<String> products) {
        try {
            if (products == null || products.isEmpty()) {
                System.out.println("List is empty");
                return;
            }
            System.out.println("=== Spliterator Characteristics ===");
            Spliterator<String> spliterator = products.spliterator();
            int chars = spliterator.characteristics();
            
            System.out.println("ORDERED: " + ((chars & Spliterator.ORDERED) != 0));
            System.out.println("SIZED: " + ((chars & Spliterator.SIZED) != 0));
            System.out.println("SUBSIZED: " + ((chars & Spliterator.SUBSIZED) != 0));
            System.out.println("Estimated size: " + spliterator.estimateSize());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void processComplexData(List<Product> products) {
        try {
            if (products == null || products.isEmpty()) {
                System.out.println("List is empty");
                return;
            }
            System.out.println("=== Complex Product Processing ===");
            
            Spliterator<Product> firstSplit = products.spliterator();
            Spliterator<Product> secondSplit = firstSplit.trySplit();

            System.out.println("High-value products (>$500):");
            firstSplit.forEachRemaining(p -> {
                if (p.getPrice() > 500) System.out.println("  " + p);
            });

            if (secondSplit != null) {
                System.out.println("\nProducts by category:");
                secondSplit.forEachRemaining(p -> 
                    System.out.println("  [" + p.getCategory() + "] " + p.getName()));
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void demonstrateParallelProcessing(List<Product> products) {
        try {
            if (products == null || products.isEmpty()) {
                System.out.println("List is empty");
                return;
            }
            System.out.println("=== Parallel-Like Processing ===");
            
            Spliterator<Product> split1 = products.spliterator();
            Spliterator<Product> split2 = split1.trySplit();

            final double[] total = {0};
            split1.forEachRemaining(p -> total[0] += p.getTotalValue());
            System.out.println("Batch 1 total value: $" + String.format("%.2f", total[0]));

            if (split2 != null) {
                final int[] count = {0};
                final int[] lowStock = {0};
                split2.forEachRemaining(p -> {
                    count[0]++;
                    if (p.getQuantity() < 10) lowStock[0]++;
                });
                System.out.println("Batch 2 count: " + count[0] + ", Low stock items: " + lowStock[0]);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void performanceComparison(List<Product> products) {
        try {
            if (products == null || products.isEmpty()) {
                System.out.println("List is empty");
                return;
            }
            System.out.println("=== Performance Comparison ===");
            
            long start = System.nanoTime();
            final long[] splitSum = {0};
            products.spliterator().forEachRemaining(p -> splitSum[0] += (long) p.getTotalValue());
            long splitTime = System.nanoTime() - start;

            start = System.nanoTime();
            long iterSum = 0;
            for (Product p : products) iterSum += (long) p.getTotalValue();
            long iterTime = System.nanoTime() - start;

            System.out.println("Spliterator: " + splitTime + " ns");
            System.out.println("Iterator: " + iterTime + " ns");
            System.out.println("Total value: $" + String.format("%.2f", splitSum[0] / 100.0));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
