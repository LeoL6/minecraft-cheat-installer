package connection;

import encryption.BCrypt;
import model.User;

import java.sql.*;

public class DatabaseConnection {
    // Database Configurations
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/login_db";
    private static final String DB_USERNAME = "leo";
    private static final String DB_PASSWORD = "password123";

    public static User validateLogin(String username, String password) {
        try {
            // Establish connection to database
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement preparedSaltStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ?"
            );

            preparedSaltStatement.setString(1, username);

            ResultSet saltResultSet = preparedSaltStatement.executeQuery();

            if (saltResultSet.next()) {

                String salt = saltResultSet.getString("hash");

                salt.substring(0, salt.length() / 2);

                String bcryptHashString = BCrypt.hashpw(password, salt);

                Boolean result = BCrypt.checkpw(password, bcryptHashString);

                if (result) {
                    // Replace the ? with the values
                    // Parameter index referring to the location of the ? by index
                    // Create SQL query
                    PreparedStatement preparedAuthStatement = connection.prepareStatement(
                            "SELECT * FROM users WHERE username = ? AND hash = ?"
                    );

                    preparedAuthStatement.setString(1, username);
                    preparedAuthStatement.setString(2, bcryptHashString);

                    ResultSet resultSet = preparedAuthStatement.executeQuery();

                    if (resultSet.next()) {
                        // Success

                        // Get id
                        int userId = resultSet.getInt("uid");

                        return new User(userId, username, password);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If not valid user
        return null;
    }
}
