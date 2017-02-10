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
 * Created by Alfredo Milani
 */
public class ControlloreLingua {

    private LinguaDBManager lDBM;

    public ControlloreLingua() {
        if (Constants.DB == 1)
            this.lDBM = new LinguaDBManager();
    }


    /**
     * Metodo che fornisce il giusto ResourceBundle corrispondente al path (PACKAGE_LANGUAGE),
     * in funzione della lingua scelta
     * @param locale lingua scelta dall'utente
     * @return ResourceBundle corrispondente alla lingua scelta contenente informazioni
     *      del tipo key=value
     */
    public static ResourceBundle getBundle(Locale locale) {
        if (locale != null)
            return ResourceBundle
                    .getBundle(Constants.PACKAGE_LANGUAGE, locale);
        else
            return ResourceBundle
                    .getBundle(Constants.PACKAGE_LANGUAGE, Locale.getDefault());
    }

    /**
     * Conversione informazioni riguardante la scelta della lingua
     * @param lang lingua scelta in formato String
     * @return lingua scelta in formato Locale
     */
    public Locale getLocaleFromString(String lang) {
        return new Locale(lang, "");
    }

    /**
     * Ottiene la lingua di default del sistema corrente
     * @return Informazioni sulla lingua in formato Locale
     */
    public Locale getLang() {
        return new Locale(Constants.LANG_DEFAULT, "");
    }

    /**
     * Ottiene la lingua preferita dall utente @loggedUser
     * @param loggedUser utente che richiede informazioni sull sua preferenza
     * @return lingua preferita dall'utente in formato Locale
     */
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

    /**
     * Si assume che l'utente @username abbia già fatto l'accesso al sistema
     * Il seguente metodo, nel caso in cui sia necessario, aggiorna le preferenze dell'utente
     * @param username nome utente dell'attore che necessita di aggiornare le proprie preferenze
     * @param newLocale nuova preferenza dell'utente da salvare
     * @return
     */
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

    /**
     * Conversione di formato dell'informazione riguardante la preferenza sulla lingua dal formato
     * Locale a String
     * @param locale oggetto da convertire
     * @return oggetto convertito (in formato String)
     */
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