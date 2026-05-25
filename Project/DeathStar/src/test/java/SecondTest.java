import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SecondTest {


    @Test
    public void testMethod1() {
        System.out.println("This is SecondTest 1");
    }

    @Test
    public void testMethod2() {
        System.out.println("This is SecondTest 2");
    }

    @Test
    public void testMethod3() {
        System.out.println("This is SecondTest 3");
    }

    @Test
    public void testMethod4() {
        System.out.println("This is SecondTest 4");
    }

    @BeforeTest
    public void testMethod5() {
        System.out.println("This is Before-Test For SecondTest");
    }

    @BeforeMethod
    public void testMethod6() {
        System.out.println("This is Before SecondTest");
    }
}


