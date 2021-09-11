package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Delete_value_from_tableTest {

	public static void main(String[] args) throws Throwable {
		//step:1-register the driver
				Driver driver=new Driver();
				DriverManager.registerDriver(driver);
				//step:2-establish the connection
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
				//step:3-issue the statement
				Statement statement = connection.createStatement();
				//step:4-update the querry.
				int result = statement.executeUpdate("delete from student_info where regno=1");
				if(result==1) {
					System.out.println("Data is deleted from the database");
				}else
					System.out.println("Data is  not deleted from the database");
				//step:5-close the connection.
				connection.close();
	}

}
