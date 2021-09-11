package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

public class jdbc2 {

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
	String column = result.getString(2);
	for(int i=0;i<column.length();i++) {
	System.out.println(column);
	}
	}
		//step:5-close the connection.
	connection.close();

}
}
