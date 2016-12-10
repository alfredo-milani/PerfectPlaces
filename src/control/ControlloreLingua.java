package control;

import constants.Constants;
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

    public static Locale getLocale(String lang) {
        return new Locale(lang, "");
    }

    public static Locale getLang() {
        return new Locale(Constants.LANG_DEFAULT, "");
    }

    @SuppressWarnings("unchecked")
    public static Locale getLang(String loggedUser) throws DeserializzazioneException {
        ArrayList<Utente> utenti = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(Constants.UTENTI_PATH);

        for (Utente utente : utenti)
            if (utente.getUsername().equals(loggedUser))
                return utente.getLingua();

        return Locale.getDefault();
    }

    @SuppressWarnings("unchecked")
    public static void updatePref(String username,  Locale newLocale)
            throws DeserializzazioneException, SerializzazioneException {
        ArrayList<Utente> utenteArrayList = (ArrayList<Utente>) DeserializzaOggetti
                .deserializza(Constants.UTENTI_PATH);

        for (Utente utente : utenteArrayList) {
            if (utente.getUsername().equals(username)) {
                utente.setLingua(newLocale);
                break;
            }
        }

        SerializzaOggetti.serializza(utenteArrayList, Constants.UTENTI_PATH);
    }
}