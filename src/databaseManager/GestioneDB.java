package databaseManager;

import constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alfredo
 */
public class GestioneDB {

    private final Connection connection;

    public GestioneDB() {
        this.connection = DBConnection.getSingleConn();
    }

    public Boolean usernameEsistente (String username) {
        boolean esiste = false;
        if (this.connection != null) {
            String query = String.format(
                    Constants.DB_QUERY_SELECT,
                    "*",
                    Constants.DB_TABLE_UTENTI,
                    Constants.DB_UTENTI_US + "=?"
            );

            synchronized (this.connection) {
                PreparedStatement statement = null;
                ResultSet result = null;
                try {
                    statement = connection
                            .prepareStatement(query);

                    statement.setString(1, username);
                    result = statement.executeQuery();
                    if (result.next())
                        esiste = true;
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (result != null) {
                        try {
                            result.close();
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }
                    }
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }
                    }
                }
            }
        }

        return esiste;
    }

}
