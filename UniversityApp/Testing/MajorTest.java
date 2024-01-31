package Testing;

import App.Major;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MajorTest {
	  private Major major;
	  private ArrayList<String> semesterSubjects;
	  private ArrayList<String> takenSubjects;

	@Before
	public void setUp() throws SQLException {
		major = new Major("Computer Science", 10);
	    semesterSubjects = new ArrayList<>();
	    takenSubjects = new ArrayList<>();
	    semesterSubjects.add("Software Engineering");
	    semesterSubjects.add("Operating Systems");
	    semesterSubjects.add("Programming in Python");
	    major.setSemesterSubjects(semesterSubjects);
	    major.setTakenSubjects(takenSubjects);
	    
	}

	@Test
	public void testName() {
		assertEquals("Computer Science", major.getName());
	}
	@Test
	  public void testAddSubject() throws SQLException {
	    boolean result = major.addSubject("Operating Systems");
	    assertTrue(result);
	    result = major.addSubject("Programming in Python");
	    assertFalse(result);
	  }

	@Test
	public void testTakenSubjects() {
		ArrayList<String> expectedTakenSubjects = new ArrayList<>(Arrays.asList("Computer Science", "Business administrat"));
		assertEquals(expectedTakenSubjects, major.getTakenSubjects());
	}

	@Test
	public void testSemesterSubjects() {
		ArrayList<String> expectedSemesterSubjects = new ArrayList<>(Arrays.asList("Psychology", "History"));
		assertEquals(expectedSemesterSubjects, major.getSemesterSubjects());
	}
	
	@Test
	public void testStudentId() {
		assertEquals(10, major.getSt_id());
	}
	@Test
	  public void testRemoveSubject() throws SQLException {
	    major.removeSubject("Programming in Python");
	    assertEquals(2, major.getSemesterSubjects().size());
	    assertEquals("Software Engineering", major.getSemesterSubjects().get(0));
	    assertEquals("Operating Systems", major.getSemesterSubjects().get(1));
	  }
	
	  @Test
	  public void testGetSemesterSubjects() {
	    assertEquals(semesterSubjects, major.getSemesterSubjects());
	  }

	  @Test
	  public void testGetTakenSubjects() {
	    assertEquals(takenSubjects, major.getTakenSubjects());
	    }
	
	@Test
	  public void testTakenSubjectsToString() {
	    String expected = "Software Engineering,Operating Systems,Programming in Python";
	    String result = major.takenSubjectsToString();
	    assertEquals(expected, result);
	  }
}
