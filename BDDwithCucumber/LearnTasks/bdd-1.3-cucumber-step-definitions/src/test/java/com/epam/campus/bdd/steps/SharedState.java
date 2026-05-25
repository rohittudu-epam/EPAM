package com.epam.campus.bdd.steps;

import com.epam.campus.bdd.AuthenticationService;
import com.epam.campus.bdd.ShoppingCart;

/**
 * Shared state between step definition classes using dependency injection.
 * Cucumber creates a new instance per scenario, ensuring test isolation.
 */
public class SharedState {

    private final AuthenticationService authService = new AuthenticationService();
    private final ShoppingCart shoppingCart = new ShoppingCart();

    private String currentPage = "home";
    private String lastMessage = "";

    public AuthenticationService getAuthService() {
        return authService;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
