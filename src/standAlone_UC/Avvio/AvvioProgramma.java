package standAlone_UC.Avvio;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import constants.Constants;
import entity.Utente;
import exception.SerializzazioneException;
import standAlone_UC.boundary.ConfineAvvio;
import utils.SerializzaOggetti;

public class AvvioProgramma {
	
	private String percorsoAmministratori = Constants.ADMIN_PATH;
	
	private AvvioProgramma(){
		
	}
	
	// Se il file percorsoAmministratori è vuoto, il metodo aggiunge l'amministratore root
	private void checkRoot() throws SerializzazioneException{
		File file = new File(percorsoAmministratori);
		if(file.length() == 0){
			ArrayList<Utente> amministratori = new ArrayList<Utente>();
			Utente u = new Utente("root","root","root",
					"root","root",
                    "root", null, null, null);
			amministratori.add(u);
			SerializzaOggetti.serializza(amministratori, percorsoAmministratori);
		}
	}
	
	public static void main(String[] args)
			throws SerializzazioneException {

        Locale defaultLocale = Locale.getDefault();
        Locale locale = new Locale("en", "US");
        ResourceBundle bundle2 = ResourceBundle.getBundle("language.Lang", locale);

		AvvioProgramma ap = new AvvioProgramma();
		ap.checkRoot();		
		new ConfineAvvio();
	}
}
