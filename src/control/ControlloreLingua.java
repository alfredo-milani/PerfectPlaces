package control;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;
import utils.dbManager.LinguaDBManager;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Alfredo Milani on 09/12/2016.
 */
public class ControlloreLingua {

    private LinguaDBManager lDBM = new LinguaDBManager();

    public ControlloreLingua() {
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

    @SuppressWarnings("unchecked")
    public synchronized void updatePref(String username,  Locale newLocale)
            throws DeserializzazioneException, SerializzazioneException {

        if (Constants.DB == 1) {

            String lang = getStringFromLocale(newLocale);
            lDBM.aggiornaPref(username, lang);

        } else {

            ArrayList<Utente> utenteArrayList = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(Constants.UTENTI_PATH);

            for (Utente utente : utenteArrayList)
                if (utente.getUsername().equals(username)) {
                    utente.setLingua(newLocale);
                    break;
                }

            SerializzaOggetti.serializza(utenteArrayList, Constants.UTENTI_PATH);

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