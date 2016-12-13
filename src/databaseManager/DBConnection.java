package databaseManager;

import constants.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Alfredo Milani on 12/12/2016.
 */
class DBConnection {

    // SINGLETON PATTERN per tentare una sola connessiona al DB
    private Connection connection;

    private static class LazyConn {
        private final static DBConnection CONNECTION =
                new DBConnection();
    }

    private DBConnection() {
        try {
            Class.forName(Constants.DB_DRIVER);

            this.connection = DriverManager.getConnection(
                    Constants.DB_URL, Constants.DB_USER,Constants.DB_PASSWORD);
            if (connection != null) {
                System.out.println("Connected to " + Constants.DB_URL);
            } else {
                System.out.println("Connection failed to " + Constants.DB_URL);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static Connection getSingleConn() {
        return LazyConn.CONNECTION.connection;
    }

}