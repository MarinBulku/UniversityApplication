package Testing;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class checkEmailTestLogin {
	App.logIn logIn = new App.logIn();
    @Test
    public void testValidEmail() {
    	
        String email = "example@domain.com";
        assertTrue(App.logIn.checkEmail(email));
    }

	@Test
    public void testInvalidEmail() {
        String email = "not a valid email";
        assertFalse(App.logIn.checkEmail(email));
    }
}
