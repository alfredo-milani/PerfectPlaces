package databaseManager;

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
public class PostaDBManager {

    private final Connection connection;
    private final GestioneDB gDB;

    public PostaDBManager() {
        this.connection = DBConnection.getSingleConn();
        this.gDB = new GestioneDB();
    }

    public Messaggio getMessaggioCod(int codice) {
        Messaggio messaggio = null;
        if (this.connection != null) {
            String query = String.format(
                    Constants.DB_QUERY_SELECT,
                    "*",
                    Constants.DB_TABLE_MESSAGGI,
                    Constants.DB_MESSAGGI_COD + "=?"
            );

            synchronized (this.connection) {
                PreparedStatement statement = null;
                try {
                    statement = connection
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
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return messaggio;
    }

    public ArrayList<Messaggio> getMessaggi(String username) {
        ArrayList<Messaggio> messaggi = new ArrayList<>();
        if (this.connection != null) {
            String query = String.format(
                    Constants.DB_QUERY_SELECT,
                    "*",
                    Constants.DB_TABLE_MESSAGGI,
                    Constants.DB_MESSAGGI_DEST + "=?"
            );

            synchronized (this.connection) {
                PreparedStatement statement = null;
                ResultSet result = null;
                try {
                    statement = connection
                            .prepareStatement(query);

                    statement.setString(1, username);
                    result = statement.executeQuery();
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

        return messaggi;
    }

    public void setMessaggio(String oggetto, String mittente,
                             String destinatario, String contenuto,
                             String data) {
        if (this.connection != null) {
            String query = String.format(
                    Constants.DB_QUERY_INSERT,
                    Constants.DB_TABLE_MESSAGGI,
                    "(DEFAULT,?,?,?,?,?)"
            );

            synchronized (this.connection) {
                PreparedStatement statement = null;
                try {
                    statement = connection
                            .prepareStatement(query);

                    statement.setString(1, oggetto);
                    statement.setString(2, mittente);
                    statement.setString(3, destinatario);
                    statement.setString(4, contenuto);
                    statement.setString(5, data);

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

    public boolean rimuoviMessaggio(int codice) {
        boolean esito = false;
        if (this.connection != null) {
            String query = String.format(
                    Constants.DB_QUERY_DELETE,
                    Constants.DB_TABLE_MESSAGGI,
                    Constants.DB_MESSAGGI_COD + "=?"
            );

            synchronized (this.connection) {
                PreparedStatement statement = null;
                try {
                    statement = connection
                            .prepareStatement(query);

                    statement.setInt(1, codice);

                    statement.executeUpdate();
                    esito = true;
                } catch (SQLException e) {
                    esito = false;
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

        return esito;
    }

    public boolean controlloUsername(String username) {
        return gDB.usernameEsistente(username);
    }

}