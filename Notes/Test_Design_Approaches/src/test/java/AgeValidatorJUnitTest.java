import org.example.EquivalancePartition.AgeValidator;
import org.junit.*;
import static org.junit.Assert.*;


public class AgeValidatorJUnitTest {
    @Test
    public void validAgePartition(){
        assertTrue(AgeValidator.isValidAge(25));
    }

    @Test
    public void invalidLowerAgePartition(){
        assertFalse(AgeValidator.isValidAge(15));
    }

    @Test
    public void invalidUpperAgePartition(){
        assertFalse(AgeValidator.isValidAge(65));
    }
}
