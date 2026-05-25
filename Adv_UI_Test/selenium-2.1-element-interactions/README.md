# Hands-On Task: Web Element Interactions

In this task, you will perform basic and advanced actions on web elements using Selenium WebDriver. This task includes interacting with web elements such as buttons, text inputs, dropdowns, and more. You will also explore advanced interactions like mouse hover, drag-and-drop, keyboard events, and file uploads/downloads.

---

## Objectives

By completing this hands-on task, you will:

1. Perform basic actions on web elements:
    - Clicking buttons
    - Sending text input to fields
    - Clearing input fields
    - Selecting options from dropdown menus

2. Handle advanced interactions:
    - Mouse hover actions
    - Implementing drag-and-drop functionality
    - Handling keyboard events
    - Performing file uploads and downloads

3. Learn best practices for interacting with web elements in Selenium WebDriver.

---

## Prerequisites

The following have already been set up in your provided Maven project:

1. Selenium WebDriver dependency is configured in the `pom.xml`.
2. Maven project structure (`src/test/java`) is ready for adding test classes.
3. Basic TestNG setup is included.

Before starting, ensure you are familiar with Selenium WebDriver methods for web element interactions and basic TestNG annotations.

### Practice Websites:
- **ButtonClick**: https://demoqa.com/buttons
- **SendInput**: https://demoqa.com/text-box
- **ClearField**: https://demoqa.com/text-box
- **DropDown Selection**: https://demoqa.com/select-menu
- **MouseHover**: https://demoqa.com/tool-tips
- **DragAndDrop**: https://demoqa.com/droppable
- **KeyBoardEvents**: https://demoqa.com/text-box
- **FileUpload**: https://demoqa.com/upload-download
- **FileDownload**: https://demoqa.com/upload-download

---

## Instructions

### 1. Create a Test Class for Interactions

- **Location**: Place the test class in the `src/test/java/com.example.interactions` package.
- **Steps**:
    1. Create a package named `com.example.interactions` (if it does not already exist).
    2. Inside this package, create a new test class named `WebElementInteractionTests`.

#### Test Class Details:

1. **Test Class Name**: `WebElementInteractionTests`

2. **Methods to Implement**:

    - **Basic Element Interactions**:

        - **`testButtonClick()`**:
            - Purpose: Locate a button and perform a click action. Verify that the expected result (such as navigation or an alert) occurs.

        - **`testSendTextInput()`**:
            - Purpose: Locate a text input field, send text, and verify the entered value.

        - **`testClearField()`**:
            - Purpose: Clear the content of an input field and verify that it has been cleared.

        - **`testDropdownSelection()`**:
            - Purpose: Select an option from a dropdown menu using Selenium’s `Select` class.

    - **Advanced Interactions**:

        - **`testMouseHover()`**:
            - Purpose: Perform a mouse hover action on specific elements (e.g., menu items) using `Actions` class. Verify the hover functionality.

        - **`testDragAndDrop()`**:
            - Purpose: Automate drag-and-drop functionality using the Selenium `Actions` class.

        - **`testKeyboardEvents()`**:
            - Purpose: Simulate keyboard events like pressing Enter, Tab, or other keyboard keys using the `Actions` class.

        - **`testFileUpload()`**:
            - Purpose: Automate file uploads by interacting with `<input type="file">` elements. Verify that the correct file was uploaded.

        - **`testFileDownload()`**:
            - Purpose: Automate file downloads by triggering a download request and verifying the file in the download directory.

---

### 2. Execute Advanced Interactions

#### Tips for Implementing Advanced Actions:

1. **Mouse Hover**:
    - Use the `Actions` class to move the mouse to a specific element and hover over it.
    - Confirm that hidden elements or tooltips appear after hovering.

2. **Drag-and-Drop**:
    - Locate source and target elements.
    - Use the `dragAndDrop(source, target)` method in the `Actions` class to perform the action.

3. **Keyboard Events**:
    - Use the `Actions.sendKeys(Keys)` method to simulate keyboard events such as pressing Enter, Tab, etc.

4. **File Uploads**:
    - Locate the `<input type="file">` element.
    - Use the `.sendKeys(filePath)` method to upload the file.

5. **File Downloads**:
    - Trigger a download request by clicking a download button or link.
    - Verify the presence of the file in the expected download directory after the download completes.

---

### 3. Execute Test Cases

#### Steps to Execute:

1. Run individual test methods (`testButtonClick`, `testSendTextInput`, etc.) to validate basic element interactions.
2. Execute advanced interaction test cases (`testMouseHover`, `testDragAndDrop`, etc.) and verify that behaviors are correctly implemented.
3. Analyze interaction robustness and make adjustments if required.

---

## Deliverables

By completing this task, you should have:

1. **WebElementInteractionTests Class**:
    - Implemented test methods for basic interactions (clicking buttons, sending input, clearing fields, dropdown selection).
    - Implemented test methods for advanced interactions (mouse hover, drag-and-drop, keyboard events, file uploads/downloads).

2. **Verified Test Execution**:
    - All test cases pass successfully with web element interactions working as expected.

---

## Additional Information

1. **Selenium Methods for Interactions**:
    - Click: `element.click()`
    - Send Text: `element.sendKeys(text)`
    - Clear Field: `element.clear()`
    - Dropdown Selection: Use `Select` class methods:
        - `selectByVisibleText()`
        - `selectByIndex()`
        - `selectByValue()`

2. **Advanced Actions**:
    - Utilize Selenium’s `Actions` class for advanced tasks like mouse hover and keyboard inputs.
    - Handle file uploads/downloads using system paths and appropriate browser configurations.

3. **Useful Documentation**:
    - Selenium WebDriver: [https://www.selenium.dev/documentation/webdriver/](https://www.selenium.dev/documentation/webdriver/)
    - TestNG: [https://testng.org/doc/](https://testng.org/doc/)

---

## Submission Checklist

Before submitting, ensure the following:

- [ ] A new test class named `WebElementInteractionTests` has been created inside the `com.example.interactions` package.
- [ ] Test methods for basic web element interactions (`testButtonClick`, `testSendTextInput`, `testDropdownSelection`, etc.) are implemented.
- [ ] Test methods for advanced interactions (`testMouseHover`, `testDragAndDrop`, `testFileUpload`, etc.) are implemented.
- [ ] All tests execute successfully, and interactions with web elements are validated.

---

## Example Commit Messages

- "Added WebElementInteractionTests class with methods for basic interactions."
- "Implemented advanced interactions using Actions class for mouse hover and drag-and-drop."
- "Automated file upload and download actions and validated functionality."
- "Verified all web element interactions across supported browsers."

---
