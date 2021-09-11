package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.mysql.cj.jdbc.Driver;

public class Jdbc1 {

	public static void main(String[] args) throws Throwable {
		//step:1-register the driver
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		//step:2-establish the connection
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		//step:3-issue the statement
	Statement statement = connection.createStatement();
		//step:4-execute the querry.
	ResultSet result = statement.executeQuery("select * from student_info");
	while(result.next()) {
	System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getString(4));
	}
		//step:5-close the connection.
	connection.close();
	}

}
