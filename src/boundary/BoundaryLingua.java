package boundary;

import control.ControlloreLingua;
import exception.DeserializzazioneException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by alfredo on 18/12/16.
 */
public class BoundaryLingua {

    private ControlloreLingua cL;

    public BoundaryLingua() {
        cL = new ControlloreLingua();
    }

    public ResourceBundle riceviBundle(Locale locale) {
        return ControlloreLingua.getBundle(locale);
    }

    public Locale riceviLocaleDaString(String lang) {
                return cL.getLocaleFromString(lang);
    }

    public String riceviStringDaLocale(Locale locale) {
        return cL.getStringFromLocale(locale);
    }

    public Locale riceviLinguaDefault() {
        return cL.getLang();
    }

    public  Locale riceviLingua(String user) {
        Locale locale = Locale.getDefault();
        try {
            return cL.getLang(user);
        } catch (DeserializzazioneException e) {
            e.printStackTrace();
        }

        return locale;
    }

    public Locale aggiornaPreferenze(String username, String lang) {
        return cL.checkUpdatePref(username, lang);
    }
}
