package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.getProperty("base.url"));
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
