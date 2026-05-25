Interviewer Questions Only
Can you please introduce yourself?
Can you just tell me what is is-a and has-a relationship?
How will you inject that dependency?
What is the difference between aggregation and composition?
What is the difference between interface and abstract class with respect to a constructor?
What is the job of a constructor?
Why is constructor not applicable for interface?
Can we override the constructor?
Can we overload the final method?
Can we overload the main method?
Can you explain System.out.println?
What is the exception hierarchy?
What is finally block and what is its use?
Can you explain custom/user-defined exceptions?
How do you generate a custom exception?
What is the best practice while creating custom exceptions?
What would be the output of this code snippet?
What would be the output here? (compile time error / runtime error / output)
What would be the output here related to try-catch ordering?
What would be the output for overloaded methods?
What would be the output for parent-child overriding example?
What happens at line number 15?
What happens at line number 17?
Will this overriding example compile successfully or not?
Tell me one collection class where duplicates are not allowed and insertion order is preserved.
What is the difference between HashMap and LinkedHashMap?
What is TreeMap?
What is the default sorting order in TreeMap?
What is Comparable and Comparator?
What is a functional interface?
What are the predefined functional interfaces?
Can you explain Consumer functional interface?
What is the difference between map and flatMap in streams?
What is Optional class?
What are the different operations in streams?
What are TestNG listeners?
How do you configure listeners in framework/TestNG?
What is a DataProvider?
What is the return type of DataProvider?
How can you run a test case multiple times?
How can you skip a test case?
If priorities are -10, -5, and 0, which test executes first?
If test case 2 depends on test case 1 and test case 1 fails, what will be the status of test case 2?
If you still want to execute dependent test case 2 even after failure of test case 1, how will you do it?
What is git pull and git fetch?
What is git stash?
What is git checkout?
What is git rebase?
Are you aware of cherry-pick?
What is STLC?
What are the phases of STLC?
What is Test Pyramid?
What is non-functional testing?
What is static and dynamic testing?
What is smoke and sanity testing?
What are the test design techniques?
What are Maven goals?
What is the use/purpose of Surefire plugin?
What is Transitive Dependency?
What are SOLID principles?
Can you explain Interface Segregation Principle with a real-life example?
Can you explain Dependency Inversion Principle with a real-life example?
What is Builder Design Pattern?
What is Factory Design Pattern?
Can you write a program for the given requirement?
Which package will you import?
Do you know about SonarQube?
What issues will SonarQube raise in this program?
Can you write this using streams?
How will you print the result in descending order?
Can you make this class a Singleton class?
Can you write code for a POST request using Rest Assured?
How many POJO classes will you create for this JSON body?
What would be the names of those POJO classes?
Can you declare the University class?
Can you now write the POST request code?
How will you validate the department ID from the response POJO?
Do you have any questions for me?


# INTERVIEW ASSESSMENT - QUESTIONS (TOPIC-WISE)

**Candidate:** Pratham Goswami  
**Organization:** EPAM Systems Campus Training

---

# 1. CORE JAVA (18 Questions)

## Q1. What is meant by has-a relationship and is-a relationship?
- Any example for weak coupling?

## Q2. Can you explain the concept of encapsulation?
- Why do we have to go for encapsulation? Any practical use case?
- You have two variables — employee number and employee salary.  
  How will you create an encapsulation for this in a Java class?
- Why are we declaring them private and creating getter/setter methods?  
  What benefit are we achieving?

## Q3. What is the role of the `super` keyword?

## Q4. What is the default access modifier in Java?
- What if it is accessed from outside the package?
- Will it allow access for a subclass from a different package?

## Q5. What is runtime polymorphism?
- Can you give an example?

## Q6. What is the role of the `static` keyword?
- What are the things that can use the `static` keyword?
- Is it possible to create a static class?

## Q7. What is the role of the `final` keyword?
- Where can it be applied or used?

## Q8. What is the difference between errors and exceptions?

## Q9. What are the different types of exceptions?

## Q10. What are the different ways of handling exceptions?
- What is the main role of `throw` vs `throws`?

## Q11. How do you create a custom exception?
- Have you created any custom exceptions? What was the logic?

## Q12. What is the difference between `String`, `StringBuffer`, and `StringBuilder`?
- What is the main advantage of `StringBuffer`?

## Q13. In Java Collections — what interfaces are implemented by `ArrayList` and `LinkedList`?
- What are the additional methods retrieved via the `Deque` interface?

