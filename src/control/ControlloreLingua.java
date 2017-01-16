package control;

import constants.Constants;
import databaseManager.LinguaDBManager;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Alfredo Milani on 09/12/2016.
 */
public class ControlloreLingua {

    private LinguaDBManager lDBM;

    public ControlloreLingua() {
        this.lDBM = new LinguaDBManager();
    }

    public static ResourceBundle getBundle(Locale locale) {
        if (locale != null)
            return ResourceBundle
                    .getBundle(Constants.PACKAGE_LANGUAGE, locale);
        else
            return ResourceBundle
                    .getBundle(Constants.PACKAGE_LANGUAGE, Locale.getDefault());
    }

    public Locale getLocaleFromString(String lang) {
        return new Locale(lang, "");
    }

    public Locale getLang() {
        return new Locale(Constants.LANG_DEFAULT, "");
    }

    @SuppressWarnings("unchecked")
    public Locale getLang(String loggedUser)
            throws DeserializzazioneException {

        if (Constants.DB == 1) {

            String lang;
            lang = lDBM.getLingua(loggedUser);
            return lang != null ?
                    getLocaleFromString(lang) : Locale.getDefault();

        } else {
            ArrayList<Utente> utenti = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(Constants.UTENTI_PATH);

            for (Utente utente : utenti)
                if (utente.getUsername().equals(loggedUser))
                    return utente.getLingua();

            return Locale.getDefault();
        }
    }

    // si assume che l'utente 'username' sia loggato
    @SuppressWarnings("unchecked")
    public synchronized Locale checkUpdatePref(String username,
                                               String newLocale) {
        Locale userPref = null;

        if (Constants.DB == 1) {

            try {
                userPref = getLang(username);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }
            if (newLocale != null) {
                Locale locale = getLocaleFromString(newLocale);
                if (locale != userPref)
                    lDBM.aggiornaPref(username, newLocale);

                return locale;
            }

        } else {

            ArrayList<Utente> utenteArrayList = null;
            try {
                utenteArrayList = (ArrayList<Utente>) DeserializzaOggetti
                        .deserializza(Constants.UTENTI_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            for (Utente utente : utenteArrayList)
                if (utente.getUsername().equals(username)) {
                    Locale userPrefS = utente.getLingua();
                    if (userPrefS != null && newLocale != null) {
                        Locale locale = getLocaleFromString(newLocale);
                        if (locale != userPrefS)
                            utente.setLingua(locale);
                    }

                    break;
                }

            try {
                SerializzaOggetti.serializza(utenteArrayList,
                        Constants.UTENTI_PATH);
            } catch (SerializzazioneException e) {
                e.printStackTrace();
            }

        }




        return userPref;
    }

    public String getStringFromLocale(Locale locale) {
        if (locale.getDisplayLanguage()
                .equals(Locale.ENGLISH.getDisplayLanguage()))
            return Constants.EN;
        else if (locale.getDisplayLanguage()
                .equals(Locale.ITALIAN.getDisplayLanguage()))
            return Constants.IT;
        else
            return Constants.LANG_DEFAULT;
    }
}