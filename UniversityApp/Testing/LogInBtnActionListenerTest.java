package Testing;

import static org.junit.Assert.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.Test;

public class LogInBtnActionListenerTest implements ActionListener {
	@Test
	public void testActionPerformedLoginBtn() {
		JFrame frame = new JFrame();
		LogInBtnActionListenerTest logInBtnActionListener = new LogInBtnActionListenerTest();
		ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null);
		logInBtnActionListener.actionPerformed(event);

		assertFalse(frame.isVisible());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Test
	public void testActionPerformed() {
		// Create the objects
		JButton signUpBtn = new JButton("Sign-up");
		ActionEvent e = new ActionEvent(signUpBtn, 0, "");
		JFrame frame = new JFrame();
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				App.signUp.main(null);
			}
		});

		signUpBtn.getActionListeners()[0].actionPerformed(e);

		assertFalse(frame.isVisible());
	}
}
