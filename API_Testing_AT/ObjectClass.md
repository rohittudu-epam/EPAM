# Java Object Class - Comprehensive Guide

---

## Table of Contents
1. [Introduction to the Concept](#1-introduction-to-the-concept)
2. [Definition, Explanation, Usage](#2-definition-explanation-usage)
3. [Analogy](#3-analogy)
4. [Methods with Code Snippets](#4-methods-with-code-snippets)
5. [Interview Questions for Senior Developers](#5-interview-questions-for-senior-developers)

---

## 1. Introduction to the Concept

### The Root of All Classes

The `java.lang.Object` class is the **ultimate superclass** of all classes in Java. Every class in Java, whether explicitly declared or not, directly or indirectly inherits from the `Object` class. This forms the foundation of Java's object-oriented hierarchy.

```
                    Object (Root)
                       │
         ┌─────────────┼─────────────┐
         │             │             │
       String       Number      YourClass
                       │
              ┌────────┼────────┐
              │        │        │
           Integer  Double   Float
```

### Why Object Class Exists

| Purpose | Description |
|---------|-------------|
| **Polymorphism** | Enables treating all objects uniformly |
| **Common Behavior** | Provides default implementations for fundamental operations |
| **Type Safety** | Acts as a common type for collections and generic operations |
| **Runtime Support** | Provides methods for threading, garbage collection, and reflection |

### Class Declaration

```java
package java.lang;

public class Object {
    private static native void registerNatives();
    static {
        registerNatives();
    }
    // ... methods
}
```

---

## 2. Definition, Explanation, Usage

### Official Definition

> **Object** is a class in the `java.lang` package that sits at the top of the class hierarchy. Every class has Object as a superclass. All objects, including arrays, implement the methods of this class.

### Key Characteristics

```
┌─────────────────────────────────────────────────────────────┐
│                    OBJECT CLASS FEATURES                    │
├─────────────────────────────────────────────────────────────┤
│  • Implicitly extended by every Java class                  │
│  • Contains 11 methods (some native implementations)        │
│  • Located in java.lang package (auto-imported)             │
│  • Cannot be instantiated directly (but can be)             │
│  • Provides identity-based default implementations          │
│  • Thread synchronization support via wait/notify           │
└─────────────────────────────────────────────────────────────┘
```

### Method Overview

| Method | Purpose | Default Behavior | Common Override |
|--------|---------|------------------|-----------------|
| `getClass()` | Returns runtime class | Returns Class<?> object | Cannot override (final) |
| `hashCode()` | Returns hash code | Memory address based | Yes - for collections |
| `equals(Object)` | Checks equality | Reference comparison | Yes - for value comparison |
| `clone()` | Creates copy | Shallow copy | Yes - for deep copy |
| `toString()` | String representation | ClassName@hashcode | Yes - for readable output |
| `notify()` | Wakes one thread | Native implementation | Cannot override (final) |
| `notifyAll()` | Wakes all threads | Native implementation | Cannot override (final) |
| `wait()` | Thread waiting | Native implementation | Cannot override (final) |
| `wait(long)` | Timed wait | Native implementation | Cannot override (final) |
| `wait(long, int)` | Precise timed wait | Native implementation | Cannot override (final) |
| `finalize()` | Cleanup before GC | Empty implementation | Deprecated in Java 9+ |

### Usage Scenarios

```java
// 1. As a generic container type
Object[] mixedArray = new Object[3];
mixedArray[0] = "String";
mixedArray[1] = 42;
mixedArray[2] = new ArrayList<>();

// 2. For method parameters accepting any type
public void printAnything(Object obj) {
    System.out.println(obj.toString());
}

// 3. In collections before generics (legacy code)
List legacyList = new ArrayList();
legacyList.add(new Object());

// 4. For synchronization
Object lock = new Object();
synchronized(lock) {
    // critical section
}
```

---

## 3. Analogy

### The "Universal Blueprint" Analogy

Think of the Object class as the **Universal Building Code** that every construction project must follow:

```
🏛️ UNIVERSAL BUILDING CODE (Object Class)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    ┌─────────────────────────────────────┐
    │      REQUIRED FOR ALL BUILDINGS     │
    ├─────────────────────────────────────┤
    │  📋 Building ID (hashCode)          │
    │  🔍 Building Inspector (equals)     │
    │  📝 Building Description (toString) │
    │  📂 Building Classification(getClass)|
    │  🔔 Alarm System (notify/wait)      │
    │  📑 Blueprint Copy (clone)          │
    │  🗑️ Demolition Plan (finalize)      │
    └─────────────────────────────────────┘
                    │
    ┌───────────────┼───────────────┐
    │               │               │
    🏠              🏢              🏭
  House          Office         Factory
(Custom Class) (Custom Class) (Custom Class)

Each building MUST have these basic features,
but can CUSTOMIZE how they work!
```

### The "DNA" Analogy

```
Object Class = Human DNA Template
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Every human has:
• A unique fingerprint    → hashCode()
• Can recognize others    → equals()
• Has a name/description  → toString()
• Can be cloned (theory)  → clone()
• Has a blood type/class  → getClass()
• Can communicate signals → notify()/wait()

Just as all humans share these basic traits
but express them differently, all Java objects
share Object's methods but implement them uniquely.
```

### The "Government ID" Analogy

```
┌────────────────────────────────────────────────┐
│           CITIZEN REGISTRATION SYSTEM          │
│              (Object Class Analogy)            │
├────────────────────────────────────────────────┤
│                                                │
│  🆔 Social Security Number = hashCode()        │
│     • Unique identifier for each citizen       │
│     • Used for quick lookup in databases       │
│                                                │
│  👥 Identity Verification = equals()           │
│     • Are these two IDs the same person?       │
│     • Default: Same physical ID card           │
│     • Override: Same person details            │
│                                                │
│  📄 Profile Summary = toString()               │
│     • Human-readable description               │
│     • Default: ID#12345@OFFICE                 │
│     • Override: "John Doe, Age 30, NYC"        │
│                                                │
│  📋 Citizenship Type = getClass()              │
│     • What category of citizen?                │
│     • Cannot change once born                  │
│                                                │
│  📢 Notification System = notify()/wait()      │
│     • Wake up when your number is called       │
│     • Wait in queue until notified             │
│                                                │
└────────────────────────────────────────────────┘
```

---

## 4. Methods with Code Snippets

### 4.1 getClass() Method

```java
/**
 * Returns the runtime class of this Object.
 * 
 * Signature: public final native Class<?> getClass()
 * 
 * Key Points:
 * - Final method (cannot be overridden)
 * - Native implementation (JVM level)
 * - Returns Class<?> object for reflection
 */
```

#### Implementation Details

```java
public class GetClassDemo {
    
    public static void main(String[] args) {
        
        // Example 1: Basic usage
        String str = "Hello";
        Class<?> strClass = str.getClass();
        
        System.out.println("Class name: " + strClass.getName());
        // Output: Class name: java.lang.String
        
        System.out.println("Simple name: " + strClass.getSimpleName());
        // Output: Simple name: String
        
        // Example 2: Runtime type checking
        Object obj = new ArrayList<String>();
        System.out.println("Runtime class: " + obj.getClass());
        // Output: Runtime class: class java.util.ArrayList
        
        // Example 3: Comparing classes
        Integer num1 = 100;
        Integer num2 = 200;
        Double dbl = 10.5;
        
        System.out.println(num1.getClass() == num2.getClass()); // true
        System.out.println(num1.getClass() == dbl.getClass());  // false
        
        // Example 4: Class hierarchy inspection
        Class<?> clazz = ArrayList.class;
        while (clazz != null) {
            System.out.println(clazz.getName());
            clazz = clazz.getSuperclass();
        }
        /*
         * Output:
         * java.util.ArrayList
         * java.util.AbstractList
         * java.util.AbstractCollection
         * java.lang.Object
         */
        
        // Example 5: getClass() vs instanceof
        Animal animal = new Dog();
        
        // getClass() - exact type match
        System.out.println(animal.getClass() == Dog.class);    // true
        System.out.println(animal.getClass() == Animal.class); // false
        
        // instanceof - inheritance aware
        System.out.println(animal instanceof Dog);    // true
        System.out.println(animal instanceof Animal); // true
        
        // Example 6: Creating new instance via getClass()
        try {
            String original = "Hello";
            String newInstance = original.getClass()
                                        .getDeclaredConstructor(String.class)
                                        .newInstance("World");
            System.out.println(newInstance); // World
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Animal {}
class Dog extends Animal {}
```

#### Advanced Usage - Reflection

```java
public class GetClassAdvancedDemo {
    
    private String name;
    public int age;
    
    public void display() {}
    private void secret() {}
    
    public static void main(String[] args) {
        GetClassAdvancedDemo obj = new GetClassAdvancedDemo();
        Class<?> clazz = obj.getClass();
        
        // Get all declared fields
        System.out.println("=== Fields ===");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field.getName() + " : " + 
                             field.getType().getSimpleName());
        }
        
        // Get all declared methods
        System.out.println("\n=== Methods ===");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method.getName() + "()");
        }
        
        // Get modifiers
        System.out.println("\n=== Class Modifiers ===");
        int modifiers = clazz.getModifiers();
        System.out.println("Is Public: " + Modifier.isPublic(modifiers));
        
        // Check interfaces
        System.out.println("\n=== Interfaces ===");
        for (Class<?> iface : ArrayList.class.getInterfaces()) {
            System.out.println(iface.getSimpleName());
        }
    }
}
```

---

### 4.2 hashCode() Method

```java
/**
 * Returns a hash code value for the object.
 * 
 * Signature: public native int hashCode()
 * 
 * Contract:
 * 1. Consistent: Multiple calls return same value (if object unchanged)
 * 2. Equal objects MUST have equal hash codes
 * 3. Unequal objects MAY have equal hash codes (collisions allowed)
 */
```

#### Default Implementation Understanding

```java
public class HashCodeDefaultDemo {
    
    public static void main(String[] args) {
        
        // Default hashCode() - based on memory address (JVM implementation)
        Object obj1 = new Object();
        Object obj2 = new Object();
        
        System.out.println("obj1 hashCode: " + obj1.hashCode());
        System.out.println("obj2 hashCode: " + obj2.hashCode());
        // Different memory locations = different hash codes
        
        // Identity hash code
        String s1 = new String("Hello");
        String s2 = new String("Hello");
        
        // String overrides hashCode()
        System.out.println("s1 hashCode: " + s1.hashCode());
        System.out.println("s2 hashCode: " + s2.hashCode());
        // Same content = same hash code (String's implementation)
        
        // Original identity hash (what Object.hashCode() would return)
        System.out.println("s1 identity hash: " + System.identityHashCode(s1));
        System.out.println("s2 identity hash: " + System.identityHashCode(s2));
        // Different objects = different identity hashes
    }
}
```

#### Custom hashCode() Implementation

```java
import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    
    // ==========================================
    // Method 1: Manual Implementation
    // ==========================================
    @Override
    public int hashCode() {
        int result = 17; // Start with non-zero constant
        
        // For each significant field:
        result = 31 * result + id;                              // int
        result = 31 * result + (name != null ? name.hashCode() : 0);   // Object
        result = 31 * result + (department != null ? department.hashCode() : 0);
        
        // For double: convert to long bits
        long salaryBits = Double.doubleToLongBits(salary);
        result = 31 * result + (int)(salaryBits ^ (salaryBits >>> 32));
        
        return result;
    }
    
    // ==========================================
    // Method 2: Using Objects.hash() (Java 7+)
    // ==========================================
    // @Override
    // public int hashCode() {
    //     return Objects.hash(id, name, department, salary);
    // }
    
    // ==========================================
    // Method 3: Using Objects.hashCode() for null-safety
    // ==========================================
    // @Override
    // public int hashCode() {
    //     int result = Integer.hashCode(id);
    //     result = 31 * result + Objects.hashCode(name);
    //     result = 31 * result + Objects.hashCode(department);
    //     result = 31 * result + Double.hashCode(salary);
    //     return result;
    // }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Employee employee = (Employee) obj;
        return id == employee.id &&
               Double.compare(employee.salary, salary) == 0 &&
               Objects.equals(name, employee.name) &&
               Objects.equals(department, employee.department);
    }
    
    public static void main(String[] args) {
        Employee emp1 = new Employee(1, "John", "IT", 50000);
        Employee emp2 = new Employee(1, "John", "IT", 50000);
        Employee emp3 = new Employee(2, "Jane", "HR", 60000);
        
        System.out.println("emp1.hashCode(): " + emp1.hashCode());
        System.out.println("emp2.hashCode(): " + emp2.hashCode());
        System.out.println("emp3.hashCode(): " + emp3.hashCode());
        
        System.out.println("\nemp1.equals(emp2): " + emp1.equals(emp2)); // true
        System.out.println("emp1.hashCode() == emp2.hashCode(): " + 
                          (emp1.hashCode() == emp2.hashCode())); // true (contract)
        
        // Using in HashMap
        Map<Employee, String> employeeMap = new HashMap<>();
        employeeMap.put(emp1, "First Employee");
        
        // emp2 can find emp1's value because hashCode and equals match
        System.out.println("\nValue for emp2: " + employeeMap.get(emp2));
    }
}
```

#### Why 31 in hashCode()?

```java
/**
 * Why use 31 as multiplier in hashCode?
 * 
 * 1. 31 is an odd prime number
 * 2. 31 * i == (i << 5) - i (JVM optimization)
 * 3. Provides good distribution of hash values
 * 4. Reduces collisions in hash-based collections
 */

public class WhyThirtyOneDemo {
    
    public static void main(String[] args) {
        // Demonstration of 31's bit optimization
        int i = 100;
        
        int multiplyResult = 31 * i;
        int bitShiftResult = (i << 5) - i; // Same result, faster
        
        System.out.println("31 * " + i + " = " + multiplyResult);
        System.out.println("(i << 5) - i = " + bitShiftResult);
        
        // Both produce: 3100
    }
}
```

#### Hash Collision Demonstration

```java
public class HashCollisionDemo {
    
    public static void main(String[] args) {
        // String hash collision examples
        String s1 = "Aa";
        String s2 = "BB";
        
        System.out.println("\"Aa\".hashCode() = " + s1.hashCode()); // 2112
        System.out.println("\"BB\".hashCode() = " + s2.hashCode()); // 2112
        
        // Different strings, same hash code!
        System.out.println("Hash collision: " + (s1.hashCode() == s2.hashCode()));
        System.out.println("But not equal: " + s1.equals(s2));
        
        // More collision examples
        System.out.println("\n\"AaAa\".hashCode() = " + "AaAa".hashCode());
        System.out.println("\"BBBB\".hashCode() = " + "BBBB".hashCode());
        System.out.println("\"AaBB\".hashCode() = " + "AaBB".hashCode());
        System.out.println("\"BBAa\".hashCode() = " + "BBAa".hashCode());
        // All produce: 2031744
    }
}
```

---

### 4.3 equals(Object obj) Method

```java
/**
 * Indicates whether some other object is "equal to" this one.
 * 
 * Signature: public boolean equals(Object obj)
 * 
 * Contract (must satisfy all):
 * 1. Reflexive:   x.equals(x) == true
 * 2. Symmetric:   x.equals(y) == y.equals(x)
 * 3. Transitive:  x.equals(y) && y.equals(z) → x.equals(z)
 * 4. Consistent:  Multiple calls return same result
 * 5. Null-safe:   x.equals(null) == false
 */
```

#### Default Implementation

```java
// In Object class:
public boolean equals(Object obj) {
    return (this == obj); // Reference equality
}
```

#### Complete equals() Implementation

```java
import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    
    public Person(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }
    
    // ==========================================
    // Proper equals() Implementation
    // ==========================================
    @Override
    public boolean equals(Object obj) {
        // Step 1: Check if same reference
        if (this == obj) {
            return true;
        }
        
        // Step 2: Check for null
        if (obj == null) {
            return false;
        }
        
        // Step 3: Check class type
        // Option A: getClass() - strict type matching
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        // Option B: instanceof - allows subclass comparison
        // if (!(obj instanceof Person)) {
        //     return false;
        // }
        
        // Step 4: Cast and compare fields
        Person other = (Person) obj;
        
        // Compare primitives with ==
        if (age != other.age) {
            return false;
        }
        
        // Compare objects with Objects.equals() for null safety
        return Objects.equals(firstName, other.firstName) &&
               Objects.equals(lastName, other.lastName) &&
               Objects.equals(email, other.email);
    }
    
    // ==========================================
    // Must override hashCode() when overriding equals()
    // ==========================================
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, email);
    }
    
    @Override
    public String toString() {
        return String.format("Person{firstName='%s', lastName='%s', age=%d, email='%s'}",
                           firstName, lastName, age, email);
    }
    
    public static void main(String[] args) {
        Person p1 = new Person("John", "Doe", 30, "john@example.com");
        Person p2 = new Person("John", "Doe", 30, "john@example.com");
        Person p3 = new Person("Jane", "Doe", 25, "jane@example.com");
        Person p4 = p1; // Same reference
        
        System.out.println("=== Equality Tests ===");
        System.out.println("p1.equals(p1): " + p1.equals(p1)); // Reflexive: true
        System.out.println("p1.equals(p2): " + p1.equals(p2)); // Same content: true
        System.out.println("p2.equals(p1): " + p2.equals(p1)); // Symmetric: true
        System.out.println("p1.equals(p3): " + p1.equals(p3)); // Different: false
        System.out.println("p1.equals(p4): " + p1.equals(p4)); // Same ref: true
        System.out.println("p1.equals(null): " + p1.equals(null)); // Null: false
        
        System.out.println("\n=== Reference vs Value ===");
        System.out.println("p1 == p2: " + (p1 == p2));   // Reference: false
        System.out.println("p1 == p4: " + (p1 == p4));   // Reference: true
    }
}
```

#### equals() with Inheritance - The Challenge

```java
public class EqualsInheritanceDemo {
    
    // Base class
    static class Point {
        private final int x;
        private final int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Point)) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    // Subclass - adding color
    static class ColorPoint extends Point {
        private final String color;
        
        public ColorPoint(int x, int y, String color) {
            super(x, y);
            this.color = color;
        }
        
        // BROKEN: Violates symmetry!
        // @Override
        // public boolean equals(Object obj) {
        //     if (!(obj instanceof ColorPoint)) return false;
        //     return super.equals(obj) && 
        //            Objects.equals(color, ((ColorPoint) obj).color);
        // }
        
        // Better approach: Use composition instead of inheritance
        // Or use getClass() for strict matching
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            if (!super.equals(obj)) return false;
            ColorPoint that = (ColorPoint) obj;
            return Objects.equals(color, that.color);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), color);
        }
    }
    
    public static void main(String[] args) {
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1, 2, "red");
        
        // With getClass() approach:
        System.out.println("p.equals(cp): " + p.equals(cp));   // true (Point's equals)
        System.out.println("cp.equals(p): " + cp.equals(p));   // false (different class)
        
        // This breaks symmetry! That's why inheritance + equals is tricky
    }
}
```

#### Common equals() Mistakes

```java
public class EqualsMistakesDemo {
    
    // MISTAKE 1: Wrong method signature (overloading, not overriding!)
    static class BadEquals1 {
        private int value;
        
        // This is OVERLOADING, not OVERRIDING!
        public boolean equals(BadEquals1 other) { // Wrong parameter type!
            return this.value == other.value;
        }
    }
    
    // MISTAKE 2: Not handling null
    static class BadEquals2 {
        private String name;
        
        @Override
        public boolean equals(Object obj) {
            BadEquals2 other = (BadEquals2) obj; // Crashes if null!
            return name.equals(other.name);       // NPE if name is null!
        }
    }
    
    // MISTAKE 3: Using == for objects
    static class BadEquals3 {
        private String name;
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof BadEquals3)) return false;
            BadEquals3 other = (BadEquals3) obj;
            return name == other.name; // Should use .equals()!
        }
    }
    
    // MISTAKE 4: Not overriding hashCode
    static class BadEquals4 {
        private int id;
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof BadEquals4)) return false;
            return id == ((BadEquals4) obj).id;
        }
        // Missing hashCode() override!
    }
    
    // CORRECT Implementation
    static class GoodEquals {
        private int id;
        private String name;
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            GoodEquals that = (GoodEquals) obj;
            return id == that.id && Objects.equals(name, that.name);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
    
    public static void main(String[] args) {
        // Demonstrate BadEquals4 problem with HashMap
        BadEquals4 key1 = new BadEquals4();
        key1.id = 1;
        
        BadEquals4 key2 = new BadEquals4();
        key2.id = 1;
        
        Map<BadEquals4, String> map = new HashMap<>();
        map.put(key1, "Value");
        
        System.out.println("key1.equals(key2): " + key1.equals(key2)); // true
        System.out.println("map.get(key2): " + map.get(key2)); // null! (broken)
    }
}
```

---

### 4.4 clone() Method

```java
/**
 * Creates and returns a copy of this object.
 * 
 * Signature: protected native Object clone() throws CloneNotSupportedException
 * 
 * Requirements:
 * 1. Class must implement Cloneable interface
 * 2. Must override clone() and make it public
 * 3. Must call super.clone()
 * 
 * Types:
 * - Shallow Copy: Copies references to objects
 * - Deep Copy: Copies actual objects recursively
 */
```

#### Shallow Clone Implementation

```java
public class ShallowCloneDemo implements Cloneable {
    private int primitiveValue;
    private String name;                    // Immutable - safe to share
    private int[] array;                    // Mutable - problematic in shallow copy
    private Address address;                // Mutable - problematic in shallow copy
    
    public ShallowCloneDemo(int value, String name, int[] array, Address address) {
        this.primitiveValue = value;
        this.name = name;
        this.array = array;
        this.address = address;
    }
    
    // Shallow Clone
    @Override
    public ShallowCloneDemo clone() {
        try {
            return (ShallowCloneDemo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen if Cloneable
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        Address addr = new Address("123 Main St", "NYC");
        ShallowCloneDemo original = new ShallowCloneDemo(42, "Original", arr, addr);
        
        // Create shallow clone
        ShallowCloneDemo clone = original.clone();
        
        System.out.println("=== Before Modification ===");
        System.out.println("Original array[0]: " + original.array[0]);
        System.out.println("Clone array[0]: " + clone.array[0]);
        
        // Modify clone's array
        clone.array[0] = 999;
        clone.address.setStreet("456 Oak Ave");
        
        System.out.println("\n=== After Modifying Clone ===");
        System.out.println("Original array[0]: " + original.array[0]); // 999! 
        System.out.println("Clone array[0]: " + clone.array[0]);       // 999
        System.out.println("Original address: " + original.address);   // 456 Oak Ave!
        
        // Shallow copy shares references - changes affect original!
    }
}

class Address {
    private String street;
    private String city;
    
    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    @Override
    public String toString() {
        return street + ", " + city;
    }
}
```

#### Deep Clone Implementation

```java
public class DeepCloneDemo implements Cloneable {
    private int primitiveValue;
    private String name;
    private int[] array;
    private AddressCloneable address;
    
    public DeepCloneDemo(int value, String name, int[] array, AddressCloneable address) {
        this.primitiveValue = value;
        this.name = name;
        this.array = array;
        this.address = address;
    }
    
    // Deep Clone
    @Override
    public DeepCloneDemo clone() {
        try {
            DeepCloneDemo clone = (DeepCloneDemo) super.clone();
            
            // Deep copy mutable array
            clone.array = this.array.clone();
            
            // Deep copy mutable object
            clone.address = this.address.clone();
            
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        AddressCloneable addr = new AddressCloneable("123 Main St", "NYC");
        DeepCloneDemo original = new DeepCloneDemo(42, "Original", arr, addr);
        
        // Create deep clone
        DeepCloneDemo clone = original.clone();
        
        // Modify clone
        clone.array[0] = 999;
        clone.address.setStreet("456 Oak Ave");
        
        System.out.println("=== After Modifying Clone (Deep Copy) ===");
        System.out.println("Original array[0]: " + original.array[0]); // 1 (unchanged!)
        System.out.println("Clone array[0]: " + clone.array[0]);       // 999
        System.out.println("Original address: " + original.address);   // 123 Main St
        System.out.println("Clone address: " + clone.address);         // 456 Oak Ave
    }
}

class AddressCloneable implements Cloneable {
    private String street;
    private String city;
    
    public AddressCloneable(String street, String city) {
        this.street = street;
        this.city = city;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    @Override
    public AddressCloneable clone() {
        try {
            return (AddressCloneable) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
    @Override
    public String toString() {
        return street + ", " + city;
    }
}
```

#### Copy Constructor Alternative (Recommended)

```java
public class CopyConstructorDemo {
    private int value;
    private String name;
    private List<String> items;
    private Map<String, Integer> data;
    
    // Regular constructor
    public CopyConstructorDemo(int value, String name, List<String> items, 
                               Map<String, Integer> data) {
        this.value = value;
        this.name = name;
        this.items = items;
        this.data = data;
    }
    
    // Copy constructor
    public CopyConstructorDemo(CopyConstructorDemo other) {
        this.value = other.value;
        this.name = other.name; // String is immutable - safe to share
        
        // Deep copy collections
        this.items = new ArrayList<>(other.items);
        this.data = new HashMap<>(other.data);
    }
    
    // Static factory method alternative
    public static CopyConstructorDemo copyOf(CopyConstructorDemo other) {
        return new CopyConstructorDemo(other);
    }
    
    public void addItem(String item) {
        items.add(item);
    }
    
    public List<String> getItems() {
        return new ArrayList<>(items); // Defensive copy
    }
    
    public static void main(String[] args) {
        List<String> items = new ArrayList<>(Arrays.asList("A", "B", "C"));
        Map<String, Integer> data = new HashMap<>();
        data.put("key1", 1);
        
        CopyConstructorDemo original = new CopyConstructorDemo(42, "Original", items, data);
        CopyConstructorDemo copy = new CopyConstructorDemo(original);
        
        // Modify copy
        copy.addItem("D");
        
        System.out.println("Original items: " + original.getItems()); // [A, B, C]
        System.out.println("Copy items: " + copy.getItems());         // [A, B, C, D]
    }
}
```

#### Serialization-Based Deep Clone

```java
import java.io.*;

public class SerializationCloneDemo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int value;
    private String name;
    private List<String> items;
    
    public SerializationCloneDemo(int value, String name, List<String> items) {
        this.value = value;
        this.name = name;
        this.items = items;
    }
    
    // Deep clone using serialization
    @SuppressWarnings("unchecked")
    public SerializationCloneDemo deepClone() {
        try {
            // Serialize
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();
            
            // Deserialize
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            SerializationCloneDemo clone = (SerializationCloneDemo) ois.readObject();
            ois.close();
            
            return clone;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Clone failed", e);
        }
    }
    
    public static void main(String[] args) {
        List<String> items = new ArrayList<>(Arrays.asList("X", "Y", "Z"));
        SerializationCloneDemo original = new SerializationCloneDemo(100, "Test", items);
        SerializationCloneDemo clone = original.deepClone();
        
        clone.items.add("W");
        
        System.out.println("Original items: " + original.items); // [X, Y, Z]
        System.out.println("Clone items: " + clone.items);       // [X, Y, Z, W]
    }
}
```

---

### 4.5 toString() Method

```java
/**
 * Returns a string representation of the object.
 * 
 * Signature: public String toString()
 * 
 * Default implementation:
 * getClass().getName() + '@' + Integer.toHexString(hashCode())
 * 
 * Best Practices:
 * - Should return concise, informative, human-readable representation
 * - Should include all interesting information
 * - Recommended to override in all classes
 */
```

#### Default Implementation

```java
// In Object class:
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

#### Custom toString() Implementations

```java
import java.util.StringJoiner;
import java.util.Objects;

public class ToStringDemo {
    private int id;
    private String name;
    private double salary;
    private String[] skills;
    private boolean active;
    
    public ToStringDemo(int id, String name, double salary, String[] skills, boolean active) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.skills = skills;
        this.active = active;
    }
    
    // ==========================================
    // Method 1: Simple String concatenation
    // ==========================================
    public String toStringSimple() {
        return "ToStringDemo{id=" + id + ", name=" + name + 
               ", salary=" + salary + ", active=" + active + "}";
    }
    
    // ==========================================
    // Method 2: String.format()
    // ==========================================
    public String toStringFormatted() {
        return String.format("ToStringDemo{id=%d, name='%s', salary=%.2f, skills=%s, active=%b}",
                           id, name, salary, Arrays.toString(skills), active);
    }
    
    // ==========================================
    // Method 3: StringBuilder (most efficient)
    // ==========================================
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ToStringDemo{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append("'");
        sb.append(", salary=").append(String.format("%.2f", salary));
        sb.append(", skills=").append(Arrays.toString(skills));
        sb.append(", active=").append(active);
        sb.append("}");
        return sb.toString();
    }
    
    // ==========================================
    // Method 4: StringJoiner (Java 8+)
    // ==========================================
    public String toStringJoiner() {
        return new StringJoiner(", ", "ToStringDemo[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("salary=" + salary)
                .add("skills=" + Arrays.toString(skills))
                .add("active=" + active)
                .toString();
    }
    
    public static void main(String[] args) {
        String[] skills = {"Java", "Python", "SQL"};
        ToStringDemo obj = new ToStringDemo(1, "John Doe", 75000.50, skills, true);
        
        // Default Object.toString() would print something like:
        // ToStringDemo@15db9742
        
        System.out.println("=== toString() Methods ===");
        System.out.println("Simple: " + obj.toStringSimple());
        System.out.println("Formatted: " + obj.toStringFormatted());
        System.out.println("StringBuilder: " + obj.toString());
        System.out.println("StringJoiner: " + obj.toStringJoiner());
        
        // Automatically called in print statements
        System.out.println("\n=== Auto-invocation ===");
        System.out.println(obj); // Calls toString() automatically
        
        // String concatenation
        String message = "Employee: " + obj; // toString() called
        System.out.println(message);
    }
}
```

#### Using Libraries for toString()

```java
import java.util.Objects;

// Using Apache Commons Lang (example structure)
// import org.apache.commons.lang3.builder.ToStringBuilder;
// import org.apache.commons.lang3.builder.ToStringStyle;

public class ToStringLibraryDemo {
    private int id;
    private String name;
    private List<String> items;
    
    public ToStringLibraryDemo(int id, String name, List<String> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }
    
    // Using Objects class for null-safe toString on fields
    @Override
    public String toString() {
        return "ToStringLibraryDemo{" +
               "id=" + id +
               ", name='" + Objects.toString(name, "N/A") + "'" +
               ", items=" + Objects.toString(items, "[]") +
               '}';
    }
    
    // With Apache Commons Lang (if available):
    // @Override
    // public String toString() {
    //     return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
    //             .append("id", id)
    //             .append("name", name)
    //             .append("items", items)
    //             .toString();
    // }
    
    // With reflection-based approach (for debugging):
    // @Override
    // public String toString() {
    //     return ToStringBuilder.reflectionToString(this);
    // }
    
    public static void main(String[] args) {
        ToStringLibraryDemo obj = new ToStringLibraryDemo(1, null, null);
        System.out.println(obj);
        // Output: ToStringLibraryDemo{id=1, name='N/A', items=[]}
    }
}
```

#### toString() for Collections

```java
public class ToStringCollectionsDemo {
    
    public static void main(String[] args) {
        // Arrays need special handling
        int[] primitiveArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"A", "B", "C"};
        int[][] nested2DArray = {{1, 2}, {3, 4}};
        
        System.out.println("=== Array toString() ===");
        System.out.println("Primitive array (wrong): " + primitiveArray);
        // Output: [I@hashcode (useless!)
        
        System.out.println("Primitive array (correct): " + Arrays.toString(primitiveArray));
        // Output: [1, 2, 3, 4, 5]
        
        System.out.println("String array: " + Arrays.toString(stringArray));
        // Output: [A, B, C]
        
        System.out.println("2D array (deep): " + Arrays.deepToString(nested2DArray));
        // Output: [[1, 2], [3, 4]]
        
        // Collections work well with toString()
        List<String> list = Arrays.asList("X", "Y", "Z");
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        
        System.out.println("\n=== Collection toString() ===");
        System.out.println("List: " + list);         // [X, Y, Z]
        System.out.println("Map: " + map);           // {one=1, two=2}
    }
}
```

---

### 4.6 notify() and notifyAll() Methods

```java
/**
 * Thread communication methods for inter-thread signaling.
 * 
 * notify(): Wakes up a single thread waiting on this object's monitor
 * notifyAll(): Wakes up all threads waiting on this object's monitor
 * 
 * Signatures:
 * public final native void notify()
 * public final native void notifyAll()
 * 
 * Requirements:
 * - Must be called from synchronized context
 * - Must own the object's monitor (intrinsic lock)
 */
```

#### Basic notify() Example

```java
public class NotifyBasicDemo {
    private final Object lock = new Object();
    private boolean dataReady = false;
    private String data;
    
    public void produce() throws InterruptedException {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ": Producing data...");
            Thread.sleep(2000); // Simulate work
            
            data = "Hello from producer!";
            dataReady = true;
            
            System.out.println(Thread.currentThread().getName() + ": Data ready, notifying...");
            lock.notify(); // Wake up ONE waiting thread
        }
    }
    
    public void consume() throws InterruptedException {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ": Waiting for data...");
            
            while (!dataReady) { // Always use while loop, not if!
                lock.wait();
            }
            
            System.out.println(Thread.currentThread().getName() + ": Received: " + data);
        }
    }
    
    public static void main(String[] args) {
        NotifyBasicDemo demo = new NotifyBasicDemo();
        
        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                demo.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");
        
        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                demo.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");
        
        consumer.start();
        
        try {
            Thread.sleep(500); // Ensure consumer starts first
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        producer.start();
    }
}
```

#### notify() vs notifyAll() Comparison

```java
public class NotifyVsNotifyAllDemo {
    private final Object lock = new Object();
    private int count = 0;
    
    public void waitForSignal(String name) throws InterruptedException {
        synchronized (lock) {
            System.out.println(name + ": Waiting...");
            lock.wait();
            System.out.println(name + ": Woke up! Count = " + count);
        }
    }
    
    public void signalOne() {
        synchronized (lock) {
            count++;
            System.out.println("Signaling ONE thread...");
            lock.notify(); // Wakes up only ONE waiting thread
        }
    }
    
    public void signalAll() {
        synchronized (lock) {
            count++;
            System.out.println("Signaling ALL threads...");
            lock.notifyAll(); // Wakes up ALL waiting threads
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        NotifyVsNotifyAllDemo demo = new NotifyVsNotifyAllDemo();
        
        // Create 3 waiting threads
        for (int i = 1; i <= 3; i++) {
            final int threadNum = i;
            new Thread(() -> {
                try {
                    demo.waitForSignal("Thread-" + threadNum);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        
        Thread.sleep(1000); // Let all threads start waiting
        
        // Option 1: notify() - only ONE thread wakes up
        // demo.signalOne();
        
        // Option 2: notifyAll() - ALL threads wake up
        demo.signalAll();
        
        Thread.sleep(2000); // Let threads finish
        System.out.println("Main thread done.");
    }
}
```

#### Producer-Consumer with notifyAll()

```java
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;
    private final Object lock = new Object();
    
    public void produce(int value) throws InterruptedException {
        synchronized (lock) {
            // Wait while queue is full
            while (queue.size() == CAPACITY) {
                System.out.println("Producer: Queue full, waiting...");
                lock.wait();
            }
            
            queue.offer(value);
            System.out.println("Produced: " + value + " | Queue size: " + queue.size());
            
            // Notify consumers that data is available
            lock.notifyAll();
        }
    }
    
    public int consume() throws InterruptedException {
        synchronized (lock) {
            // Wait while queue is empty
            while (queue.isEmpty()) {
                System.out.println("Consumer: Queue empty, waiting...");
                lock.wait();
            }
            
            int value = queue.poll();
            System.out.println("Consumed: " + value + " | Queue size: " + queue.size());
            
            // Notify producers that space is available
            lock.notifyAll();
            
            return value;
        }
    }
    
    public static void main(String[] args) {
        ProducerConsumerDemo demo = new ProducerConsumerDemo();
        
        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    demo.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");
        
        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    demo.consume();
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");
        
        producer.start();
        consumer.start();
    }
}
```

---

### 4.7 wait() Methods

```java
/**
 * Causes the current thread to wait until notified or interrupted.
 * 
 * Signatures:
 * public final void wait() throws InterruptedException
 * public final native void wait(long timeout) throws InterruptedException
 * public final void wait(long timeout, int nanos) throws InterruptedException
 * 
 * Requirements:
 * - Must be called from synchronized context
 * - Releases the object's monitor while waiting
 * - Reacquires monitor before returning
 */
```

#### wait() with Timeout

```java
public class WaitTimeoutDemo {
    private final Object lock = new Object();
    private boolean eventOccurred = false;
    
    public void waitForEvent(long timeoutMillis) throws InterruptedException {
        synchronized (lock) {
            long startTime = System.currentTimeMillis();
            long remaining = timeoutMillis;
            
            while (!eventOccurred && remaining > 0) {
                System.out.println("Waiting for event... (timeout: " + remaining + "ms)");
                lock.wait(remaining);
                
                // Recalculate remaining time
                remaining = timeoutMillis - (System.currentTimeMillis() - startTime);
            }
            
            if (eventOccurred) {
                System.out.println("Event occurred!");
            } else {
                System.out.println("Timeout! Event did not occur.");
            }
        }
    }
    
    public void triggerEvent() {
        synchronized (lock) {
            System.out.println("Triggering event...");
            eventOccurred = true;
            lock.notify();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        WaitTimeoutDemo demo = new WaitTimeoutDemo();
        
        // Scenario 1: Event occurs before timeout
        Thread waiter1 = new Thread(() -> {
            try {
                demo.waitForEvent(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        waiter1.start();
        Thread.sleep(2000);
        demo.triggerEvent();
        waiter1.join();
        
        // Reset for Scenario 2
        demo.eventOccurred = false;
        
        // Scenario 2: Timeout occurs (no event)
        System.out.println("\n--- Scenario 2: Timeout ---");
        Thread waiter2 = new Thread(() -> {
            try {
                demo.waitForEvent(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        waiter2.start();
        // Don't trigger event - let it timeout
        waiter2.join();
    }
}
```

#### wait() with Nanosecond Precision

```java
public class WaitNanosDemo {
    private final Object lock = new Object();
    
    public void preciseWait(long millis, int nanos) throws InterruptedException {
        synchronized (lock) {
            long startTime = System.nanoTime();
            
            lock.wait(millis, nanos);
            
            long elapsedNanos = System.nanoTime() - startTime;
            long elapsedMillis = elapsedNanos / 1_000_000;
            long remainderNanos = elapsedNanos % 1_000_000;
            
            System.out.printf("Waited for: %d ms and %d ns%n", 
                            elapsedMillis, remainderNanos);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        WaitNanosDemo demo = new WaitNanosDemo();
        
        Thread waiter = new Thread(() -> {
            try {
                demo.preciseWait(100, 500000); // 100ms + 500,000ns = 100.5ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        waiter.start();
        
        // Notify after 50ms
        Thread.sleep(50);
        synchronized (demo.lock) {
            demo.lock.notify();
        }
        
        waiter.join();
    }
}
```

#### Spurious Wakeups and Guard Pattern

```java
public class SpuriousWakeupDemo {
    private final Object lock = new Object();
    private boolean condition = false;
    
    // WRONG: Using if (susceptible to spurious wakeups)
    public void wrongWait() throws InterruptedException {
        synchronized (lock) {
            if (!condition) { // BUG: Should be while!
                lock.wait();
            }
            // May execute even if condition is still false!
            processData();
        }
    }
    
    // CORRECT: Using while loop (guard pattern)
    public void correctWait() throws InterruptedException {
        synchronized (lock) {
            while (!condition) { // Re-check after each wakeup
                lock.wait();
            }
            // Guaranteed: condition is true
            processData();
        }
    }
    
    // Even better: Using predicate with timeout
    public boolean waitWithTimeout(long timeout) throws InterruptedException {
        synchronized (lock) {
            long deadline = System.currentTimeMillis() + timeout;
            
            while (!condition) {
                long waitTime = deadline - System.currentTimeMillis();
                if (waitTime <= 0) {
                    return false; // Timeout expired
                }
                lock.wait(waitTime);
            }
            return true; // Condition met
        }
    }
    
    private void processData() {
        System.out.println("Processing data...");
    }
    
    public void setCondition(boolean value) {
        synchronized (lock) {
            condition = value;
            lock.notifyAll();
        }
    }
}
```

---

### 4.8 finalize() Method

```java
/**
 * Called by the garbage collector before reclaiming the object's memory.
 * 
 * Signature: protected void finalize() throws Throwable
 * 
 * Status: DEPRECATED since Java 9
 * 
 * Problems:
 * - Unpredictable execution timing
 * - Not guaranteed to run
 * - Can cause resurrection of objects
 * - Performance impact
 * - Security vulnerabilities
 */
```

#### finalize() Demonstration (For Understanding Only)

```java
public class FinalizeDemo {
    private String name;
    private static FinalizeDemo resurrectedObject;
    
    public FinalizeDemo(String name) {
        this.name = name;
        System.out.println("Created: " + name);
    }
    
    @Override
    @Deprecated // Don't use in production!
    protected void finalize() throws Throwable {
        try {
            System.out.println("Finalizing: " + name);
            
            // Demonstrating resurrection (BAD PRACTICE!)
            // resurrectedObject = this;
        } finally {
            super.finalize();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        // Create object
        FinalizeDemo obj = new FinalizeDemo("TestObject");
        
        // Remove reference
        obj = null;
        
        // Request garbage collection
        System.gc();
        
        // Wait for finalization
        Thread.sleep(1000);
        
        System.out.println("Program ending...");
        
        // Note: finalize() may or may not have run by now!
    }
}
```

#### Modern Alternatives to finalize()

```java
// Alternative 1: try-with-resources (AutoCloseable)
public class ResourceHandler implements AutoCloseable {
    private final String name;
    private boolean closed = false;
    
    public ResourceHandler(String name) {
        this.name = name;
        System.out.println("Resource created: " + name);
    }
    
    public void doWork() {
        if (closed) {
            throw new IllegalStateException("Resource already closed");
        }
        System.out.println("Working with: " + name);
    }
    
    @Override
    public void close() {
        if (!closed) {
            System.out.println("Closing resource: " + name);
            closed = true;
            // Release resources here
        }
    }
    
    public static void main(String[] args) {
        // Automatic cleanup with try-with-resources
        try (ResourceHandler handler = new ResourceHandler("MyResource")) {
            handler.doWork();
        } // close() called automatically
        
        System.out.println("After try block");
    }
}

// Alternative 2: Cleaner API (Java 9+)
import java.lang.ref.Cleaner;

public class CleanerDemo implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    
    private final Cleaner.Cleanable cleanable;
    private final State state;
    
    // Cleaning state - must not reference the CleanerDemo instance!
    private static class State implements Runnable {
        private final String resourceName;
        
        State(String resourceName) {
            this.resourceName = resourceName;
        }
        
        @Override
        public void run() {
            System.out.println("Cleaning up: " + resourceName);
            // Release native resources here
        }
    }
    
    public CleanerDemo(String name) {
        this.state = new State(name);
        this.cleanable = cleaner.register(this, state);
    }
    
    @Override
    public void close() {
        cleanable.clean(); // Explicit cleanup
    }
    
    public static void main(String[] args) {
        try (CleanerDemo demo = new CleanerDemo("TestResource")) {
            System.out.println("Using resource...");
        }
        
        // Or without try-with-resources (cleaner will eventually run)
        CleanerDemo demo2 = new CleanerDemo("AutoCleanResource");
        demo2 = null;
        System.gc();
    }
}

// Alternative 3: Explicit cleanup method
public class ExplicitCleanupDemo {
    private boolean disposed = false;
    private int[] largeArray = new int[1000000];
    
    public void dispose() {
        if (!disposed) {
            largeArray = null; // Release reference
            disposed = true;
            System.out.println("Resources disposed");
        }
    }
    
    // Optional: Warning if not disposed
    @Override
    protected void finalize() throws Throwable {
        try {
            if (!disposed) {
                System.err.println("WARNING: dispose() was not called!");
                dispose();
            }
        } finally {
            super.finalize();
        }
    }
}
```

---

## 5. Interview Questions for Senior Developers

### Category 1: equals() and hashCode()

#### Question 1: The hashCode() Contract
```
Q: What happens if you override equals() but not hashCode()? 
   Provide a concrete example of the bug this causes.

Expected Answer:
- Objects that are equal will have different hash codes
- HashMap/HashSet will malfunction
- Equal objects will be placed in different buckets
- get() won't find objects that were put() in the map

Example:
```java
class BrokenEmployee {
    int id;
    String name;
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BrokenEmployee)) return false;
        return id == ((BrokenEmployee) obj).id;
    }
    // No hashCode() override!
}

// Problem demonstration:
Map<BrokenEmployee, String> map = new HashMap<>();
BrokenEmployee e1 = new BrokenEmployee(1, "John");
BrokenEmployee e2 = new BrokenEmployee(1, "John");

map.put(e1, "Value");
System.out.println(e1.equals(e2)); // true
System.out.println(map.get(e2));   // null! (should be "Value")
```

---

#### Question 2: equals() Symmetry Violation
```
Q: This equals() implementation violates symmetry. Identify and fix the bug:
```

```java
class CaseInsensitiveString {
    private final String s;
    
    CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CaseInsensitiveString) {
            return s.equalsIgnoreCase(((CaseInsensitiveString) obj).s);
        }
        if (obj instanceof String) { // BUG!
            return s.equalsIgnoreCase((String) obj);
        }
        return false;
    }
}

// Problem:
CaseInsensitiveString cis = new CaseInsensitiveString("Hello");
String s = "hello";
System.out.println(cis.equals(s)); // true
System.out.println(s.equals(cis)); // false - Symmetry violated!
```

```
Expected Answer:
Remove the String comparison. Only compare with same type:

@Override
public boolean equals(Object obj) {
    return obj instanceof CaseInsensitiveString &&
           s.equalsIgnoreCase(((CaseInsensitiveString) obj).s);
}
```

---

#### Question 3: Mutable hashCode() Problem
```
Q: What's wrong with this code and how would you fix it?
```

```java
class MutableKey {
    private String value;
    
    MutableKey(String value) { this.value = value; }
    
    void setValue(String value) { this.value = value; }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MutableKey)) return false;
        return Objects.equals(value, ((MutableKey) obj).value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}

// Usage:
Map<MutableKey, String> map = new HashMap<>();
MutableKey key = new MutableKey("original");
map.put(key, "data");

key.setValue("modified"); // Mutate the key!
System.out.println(map.get(key)); // null! (data is lost)
```

```
Expected Answer:
1. Never use mutable objects as HashMap keys
2. Make the class immutable:
   - Make field final
   - Remove setter
   - Make class final or document for extension
3. Alternative: Use only the immutable id field for hashCode
```

---

### Category 2: clone() and Object Copying

#### Question 4: Deep Clone Challenge
```
Q: Implement a proper deep clone for this class:
```

```java
class Department implements Cloneable {
    private String name;
    private List<Employee> employees;
    private Manager manager;
    
    @Override
    public Department clone() {
        // Your implementation
    }
}

class Employee implements Cloneable {
    private String name;
    private Address address;
    
    @Override
    public Employee clone() {
        // Your implementation
    }
}

class Manager extends Employee {
    private List<Employee> directReports;
    
    @Override
    public Manager clone() {
        // Your implementation
    }
}

class Address implements Cloneable {
    private String street;
    private String city;
    
    @Override
    public Address clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
```

```
Expected Answer:
```java
class Department implements Cloneable {
    @Override
    public Department clone() {
        try {
            Department clone = (Department) super.clone();
            // Deep copy the list
            clone.employees = new ArrayList<>();
            for (Employee emp : this.employees) {
                clone.employees.add(emp.clone());
            }
            // Deep copy the manager
            if (this.manager != null) {
                clone.manager = this.manager.clone();
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
```

---

#### Question 5: Clone vs Copy Constructor
```
Q: When would you choose copy constructor over clone()? 
   What are the advantages and disadvantages of each?

Expected Answer:

Copy Constructor Advantages:
- No dependency on Cloneable interface
- Can accept interface types as parameter
- Compiler-enforced (no runtime exceptions)
- More flexible (can create different types)
- Works well with immutable classes
- Clear, explicit contract

Clone() Advantages:
- Polymorphic - subclasses can provide own implementation
- Single method for entire hierarchy
- Can be faster for simple objects (native implementation)

Copy Constructor preferred when:
- Class is final (no inheritance concerns)
- Working with interfaces
- Defensive copying of constructor arguments
- Creating immutable instances from mutable sources

Clone() may be useful when:
- Need polymorphic copy behavior
- Performance critical and objects are simple
- Working with array copying (arrays have clone())
```

---

### Category 3: Threading (wait/notify)

#### Question 6: Why While Loop with wait()?
```
Q: Explain why this code is buggy:

synchronized (lock) {
    if (!condition) {
        lock.wait();
    }
    // Process
}

Expected Answer:
Problems with using 'if' instead of 'while':

1. Spurious Wakeups: JVM may wake up threads without notify()
2. Multiple Waiters: When notifyAll() is called, all threads wake up
   but only one should process
3. Race Condition: Condition may have changed between wakeup 
   and acquiring lock

Correct pattern:
synchronized (lock) {
    while (!condition) { // Re-check after every wakeup
        lock.wait();
    }
    // Guaranteed: condition is true here
}
```

---

#### Question 7: Implement a Bounded Blocking Queue
```
Q: Implement a thread-safe bounded blocking queue using wait/notify:
```

```java
// Expected Answer:
public class BoundedBlockingQueue<E> {
    private final Queue<E> queue;
    private final int capacity;
    
    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }
    
    public synchronized void put(E element) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // Wait for space
        }
        queue.offer(element);
        notifyAll(); // Notify consumers
    }
    
    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait for elements
        }
        E element = queue.poll();
        notifyAll(); // Notify producers
        return element;
    }
    
    public synchronized int size() {
        return queue.size();
    }
}
```

---

#### Question 8: notify() vs notifyAll() Deadlock
```
Q: This code can deadlock with notify(). Explain why and fix it:
```

```java
class Buffer {
    private int[] data = new int[1];
    private boolean hasData = false;
    
    synchronized void put(int value) throws InterruptedException {
        while (hasData) {
            wait();
        }
        data[0] = value;
        hasData = true;
        notify(); // BUG under certain conditions
    }
    
    synchronized int get() throws InterruptedException {
        while (!hasData) {
            wait();
        }
        hasData = false;
        notify(); // BUG under certain conditions
        return data[0];
    }
}

// With multiple producers and consumers...
```

```
Expected Answer:
The deadlock scenario:
1. Producer P1 and P2, Consumer C1 and C2
2. C1 and C2 are waiting (buffer empty)
3. P1 puts data, calls notify(), wakes C1
4. C1 gets data, calls notify(), wakes C2 (not P!)
5. C2 finds no data, waits again
6. P2 was waiting, still waiting
7. All threads waiting = DEADLOCK

Fix: Use notifyAll() to wake all threads:

synchronized void put(int value) throws InterruptedException {
    while (hasData) {
        wait();
    }
    data[0] = value;
    hasData = true;
    notifyAll(); // Wake ALL waiting threads
}
```

---

### Category 4: Advanced Concepts

#### Question 9: getClass() vs instanceof
```
Q: When should you use getClass() comparison vs instanceof in equals()?
   What are the implications of each choice?

Expected Answer:

getClass() comparison:
- Strict type matching (exact class only)
- Safer for inheritance hierarchies
- Preserves symmetry with subclasses
- Use when: Class has subclasses that add value-significant fields

instanceof check:
- Allows comparison with subclasses
- Follows Liskov Substitution Principle
- Can cause symmetry violations if subclass adds fields
- Use when: Class is final, or subclasses don't add significant fields

Example of the problem:
class Point {
    int x, y;
    boolean equals(Object obj) {
        if (!(obj instanceof Point)) return false; // Uses instanceof
        Point p = (Point) obj;
        return x == p.x && y == p.y;
    }
}

class ColorPoint extends Point {
    String color;
    boolean equals(Object obj) {
        if (!(obj instanceof ColorPoint)) return false;
        ColorPoint cp = (ColorPoint) obj;
        return super.equals(obj) && color.equals(cp.color);
    }
}

Point p = new Point(1, 2);
ColorPoint cp = new ColorPoint(1, 2, "red");
p.equals(cp); // true (Point.equals accepts ColorPoint)
cp.equals(p); // false (ColorPoint.equals rejects Point)
// SYMMETRY VIOLATED!

Solution: Use getClass() in both, or use composition instead of inheritance
```

---

#### Question 10: Object Identity vs Equality
```
Q: Explain the difference between identity and equality in Java.
   When would you use == vs .equals()?

Expected Answer:

Identity (==):
- Checks if two references point to same object in memory
- Uses memory address comparison
- Always safe for primitives
- Safe for null checks

Equality (.equals()):
- Checks if two objects are logically equivalent
- Default implementation uses identity (==)
- Should be overridden for value-based classes
- Must follow equals() contract

When to use ==:
1. Comparing primitives
2. Checking for null
3. Comparing enum values
4. Intentional identity check (singleton pattern)
5. Performance-critical code with interned strings

When to use .equals():
1. Comparing object content/values
2. Collection operations (contains, remove, etc.)
3. String comparison (unless interned)
4. Any value-based comparison

Common pitfall:
Integer a = 127;
Integer b = 127;
System.out.println(a == b); // true (cached)

Integer c = 128;
Integer d = 128;
System.out.println(c == d); // false (not cached!)
System.out.println(c.equals(d)); // true (always correct)
```

---

#### Question 11: Design a Value Class
```
Q: Design an immutable Money class with proper Object methods:

Requirements:
- Amount and currency
- Thread-safe
- Can be used in HashMap
- Proper equals/hashCode/toString/compareTo
```

```java
// Expected Answer:
public final class Money implements Comparable<Money> {
    private final BigDecimal amount;
    private final Currency currency;
    
    public Money(BigDecimal amount, Currency currency) {
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null")
                            .setScale(2, RoundingMode.HALF_UP);
        this.currency = Objects.requireNonNull(currency, "Currency cannot be null");
    }
    
    public static Money of(String amount, String currencyCode) {
        return new Money(
            new BigDecimal(amount),
            Currency.getInstance(currencyCode)
        );
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public Currency getCurrency() {
        return currency;
    }
    
    public Money add(Money other) {
        requireSameCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }
    
    private void requireSameCurrency(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException(
                "Currency mismatch: " + currency + " vs " + other.currency);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return amount.compareTo(money.amount) == 0 && // Use compareTo for BigDecimal!
               currency.equals(money.currency);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(amount.stripTrailingZeros(), currency);
    }
    
    @Override
    public String toString() {
        return currency.getSymbol() + amount.toPlainString();
    }
    
    @Override
    public int compareTo(Money other) {
        requireSameCurrency(other);
        return amount.compareTo(other.amount);
    }
}
```

---

#### Question 12: finalize() Resurrection Attack
```
Q: Explain the "finalizer attack" and how to prevent it.

Expected Answer:

The Attack:
```java
public class SecureClass {
    public SecureClass() {
        // Security check
        if (!isAuthorized()) {
            throw new SecurityException("Not authorized!");
        }
    }
}

// Attacker's code:
public class MaliciousSubclass extends SecureClass {
    static MaliciousSubclass instance;
    
    public MaliciousSubclass() {
        super(); // Throws SecurityException
    }
    
    @Override
    protected void finalize() {
        instance = this; // RESURRECTION!
    }
    
    public static void attack() {
        try {
            new MaliciousSubclass();
        } catch (SecurityException e) {
            // Expected
        }
        System.gc();
        System.runFinalization();
        // instance now holds a partially constructed object
        // that bypassed security checks!
    }
}
```

Prevention:
1. Make class final
2. Use a "final finalize" pattern:
```java
public class SecureClass {
    public SecureClass() {
        if (!isAuthorized()) {
            throw new SecurityException();
        }
    }
    
    // Prevent subclass from overriding finalize
    @Override
    protected final void finalize() throws Throwable {
        // Empty or minimal implementation
    }
}
```
3. Use a flag pattern (check in every method)
4. Better: Don't use finalize() at all!
```

---

### Summary Table

| Topic | Key Points to Remember |
|-------|----------------------|
| **getClass()** | Final, native, returns Class<?>, exact type matching |
| **hashCode()** | Contract with equals(), use prime numbers, handle null |
| **equals()** | Reflexive, symmetric, transitive, consistent, null-safe |
| **clone()** | Shallow vs deep, Cloneable interface, prefer copy constructors |
| **toString()** | Should be informative, use StringBuilder, override always |
| **wait/notify** | Must synchronize, use while loops, notifyAll() safer |
| **finalize()** | Deprecated, use AutoCloseable, Cleaner API instead |

---

**Document Version:** 2.0  
**Last Updated:** 2024  
**Java Version Reference:** Java 8 - 17