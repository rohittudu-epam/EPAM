package SampleTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleTest2 {

    @BeforeMethod
    public void setUp(){
        System.out.println("Setting up Browser");
    }

    @Test
    public void test1(){
        System.out.println("Running Test 1");
    }

    @Test
    public void test2(){
        System.out.println("Running test 2");
    }

    @AfterMethod
    public void destroy(){
        System.out.println("Closing Browser...");
    }
}
