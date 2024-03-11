package com.example.javafx_plus_willpower;

import java.sql.*;

public class DatabaseUtilities {
    private static PreparedStatement preparedStatement;
    private static Connection con;
    private DatabaseUtilities() {
        // Private constructor to prevent instantiation from outside the class
    }
    // Method to obtain a database connection based on the provided query
    private static Connection getConnection(String query) {
        try {
            // Load the JDBC driver and establish a connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbjavafx", "root", "Chua123");
            preparedStatement = con.prepareStatement(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    // Method to insert user data into the database
    public static String signUpMethod(String username, String password) {
        String query = "INSERT INTO tbluseraccount (user_name, password_hashed) VALUES (?, ?)";
        try (Connection connection = getConnection(query)) {
            // Set parameters for the prepared statement
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Execute the update and check the affected rows
            int rowAffected = preparedStatement.executeUpdate();

            // Return a success or failure message based on the result
            return (rowAffected > 0) ? "Data inserted successfully!" : "Failed to insert data.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to insert data.";
        }
    }
    // Method to check user credentials in the database
    public static Boolean userCheckerMethod(String username, String password) {
        String query = "SELECT * FROM tbluseraccount WHERE user_name = ? AND password_hashed = ?";
        try (Connection connection = getConnection(query)) {
            // Set parameters for the prepared statement
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Execute the query and check if a result is found
            ResultSet resultSet = preparedStatement.executeQuery();

            // Return a success or failure message based on the result
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
