import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestMethods {

    @Test
    public void testMethod1() {
        System.out.println("This is Method 1");
    }

    @Test
    public void testMethod2() {
        System.out.println("This is Method 2");
    }

    @Test
    public void testMethod3() {
        System.out.println("This is Method 3");
    }

    @Test
    public void testMethod4() {
        System.out.println("This is Method 4");
    }

    @BeforeTest
    public void testMethod5() {
        System.out.println("This is Before Test");
    }

    @BeforeMethod
    public void testMethod6() {
        System.out.println("This is Before Method");
    }
}
