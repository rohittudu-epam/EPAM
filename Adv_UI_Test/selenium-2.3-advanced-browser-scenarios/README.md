# Hands-On Task: Handling Advanced Browser Scenarios

In this task, you will learn to handle advanced browser scenarios using Selenium WebDriver. This includes managing browser alerts, pop-ups, and confirmation boxes; automating switching between multiple windows, tabs, and iframes; and performing browser-level actions like setting cookies and clearing the browser cache.

---

## Objectives

By completing this hands-on task, you will:

1. Automate the handling of:
    - Browser alerts, confirmation boxes, and pop-ups.
    - File upload/download dialogs (browser-based).
2. Work with multiple browser contexts:
    - Switching between windows and tabs.
    - Handling iframes.
3. Perform browser-level actions such as:
    - Setting cookies.
    - Retrieving and deleting cookies.
    - Clearing browser cache and managing browser storage.

---

## Prerequisites

The following are already set up in your provided Maven project:

1. **Selenium WebDriver** dependency is configured in the `pom.xml`.
2. Maven project structure (`src/test/java`) is ready for adding test classes.
3. Basic TestNG setup is included.

Ensure that you understand how to inspect web elements in the developer tools (e.g., iframes) and basic Selenium WebDriver commands.


### Practice Websites:
- **Alerts**: https://demoqa.com/alerts
- **PopUp**: https://demoqa.com/modal-dialogs
- **Window Handling**: https://demoqa.com/browser-windows
- **Frame Handling**: https://demoqa.com/frames
- **Manage Cookies & Clear Cache**: https://demoqa.com
---

## Instructions

### 1. Create a Test Class for Handling Advanced Browser Scenarios

- **Location**: Place the test class in the `src/test/java/com.example.browser` package.
- **Steps**:
    1. Create a package named `com.example.browser` (if it does not already exist).
    2. Inside this package, create a new test class named `AdvancedBrowserScenariosTests`.

#### Test Class Details:

1. **Test Class Name**: `AdvancedBrowserScenariosTests`

2. **Methods to Implement**:

    - **Managing Alerts and Pop-Ups**

        - **`testHandleAlert()`**
            - **Purpose**: Handle browser alerts and confirmation dialogs.
            - **Description**:
                - Automate interaction with JavaScript alerts.
                - Accept or dismiss confirmation boxes and retrieve their text.

        - **`testHandlePopUp()`**
            - **Purpose**: Automate dealing with custom pop-ups (e.g., modal dialogs).
            - **Description**:
                - Locate and interact with pop-up dialogs, buttons, and close options.

    - **Switching Between Windows and Tabs**

        - **`testWindowSwitching()`**
            - **Purpose**: Automate switching between multiple browser windows and tabs.
            - **Description**:
                - Use Selenium’s `getWindowHandles()` and `switchTo().window()` methods to:
                    - Retrieve window/tab handles.
                    - Switch between them and perform actions in different contexts.

    - **Handling Iframes**

        - **`testIframeSwitching()`**
            - **Purpose**: Automate interactions with elements inside iframes.
            - **Description**:
                - Use Selenium’s `switchTo().frame()` to switch to an iframe.
                - Update or retrieve data from elements inside the iframe.
                - Switch back to the default content after interacting with the iframe.

    - **Browser-Level Actions**

        - **`testManageCookies()`**
            - **Purpose**: Automate cookie management actions.
            - **Description**:
                - Set, retrieve, and delete cookies using Selenium’s `Cookie` class.

        - **`testClearCache()`**
            - **Purpose**: Automate clearing browser cache or local storage.
            - **Description**:
                - Clear cache and storage using browser developer tools APIs or through Selenium commands.

---

### 2. Implement Advanced Scenarios

#### **Alerts and Pop-Ups**
- Use Selenium’s `switchTo().alert()` method to interact with alerts.
- Actions to automate:
    - Accept or dismiss alerts.
    - Retrieve alert text and assert its value for validation.
    - Send text input to alerts if required.

#### **Switching Between Windows/Tabs**
- Use Selenium’s `getWindowHandles()` to retrieve all open window handles.
- Use `switchTo().window(handle)` to switch to another window or tab.
- Verify each window/tab by checking titles or URLs.

