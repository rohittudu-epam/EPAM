package SampleTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest {

    @Test
    public void firstTest(){
        System.out.println("Running First Test W.TestNG");
        Assert.assertTrue(true);
    }

}
