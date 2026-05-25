package SampleTest;

import org.example.Calc;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SampleTest5 {

    @DataProvider(name = "addData")
    public Object[][] addDataProvider() {
        return new Object[][]{
                {2, 3, 5},
                {0, 0, 0},
                {-1, 4, 3}
        };
    }


    @Test(dataProvider = "addData")
    public void additionTest(int a, int b, int expected){
        Calc c = new Calc();
        Assert.assertEquals(c.add(a, b), expected);
    }
}
