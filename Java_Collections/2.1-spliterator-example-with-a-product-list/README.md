# Task : Advanced Spliterator Example with a Product List
## Description : 
Create an ArrayList of products and use advanced Spliterator techniques to divide lists into multiple parts with comprehensive error handling and complex processing scenarios. This project demonstrates both basic and advanced Spliterator features with real-world product management examples.

## Topics Covered : 
Spliterator, ArrayList, Collections, Error Handling, Recursive Splitting, Performance Analysis, Complex Data Processing

## Objectives : 
1. Understand how to split and process collections using Spliterator
2. Implement multiple levels of recursive splitting
3. Explore Spliterator characteristics and advanced features
4. Handle error cases and edge conditions
5. Process complex objects with multiple properties
6. Compare performance with traditional iteration approaches
7. Demonstrate parallel-like processing patterns

## Features Implemented :

### 1. **Basic Splitting** (`splitArrayList`)
   - Single-level splitting using `trySplit()`
   - Simple element processing with null/empty list handling
   - Foundation for understanding Spliterator basics

### 2. **Multi-Level Splitting** (`splitArrayListMultipleLevels`)
   - Recursive subdivision of lists into 4+ parts
   - Level-based indentation for visual hierarchy
   - Demonstrates tree-like splitting patterns
   - Handles edge cases at each level

### 3. **Try Advance Method** (`demonstrateTryAdvance`)
   - Manual element-by-element processing
   - Limited iteration (max 3 elements)
   - Remaining elements processed with `forEachRemaining()`
   - Shows difference between advance and bulk operations

### 4. **Spliterator Characteristics** (`demonstrateSpliteratorCharacteristics`)
   - Analyzes all 8 Spliterator characteristic flags:
     - ORDERED, DISTINCT, SORTED, SIZED
     - NONNULL, IMMUTABLE, CONCURRENT, SUBSIZED
   - Provides size estimation capabilities
   - Helps understand collection properties

### 5. **Complex Product Processing** (`processComplexData`)
   - Works with Product objects (name, price, quantity, category)
   - Demonstrates filtering (high-value products > $500)
   - Processes multiple properties in split operations
   - Real-world product inventory scenarios

### 6. **Parallel-Like Processing** (`demonstrateParallelProcessing`)
   - Simulates parallel processing with multiple splits
   - Performs different computations on different batches:
     - Total inventory value calculation
     - Product counting
     - Category analysis
     - Low-stock item identification
   - Shows how to distribute work across splits

### 7. **Performance Comparison** (`performanceComparison`)
   - Compares Spliterator vs. traditional Iterator
   - Measures execution time in nanoseconds
   - Provides insights into performance characteristics
   - Validates correctness across approaches

## Product Class Features :
- **Properties**: name, price, quantity, category
- **Methods**: getName(), getPrice(), getQuantity(), getCategory(), getTotalValue()
- **Validation**: Comprehensive input validation with meaningful error messages
- **Utility Methods**: toString(), equals(), hashCode()

## Error Handling :
- Null/empty list validation
- IllegalArgumentException for invalid inputs
- Try-catch blocks with informative error messages
- Graceful degradation for edge cases
- Stack trace preservation for debugging

## Usage :
```java
ArrayListSplit processor = new ArrayListSplit();

// Basic string products
List<String> stringProducts = new ArrayList<>(Arrays.asList(
    "Laptop", "Fridge", "Fan", "Lights", "Table", "Phone", "Dock"
));

// Advanced demonstrations
processor.splitArrayListMultipleLevels(stringProducts);
processor.demonstrateTryAdvance(stringProducts);
processor.demonstrateSpliteratorCharacteristics(stringProducts);

// Complex data processing
List<Product> products = createProductList();
processor.processComplexData(products);
processor.demonstrateParallelProcessing(products);
processor.performanceComparison(products);
```

## Files :
- **App.java** - Main application demonstrating all features
- **ArrayListSplit.java** - Core Spliterator implementations (7 different methods)
- **Product.java** - Complex data model with properties and validation
- **README.md** - This documentation

## Learning Outcomes :
After completing this project, you will understand:
- ✓ Basic Spliterator splitting with `trySplit()`
- ✓ Recursive multi-level list subdivision
- ✓ Manual element processing with `tryAdvance()`
- ✓ Spliterator characteristics and their meanings
- ✓ Complex data structure processing
- ✓ Error handling in collection operations
- ✓ Performance characteristics of Spliterator vs Iterator
- ✓ Patterns for simulating parallel processing
