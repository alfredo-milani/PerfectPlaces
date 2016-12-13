package standAlone_UseCase.Avvio;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import standAlone_UseCase.boundary.ConfineAvvio;
import constants.Constants;
import entity.Utente;
import exception.SerializzazioneException;
import utils.SerializzaOggetti;

public class AvvioProgramma {
	
	private String percorsoAmministratori = Constants.ADMIN_PATH;
	
	// Costruttore
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
	
	public static void main(String[] args) throws SerializzazioneException {
	    // questo sara il file dove sara salvata la stringa che
        // indichera la preferenza relativa alla lingua dell getUtente

        // NOTA: ogni getUtente avra la sua preferenza di lingua quindi bisogna
        // modifica leggermente il pack entity getUtente

	    // File file = new File();
        Locale defaultLocale = Locale.getDefault();
        Locale locale = new Locale("en", "US");
        ResourceBundle bundle2 = ResourceBundle.getBundle("language.Lang", locale);
        /*
        Locale swedishLocale = new Locale("sv", "SE");
        ResourceBundle bundle3 = ResourceBundle.getBundle("TestBundle", swedishLocale);
        */

		AvvioProgramma ap = new AvvioProgramma();
		ap.checkRoot();		
		new ConfineAvvio();
	}
}
