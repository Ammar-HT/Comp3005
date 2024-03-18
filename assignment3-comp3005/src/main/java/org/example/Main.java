package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //Function to establish the connection with the database
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/assignment3";
        String user = "postgres";
        String password = "Coolking@2003";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            if (connection == null){
                System.out.println("Failed to connect to the database");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return DriverManager.getConnection(url, user, password);
    }
    // Function to get all the students from the table and print them out
    public static void getAllStudents() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM students");
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()){
            System.out.print(resultSet.getString("student_id")+ "\t");
            System.out.print(resultSet.getString("first_name")+ "\t");
            System.out.print(resultSet.getString("last_name")+ "  \t");
            System.out.print(resultSet.getString("email")+ "\t");
            System.out.print(resultSet.getString("enrollment_date")+ "\t");
            System.out.println();
        }
        statement.close();
    }
    //Function to insert a new student record into the students table
    public static void addStudent(String first_name, String last_name, String email, Date enrollment_date) throws SQLException {
        Connection connection = getConnection();
        String sqlQuery = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1,first_name);
        statement.setString(2,last_name);
        statement.setString(3,email);
        statement.setDate(4,enrollment_date);
        statement.executeUpdate();

        statement.close();
    }
    // Function to update the email address for a student with the specified student_id
    public static void updateStudentEmail(int student_id, String email) throws SQLException {
        Connection connection = getConnection();
        String sqlQuery = "UPDATE students SET email = ? WHERE student_id = ?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, email);
        statement.setInt(2, student_id);
        statement.executeUpdate();

        statement.close();
    }
    //Function to delete the record of the student with the specified student_id
    public static void deleteStudent(int student_id) throws SQLException {
        Connection connection = getConnection();
        String sqlQuery = "DELETE FROM students WHERE student_id = ?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, student_id);
        statement.executeUpdate();

        statement.close();
    }



    public static void main(String[] args) {

        try {
            //addStudent("Ahmed", "example", "ahmed@example.com", Date.valueOf("2024-03-18"));
            //updateStudentEmail(4, "ahmed@example1.com");
            //deleteStudent(4);
            getAllStudents();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}