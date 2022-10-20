package Employee_payroll.jdbc;

import java.sql.*;
import java.util.*;

public class Employee_payroll {
	static Connection connection = null;

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
		String UserName = "Varun";
		String password = "Varun@8870187077";

		// DRIVER
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in th classpath!", e);
		}
		listDrivers();

		// CONNECTING DATABASE
		try {
			System.out.println("Connecting to database:" + jdbcURL);
			connection = DriverManager.getConnection(jdbcURL, UserName, password);
			retriveData();
			updateData();
			updatedataPreparedstatement();
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

	// RETRIVING DATA FROM THE DATABASE:
	private static void retriveData() {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from employee_payroll");
			while (result.next()) {
				System.out.print("ID:" + result.getInt("ID") + "	");
				System.out.print("NAME:" + result.getString("NAME") + "     ");
				System.out.print("SALARY:" + result.getString("SALARY") + "     ");
				System.out.print("STARTDATE:" + result.getString("STARTDATE") + "     ");
				System.out.print("GENDER:" + result.getString("GENDER") + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// UPDATE THE DATA IN DATABASE:
	private static void updateData() {
		try {
			Statement statement = connection.createStatement();
			String query = "update employee_payroll set SALARY=450000 where NAME='Varun'";
			Integer Updated = statement.executeUpdate(query);
			System.out.println("\nUpdated: " + Updated);
			retriveData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// UPDATE THE DATA IN DATABASE USING PREPARED STATEMENTS:
	private static void updatedataPreparedstatement() {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update employee_payroll set SALARY=? where NAME=? ");
			preparedStatement.setLong(1, 60000);
			preparedStatement.setString(2, "Varun");
			Integer Updated = preparedStatement.executeUpdate();
			System.out.println("\nUPDATED: " + Updated);
			retriveData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}