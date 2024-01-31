package Testing;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

import App.logIn;

public class testIsregisteredLogin {
 
  @Test
  public void testIsRegistered_ValidCredentials() throws SQLException {
    boolean result = logIn.isRegistered("orsilda.malaj@fti.edu.al", "zxcvb123");
    assertTrue(result);
  }
 
  @Test
  public void testIsRegistered_InvalidCredentials() throws SQLException {
    boolean result = logIn.isRegistered("notregistered@email.com", "incorrectpassword");
    assertFalse(result);
  }
}
