package databaseManager;

import constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

                statement.setString(1, psw);
                statement.setString(2, nome);
                statement.setString(3, cognome);
                statement.setString(4, email);
                statement.setString(5, immagine);
                statement.setString(6, lingua);
                statement.setString(7, nascita);
                statement.setString(8, sesso);
                statement.setString(9, un);

                statement.executeUpdate();
                statement.close();
                result = 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}