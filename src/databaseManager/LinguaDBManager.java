package databaseManager;

import constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alfredo Milani on 12/12/2016.
 */
public class LinguaDBManager {

    private final Connection connection;

    public LinguaDBManager() {
        this.connection = DBConnection.getSingleConn();
    }

    public void aggiornaPref(String username, String lang) {
        if (this.connection != null) {
            String query = String.format(
                    Constants.DB_QUERY_UPDATE,
                    Constants.DB_TABLE_UTENTI,
                    Constants.DB_UTENTI_LINGUA + "=?",
                    Constants.DB_UTENTI_US + "=?"
            );

            synchronized (this.connection) {
                PreparedStatement statement = null;
                try {
                    statement = connection
                            .prepareStatement(query);

                    statement.setString(1, lang);
                    statement.setString(2, username);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
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
    }

    public String getLingua(String username) {
        String lang = null;
        String query = String.format(
                Constants.DB_QUERY_SELECT,
                "*",
                Constants.DB_TABLE_UTENTI,
                Constants.DB_UTENTI_US + "=?"
        );

        if (this.connection != null) {
            synchronized (this.connection) {
                PreparedStatement statement = null;
                ResultSet result = null;
                try {
                    statement = connection
                            .prepareStatement(query);

                    statement.setString(1, username);
                    result = statement.executeQuery();
                    if (result.next()) {
                        lang = result
                                .getString(Constants.DB_UTENTI_LINGUA);
                    }
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

        return lang;
    }
}
