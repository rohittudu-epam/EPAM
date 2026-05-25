# Hands-On Task: Element Locators in Selenium

In this task, you will master the use of various Selenium WebDriver locators to identify and interact with web elements. Additionally, you will learn techniques to handle dynamic elements and create robust locators using advanced XPath and CSS Selector best practices.

---

## Objectives

By completing this hands-on task, you will:

1. **Explore and Work with Locators**:
    - Use Selenium locators to identify web elements, including:
        - ID
        - Name
        - Class Name
        - Tag Name
        - Link Text
        - Partial Link Text
        - XPath
        - CSS Selector

2. **Handle Dynamic Web Elements**:
    - Learn strategies for handling dynamic attributes such as changing IDs, classes, and text content.
    - Create stable and reusable locators using advanced XPath and CSS Selector techniques.

3. Implement best practices for writing efficient and robust locators to improve test reliability and minimize maintenance.

---

## Prerequisites

The following are already configured in your provided Maven project:

1. Selenium WebDriver dependency (pre-installed in `pom.xml`).
2. Maven project structure (`src/test/java`).
3. Basic TestNG setup.

Before starting, ensure you understand the basics of Selenium locators, HTML structure, and attributes.

### Practice Websites:
- https://demoqa.com/text-box
- https://demoqa.com/links

---

## Instructions

### 1. Implement the Test Class

- **Location**: Create the test class inside the `src/test/java/com.example.locators` package.
- **Steps**:
    1. Create a package named `com.example.locators` (if it does not already exist).
    2. Inside this package, create a new test class named `ElementLocatorTests`.

#### Test Class Details:

1. **Test Class Name**: `ElementLocatorTests`

2. **Methods to Implement**:

    - **`testById()`**:
        - Purpose: Identify an element using the `id` attribute and perform a simple action (e.g., input text, click).
        - Annotation: Use `@Test` to signify this test method.

    - **`testByName()`**:
        - Purpose: Locate an element using the `name` attribute and verify or interact with it.

    - **`testByClassName()`**:
        - Purpose: Use the `className` locator to identify and interact with elements.

    - **`testByTagName()`**:
        - Purpose: Locate elements using their tag name (e.g., `<input>`, `<h1>`) and validate their properties.

    - **`testByLinkText()`**:
        - Purpose: Use `linkText` to locate hyperlinks by their exact text content.

    - **`testByPartialLinkText()`**:
        - Purpose: Use `partialLinkText` to locate hyperlinks by matching parts of their text content.

    - **`testByXPath()`**:
        - Purpose: Use XPath expressions to locate elements based on structure patterns, attributes, and relationships. Demonstrate:
            - Absolute XPath
            - Relative XPath with attributes
            - XPath functions (e.g., `contains`, `starts-with`)
            - XPath for navigating hierarchical structures (e.g., parent/child).

    - **`testByCssSelector()`**:
        - Purpose: Use CSS Selectors to identify elements by CSS rules. Demonstrate:
            - Attribute-based selectors
            - Combiner selectors (e.g., child, adjacent sibling)
            - Class and ID selectors.

---

### 2. Handle Dynamic Elements

#### Dynamic Locators:

1. Identify elements with attributes that change dynamically (e.g., IDs generated with random numbers or classes added/removed dynamically).
2. Update the locator strategy to handle dynamic attributes using:
    - XPath functions:
        - `contains()` to match partial attribute values.
        - `starts-with()` to match attributes with common prefixes.
    - CSS Selectors:
        - Attribute partial matches (e.g., `[attribute*=value]`).

#### Test Method for Dynamic Elements:

- **`testDynamicElement()`**:
    - Purpose: Handle dynamic elements intelligently and ensure element stability during interaction.
    - Strategies:
        - Use XPath or CSS to locate elements with variable attributes.
        - Validate the locator with assertions to check its correctness.

---

### 3. Execute Test Cases

#### Steps to Execute the Tests:

1. **Run the Default TestNG Configuration**:
    - Execute the `ElementLocatorTests` class through the `testng.xml` file or directly from IntelliJ IDEA.

2. **Verify Locators**:
    - Confirm that each locator correctly identifies the web element it is intended for.

3. **Analyze Locator Robustness**:
    - Ensure that dynamic locators remain stable even when attributes change or elements move in the DOM.

---

## Deliverables

By completing this task, you should have:

1. **ElementLocatorTests Class**:
    - Implemented test methods for each locator:
        - ID
        - Name
        - Class Name
        - Tag Name
        - Link Text
        - Partial Link Text
        - XPath
        - CSS Selector

2. **Dynamic Element Handling**:
    - A test method demonstrating robust XPath and CSS Strategies for dynamic elements.

3. **Verified Execution**:
    - Tests executed successfully with stable locators across all locator types.

---

## Additional Information

1. **Locator Best Practices**:
    - Always use locators that uniquely identify an element to avoid ambiguity.
    - Prefer ID and Name locators for simplicity and speed when available.
    - Use XPath and CSS Selectors for complex structures and dynamic elements.

2. **Dynamic Elements**:
    - Review common DOM patterns with dynamic attributes (e.g., auto-generated IDs, changing classes) and how to handle them.

3. **Useful Documentation**:
    - Selenium Locators: [Selenium Locators Documentation](https://www.selenium.dev/documentation/en/webdriver/locating_elements/)

---

## Submission Checklist

Before submitting, ensure the following:

- [ ] A new test class named `ElementLocatorTests` has been created inside the `com.example.locators` package.
- [ ] Test methods for **all supported locators** (ID, Name, Class Name, Tag Name, Link Text, Partial Link Text, XPath, and CSS Selector) are implemented.
- [ ] A dynamic element test method (`testDynamicElement`) has been implemented with robust strategies.
- [ ] All test cases execute successfully and locators identify web elements accurately.

---

## Example Commit Messages

- "Added ElementLocatorTests class with tests for all Selenium locators."
- "Implemented dynamic element handling using XPath and CSS Selector strategies."
- "Validated all locators for stability and accuracy."
