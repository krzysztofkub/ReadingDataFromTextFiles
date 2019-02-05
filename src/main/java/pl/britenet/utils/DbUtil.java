package pl.britenet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static Connection connection;
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/britenet?useSSL=false&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASS);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Connection to database failed");
        }
        return connection;
    }
}