package utils.dbManager;

import constants.Constants;
import entity.Messaggio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Alfredo Milani on 13/12/2016.
 */
public class GestionePostaDBManager {

    private Connection connection;

    public GestionePostaDBManager() {
        this.connection = DBConnection.getSingleConn();
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

                Messaggio msgTmp = new Messaggio(oggettoTmp,
                        mittenteTmp, destTmp, contTmp);
            }

            statement.close();
        } catch (SQLException e) {
            messaggi = null;
            e.printStackTrace();
        }

        return messaggi;
    }
}