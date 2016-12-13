package utils.dbManager;

import constants.Constants;
import entity.Messaggio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alfredo Milani on 13/12/2016.
 */
public class GestionePostaDBManager {

    private Connection connection;

    public GestionePostaDBManager() {
        this.connection = DBConnection.getSingleConn();
    }

    public Messaggio getMessaggioCod(int codice) {
        Messaggio messaggio = null;
        String query = String.format(
                Constants.DB_QUERY_SELECT,
                "*",
                Constants.DB_TABLE_MESSAGGI,
                Constants.DB_MESSAGGI_COD + "=?"
        );

        try {
            PreparedStatement statement = connection
                    .prepareStatement(query);

            statement.setInt(1, codice);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String oggettoTmp = result
                        .getString(Constants.DB_MESSAGGI_OBJ);
                String mittenteTmp = result
                        .getString(Constants.DB_MESSAGGI_MIT);
                String destTmp = result
                        .getString(Constants.DB_MESSAGGI_DEST);
                String contTmp = result
                        .getString(Constants.DB_MESSAGGI_CONT);
                String dataTmp = result
                        .getString(Constants.DB_MESSAGGI_DATA);

                messaggio = new Messaggio(oggettoTmp,
                        mittenteTmp, destTmp,
                        contTmp, dataTmp, codice);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messaggio;
    }

    public ArrayList<Messaggio> getMessaggi(String username) {
        ArrayList<Messaggio> messaggi = new ArrayList<>();
        String query = String.format(
                Constants.DB_QUERY_SELECT,
                "*",
                Constants.DB_TABLE_MESSAGGI,
                Constants.DB_MESSAGGI_DEST + "=?"
        );

        try {
            PreparedStatement statement = connection
                    .prepareStatement(query);

            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String oggettoTmp = result
                        .getString(Constants.DB_MESSAGGI_OBJ);
                String mittenteTmp = result
                        .getString(Constants.DB_MESSAGGI_MIT);
                String destTmp = result
                        .getString(Constants.DB_MESSAGGI_DEST);
                String contTmp = result
                        .getString(Constants.DB_MESSAGGI_CONT);
                String dataTmp = result
                        .getString(Constants.DB_MESSAGGI_DATA);
                int codiceTmp = result
                        .getInt(Constants.DB_MESSAGGI_COD);

                messaggi.add(new Messaggio(oggettoTmp,
                        mittenteTmp, destTmp,
                        contTmp, dataTmp, codiceTmp));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messaggi;
    }

    public void setMessaggio(String oggetto, String mittente,
                             String destinatario, String contenuto,
                             String data) {
        String query = String.format(
                Constants.DB_QUERY_INSERT,
                Constants.DB_TABLE_MESSAGGI,
                "(DEFAULT,?,?,?,?,?)"
        );

        try {
            PreparedStatement statement = connection
                    .prepareStatement(query);

            statement.setString(1, oggetto);
            statement.setString(2, mittente);
            statement.setString(3, destinatario);
            statement.setString(4, contenuto);
            statement.setString(5, data);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean rimuoviMessaggio(int codice) {
        boolean esito;
        String query = String.format(
                Constants.DB_QUERY_DELETE,
                Constants.DB_TABLE_MESSAGGI,
                Constants.DB_MESSAGGI_COD + "=?"
        );

        try {
            PreparedStatement statement = connection
                    .prepareStatement(query);

            statement.setInt(1, codice);

            statement.executeUpdate();
            statement.close();
            esito = true;
        } catch (SQLException e) {
            esito = false;
            e.printStackTrace();
        }

        return esito;
    }
}