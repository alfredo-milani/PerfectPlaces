package utils;

import constants.Constants;
import databaseManager.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alfredo on 27/12/16.
 */
public class GestioneDB {

    private Connection connection;

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

            try {
                PreparedStatement statement = connection
                        .prepareStatement(query);

                statement.setString(1, username);
                ResultSet result = statement.executeQuery();
                if (result.next())
                    esiste = true;

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return esiste;
    }

}
