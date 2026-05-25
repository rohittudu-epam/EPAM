package com.epam.campus.bdd.steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step Definitions for Shopping Cart feature.
 *
 * Demonstrates:
 * - Parameterized steps with multiple data types (String and double)
 * - Floating-point comparison with delta tolerance
 * - Reuse of login steps from LoginSteps class (Cucumber auto-discovers all step defs)
 * - Steps that combine actions with state transitions
 */
public class ShoppingCartSteps {

    private final SharedState state;

    public ShoppingCartSteps(SharedState state) {
        this.state = state;
    }

    // ---- WHEN steps (actions) ----

    @When("the user adds item {string} with price {double} to the cart")
    public void theUserAddsItemWithPriceToTheCart(String itemName, double price) {
        assertTrue(state.getAuthService().isLoggedIn(),
                "User must be logged in to add items to cart");

        state.getShoppingCart().addItem(itemName, price);
    }

    @When("the user removes item {string} from the cart")
    public void theUserRemovesItemFromTheCart(String itemName) {
        boolean removed = state.getShoppingCart().removeItem(itemName);
        assertTrue(removed,
                "Failed to remove item '" + itemName + "' from cart — item not found");
    }

    // ---- THEN steps (assertions) ----

    @Then("the cart should contain {int} item(s)")
    public void theCartShouldContainItems(int expectedCount) {
        int actualCount = state.getShoppingCart().getItemCount();
        assertEquals(expectedCount, actualCount,
                "Cart item count mismatch. Expected: " + expectedCount
                        + " but got: " + actualCount);
    }

    @Then("the cart total should be {double}")
    public void theCartTotalShouldBe(double expectedTotal) {
        double actualTotal = state.getShoppingCart().getTotal();
        assertEquals(expectedTotal, actualTotal, 0.01,
                "Cart total mismatch. Expected: " + expectedTotal
                        + " but got: " + actualTotal);
    }
}
