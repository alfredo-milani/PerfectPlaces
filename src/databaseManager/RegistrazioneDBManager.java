package databaseManager;

import constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alfredo Milani on 12/12/2016.
 */
public class RegistrazioneDBManager {

    private Connection connection;

    public RegistrazioneDBManager() {
        this.connection = DBConnection.getSingleConn();
    }

    public int inserisciUtente (String un, String psw,
                                String nome, String cognome,
                                String email, String lingua,
                                 String immagine, String nascita,
                                 String sesso) {
        int result = 0;
        if (this.connection != null) {
            String query = String.format(
                    Constants.DB_QUERY_INSERT,
                    Constants.DB_TABLE_UTENTI,
                    "(?,?,?,?,?,?,?,?,?)"
            );

            try {
                PreparedStatement statement = connection
                        .prepareStatement(query);

                statement.setString(1, un);
                statement.setString(2, psw);
                statement.setString(3, nome);
                statement.setString(4, cognome);
                statement.setString(5, email);
                statement.setString(6, immagine);
                statement.setString(7, lingua);
                statement.setString(8, nascita);
                statement.setString(9, sesso);

                statement.executeUpdate();
                statement.close();
                result = 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
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