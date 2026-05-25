# Interview Question Bank (Consolidated)

**Candidate:** Pratham Goswami  
**Organization:** EPAM Systems Campus Training  
**Note:** This is a regenerated, topic-wise consolidation of all interview questions captured across multiple sessions. No questions have been removed. Follow-up / probe questions are listed as sub-bullets under the parent question.

---

## Table of Contents

1. [Self Introduction & Behavioral](#1-self-introduction--behavioral)
2. [Testing Fundamentals & STLC](#2-testing-fundamentals--stlc)
3. [Defect Management](#3-defect-management)
4. [Test Design Techniques](#4-test-design-techniques)
5. [Testing Types & Levels](#5-testing-types--levels)
6. [Core Java – OOPs](#6-core-java--oops)
7. [Core Java – Keywords & Modifiers](#7-core-java--keywords--modifiers)
8. [Core Java – Constructors](#8-core-java--constructors)
9. [Core Java – Overloading & Overriding / Polymorphism](#9-core-java--overloading--overriding--polymorphism)
10. [Core Java – Abstract Class vs Interface](#10-core-java--abstract-class-vs-interface)
11. [Core Java – Strings](#11-core-java--strings)
12. [Exception Handling](#12-exception-handling)
13. [Collections Framework](#13-collections-framework)
14. [Comparable & Comparator](#14-comparable--comparator)
15. [Java 8+ Features](#15-java-8-features)
16. [Streams & Optional](#16-streams--optional)
17. [Coding / Output Prediction Problems](#17-coding--output-prediction-problems)
18. [Design Patterns](#18-design-patterns)
19. [SOLID Principles & Clean Code](#19-solid-principles--clean-code)
20. [TestNG](#20-testng)
21. [REST Assured / API Testing](#21-rest-assured--api-testing)
22. [Git](#22-git)
23. [Maven & Build Tools](#23-maven--build-tools)
24. [Tools – SonarQube](#24-tools--sonarqube)
25. [Hidden / Implicit Follow-Up Concepts](#25-hidden--implicit-follow-up-concepts)

---

## 1. Self Introduction & Behavioral

1. Can you please introduce yourself?
2. How do you prepare after a weak interview performance?
3. How do you improve explanation and communication skills during interviews?
4. How important is confidence and energy during interviews?
5. Do you have any questions for me?

---

## 2. Testing Fundamentals & STLC

1. What is STLC (Software Testing Life Cycle)?
2. Can you briefly explain the Software Testing Life Cycle (STLC)?
3. What are the phases of STLC?
4. What would you do if some part of the requirement is unclear during requirement analysis?
5. What do you do in the requirement analysis phase if the requirements are unclear?
6. Who is the "user" you seek clarification from regarding requirements?
7. What is a Requirement Traceability Matrix (RTM)?
8. What does the Requirement Traceability Matrix (RTM) consist of, and is it possible to have a complete RTM during the initial analysis phase?
9. What does it mean for a test case to be "atomic"?
10. What do you mean by "partially passed" or "partially failed" in the context of atomicity?
11. What is the "Blocked" status in test case execution?
12. Can a test case become blocked due to internal application dependency?

---

## 3. Defect Management

1. How do you report a defect effectively?
2. What are the components of a good defect report?
3. Why are screenshots/videos attached in defect reports?
4. What are “Steps to Reproduce” in a bug report?
5. Why is environment information important in defect reporting?
6. What would you do if a defect is rejected as “Not a Valid Scenario”?
7. Who should you approach if there is a disagreement between QA and Development regarding a defect?
8. What is the defect life cycle?
9. What is the "deferred" status in the defect life cycle?
10. Define Severity and Priority.
11. Can you provide an example of a Low Severity / High Priority defect?

---

## 4. Test Design Techniques

1. What are the different test design techniques?
2. What is Equivalence Class Partitioning?
3. Explain Equivalence Partitioning using an example.
4. What are the benefits of Equivalence Partitioning?
5. What is Equivalence Class Partitioning, and do you apply it while writing or executing test cases?

---

## 5. Testing Types & Levels

1. What is the Test Pyramid?
2. What are the different testing levels (Testing Pyramid)?
3. What is non-functional testing?
4. What is static and dynamic testing?
5. What is smoke and sanity testing?
6. What is exploratory testing?
7. How does exploratory testing differ from user acceptance testing?
8. Is User Acceptance Testing (UAT) mandatory, and who generally performs it?

---

## 6. Core Java – OOPs

1. Can you briefly explain the four main pillars of OOPs?
2. Can you tell me what is is-a and has-a relationship?
3. What is meant by has-a relationship and is-a relationship?
4. Any example for weak coupling?
5. How will you inject that dependency?
6. What is the difference between aggregation and composition?
7. Can you explain the concept of encapsulation?
8. Why do we have to go for encapsulation? Any practical use case?
9. You have two variables — employee number and employee salary. How will you create an encapsulation for this in a Java class?
10. Why are we declaring them private and creating getter/setter methods? What benefit are we achieving?
11. How do you restrict an outside class from modifying an instance variable to an invalid value if you are using public setters?
12. Is multiple inheritance allowed in Java?
13. What is the "Diamond Problem"?
14. What is runtime polymorphism?
15. Can you give an example of runtime polymorphism?
16. If you have `Parent p = new Child();` and call a method overridden in the child, what is the output?
17. What are covariant return types?

---

## 7. Core Java – Keywords & Modifiers

1. What is the role of the `super` keyword?
2. What is the role of the `static` keyword?
3. What are the things that can use the `static` keyword?
4. Is it possible to create a static class?
5. What is the role of the `final` keyword?
6. Where can `final` be applied or used?
7. How do you prevent a class from being inherited?
8. What is the default access modifier in Java?
9. What if a default-access member is accessed from outside the package?
10. Will the default modifier allow access for a subclass from a different package?
11. What are the rules for access modifiers when overriding a method? What is the reasoning behind not being allowed to decrease visibility?
12. Can you explain `System.out.println`?
13. What are static imports in Java?
14. Why do we use static import in REST Assured?

---

## 8. Core Java – Constructors

1. What is the job of a constructor?
2. What is the difference between interface and abstract class with respect to a constructor?
3. Why is a constructor not applicable for an interface?
4. Can we override the constructor?
5. Can abstract classes have constructors?
6. Why does an abstract class have a constructor if we cannot create its object?
7. If you can't instantiate an abstract class, what is the purpose of its constructor?

---

## 9. Core Java – Overloading & Overriding / Polymorphism

1. Can we overload the `final` method?
2. Can we overload the `main` method?
3. What are the rules for method overloading?
4. What are the criteria for method overloading?
5. Does return type matter in method overloading?
6. Does order of parameters matter in method overloading?
7. Explain method overriding with an example.
8. What will be the output of: `Parent p = new Child(); p.method();`
9. What happens if the method does not exist in the parent class?
10. In method overriding, should parent and child return types be same?
11. Will this overriding example compile successfully or not? (with code snippet)
12. What would be the output for overloaded methods?
13. What would be the output for parent-child overriding example?

---

## 10. Core Java – Abstract Class vs Interface

1. What is the difference between an abstract class and an interface?
2. When should you use an abstract class over an interface (and vice versa)?
3. Can an abstract class contain concrete methods?
4. Can an abstract class contain only concrete methods?
5. Is it mandatory for an abstract class to have abstract methods?
6. What is the point of an abstract class if it only contains concrete methods?
7. Can we create an object of an abstract class?
8. Why were default methods introduced in interfaces in Java 8?
9. Why were default methods and static methods introduced in Java 8?

---

## 11. Core Java – Strings

1. What is the difference between `String`, `StringBuffer`, and `StringBuilder`?
2. What is the main advantage of `StringBuffer`?
3. What will be the output of String comparison examples using `==` and `.equals()`?
4. Difference between String pool references and object references.

---

## 12. Exception Handling

1. What is the exception hierarchy?
2. What is the difference between errors and exceptions?
3. What are the different types of exceptions?
4. Difference between Checked and Unchecked exceptions.
5. What are the different ways of handling exceptions?
6. What is the difference between try-catch and the `throws` keyword?
7. What is the main role of `throw` vs `throws`?
8. What is the purpose of the `throws` keyword?
9. Does `throws` handle exceptions?
10. What happens if a `throws` exception is never handled and reaches the JVM?
11. What is the `finally` block and what is its use?
12. Can you explain custom / user-defined exceptions?
13. How do you generate / create a custom exception?
14. Have you used / created any custom exceptions? What was the logic?
15. How do you create a custom (user-defined) exception, and what is the functional purpose of doing so?
16. Why is `RuntimeException` often extended for custom exceptions?
17. What is the best practice while creating custom exceptions?
18. What would be the output of this code snippet? (exception scenario)
19. What would be the output here? (compile time error / runtime error / output)
20. What would be the output here related to try-catch ordering?
21. What happens at line number 15?
22. What happens at line number 17?

---

## 13. Collections Framework

1. In Java Collections — what interfaces are implemented by `ArrayList` and `LinkedList`?
2. What are the additional methods retrieved via the `Deque` interface?
3. What is the default capacity of an `ArrayList`?
4. What happens internally when you add the 11th element to an `ArrayList`?
5. When would you prefer a standard Array over an `ArrayList`?
6. Why use generics if Collections can technically store heterogeneous objects?
7. What are the properties of `Set`? When would you use it?
8. When will you use `HashSet` vs `LinkedHashSet`?
9. What is the inner logic behind how `LinkedHashSet` preserves insertion order?
10. Is the before/after node logic not available in `HashSet`?
11. What does `LinkedHashSet` use internally? *(doubly linked list)*
12. Tell me one collection class where duplicates are not allowed and insertion order is preserved.
13. When should we use `HashMap`?
14. Can we insert duplicate values into `HashMap`?
15. Can we insert duplicate keys into `HashMap`?
16. What happens if duplicate keys are inserted into `HashMap`?
17. What is the difference between `HashMap` and `LinkedHashMap`?
18. What is `TreeMap`?
19. What is the default sorting order in `TreeMap`?
20. What is the difference between `LinkedHashMap` and `TreeMap`?
21. Internal working of `LinkedHashMap`.
22. Internal working of `TreeMap` (Red-Black Tree).
23. Mutable vs Immutable keys in `HashMap`.
24. Can custom objects be inserted into `TreeSet`?
25. Why must `Comparable` or `Comparator` be implemented for `TreeSet`?
26. If you insert custom objects into a `TreeSet` without implementing `Comparable` or `Comparator`, will it work?
27. What are the different types of iterators you are aware of?
28. What is the difference between `Iterator` and `ListIterator`?
29. What does "fail-fast" mean?
30. What is `ConcurrentModificationException`?

---

## 14. Comparable & Comparator

1. What is `Comparable` and `Comparator`?
2. What is the main difference between `Comparable` and `Comparator`?
3. How can you perform sorting in Maps?
4. Which constructor/interface is used for custom sorting in `TreeMap`?
5. What is the logic behind how `Comparator` works?
6. How does comparison work internally for `int` vs `String`?
7. What are the possible return values from the `compare()` method? *(-1, 0, +1)*
8. What other methods are available in the `Comparator` interface besides `compare()`?

---

## 15. Java 8+ Features

1. What is meant by a Functional Interface?
2. What is a functional interface?
3. Why do we go for functional interfaces when the interface concept already exists? What is the advantage?
4. Which functional interface returns a Boolean response?
5. What are the predefined functional interfaces?
6. Name common Functional Interfaces (`Predicate`, `Supplier`, `Consumer`, etc.).
7. Can you explain the `Consumer` functional interface?
8. When specifically would you use a `Supplier`?
9. What is `BiFunction`?
10. Can you explain what a lambda expression is?
11. What are method references and constructor references?
12. What is a Method Reference?
13. What is a Constructor Reference?
14. What are Records in Java?
15. What are the advantages of using Records?
16. Can you give an example — one inbuilt usage of Records?

---

## 16. Streams & Optional

1. What is the Stream API?
2. What are the advantages and commonly used methods of the Stream API?
3. What are the different operations in streams?
4. What is the use of the `map()` method in streams?
5. What is the difference between `map` and `flatMap` in streams?
6. What is the `filter` method in the Stream API, and what is a Predicate?
7. Stream transformations.
8. What is the `Optional` class?
9. What are Optionals in Java?
10. When should we use `Optional`?
11. How does `Optional` help avoid `NullPointerException`?
12. What is the purpose of the `Optional` class?
13. Any example methods from `Optional`?

---

## 17. Coding / Output Prediction Problems

1. Find the first non-repeating character from a given string. Example: `"Anurag"`.
    - Write the correct import statement for `Map`.
    - Can you explain what the second `for` loop in your code is doing?
    - Can you think of an alternative approach without using `Map` (e.g., using a frequency array of size 26)?
2. Can you write a program for the given requirement?
3. Which package will you import?
4. Can you write this using streams?
5. How will you print the result in descending order?
6. Can you make this class a Singleton class?
7. What is the output order of static blocks and main method?
8. What will be the output of String comparison examples using `==` and `.equals()`?
9. What would be the output of this code snippet?
10. What would be the output here? (compile time / runtime error / output)
11. What would be the output here related to try-catch ordering?
12. What would be the output for overloaded methods?
13. What would be the output for parent-child overriding example?
14. What happens at line number 15?
15. What happens at line number 17?
16. Will this overriding example compile successfully or not?

---

## 18. Design Patterns

1. What design patterns are you aware of?
2. Which design patterns have you used practically?
3. What is the Builder Design Pattern?
4. Where have you used the Builder Design Pattern?
5. In which scenarios should the Builder Pattern be used?
6. What problem does the Builder Pattern solve?
7. How have you used the Builder design pattern (specifically with POJOs and Rest Assured)?
8. Builder methods in Fluent APIs.
9. What is the Factory Design Pattern?
10. What are the different types of Singleton design patterns?
11. What is the Bill Pugh (build flow) Singleton?
12. When should the Singleton Design Pattern be used?
13. What is the Singleton pattern, and what real-world problem does it solve in Selenium automation?
14. Why did you implement Singleton for WebDriver?
15. Why combine Singleton with `ThreadLocal`?
16. Thread safety in Singleton.

---

## 19. SOLID Principles & Clean Code

1. What are SOLID principles?
2. What are the design principles you are aware of?
3. Can you explain SOLID principles?
4. Can you explain the Interface Segregation Principle with a real-life example?
5. Can you explain the Dependency Inversion Principle with a real-life example?
6. Which of the SOLID principles do you find most difficult to understand?
7. Which SOLID principle is hardest to understand for you?
8. Explain the Dependency Inversion Principle.
9. Can you provide an example of Dependency Inversion?
10. How can we maintain clean code? What principles do we follow?
11. What are clean code principles?
12. What is the KISS principle?
13. What is the DRY principle?
14. What is the YAGNI principle?
15. What things would you check during a code review?
16. What is your checklist for performing a code review (SOLID, DRY, KISS, naming conventions, etc.)?

---

## 20. TestNG

1. What is the order of TestNG annotations?  
   (`@BeforeSuite`, `@BeforeTest`, `@BeforeClass`, `@BeforeMethod`, `@Test`, `@AfterMethod`, `@AfterClass`, `@AfterTest`, `@AfterSuite`)
2. What is the difference between `@BeforeTest` and `@BeforeMethod`?
3. Difference between `@BeforeClass` and `@BeforeTest`?
4. What is the `failed-testng.xml` file and where is it created?
5. How do you pass test data in `testng.xml`?
6. Explain the structure of `testng.xml`.
7. What is a DataProvider?
8. What is the return type of a DataProvider?
9. How do you connect a DataProvider from another class?
10. If a DataProvider is in a different class than the test method, how do you link them?
11. How can you run a test case multiple times?
12. How can you skip a test case?
13. If priorities are -10, -5, and 0, which test executes first?
14. If test case 2 depends on test case 1 and test case 1 fails, what will be the status of test case 2?
15. If you still want to execute dependent test case 2 even after failure of test case 1, how will you do it?
16. What are TestNG listeners?
17. What listeners are you aware of?
18. Which TestNG listeners have you used?
19. What are Listeners, and which ones have you used (e.g., `ITestListener`, `IRetryAnalyzer`)?
20. In which scenarios have you used `ITestListener`?
21. How do you configure / "attach" listeners in framework / TestNG?
22. Can you explain how `IReporter` is used?
23. What is `RetryAnalyzer` in TestNG?

---

## 21. REST Assured / API Testing

1. Why do we perform API testing?
2. What is the importance of API testing if users interact through UI?
3. How can API testing help with security validation?
4. What are the different types of authentication methods available in API testing?
5. What is OAuth authentication?
6. What is the difference between a path parameter and a query parameter?
7. What is serialization and deserialization?
8. What is `RequestSpecBuilder`?
9. `RequestSpecification` & `ResponseSpecification` in REST Assured.
10. What is `given()` in REST Assured?
11. Is `given()` a method?
12. What does `given()` return?
13. Why do we use static import in REST Assured?
14. How do you validate response time in REST Assured?
15. Which method helps validate the schema in REST Assured?
16. What is the exact method name to validate schema?
17. How do you send a POST request using REST Assured?
18. Can you write code for a POST request using Rest Assured?
19. Convert a JSON payload into POJO classes.
20. How many POJO classes will you create for this JSON body?
21. What would be the names of those POJO classes?
22. Can you declare the `University` class?
23. Can you now write the POST request code?
24. Task: Create a POJO structure from a provided JSON body (`Employee`, `Address`, `Projects`).
25. Task: Write the code to send a POST request using that POJO.
26. How do you validate response fields in REST Assured?
27. How will you validate the department ID from the response POJO?
28. How do you validate that a specific field in the JSON response is not null using Hamcrest matchers?
29. API response validation using Hamcrest matchers.
30. What is the main difference between response code `401` and `403`?
31. Difference between HTTP 401 and 403?
32. When do we get the HTTP 405 status code?

---

## 22. Git

1. What is the regular Git workflow?
2. Which Git commands have you used?
3. What is the difference between `git pull` and `git fetch`?
4. What is `git stash`?
5. What is `git checkout`?
6. What is `git rebase`?
7. What is Git Rebase?
8. Are you aware of cherry-pick?

---

## 23. Maven & Build Tools

1. Why do we use Maven?
2. What are Maven goals?
3. What are the stages of the Maven lifecycle?
4. What is stored in the `.m2` folder?
5. What is the use / purpose of the Surefire plugin?
6. What is a Transitive Dependency?
7. What is the difference between a dependency and a transitive dependency?

---

## 24. Tools – SonarQube

1. Do you know about SonarQube?
2. What issues will SonarQube raise in this program?

---

## 25. Hidden / Implicit Follow-Up Concepts

These were indirectly tested during follow-up questioning:

1. Runtime Polymorphism
2. Static Imports in Java
3. Covariant Return Types
4. Builder Methods in Fluent APIs
5. Thread Safety in Singleton
6. `RequestSpecification` & `ResponseSpecification` in REST Assured
7. Comparable vs Comparator
8. Mutable vs Immutable Keys in HashMap
9. Internal working of TreeMap (Red-Black Tree)
10. Internal working of LinkedHashMap
11. Stream transformations
12. API response validation using Hamcrest Matchers
