package Testing;
import App.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import org.junit.Test;

class HomeAppTest {
	@Test
    public void testGetStudentsMajors() throws SQLException {
		        Student st = new Student("Marin", "Bulku", "marinbulku@gmail.com", "11", 10, "Male", 21, 3, null, null, "");;
		        Major major = new Major("Computer science", 10);
		        major.addSubject("Fundamental Data Structures");
		        major.addSubject("Basics in Java Programming");
		        st.setMajor(major);
		        String[][] expected = {{"Fundamental Data Structures"}, {"Basics in Java Programming"}};
		        String[][] actual = UAPP.getStudentsMajors();

		        assertArrayEquals(expected, actual);
		    }
    
	  @Test
	  public void testSignOutButton() {
		  JFrame frame = new JFrame();
		  HomeAppTest homeAppTest = new HomeAppTest();
		  ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null);
		    homeAppTest.actionPerformed(event);

		    
		    assertFalse(frame.isVisible());
	  }
	private void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testHomeAppConstructor() {
	    HomeApp homeApp = new HomeApp();
		assertNull(homeApp);
	    
	}

}
