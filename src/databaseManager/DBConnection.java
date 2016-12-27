package databaseManager;

import constants.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Alfredo Milani on 12/12/2016.
 */
public class DBConnection {

    // Creato all'atto di caricamento in memoria della classe, thread-safe
    private final static DBConnection CONNECTION = new DBConnection();
    private Connection connection;

    // Costruttore privato, in quanto la creazione dell'istanza deve essere controllata.
    private DBConnection() {
        try {
            Class.forName(Constants.DB_DRIVER);

            this.connection = DriverManager.getConnection(
                    Constants.DB_URL, Constants.DB_USER,Constants.DB_PASSWORD);
            if (this.connection != null) {
                System.out.println("Connected to " + Constants.DB_URL);
            } else {
                System.out.println("Connection failed to " + Constants.DB_URL);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Punto di accesso a DBConnection.
     * @return il Singleton corrispondente
     */
    public static Connection getSingleConn() {
        return CONNECTION.connection;
    }

}