## Q14. What are the properties of `Set`? When would you use it?
- When will you use `HashSet` vs `LinkedHashSet`?
- What is the inner logic behind how `LinkedHashSet` preserves insertion order?
- Is the before/after node logic not available in `HashSet`?
- What does `LinkedHashSet` use internally?  
  *(Interviewer clarified: it uses a doubly linked list)*

## Q15. What are the different types of iterators you are aware of?

## Q16. How can you perform sorting in Maps?
- Which constructor/interface is used for custom sorting in `TreeMap`?
- What is the logic behind how `Comparator` works?
- How does comparison work internally for `int` vs `String`?
- What are the possible return values from the `compare()` method?  
  *(Interviewer clarified: -1, 0, or +1)*
- What other methods are available in the `Comparator` interface besides `compare()`?

---

# 2. JAVA 8 FEATURES (6 Questions)

## Q1. What is meant by a Functional Interface?
- Why do we go for functional interfaces when the interface concept already exists? What is the advantage?
- Which functional interface returns a Boolean response?

## Q2. Can you explain what a lambda expression is?

## Q3. What are method references and constructor references?

## Q4. What are Optionals in Java?
- Any example methods from `Optional`?

## Q5. What is the Stream API? What are its advantages and commonly used methods?

## Q6. What are Records in Java?
- What are the advantages of using Records?
- Can you give an example — one inbuilt usage of Records?

---

# 3. CODING PROBLEM (1 Problem)

## Q1. Find the first non-repeating character from a given string  
Example: `"Anurag"`

- Write the correct import statement for `Map`.
- Can you explain what the second `for` loop in your code is doing?
- Can you think of an alternative approach without using `Map`  
  *(e.g., using a frequency array of size 26)?*

---

# 4. REST ASSURED / API TESTING (7 Questions)

## Q1. What are the different types of authentication methods available in API testing?
- What is OAuth authentication?

## Q2. What is the difference between a path parameter and a query parameter?

## Q3. How do you validate response time in REST Assured?

## Q4. Which method helps validate the schema in REST Assured?
- What is the exact method name?

## Q5. What is `RequestSpecBuilder`?

## Q6. What is serialization and deserialization?

## Q7. What is the main difference between response code `401` and `403`?

---

# 5. DESIGN PATTERNS & PRINCIPLES (5 Questions)

## Q1. What design patterns are you aware of?

## Q2. What is the Builder design pattern?

## Q3. What are the different types of Singleton design patterns?
- What is the Bill Pugh (build flow) Singleton?

## Q4. What are the design principles you are aware of?
- Can you explain SOLID principles?

## Q5. How can we maintain clean code? What principles do we follow?

---

# 6. TESTNG (5 Questions)

## Q1. What is the order of TestNG annotations?
Example:
- `@BeforeSuite`
- `@BeforeTest`
- `@BeforeClass`
- `@BeforeMethod`
- `@Test`
- `@AfterMethod`
- `@AfterClass`
- `@AfterTest`
- `@AfterSuite`

## Q2. What is the difference between `@BeforeTest` and `@BeforeMethod`?

## Q3. What is the `failed-testng.xml` file and where is it created?

## Q4. How do you pass test data in `testng.xml`?

## Q5. What listeners are you aware of?
- Can you explain how `IReporter` is used?

---

# 7. FUNCTIONAL TESTING CONCEPTS (4 Questions)

## Q1. What is exploratory testing?
- How does exploratory testing differ from user acceptance testing?

## Q2. What is a Requirement Traceability Matrix (RTM)?

## Q3. What is the defect life cycle?

## Q4. What are the different test design techniques?

---

# 8. GIT, MAVEN & BUILD TOOLS (4 Questions)

## Q1. What is the regular Git workflow?

## Q2. What is the difference between `git pull` and `git fetch`?

## Q3. What are the stages of the Maven lifecycle?
- What is stored in the `.m2` folder?

## Q4. What is the difference between a dependency and a transitive dependency?

---
# Testing Fundamentals

1. What would you do if some part of the requirement is unclear during requirement analysis?
2. What is the “Blocked” status in test case execution?
3. Can a test case become blocked due to internal application dependency?
4. How do you report a defect effectively?
5. What are the components of a good defect report?
6. Why are screenshots/videos attached in defect reports?
7. What are “Steps to Reproduce” in a bug report?
8. Why is environment information important in defect reporting?
9. What would you do if a defect is rejected as “Not a Valid Scenario”?
10. Who should you approach if there is a disagreement between QA and Development regarding a defect?
11. What is Equivalence Class Partitioning?
12. Explain Equivalence Partitioning using an example.
13. What are the benefits of Equivalence Partitioning?

