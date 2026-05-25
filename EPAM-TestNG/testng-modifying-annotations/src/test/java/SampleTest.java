import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AnnotationTransformer.class)
public class SampleTest {

    @Test
    public void testA(){
        System.out.println("Test A executed");
    }

    @Test
    public void testB(){
        System.out.println("Test B executed");
    }
}
