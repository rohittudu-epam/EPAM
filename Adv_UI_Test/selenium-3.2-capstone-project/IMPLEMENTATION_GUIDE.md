# SauceDemo Automation Implementation Guide

## 1) Goal

Use this guide to complete the assignment end-to-end:

- Implement all required test scenarios from README.
- Complete missing Page Object methods and tests.
- Improve reliability, test isolation, and maintainability.

This guide is based on the current project state, not only the README.
[IMPLEMENTATION_GUIDE.md](IMPLEMENTATION_GUIDE.md)
## 2) Current Gaps Summary

### Implemented

- Driver lifecycle utilities are mostly in place: `DriverFactory`, `BrowserConfigs`, `ConfigReader`.
- Base page helpers exist: click, sendKeys, wait, getText.
- `LoginTests` has data providers and basic positive/negative flow checks.
- `ProductCard` component is available.

### Missing / Incomplete

- `ProductPage` does not extend `BasePage` and calls `getText(...)` anyway (compile/runtime issue).
- `CartPage` and `CheckoutPage` are empty.
- `ProductTests`, `SortTests`, `LogoutTests` are empty.
- `testng.xml` has no classes registered.
- `config.properties` only has `browser.name=CHROME`; tests require `base.uri` and should use config-backed credentials.
- `LoginPage` uses `@FindBy` but does not initialize fields via `PageFactory`.

## 3) Assignment Coverage Map

| README Test Case | Required Outcome | Primary Classes |
|---|---|---|
| TC1 valid login | User reaches inventory page | `LoginPage`, `LoginTests` |
| TC2 invalid login | Error shown and login blocked | `LoginPage`, `LoginTests` |
| TC3 inventory access | Product page visible after login | `ProductPage`, `ProductTests` |
| TC4 add to cart | Product added, cart badge updates | `ProductPage`, `CartPage`, `ProductTests` |
| TC5 remove from cart | Product removed from cart | `ProductPage`, `CartPage`, `ProductTests` |
| TC6 checkout | Cart -> Checkout -> Finish success | `CartPage`, `CheckoutPage`, `ProductTests` or `CheckoutTests` |
| TC7 sorting | 4 sort modes validated | `ProductPage`, `SortTests` |
| TC8 logout | User returned to login page | `ProductPage` (or menu component), `LogoutTests` |

## 4) Recommended Implementation Order

1. Stabilize framework core (`BaseTest`, `config.properties`, `testng.xml`).
2. Fix/complete page objects (`LoginPage`, `ProductPage`, `CartPage`, `CheckoutPage`).
3. Add missing tests (`ProductTests`, `SortTests`, `LogoutTests`).
4. Add helper utilities for sorting and assertions.
5. Add optional robustness improvements (screenshots, retries, soft assertions).

## 5) File-by-File Implementation Checklist

## 5.1 `src/main/resources/config.properties`

Add at least:

```properties
browser.name=CHROME
base.uri=https://www.saucedemo.com/
standard.username=standard_user
standard.password=secret_sauce
locked.username=locked_out_user
invalid.username=ghost_user
invalid.password=secret_sauce
wait.seconds=10
```

Optional extras:

```properties
headless=false
implicit.wait.seconds=0
page.load.timeout.seconds=30
screenshot.on.failure=true
```

## 5.2 `src/main/java/com/epam/campus/selenium/base/BaseTest.java`

Current issue: Uses `@BeforeTest/@AfterTest` and ignores `@Parameters("browser")` value.

Implement test lifecycle for isolation:

- `@BeforeMethod`: start driver (if not active), open base URL, clear cookies if needed.
- `@AfterMethod`: clean state (logout if logged in, or clear cookies).
- `@AfterSuite` or `@AfterMethod(alwaysRun = true)`: ensure driver quit.

Suggested shape:

```java
@BeforeMethod(alwaysRun = true)
@Parameters({"browser"})
public void setup(@Optional("CHROME") String browser) { ... }

@AfterMethod(alwaysRun = true)
public void teardown() { ... }
```

If parallel execution is planned, keep `ThreadLocal` use in `DriverFactory`.

## 5.3 `src/main/java/com/epam/campus/selenium/pages/LoginPage.java`

Current issue: `@FindBy` fields are never initialized.

Add in constructor:

```java
PageFactory.initElements(driver, this);
```

Add locators and methods for invalid login assertions:

- `private WebElement errorMessageContainer`
- `private WebElement errorCloseButton` (optional)
- `public String getErrorMessage()`
- `public boolean isErrorDisplayed()`

Keep existing methods but make `enterUsername`, `enterPassword`, `clickLogin` public only if needed externally.

## 5.4 `src/main/java/com/epam/campus/selenium/pages/ProductPage.java`

Current issues:

- Must extend `BasePage`.
- Uses locator style mixed with no PageFactory initialization.
- Missing all required interactions.

Core methods to implement:

- `public boolean isDisplayed()`
- `public List<ProductCard> getAllProductCards()`
- `public ProductCard getProductByName(String productName)`
- `public void addProductToCart(String productName)`
- `public void removeProductFromCart(String productName)`
- `public int getCartBadgeCount()`
- `public void openCart()`
- `public void sortByVisibleText(String option)`
- `public List<String> getVisibleProductNames()`
- `public List<Double> getVisibleProductPrices()`
- `public void openMenu()`
- `public void logout()`

Sort options for SauceDemo:

- `Name (A to Z)`
- `Name (Z to A)`
- `Price (low to high)`
- `Price (high to low)`

