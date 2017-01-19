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
        if (Constants.DB == 1)
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

    public Locale getLang(String loggedUser) {

        if (Constants.DB == 1) {

            String lang;
            lang = lDBM.getLingua(loggedUser);
            return lang != null ?
                    getLocaleFromString(lang) : Locale.getDefault();

        } else {

            ArrayList<Utente> utenti = new ArrayList<>();
            try {
                utenti = (ArrayList<Utente>) DeserializzaOggetti
                        .deserializza(Constants.UTENTI_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            for (Utente utente : utenti)
                if (utente.getUsername().equals(loggedUser))
                    return utente.getLingua();

            return Locale.getDefault();

        }
    }

    // si assume che l'utente 'username' sia loggato
    public Locale checkUpdatePref(String username,
                                               String newLocale) {

        if (Constants.DB == 1) {

            Locale userPref = getLang(username);
            if (newLocale != null) {
                Locale locale = getLocaleFromString(newLocale);
                if (locale != userPref)
                    lDBM.aggiornaPref(username, newLocale);

                return locale;
            }

            return userPref;

        } else {

            ArrayList<Utente> utenteArrayList = new ArrayList<>();
            try {
                utenteArrayList = (ArrayList<Utente>)
                        DeserializzaOggetti
                                .deserializza(Constants.UTENTI_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            ControlloreLingua cl = new ControlloreLingua();
            Locale userPref = cl.getLocaleFromString(newLocale);
            for (Utente utente : utenteArrayList)
                if (utente.getUsername().equals(username)) {

                    utente.setLingua(userPref);
                    break;
                }

            try {
                SerializzaOggetti.serializza(utenteArrayList, Constants.UTENTI_PATH);
            } catch (SerializzazioneException e) {
                e.printStackTrace();
            }

            return userPref;

        }

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