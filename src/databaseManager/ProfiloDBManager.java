package databaseManager;

import constants.Constants;
import control.ControlloreLingua;
import entity.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by Alfredo Milani on 12/12/2016.
 */
public class ProfiloDBManager {

    private Connection connection;
    private GestioneDB gDB;

    public ProfiloDBManager() {
        this.connection = DBConnection.getSingleConn();
        this.gDB = new GestioneDB();
    }

    public Utente getUtente(String username) {
        ControlloreLingua controlloreLingua = new ControlloreLingua();
        Locale lang;

        if (gDB.usernameEsistente(username)) {
            String[] utenteInfo = {null, null, null,
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
                    utenteInfo[0] = result
                            .getString(Constants.DB_UTENTI_US);
                    utenteInfo[1] = result
                            .getString(Constants.DB_UTENTI_PSW);
                    utenteInfo[2] = result
                            .getString(Constants.DB_UTENTI_NOME);
                    utenteInfo[3] = result
                            .getString(Constants.DB_UTENTI_COGNOME);
                    utenteInfo[4] = result
                            .getString(Constants.DB_UTENTI_EMAIL);
                    utenteInfo[5] = result
                            .getString(Constants.DB_UTENTI_IMM);
                    utenteInfo[6] = result
                            .getString(Constants.DB_UTENTI_LINGUA);
                    utenteInfo[7] = result
                            .getString(Constants.DB_UTENTI_NASCITA);
                    utenteInfo[8] = result
                            .getString(Constants.DB_UTENTI_SESSO);
                }

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            lang = controlloreLingua.getLocaleFromString(utenteInfo[6]);
            return new Utente(utenteInfo[0], utenteInfo[1], utenteInfo[2],
                    utenteInfo[3], utenteInfo[4], utenteInfo[5], lang,
                    utenteInfo[7], utenteInfo[8]);
        } else {
            lang = controlloreLingua.getLang();
            return new Utente("errore", "errore", "errore",
                    "errore", "errore", "errore", lang,
                    "errore", "errore");
        }
    }

    public void aggiornaStato(Utente utente) {
        ControlloreLingua controlloreLingua = new ControlloreLingua();

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

            statement.setString(1, utente.getUsername());
            statement.setString(2, utente.getPassword());
            statement.setString(3, utente.getNome());
            statement.setString(4, utente.getCognome());
            statement.setString(5, utente.getEmail());
            statement.setString(6, utente.getImmagine());
            statement.setString(7, controlloreLingua
                    .getStringFromLocale(utente.getLingua()));
            statement.setString(8, utente.getNascita());
            statement.setString(9, utente.getSesso());
            statement.setString(10, utente.getUsername());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}