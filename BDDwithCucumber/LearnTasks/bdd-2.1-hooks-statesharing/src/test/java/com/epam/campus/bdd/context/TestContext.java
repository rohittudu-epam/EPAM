package com.epam.campus.bdd.context;

import com.epam.campus.bdd.model.User;

/**
 * TestContext is a higher-level wrapper around ScenarioContext that provides
 * typed accessors for commonly shared test data. It uses ScenarioContext
 * internally so all data remains scoped to a single scenario via PicoContainer.
 */
public class TestContext {

    private static final String CURRENT_USER = "currentUser";
    private static final String RESPONSE_STATUS = "responseStatus";
    private static final String RESPONSE_BODY = "responseBody";
    private static final String LOGIN_RESULT = "loginResult";
    private static final String ERROR_MESSAGE = "errorMessage";

    private final ScenarioContext scenarioContext;

    public TestContext(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    // --- User ---
    public void setCurrentUser(User user) {
        scenarioContext.set(CURRENT_USER, user);
    }

    public User getCurrentUser() {
        return scenarioContext.get(CURRENT_USER, User.class);
    }

    // --- API Response ---
    public void setResponseStatus(int status) {
        scenarioContext.set(RESPONSE_STATUS, status);
    }

    public int getResponseStatus() {
        return scenarioContext.get(RESPONSE_STATUS, Integer.class);
    }

    public void setResponseBody(String body) {
        scenarioContext.set(RESPONSE_BODY, body);
    }

    public String getResponseBody() {
        return scenarioContext.get(RESPONSE_BODY, String.class);
    }

    // --- Login ---
    public void setLoginResult(boolean success) {
        scenarioContext.set(LOGIN_RESULT, success);
    }

    public boolean getLoginResult() {
        return scenarioContext.get(LOGIN_RESULT, Boolean.class);
    }

    public void setErrorMessage(String message) {
        scenarioContext.set(ERROR_MESSAGE, message);
    }

    public String getErrorMessage() {
        return scenarioContext.get(ERROR_MESSAGE, String.class);
    }
}
