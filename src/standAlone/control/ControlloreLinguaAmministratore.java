package standAlone.control;

import constants.Constants;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by alfredo on 17/01/17.
 */
public class ControlloreLinguaAmministratore {

    public ResourceBundle getBundleFromProp() {
        String lang = System.getProperty(
                Constants.LINGUA_KEY, null);
        Locale langLocale = lang == null ?
                Locale.getDefault() :
                new Locale(lang, "");

        return ResourceBundle.getBundle(
                        Constants.PACKAGE_LANGUAGE,
                        langLocale);
    }
}