## 5.5 `src/main/java/com/epam/campus/selenium/pages/CartPage.java`

Required methods:

- `public boolean isDisplayed()`
- `public int getCartItemCount()`
- `public boolean hasItem(String productName)`
- `public void removeItem(String productName)`
- `public void clickCheckout()`
- `public void clickContinueShopping()` (optional)

## 5.6 `src/main/java/com/epam/campus/selenium/pages/CheckoutPage.java`

Model checkout as 2 steps (Your Information -> Overview -> Complete).

Required methods:

- `public void fillCustomerInfo(String firstName, String lastName, String postalCode)`
- `public void clickContinue()`
- `public boolean isOverviewDisplayed()`
- `public void clickFinish()`
- `public boolean isCheckoutComplete()`
- `public String getCompletionMessage()`

Useful validation helpers:

- `public String getPaymentInfo()`
- `public String getShippingInfo()`
- `public String getItemTotal()`
- `public String getTax()`
- `public String getTotal()`

## 5.7 `src/test/java/com/epam/campus/selenium/tests/LoginTests.java`

Improve assertions:

- Valid login: assert product page displayed (`ProductPage.isDisplayed()`), not only URL changed.
- Invalid login: assert error message text and that login page remains visible.
- Move credentials to `config.properties` where practical.

Note: `locked_out_user` should be validated as a negative case with expected specific message.

## 5.8 `src/test/java/com/epam/campus/selenium/tests/ProductTests.java`

Implement at least:

- `testInventoryAccessibleAfterLogin()` for TC3
- `testAddProductToCart()` for TC4
- `testRemoveProductFromCart()` for TC5
- `testCheckoutFlowToFinish()` for TC6 (or separate `CheckoutTests` class)

## 5.9 `src/test/java/com/epam/campus/selenium/tests/SortTests.java`

Implement 4 tests:

- `testSortNameAToZ()`
- `testSortNameZToA()`
- `testSortPriceLowToHigh()`
- `testSortPriceHighToLow()`

For each:

1. Login.
2. Apply sort option.
3. Read names/prices from UI.
4. Compare with expected sorted copy.

## 5.10 `src/test/java/com/epam/campus/selenium/tests/LogoutTests.java`

Implement:

- `testLogoutSuccessful()`

Flow:

1. Login with standard user.
2. Open menu and click logout.
3. Assert login page visible and URL is base URL.

## 5.11 `src/test/resources/testng.xml`

Register all test classes and parameterize browser.

Minimal example:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="SauceDemo Suite" parallel="false">
    <parameter name="browser" value="CHROME"/>

    <test name="Auth">
        <classes>
            <class name="com.epam.campus.selenium.tests.LoginTests"/>
            <class name="com.epam.campus.selenium.tests.LogoutTests"/>
        </classes>
    </test>

    <test name="Product and Checkout">
        <classes>
            <class name="com.epam.campus.selenium.tests.ProductTests"/>
            <class name="com.epam.campus.selenium.tests.SortTests"/>
        </classes>
    </test>
</suite>
```

## 6) Additional Methods That Improve Project Quality

Beyond README scope, add these for robustness:

### 6.1 BasePage improvements

- `protected boolean isVisible(By locator)`
- `protected void type(By locator, String text, boolean clearFirst)`
- `protected void safeClick(By locator)`
- `protected void waitForUrlContains(String partial)`

### 6.2 Test utility methods in `TestUtils`

- `public static double parsePrice(String value)`
- `public static List<Double> parsePrices(List<String> priceTexts)`
- `public static <T extends Comparable<T>> boolean isSortedAsc(List<T> values)`
- `public static <T extends Comparable<T>> boolean isSortedDesc(List<T> values)`

### 6.3 Better assertions

- Use informative assertion messages.
- For sort tests, assert list size > 1 before sort assertion.
- Validate cart badge count after add/remove.

### 6.4 Diagnostics

- Capture screenshot on test failure in `@AfterMethod`.
- Attach logs to report (if reporting plugin later added).

### 6.5 Optional page/components split

- `HeaderComponent`: cart icon, badge count, menu open.
- `MenuComponent`: logout/reset menu actions.

This keeps page classes smaller and follows SRP.

## 7) Locator Guidance for SauceDemo

Prefer stable locators:

- Inputs/buttons: `data-test` attributes where available.
- Product cards: `.inventory_item`
- Sort dropdown: `[data-test='product-sort-container']`
- Cart badge: `.shopping_cart_badge`
- Menu button: `#react-burger-menu-btn`
- Logout link: `#logout_sidebar_link`
- Checkout complete header: `[data-test='complete-header']`

## 8) Example Test Design Pattern

Use Arrange -> Act -> Assert in each test:

1. Arrange: login + navigate target page.
2. Act: perform one business action.
3. Assert: verify a single expected behavior strongly.
4. Cleanup: handled by framework lifecycle.

## 9) Suggested Execution Commands

Run full suite:

```bash
mvn clean test
```

Run using xml explicitly:

```bash
mvn -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml test
```

## 10) Final Completion Checklist

- Login success and failure with message assertions.
- Product inventory visibility verified.
- Add/remove cart tests working and isolated.
- Full checkout to finish verified.
- All 4 sort modes verified using deterministic comparisons.
- Logout verified.
- Tests independent (can run in any order).
- `testng.xml` includes all tests.
- Test report generated from Surefire/TestNG output.

---

If you want, the next step can be implementation in this exact order with working code patches for each class and test file.
