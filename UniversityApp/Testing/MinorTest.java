package Testing;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import App.Minor;
import App.Student;


class MinorTest {
	
	  private Minor minor;
	  private ArrayList<String> semesterSubjects;
	  private ArrayList<String> takenSubjects;
	  private Student student;
	  //String emri, String mbiemri, String email, String password, int id, String gender, int mosha,int yearOfStudy, Major major, Minor minor, String friends
	  public void setUp() throws SQLException {
			minor = new Minor("Finance", 10);
			student = new Student("Marin", "Bulku", "marinbulku@gmail.com", "11", 10, "Male", 21, 3, null, minor, "");
		    semesterSubjects = new ArrayList<>();
		    takenSubjects = new ArrayList<>();
		    semesterSubjects.add("Software Engineering");
		    semesterSubjects.add("Operating Systems");
		    semesterSubjects.add("Programming in Python");
		    minor.setSemesterSubjects(semesterSubjects);
		    minor.setTakenSubjects(takenSubjects);
		    
		}

		@Test
		public void testName() {
			assertEquals("Finance", minor.getName());
		}
		@Test
		  public void testAddSubject() throws SQLException {
		    boolean result = minor.addSubject("Operating Systems");
		    assertTrue(result);
		    result = minor.addSubject("Programming in Python");
		    assertFalse(result);
		  }
		@Test
		public void testStudentId() {
			assertEquals(1, student.getId());
		}
		@Test
		  public void testRemoveSubject() throws SQLException {
		    minor.removeSubject("Programming in Python");
		    assertEquals(2, minor.getSemesterSubjects().size());
		    assertEquals("Software Engineering", minor.getSemesterSubjects().get(0));
		    assertEquals("Operating Systems", minor.getSemesterSubjects().get(1));
		  }
		
		  @Test
		  public void testGetSemesterSubjects() {
		    assertEquals(semesterSubjects, minor.getSemesterSubjects());
		  }

		  @Test
		  public void testGetTakenSubjects() {
		    assertEquals(takenSubjects, minor.getTakenSubjects());
		    }
}
