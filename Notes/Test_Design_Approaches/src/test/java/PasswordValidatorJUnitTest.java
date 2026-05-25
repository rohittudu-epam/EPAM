import org.example.BoundaryValueAnalysis.PasswordValidator;
import org.junit.*;
import static org.junit.Assert.*;


public class PasswordValidatorJUnitTest {
    @Test
    public void passwordJustBelowMinimum() {
        assertFalse(PasswordValidator.isValidPassword("abcdefg")); // 7
    }

    @Test
    public void passwordAtMinimum() {
        assertTrue(PasswordValidator.isValidPassword("abcdefgh")); // 8
    }

    @Test
    public void passwordJustAboveMinimum() {
        assertTrue(PasswordValidator.isValidPassword("abcdefghi")); // 9
    }

    @Test
    public void passwordJustBelowMaximum() {
        assertTrue(PasswordValidator.isValidPassword("abcdefghijklmno")); // 15
    }

    @Test
    public void passwordAtMaximum() {
        assertTrue(PasswordValidator.isValidPassword("abcdefghijklmnop")); // 16
    }

    @Test
    public void passwordJustAboveMaximum() {
        assertFalse(PasswordValidator.isValidPassword("abcdefghijklmnopq")); // 17
    }
}
