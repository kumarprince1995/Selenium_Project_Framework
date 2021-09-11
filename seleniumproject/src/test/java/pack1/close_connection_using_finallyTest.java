package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class close_connection_using_finallyTest {

	public static void main(String[] args) throws Throwable {
		//creating variable connection
		Connection connection=null;
		//creating try catch block for proper execution of close() 
		try {
			//register the driver
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			//step:2-establish the connection
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
			 //issue the statement
			 Statement statement = connection.createStatement();
			 //update the querry.
			 int result = statement.executeUpdate("insert into student_info values('6','rajesh','k','kn')");
			 if(result==1){
				 System.out.println("Data is added to the database"); 
			 }else
					System.out.println("Data is not added to the database");
			
		} 
		//catch block for catching the exception type object
		catch (Exception e) {
		}
		//finally block for surity of execution of close()
			finally {
				connection.close();
			}
		}
	}


