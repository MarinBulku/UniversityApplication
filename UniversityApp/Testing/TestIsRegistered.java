package Testing;
import App.signUp;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

public class TestIsRegistered {
	
	@Test
	public void testIsRegisteredWithExistingEmail() throws SQLException {
		String email = "marin.bulku@fti.edu.al";
		
		boolean result = signUp.isRegistered(email);
		
		assertTrue("The email should be registered", result);
	}
	
	@Test
	public void testIsRegisteredWithNonExistingEmail() throws SQLException {
		String email = "orsila@fti.edu.al";
		
		boolean result = signUp.isRegistered(email);
		
		assertFalse("The email should not be registered", result);
	}
}
