package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import java.util.Scanner;



public class Database {
	static Connection connection;
	DriverManager dm;
	static Statement stm;

	public Database() {

	}

	public static Connection connect(String _DB_URL, String _username, String _password) {
		try {
			connection = DriverManager.getConnection(_DB_URL, _username, _password);
			return connection;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

	public int executeStatement(String sql) {
		try {
			stm = connection.createStatement();
			return stm.executeUpdate(sql);
		} catch (SQLException e) {

			e.printStackTrace();
			return -22;
		}
	}

	public static void printStudents() {
		try {
			String query = ("select fname as First_Name ,lname as Last_Name,faculty_no as Faculty_No from Students");
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				String firstName = rs.getString("First_Name");
				String lastName = rs.getString("Last_Name");
				String facultyno = rs.getString("Faculty_No");
				System.out.println("First Name : " + "{" + firstName + "}");
				System.out.println("Last Name : " + "{" + lastName + "}");
				System.out.println("Faculty Number :" + "{" + facultyno + "}");
				System.out.println();

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void printWorkers() {
		try {
			String query = ("select fname as First_Name ,lname as Last_Name,week_salary as Week_Salary,working_hours as Working_Hours from workers");
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				String firstName = rs.getString("First_Name");
				String lastName = rs.getString("Last_Name");
				Float weekSalary = rs.getFloat("Week_Salary");
				DecimalFormat df = new DecimalFormat("0.00");
				df.setMaximumFractionDigits(2);
				Integer workhours = rs.getInt("Working_Hours");
				System.out.println("First Name : " + "{" + firstName + "}");
				System.out.println("Last Name : " + "{" + lastName + "}");
				System.out.println("Week Salary :" + "{" + df.format(weekSalary) + "}");
				System.out.println("Working hours per day :" + "{" + workhours + "}");
				System.out.println("Salary per hour :" + "{" + df.format(weekSalary / 5 / workhours) + "}");
				System.out.println();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void insertStudent() {
			try{
			System.out.println("Insert first name for new Student");
			Scanner sca = new Scanner(System.in);
			String firstName = sca.next();
			firstName=firstName.substring(0, 1).toUpperCase()+firstName.substring(1).toLowerCase();
			System.out.println("Insert last name for new Student");
			Scanner scan = new Scanner(System.in);
			String lastName = scan.next();	
			lastName=lastName.substring(0, 1).toUpperCase()+lastName.substring(1).toLowerCase();
			System.out.println("Insert faculty number for new Student");
			Scanner scann = new Scanner(System.in);
			String facultyno = scann.next();		
			String query=("insert into students " + "values(null,?,?,?)");
			PreparedStatement stm=connection.prepareStatement(query);
			
			stm.setString(1,firstName);
			stm.setString(2,lastName);
			stm.setString(3,facultyno);
			
			stm.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();
			}}
			public static void insertWorker() {
				try{
				System.out.println("Insert first name for new Worker");
				Scanner sca = new Scanner(System.in);
				String firstName = sca.next();
				firstName=firstName.substring(0, 1).toUpperCase()+firstName.substring(1).toLowerCase();
				System.out.println("Insert last name for new Worker");
				Scanner scan = new Scanner(System.in);
				String lastName = scan.next();	
				firstName=firstName.substring(0, 1).toUpperCase()+lastName.substring(1).toLowerCase();
				System.out.println("Insert week salary new Worker");
				Scanner scann = new Scanner(System.in);
				
				double weeksal = scann.nextDouble();		
				System.out.println("Insert working hours for new Worker");
				Scanner scan4 = new Scanner(System.in);
				int hours = scan4.nextInt();		
				
				String query="insert into workers values(null,?,?,?,?)";
				PreparedStatement stm=connection.prepareStatement(query);
				stm.setString(1,firstName);
				stm.setString(2,lastName);
				stm.setDouble(3,weeksal);
				stm.setInt(4,hours);
				stm.executeUpdate();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			
			
			}
		
			public static void Menu() {

				@SuppressWarnings("unused")
				boolean go = false;

				do {
					System.out.println("\n" + "Student List");
					System.out.println("Press 1 to print Students");
					System.out.println("Press 2 to print Workers");
					System.out.println("Press 3 to add new Student");
					System.out.println("Press 4 to add new Worker");
					System.out.println("Press 5 to Exit");

					@SuppressWarnings("resource")
					Scanner sca = new Scanner(System.in);
					if (sca.hasNextInt()) {
						int input = sca.nextInt();

						switch (input) {
						case 1:
							printStudents();
							break;
						case 2:
							printWorkers();
							break;
						case 3:
							insertStudent();
							break;
						case 4:
							insertWorker();
							break;
						case 5:
							System.out.println("Goodbye!");
							go = true;
							break;
						default:
							System.out.println("Please enter a valid command" + "\n");
							go = false;

						}
					}

				} while (!go);
			}
	
	
	
	
	
	}


