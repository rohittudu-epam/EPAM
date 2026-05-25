package SampleTest;

import org.testng.Assert;
import org.testng.annotations.*;

public class SampleTest3 {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Launch browser ONCE");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Login before test");
    }

    @Test
    public void testOne() {
        System.out.println("Running testOne");
        Assert.assertTrue(true);
    }

    @Test
    public void testTwo() {
        System.out.println("Running testTwo");
        Assert.assertTrue(true);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Logout after test");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Close browser ONCE");
    }
}
