package com.epam.campus.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfileSteps {

    private Map<String, String> profileData;
    private List<Map<String, String>> cartItems;
    private List<String> validationErrors;

    @Given("the user is on the profile page")
    public void theUserIsOnTheProfilePage() {
        profileData = new HashMap<>();
    }

    @When("the user fills in the following profile details:")
    public void theUserFillsInProfileDetails(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            profileData.put(row.get("Field"), row.get("Value"));
        }
    }

    @Then("the profile should be updated successfully")
    public void theProfileShouldBeUpdatedSuccessfully() {
        assert !profileData.isEmpty() : "Profile data should not be empty";
        assert profileData.containsKey("First Name") : "First Name is required";
        assert profileData.containsKey("Email") : "Email is required";
    }

    @Given("the user has an empty shopping cart")
    public void theUserHasAnEmptyShoppingCart() {
        cartItems = new ArrayList<>();
    }

    @When("the user adds the following items:")
    public void theUserAddsItems(DataTable dataTable) {
        cartItems = dataTable.asMaps(String.class, String.class);
    }

    @Then("the cart should contain {int} different products")
    public void theCartShouldContainProducts(int expectedCount) {
        assert cartItems.size() == expectedCount :
                "Expected " + expectedCount + " products but got " + cartItems.size();
    }

    @Then("the total quantity should be {int}")
    public void theTotalQuantityShouldBe(int expectedTotal) {
        int total = cartItems.stream()
                .mapToInt(item -> Integer.parseInt(item.get("Quantity")))
                .sum();
        assert total == expectedTotal :
                "Expected total quantity " + expectedTotal + " but got " + total;
    }

    @Given("the user is on the registration form")
    public void theUserIsOnTheRegistrationForm() {
        validationErrors = new ArrayList<>();
    }

    @When("the system checks the following required fields:")
    public void theSystemChecksRequiredFields(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            boolean isFilled = Boolean.parseBoolean(row.get("Is Filled"));
            String expectedError = row.get("Expected Error");
            if (!isFilled && expectedError != null && !expectedError.isEmpty()) {
                validationErrors.add(expectedError);
            }
        }
    }

    @Then("validation errors should be displayed for unfilled fields")
    public void validationErrorsShouldBeDisplayed() {
        assert !validationErrors.isEmpty() : "Expected validation errors but found none";
        assert validationErrors.contains("Username is required");
        assert validationErrors.contains("Email is required");
        assert validationErrors.contains("Password is required");
    }
}
