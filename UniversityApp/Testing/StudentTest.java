package Testing;
import App.*; 
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class StudentTest {
	
	Student student;

	@Test
	  public void testConstructor() throws SQLException {
	    String name = "John";
	    String surname = "Doe";
	    String email = "johndoe@example.com";
	    String password = "secret";
	    int id = 123456;
	    String gender = "male";
	    int age = 25;
	    int yearOfStudy = 3;
	    Major major = new Major("Computer Science", 123456);
	    Minor minor = new Minor("Finance", 123456);
	    String friends = "Erisa Brunga,Orsilda Malaj,Ertana Kola";
	    student = new Student(name, surname, email, password, id, gender, age, yearOfStudy, major, minor, friends);

	    assertNotNull(student);
	    assertEquals(name, student.getEmri());
	    assertEquals(surname, student.getMbiemri());
	    assertEquals(email, student.getEmail());
	    assertEquals(password, student.getPassword());
	    assertEquals(id, student.getId());
	    assertEquals(gender, student.getGender());
	    assertEquals(age, student.getMosha());
	    assertEquals(yearOfStudy, student.getYearOfStudy());
	    assertEquals(major, student.getMajor());
	    assertEquals(minor, student.getMinor());
	    assertEquals(new ArrayList<>(Arrays.asList("Erisa Brunga", "Orsilda Malaj", "Ertana Kola")), student.getFriendsList());
	  }
	@Test
	  public void testGetEmri() {
	    student.setEmri("Orsilda");
	    assertEquals("Orsilda", student.getEmri());
	  }

	  @Test
	  public void testSetEmri() {
	    student.setEmri("Orsilda");
	    assertEquals("Orsilda", student.getEmri());
	  }
	  @Test
	  public void testGetMbiemri() {
	    student.setMbiemri("Malaj");
	    assertEquals("Malaj", student.getMbiemri());
	  }

	  @Test
	  public void testSetMbiemri() {
	    student.setMbiemri("Malaj");
	    assertEquals("Malaj", student.getMbiemri());
	  }
	  
	  @Test
	  public void testGetEmail() {
	    student.setEmail("Orsilda@fti.edu.al");
	    assertEquals("Orsilda@fti.edu.al", student.getEmail());
	  }

	  @Test
	  public void testSetEmail() {
	    student.setEmail("orsilda.malaj@example.com");
	    assertEquals("orsilda.malaj@example.com", student.getEmail());
	  }
	  @Test
	  public void testGetPassword() {
	    student.setPassword("password");
	    assertEquals("password", student.getPassword());
	  }

	  @Test
	  public void testSetPassword() {
	    student.setPassword("password");
	    assertEquals("password", student.getPassword());
	  }
	  
	  @Test
	  public void testGetId() {
	    student.setId(1);
	    assertEquals(1, student.getId());
	  }

	  @Test
	  public void testSetId() {
	    student.setId(1);
	    assertEquals(1, student.getId());
	  }
	  
	  @Test
	  public void testGetGender() {
	    student.setGender("male");
	    assertEquals("male", student.getGender());
	  }

	  @Test
	  public void testSetGender() {
	    student.setGender("male");
	    assertEquals("male", student.getGender());
	  }
	  
	  @Test
	  public void testGetMosha() {
	    student.setMosha(20);
	    assertEquals(20, student.getMosha());
	  }

	  @Test
	  public void testSetMosha() {
	    student.setMosha(20);
	    assertEquals(20, student.getMosha());
	  }
	  
	  @Test
	  public void testGetYearOfStudy() {
	    student.setYearOfStudy(2);
	    assertEquals(2, student.getYearOfStudy());
	  }

	  @Test
	  public void testSetYearOfStudy() {
	    student.setYearOfStudy(2);
	    assertEquals(2, student.getYearOfStudy());
	  }
	  
	  @Test
	  public void testGetMajor() throws SQLException {
	    Major major = new Major("Test", 99);
	    student.setMajor(major);
	    assertEquals(major, student.getMajor());
	  }

	  @Test
	  public void testSetMajor() throws SQLException {
	    Major major = new Major("TestMajor", 99);
	    student.setMajor(major);
	    assertEquals(major, student.getMajor());
	  }
	  
	  @Test
	  public void testGetMinor() throws SQLException {
	    Minor minor = new Minor("TestMinor", 99);
	    student.setMinor(minor);
	    assertEquals(minor, student.getMinor());
	  }

	  @Test
	  public void testSetMinor() throws SQLException {
		 Minor minor = new Minor("TestMinor", 99);
	    student.setMinor(minor);
	    assertEquals(minor, student.getMinor());
	  }
	  
	 
	  @Test
	  public void testAddFriend() throws SQLException {
	    Student student = new Student("Orsilda", "Malja", "orsilda.malaj@fti.edu.al", "password", 123, "Female", 20, 2, new Major("Kot", 123), new Minor("kot", 123), "Erisa,Marin,Ertana");
	    ArrayList<String> expectedFriendsList = new ArrayList<>(Arrays.asList("Erisa", "Marin", "Ertana", "Sara"));
	    ArrayList<String> friendsList;
	    student.addFriendsList("Sara");
	    friendsList = student.getFriendsList();
	    assertEquals(expectedFriendsList, friendsList);
	  } 
	  
	  
	  @Test
	    public void testRemoveFriend() throws SQLException {
		  Student student = new Student("Orsilda", "Malja", "orsilda.malaj@fti.edu.al", "password", 123, "Female", 20, 2,new Major("", 123), new Minor("", 123), "Erisa,Marin,Ertana");
	        student.removeFriend("Erisa");
	        ArrayList<String> friends = student.getFriendsList();
	        assertTrue(friends.contains("Marin"));
	        assertTrue(friends.contains("Ertana"));
	        assertFalse(friends.contains("Erisa"));
	    }
	  
	  @Test 
	  public void testRemoveFRequest() throws SQLException {
		  Student student = new Student("Orsilda", "Malja", "orsilda.malaj@fti.edu.al", "password", 123, "Female", 20, 2,new Major("", 123), new Minor("", 123), "Erisa,Marin,Ertana");
	        student.addFriendsList("Erisa");
	        student.removeFRequest("Erisa");
	        assertFalse(student.getFriendsList().contains("Erisa"));
	    }
	  
	  

	

}
