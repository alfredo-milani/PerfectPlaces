package databaseManager;

import constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alfredo Milani on 12/12/2016.
 */
public class GestioneProfiloDBManager {

    private Connection connection;

    public GestioneProfiloDBManager() {
        this.connection = DBConnection.getSingleConn();
    }

    public String[] getUtente(String username) {
        String[] utente = {null, null, null,
            null, null, null, null, null, null};
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
                utente[0] = result
                        .getString(Constants.DB_UTENTI_US);
                utente[1] = result
                        .getString(Constants.DB_UTENTI_PSW);
                utente[2] = result
                        .getString(Constants.DB_UTENTI_NOME);
                utente[3] = result
                        .getString(Constants.DB_UTENTI_COGNOME);
                utente[4] = result
                        .getString(Constants.DB_UTENTI_EMAIL);
                utente[5] = result
                        .getString(Constants.DB_UTENTI_IMM);
                utente[6] = result
                        .getString(Constants.DB_UTENTI_LINGUA);
                utente[7] = result
                        .getString(Constants.DB_UTENTI_NASCITA);
                utente[8] = result
                        .getString(Constants.DB_UTENTI_SESSO);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utente;
    }

    public void aggiornaStato(String[] info) {
        String query = String.format(
                Constants.DB_QUERY_UPDATE,
                Constants.DB_TABLE_UTENTI,
                Constants.DB_UTENTI_US + "=?," +
                        Constants.DB_UTENTI_PSW + "=?," +
                        Constants.DB_UTENTI_NOME + "=?," +
                        Constants.DB_UTENTI_COGNOME + "=?," +
                        Constants.DB_UTENTI_EMAIL + "=?," +
                        Constants.DB_UTENTI_IMM + "=?," +
                        Constants.DB_UTENTI_LINGUA + "=?," +
                        Constants.DB_UTENTI_NASCITA + "=?," +
                        Constants.DB_UTENTI_SESSO + "=?",
                Constants.DB_UTENTI_US + "=?"
        );

        try {
            PreparedStatement statement = connection
                    .prepareStatement(query);

            statement.setString(1, info[0]);
            statement.setString(2, info[1]);
            statement.setString(3, info[2]);
            statement.setString(4, info[3]);
            statement.setString(5, info[4]);
            statement.setString(6, info[5]);
            statement.setString(7, info[6]);
            statement.setString(8, info[7]);
            statement.setString(9, info[8]);
            statement.setString(10, info[0]);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