---

# Core Java

## OOP / Abstraction

14. What is the difference between an abstract class and an interface?
15. Can an abstract class contain concrete methods?
16. Can an abstract class contain only concrete methods?
17. Can we create an object of an abstract class?
18. Why does an abstract class have a constructor if we cannot create its object?
19. Why were default methods introduced in interfaces in Java 8?

---

## Method Overloading & Overriding

20. What are the rules for method overloading?
21. What are the criteria for method overloading?
22. Does return type matter in method overloading?
23. Does order of parameters matter in method overloading?
24. Explain method overriding with an example.
25. What will be the output of:
    `Parent p = new Child(); p.method();`
26. What happens if the method does not exist in the parent class?
27. In method overriding, should parent and child return types be same?

---

## Exception Handling

28. What is the difference between try-catch and throws keyword?
29. What is the purpose of the throws keyword?
30. Does throws handle exceptions?
31. Have you used custom exceptions?
32. How do you create a custom exception?
33. Why is RuntimeException often extended for custom exceptions?

---

# Collections Framework

34. When should we use HashMap?
35. Can we insert duplicate values into HashMap?
36. Can we insert duplicate keys into HashMap?
37. What happens if duplicate keys are inserted into HashMap?
38. Can custom objects be inserted into TreeSet?
39. Why must Comparable or Comparator be implemented for TreeSet?
40. What is the difference between LinkedHashMap and TreeMap?
41. What is the difference between Iterator and ListIterator?
42. What does “fail-fast” mean?
43. What is ConcurrentModificationException?
44. How can you prevent a class from being inherited?

---

# Java 8

45. When should we use Optional?
46. How does Optional help avoid NullPointerException?
47. What is Stream API?
48. What is the use of the map() method in streams?
49. What is BiFunction?
50. What is Method Reference?
51. What is Constructor Reference?

---

# Design Patterns

52. Which design patterns have you used practically?
53. Where have you used the Builder Design Pattern?
54. In which scenarios should Builder Pattern be used?
55. What problem does Builder Pattern solve?
56. When should Singleton Design Pattern be used?
57. Why did you implement Singleton for WebDriver?
58. Why combine Singleton with ThreadLocal?

---

# Clean Code / SOLID Principles

59. What things would you check during a code review?
60. What are clean code principles?
61. What is the KISS principle?
62. What is the DRY principle?
63. What is the YAGNI principle?
64. Which SOLID principle is hardest to understand for you?
65. Explain Dependency Inversion Principle.

---

# TestNG

66. Which TestNG listeners have you used?
67. In which scenarios have you used ITestListener?
68. What is RetryAnalyzer in TestNG?
69. How do you connect a DataProvider from another class?
70. Difference between @BeforeClass and @BeforeTest?
71. Explain the structure of testng.xml.

---

# Git

72. Which Git commands have you used?
73. What is Git Rebase?

---

# Maven

74. Why do we use Maven?
75. What is a Transitive Dependency?

---

# REST Assured / API Testing

76. Convert a JSON payload into POJO classes.
77. How do you send a POST request using REST Assured?
78. How do you validate response fields in REST Assured?
79. What is `given()` in REST Assured?
80. Is `given()` a method?
81. Why do we use static import in REST Assured?
82. What does `given()` return?
83. When do we get HTTP 405 status code?
84. Difference between HTTP 401 and 403?
85. Why do we perform API testing?
86. What is the importance of API testing if users interact through UI?
87. How can API testing help with security validation?

---

# Java Output / Code Prediction Questions

88. What is the output order of static blocks and main method?
89. What will be the output of String comparison examples using `==` and `.equals()`?
90. Difference between String pool references and object references.

---

# Behavioral / Self-Reflection Questions

91. How do you prepare after a weak interview performance?
92. How do you improve explanation and communication skills during interviews?
93. How important is confidence and energy during interviews?

---

# Hidden Follow-Up Concepts Asked Implicitly

These were indirectly tested during follow-up questioning:

