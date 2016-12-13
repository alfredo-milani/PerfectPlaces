package utils.dbManager;

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

    public Boolean accesso(String username, String password) {
        boolean accesso = false;
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
                    accesso = true;
                }
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accesso;
    }
}