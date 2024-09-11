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

            // Replace the ? with the values
            // Parameter index referring to the location of the ? by index
            // Create SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND password = ?"
            );

            String bcryptHashString = BCrypt.hashpw(password, BCrypt.gensalt());

            System.out.println(bcryptHashString);

            Boolean result = BCrypt.checkpw(password, bcryptHashString);

            System.out.println(result);

            if (result) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Success

                    // Get id
                    int userId = resultSet.getInt("idusers");

                    return new User(userId, username, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If not valid user
        return null;
    }
}