94. Runtime Polymorphism
95. Static Imports in Java
96. Covariant Return Types
97. Builder Methods in Fluent APIs
98. Thread Safety in Singleton
99. RequestSpecification & ResponseSpecification in REST Assured
100. Comparable vs Comparator
101. Mutable vs Immutable Keys in HashMap
102. Internal working of TreeMap (Red-Black Tree)
103. Internal working of LinkedHashMap
104. Stream transformations
105. API response validation using Hamcrest Matchers

1. Testing Fundamentals & STLC
STLC: Can you briefly explain the Software Testing Life Cycle (STLC)?
Requirements: What do you do in the requirement analysis phase if the requirements are unclear?
Stakeholders: Who is the "user" you seek clarification from regarding requirements?
RTM: What does the Requirement Traceability Matrix (RTM) consist of, and is it possible to have a complete RTM during the initial analysis phase?
Test Case Design:
What does it mean for a test case to be "atomic"?
What do you mean by "partially passed" or "partially failed" in the context of atomicity?
Defect Life Cycle: What is the "deferred" status in the defect life cycle?
Severity vs. Priority:
Define Severity and Priority.
Can you provide an example of a Low Severity/High Priority defect?
Test Design Techniques: What is Equivalence Class Partitioning, and do you apply it while writing or executing test cases?
Testing Levels: What are the different testing levels (Testing Pyramid)?
UAT: Is User Acceptance Testing (UAT) mandatory, and who generally performs it?
2. Core Java & OOPs
OOPs Concepts: Can you briefly explain the four main pillars of OOPs?
Encapsulation: How do you restrict an outside class from modifying an instance variable to an invalid value if you are using public setters?
Inheritance:
Is multiple inheritance allowed in Java?
What is the "Diamond Problem"?
Abstract Classes vs. Interfaces:
What is the difference between an abstract class and an interface? When should you use one over the other?
Is it mandatory for an abstract class to have abstract methods?
What is the point of an abstract class if it only contains concrete methods?
Polymorphism:
If you have Parent p = new Child(); and call a method overridden in the child, what is the output?
What are covariant return types?
What are the rules for access modifiers when overriding a method? What is the reasoning behind not being allowed to decrease visibility?
Keywords:
Can abstract classes have constructors? If you can't instantiate them, what is the purpose of the constructor?
How do you prevent a class from being inherited? (The final keyword).
3. Java 8+ Features & Collections
Interfaces: Why were default methods and static methods introduced in Java 8?
Exception Handling:
Difference between try-catch and the throws keyword.
What happens if a throws exception is never handled and reaches the JVM?
Difference between Checked and Unchecked exceptions.
How do you create a custom (user-defined) exception, and what is the functional purpose of doing so?
Collections Framework:
Difference between Iterator and ListIterator.
Can you insert duplicate keys in a HashMap? What happens if you try?
Difference between LinkedHashMap and TreeMap.
If you insert custom objects into a TreeSet without implementing Comparable or Comparator, will it work?
What is the main difference between Comparable and Comparator?
ArrayList Internals:
What is the default capacity of an ArrayList?
What happens internally when you add the 11th element?
When would you prefer a standard Array over an ArrayList?
Generics: Why use generics if Collections can technically store heterogeneous objects?
Functional Programming:
What are Method References and Constructor References?
Name common Functional Interfaces (Predicate, Supplier, Consumer, etc.).
When specifically would you use a Supplier?
What is the filter method in the Stream API, and what is a Predicate?
What is the purpose of the Optional class?
4. Design Patterns & Principles
Creational Patterns:
How have you used the Builder design pattern (specifically with POJOs and Rest Assured)?
What is the Singleton pattern, and what real-world problem does it solve in Selenium automation?
SOLID Principles:
Which of the SOLID principles do you find most difficult to understand?
Can you provide an example of Dependency Inversion?
Code Quality: What is your checklist for performing a code review (SOLID, DRY, KISS, naming conventions, etc.)?
5. Automation Tools (TestNG & Rest Assured)
TestNG:
What are Listeners, and which ones have you used (e.g., ITestListener, IRetryAnalyzer)?
How do you "attach" a listener to your execution?
Difference between @BeforeClass and @BeforeTest.
If a DataProvider is in a different class than the test method, how do you link them?
Rest Assured (Practical Task):
Task: Create a POJO structure from a provided JSON body (Employee, Address, Projects).
Task: Write the code to send a POST request using that POJO.
How do you validate that a specific field in the JSON response is not null using Hamcrest matchers?