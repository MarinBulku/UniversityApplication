package Testing;

import static org.junit.Assert.*;
import javax.swing.JButton;
import org.junit.Test;
import App.signUp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class registerBtnTest {
	
	@Test
	public void testCheckEmail() {
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assertTrue(signUp.checkEmail("valid@email.com"));
				assertFalse(signUp.checkEmail("invalidemail.com"));
			}
		});
	}
	
	@Test
	public void testPasswordMatch() {
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPassword = "password";
				String newPassword2 = "password";
				assertTrue(newPassword.equals(newPassword2));
				assertFalse(newPassword.equals("not matching"));
			}
		});
	}
	
	@Test
	public void testIsRegistered() {
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					assertTrue(signUp.isRegistered("orsilda.malaj@fti.edu.al"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					assertFalse(signUp.isRegistered("notregistered@email.com"));
				} catch (SQLException e1){
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
