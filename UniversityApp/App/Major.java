package App;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Major {
	
	private final int thisSemester = 4;
	private String name;
	private ArrayList<String>  semesterSubjects= new ArrayList<>();
	private ArrayList<String> takenSubjects = new ArrayList<>();
	private int st_id;
	Connector con = new Connector();
	
	public Major(String name, int id) throws SQLException {
		
		this.name = name;
		PreparedStatement preparedStatement=con.connection.prepareStatement("SELECT `takenMajorSubjects` , `semesterMajorSubjects` FROM student WHERE Id_student = " + id);
		ResultSet resultSet=preparedStatement.executeQuery();
		resultSet.next();
		if(!resultSet.getString("takenMajorSubjects").isBlank())
        this.semesterSubjects = new ArrayList<>(Arrays.asList(resultSet.getString("takenMajorSubjects").split(",")));
		if(!resultSet.getString("semesterMajorSubjects").isBlank())
        this.takenSubjects = new ArrayList<>(Arrays.asList(resultSet.getString("semesterMajorSubjects").split(",")));
		this.st_id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> getSemesterSubjects() {
		return semesterSubjects;
	}

	public ArrayList<String> getTakenSubjects() {
		return takenSubjects;
	}
	
	public void setSemesterSubjects(ArrayList<String> semesterSubjects) {
		this.semesterSubjects = semesterSubjects;
	}

	public void setTakenSubjects(ArrayList<String> takenSubjects) {
		this.takenSubjects = takenSubjects;
	}
	
	public int getSt_id() {
		return st_id;
	}

	public boolean addSubject(String major) throws SQLException {
		
		if(!(semesterSubjects.size() == thisSemester) && !takenSubjects.contains(major)) {
			semesterSubjects.add(major);
			takenSubjects.add(major);
			
			PreparedStatement ps = con.connection.prepareStatement("UPDATE `student` SET `semesterMajorSubjects`='"+ takenSubjectsToString()+"' WHERE Id_student = " + this.st_id);
			ps.executeUpdate();
			
			ps = con.connection.prepareStatement("UPDATE `student` SET `semesterMajorSubjects`='"+ semesterSubjectsToString()+"' WHERE Id_student = " + this.st_id);
			ps.executeUpdate();
			
			return true;
		}
		return false;
	}
	
	public void removeSubject(String minor) throws SQLException {
		semesterSubjects.remove(minor);
		PreparedStatement ps = con.connection.prepareStatement("UPDATE `student` SET `semesterMajorSubjects`='"+ semesterSubjectsToString()+"' WHERE Id_student = " + this.st_id);
		ps.executeUpdate();
	}
	
	public String takenSubjectsToString() {
		
		String s = "";
		int n = takenSubjects.size();
		
		for(int i = 0; i < n; i++) {
			s+=takenSubjects.get(i);
			if(i != n-1)
				s+= ",";
		}
		
		return s;
		
	}
	
	public String semesterSubjectsToString() {
		
		String s = "";
		int n = semesterSubjects.size();
		
		for(int i = 0; i < n; i++) {
			s+=semesterSubjects.get(i);
			if(i != n-1)
				s+= ",";
		}
		
		return s;
		
	}
}
