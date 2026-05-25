package com.epam.campus.bdd.hooks;

import com.epam.campus.bdd.factory.DriverFactory;
import com.epam.campus.bdd.utils.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver(Browser.CHROME);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}

