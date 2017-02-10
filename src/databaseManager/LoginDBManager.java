package databaseManager;

import constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alfredo Milani
 */
public class LoginDBManager {

    private final Connection connection;

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

            synchronized (this.connection) {
                PreparedStatement statement = null;
                ResultSet result = null;
                try {
                    statement = connection
                            .prepareStatement(query);

                    statement.setString(1, username);
                    result = statement.executeQuery();
                    if (result.next()) {
                        if ((result.getString(Constants.DB_UTENTI_PSW)
                                .compareTo(password) == 0) &&
                                (result.getString(Constants.DB_UTENTI_US)
                                        .compareTo(username) == 0)) {
                            accesso = 1;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    // Per liberare lo spazio relativo ai cursori
                    // necessari a gestire le seguenti risorse

                    // NOTA: la connessione al DB non viene chiusa SOLO alla chiusura del
                    //       programma dal momento che instaurare una connessione è una
                    //       operazione dispendiosa in termini di risorse

                    // NOTA: chiudere lo statement farà chiudere anche il result
                    //       chiudo result in modo manuale per rendere il codice più robusto
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

        return accesso;
    }
}