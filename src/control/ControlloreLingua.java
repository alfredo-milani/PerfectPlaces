package control;

import constants.Constants;
import databaseManager.LinguaDBManager;

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
    public Locale getLang(String loggedUser) {

        String lang;
        lang = lDBM.getLingua(loggedUser);
        return lang != null ?
                getLocaleFromString(lang) : Locale.getDefault();
    }

    // si assume che l'utente 'username' sia loggato
    public synchronized Locale checkUpdatePref(String username,
                                               String lang) {
        Locale userPref = getLang(username);
        if (lang != null) {
            Locale locale = getLocaleFromString(lang);
            if (locale != userPref)
                lDBM.aggiornaPref(username, lang);

            return locale;
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