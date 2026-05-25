package SampleTest;

import org.example.Calc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.SQLOutput;

public class SampleTest4 {

    SoftAssert softAssert;

    @BeforeClass
    public void start() {
        softAssert = new SoftAssert();
        System.out.println("Starting Test");
    }

    @Test
    public void test1() {
        Calc c = new Calc();
        softAssert.assertEquals(c.add(5, 5), 10, "Didn't fail");

        System.out.println("Test 1 successful");
//        softAssert.assertEquals(c.add(2, 3), 4, "Addition failed");
        System.out.println("Test 2 Successful");
        softAssert.assertAll();
    }
}
