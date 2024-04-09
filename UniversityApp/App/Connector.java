package App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	
	public static Connection connection;
	
	public Connector() {
		try {
			   connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/studenti", "root", "");
		   } 
		   catch (SQLException e) {
			   System.out.println("Error while connecting to the database");
			   System.out.println(e.getMessage());
		   }
		
		
		
	}
	
}

/*
 * public static void main(String[] args) throws SQLException {
		   
		   PreparedStatement preparedStatement=connection.prepareStatement("select * from Student");
	        //Creating Java ResultSet object
	        ResultSet resultSet=preparedStatement.executeQuery();
	        while(resultSet.next()){
	             String rollNo=resultSet.getString("Emri");
	             String name=resultSet.getString("Mbiemri");
	             String email=resultSet.getString("Email");
	             String psw=resultSet.getString("Password");
	             //Printing Results
	             System.out.println(rollNo+" "+name+" "+dept+" "+passw);
	        }
	} 
 */

