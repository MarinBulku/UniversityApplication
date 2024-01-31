package App;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Minor {
	
	private final int thisSemester = 2;
	private String name;
	private ArrayList<String>  semesterSubjects= new ArrayList<>();
	private ArrayList<String> takenSubjects = new ArrayList<>();
	Connector con = new Connector();
	private int st_id;
	
	public Minor(String name, int id) throws SQLException {
		
		this.name = name;
		PreparedStatement preparedStatement=con.connection.prepareStatement("SELECT `takenMinorSubjects` , `semesterMinorSubjects` FROM student WHERE Id_student = " + id);
		ResultSet resultSet=preparedStatement.executeQuery();
		resultSet.next();
		if(!resultSet.getString("takenMinorSubjects").isBlank())
	    this.semesterSubjects = new ArrayList<>(Arrays.asList(resultSet.getString("takenMinorSubjects").split(",")));
		
		if(!resultSet.getString("semesterMinorSubjects").isBlank())
	    this.takenSubjects = new ArrayList<>(Arrays.asList(resultSet.getString("semesterMinorSubjects").split(",")));
		this.st_id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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

	public boolean addSubject(String minor) throws SQLException {
		
		if(!(semesterSubjects.size() == thisSemester) && !takenSubjects.contains(minor)) {
			semesterSubjects.add(minor);
			takenSubjects.add(minor);
			
			PreparedStatement ps = con.connection.prepareStatement("UPDATE `student` SET `semesterMinorSubjects`='"+ semesterSubjectsToString()+"' WHERE Id_student = " + this.st_id);
			ps.executeUpdate();
			
			ps = con.connection.prepareStatement("UPDATE `student` SET `semesterMinorSubjects`='"+ takenSubjectsToString()+"' WHERE Id_student = " + this.st_id);
			ps.executeUpdate();
			
			return true;
		}
		return false;
	}
	
	public void removeSubject(String minor) throws SQLException {
		semesterSubjects.remove(minor);
		PreparedStatement ps = con.connection.prepareStatement("UPDATE `student` SET `semesterMinorSubjects`='"+ semesterSubjectsToString()+"' WHERE Id_student = " + this.st_id);
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
