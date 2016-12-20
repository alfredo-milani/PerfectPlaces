package databaseManager;

import com.sun.istack.internal.NotNull;
import constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alfredo Milani on 12/12/2016.
 */
public class LoginDBManager {

    private Connection connection;

    public LoginDBManager() {
        this.connection = DBConnection.getSingleConn();
    }

    // -1 --> DB non accessibile
    //  1 --> query effettuata con successo, utente trovato
    //  0 --> query effettuata con successo, utente non trovato
    public int accesso(String username, String password) {
        int accesso = -1;
        if (this.connection != null) {
            accesso = 0;
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
                    if ((result.getString(Constants.DB_UTENTI_PSW)
                            .compareTo(password) == 0) &&
                            (result.getString(Constants.DB_UTENTI_US)
                                    .compareTo(username) == 0)) {
                        accesso = 1;
                    }
                }

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accesso;
    }
}