package Testing;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class checkEmailTest {
	App.signUp signUp = new App.signUp();
    @Test
    public void testValidEmail() {
    	
        String email = "example@domain.com";
        assertTrue(App.signUp.checkEmail(email));
    }

	@Test
    public void testInvalidEmail() {
        String email = "not a valid email";
        assertFalse(App.signUp.checkEmail(email));
    }
}
