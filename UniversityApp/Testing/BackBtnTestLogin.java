package Testing;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.Test;

import App.HomeApp;

public class BackBtnTestLogin{
	JFrame frame = new JFrame();
	HomeApp wf = new HomeApp();
  @Test
  public void testBackBtn() {
    JButton backBtn = new JButton("<Back\n");
    backBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        HomeApp.main(null);
      }
    });
    ActionEvent event = new ActionEvent(backBtn, ActionEvent.ACTION_PERFORMED, "<Back\n");
    backBtn.getActionListeners()[0].actionPerformed(event);
    assertTrue(frame.isVisible() == false);
  }
}