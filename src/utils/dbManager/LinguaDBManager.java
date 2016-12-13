package utils.dbManager;

import constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alfredo Milani on 12/12/2016.
 */
public class LinguaDBManager {

    private Connection connection;

    public LinguaDBManager() {
        this.connection = DBConnection.getSingleConn();
    }

    public void aggiornaPref(String username, String lang) {
        String query = String.format(
                Constants.DB_QUERY_UPDATE,
                Constants.DB_TABLE_UTENTI,
                Constants.DB_UTENTI_LINGUA + "=?",
                Constants.DB_UTENTI_US + "=?"
        );

        try {
            PreparedStatement statement = connection
                    .prepareStatement(query);

            statement.setString(1, lang);
            statement.setString(2, username);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getLingua(String username) {
        String lang = null;
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
            if (result.next()) {
                lang = result
                        .getString(Constants.DB_UTENTI_LINGUA);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lang;
    }
}
