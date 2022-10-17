package Employee_payroll.jdbc;
import java.sql.*;
import java.util.*;
public class Employee_payroll 
{
    public static void main( String[] args )
    {
    	String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
		String UserName = "root";
		String password = "root";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in th classpath!", e);
		}
		listDrivers();
		
		try {
			System.out.println("Connecting to database:" + jdbcURL);
			connection = DriverManager.getConnection(jdbcURL, UserName, password);
		
			System.out.println("Connection is successful!: " + connection);
			} catch (Exception e) {
			e.printStackTrace();
		}
    }
		private static void listDrivers() {
			Enumeration<Driver> driverList = DriverManager.getDrivers();
			while (driverList.hasMoreElements()) {
				Driver driverClass = (Driver) driverList.nextElement();
				System.out.println(" " + driverClass.getClass().getName());

			}
    }
}