#### **Working with Iframes**
- Locate an iframe using WebDriver locators (e.g., ID, Name, or WebElement).
- Switch to the iframe using `switchTo().frame(iframeElement)` or `switchTo().frame(index)`.
- Perform actions inside the iframe, then switch back to the main content using `switchTo().defaultContent()`.

#### **Browser Actions**

1. **Cookies Management**
    - Add a cookie using `driver.manage().addCookie(new Cookie(name, value))`.
    - Retrieve cookies using `driver.manage().getCookies()`.
    - Delete a specific cookie or all cookies.

2. **Clearing Cache or Storage**
    - Automate browser storage clearing via interactions like:
        - Pressing developer tool shortcuts for clearing cache.
        - Accessing and clearing local/session storage via JavaScript.

---

### 3. Execute Test Cases

#### Steps to Execute:

1. **Run Alert and Pop-Up Tests**
    - Run `testHandleAlert`:
        - Confirm actions like accepting and dismissing alerts.
    - Run `testHandlePopUp`:
        - Validate pop-up handling (e.g., modal dialogs).

2. **Test Window and Tab Switching**
    - Run `testWindowSwitching`:
        - Open multiple tabs/windows and verify switching functionality.

3. **Test Iframes**
    - Run `testIframeSwitching`:
        - Verify if the test interacts correctly with iframe elements.

4. **Browser-Level Actions**
    - Run `testManageCookies`:
        - Verify cookie creation, retrieval, and deletion.
    - Run `testClearCache`:
        - Assert successful clearing of cache/storage.

---

## Deliverables

By completing this task, you should have:

1. **AdvancedBrowserScenariosTests Class**
    - Test methods covering:
        - Alerts and pop-ups.
        - Window/tab switching.
        - Iframe interactions.
        - Cookie management and cache clearing.

2. **Successful Execution**
    - Successfully verify interactions with browser alerts, multi-window contexts, iframes, and browser storage/actions.

---

## Additional Information

### **Best Practices**

1. **Alerts/Pop-Ups**:
    - Use assertions to ensure the correct alerts or dialogs are handled.
    - Handle potential unexpected alerts to avoid test interruptions.

2. **Window/Tab Management**:
    - Always store the default window handle using `getWindowHandle()` so you can switch back after performing actions in other windows/tabs.

3. **Iframe Handling**:
    - Verify that an iframe exists before switching to it.
    - Switch back to the main content after completing actions inside the iframe.

4. **Browser Actions**:
    - Use Selenium’s Cookie API for cookie management.
    - Use JavaScriptExecutor for advanced storage management when required.

### **Useful Documentation**
- Alerts and Pop-Ups: [https://www.selenium.dev/documentation/webdriver/interactions/alerts/](https://www.selenium.dev/documentation/webdriver/interactions/alerts/)
- Windows/Tabs: [https://www.selenium.dev/documentation/webdriver/interactions/windows/](https://www.selenium.dev/documentation/webdriver/interactions/windows/)
- Iframes: [https://www.selenium.dev/documentation/webdriver/interactions/frames/](https://www.selenium.dev/documentation/webdriver/interactions/frames/)
- Cookies Management: [https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/Cookie.html](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/Cookie.html)

---

## Submission Checklist

Before submission, ensure the following:

- [ ] A new test class named `AdvancedBrowserScenariosTests` has been created in the `com.example.browser` package.
- [ ] Test methods for handling alerts and pop-ups (`testHandleAlert`, `testHandlePopUp`) are implemented.
- [ ] Test methods for window/tab switching and iframe interactions (`testWindowSwitching`, `testIframeSwitching`) are implemented.
- [ ] Cookie management and browser storage methods (`testManageCookies`, `testClearCache`) are implemented.
- [ ] All tests execute successfully, handling advanced browser scenarios effectively.

---

## Example Commit Messages

- "Added AdvancedBrowserScenariosTests class with alert handling tests."
- "Implemented multi-window/tab switching functionality."
- "Added iframe handling and browser cookie management features."
- "Verified advanced browser scenarios test cases for alerts, iframes, and cookies."

---
