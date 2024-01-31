package App;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Student {
	
	private String emri, mbiemri, email, password, gender;
	private int id;
	private int mosha, yearOfStudy;
	private Major major;
	private Minor minor;
	private ArrayList<String> friendsList;
	private Connector con = new Connector();;
	
	public Student(String emri, String mbiemri, String email, String password, int id, String gender, int mosha,
			int yearOfStudy, Major major, Minor minor, String friends) {
		this.emri = emri;
		this.mbiemri = mbiemri;
		this.email = email;
		this.password = password;
		this.id = id;
		this.gender = gender;
		this.mosha = mosha;
		this.yearOfStudy = yearOfStudy;
		this.major = major;
		this.minor = minor;
		this.friendsList = new ArrayList<>(Arrays.asList(friends.split(",")));
	}
	
	public String getEmri() {
		return emri;
	}
	
	public void setEmri(String emri) {
		this.emri = emri;
	}
	
	public String getMbiemri() {
		return mbiemri;
	}
	
	public void setMbiemri(String mbiemri) {
		this.mbiemri = mbiemri;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getMosha() {
		return mosha;
	}
	
	public void setMosha(int mosha) {
		this.mosha = mosha;
	}
	
	public int getYearOfStudy() {
		return yearOfStudy;
	}
	
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	
	public Major getMajor() {
		return major;
	}
	
	public void setMajor(Major major) {
		this.major = major;
	}
	
	public Minor getMinor() {
		return minor;
	}
	
	public void setMinor(Minor minor) {
		this.minor = minor;
	}
	
	public ArrayList<String> getFriendsList() {
		return friendsList;
	}

	public void addFriendsList(String friend) throws SQLException {
		this.friendsList.add(friend);
		PreparedStatement preparedStatement=con.connection.prepareStatement("UPDATE `student` SET `friends_list`='"+ friendsToString() + "' WHERE Id_student = " + this.id);
        preparedStatement.executeUpdate();
	}
	
	public void removeFriend(String friend) throws SQLException {
		this.friendsList.remove(friend);
		PreparedStatement preparedStatement=con.connection.prepareStatement("UPDATE `student` SET `friends_list`='"+ friendsToString() + "' WHERE Id_student = " + this.id);
        preparedStatement.executeUpdate();
	}
	
	public void removeFRequest(String req) throws SQLException {
		this.friendsList.remove(req);
		PreparedStatement preparedStatement=con.connection.prepareStatement("UPDATE `student` SET `friends_list`='"+ friendsToString() + "' WHERE Id_student = " + this.id);
        preparedStatement.executeUpdate();
	}
	
	public String friendsToString() {
		
		int size = friendsList.size();
		String s = "";
		for(int i =0; i< size; i++) {
			s+=friendsList.get(i);
			if(i != size-1) s += ",";
		}
		
		return s;
	}
}