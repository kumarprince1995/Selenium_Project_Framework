package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Insert_rowvalueTest {

	public static void main(String[] args) throws Throwable {
		//step:1-register the driver
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		//step:2-establish the connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		//step:3-issue the statement
		Statement statement = connection.createStatement();
		//step:4-update the querry.
		int result = statement.executeUpdate("insert into student_info values('6','adesh','m','mn')");
		if(result==1) {
			System.out.println("Data is added to the database");
		}else
			System.out.println("Data is not added to the database");
		//step:5-close the connection.
		connection.close();
	}

}
