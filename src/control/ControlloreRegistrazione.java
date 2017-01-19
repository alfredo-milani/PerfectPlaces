package control;

import constants.Constants;
import databaseManager.RegistrazioneDBManager;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

// Questa classe si occupa della registrazione degli utenti.

public class ControlloreRegistrazione {

	private ArrayList<Utente> utenti;
	private RegistrazioneDBManager rDBM;

	// Costruttore
	public ControlloreRegistrazione() {
	    if (Constants.DB == 1) {
            this.rDBM = new RegistrazioneDBManager();
        } else {
            this.utenti = new ArrayList<>();
        }
	}
	
	//Inserimento nuovo getUtente nel file utenti. Viene restituito un intero che indica il tipo di errore.
	// 0 --> Tutto ok
	// 1 --> C'è qualche campo del form vuoto
	// 2 --> Le due password non sono uguali
	// 3 --> Lo username inserito è già stato utilizzato
	public int registrazione(String username, String password,
										  String password2, String nome,
                                          String cognome, String email, Locale lingua,
                                          String nascita, String sesso)
            throws DeserializzazioneException, SerializzazioneException {

        if (Constants.DB == 1) {

            if (username == null ||
                    password == null ||
                    password2 == null ||
                    nome == null ||
                    cognome == null ||
                    email == null ||
                    username.equals("") ||
                    password.equals("") ||
                    password2.equals("") ||
                    nome.equals("") ||
                    cognome.equals("") ||
                    email.equals(""))
                return 1;

            if (rDBM.controlloUsername(username))
                return 3;

            if (!password.equals(password2))
                return 2;

            ControlloreLingua controlloreLingua = new ControlloreLingua();
            String lang = controlloreLingua.getStringFromLocale(lingua);
            String immagine = "profiloDefault.png";

            int result = rDBM.inserisciUtente(username, password, nome,
                    cognome, email, lang,
                    immagine, nascita, sesso);
            if (result != 1)
                return 1;

            return 0;

        } else {


            if (username == null ||
                    password == null ||
                    password2 == null ||
                    nome == null ||
                    cognome == null ||
                    email == null ||
                    username.equals("") ||
                    password.equals("") ||
                    password2.equals("") ||
                    nome.equals("") ||
                    cognome.equals("") ||
                    email.equals("")) return 1;

            if (!password.equals(password2)) return 2;

            String immagine = "profiloDefault.png";
            Utente ut = new Utente(username, password, nome,
                    cognome, email, immagine, lingua, nascita, sesso);

            File file = new File(Constants.UTENTI_PATH);
            if (file.length() == 0) {
                utenti.add(ut);
                SerializzaOggetti.serializza(utenti, Constants.UTENTI_PATH);
                return 0;
            }

            utenti = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(Constants.UTENTI_PATH);

            for (Utente utente : utenti)
                if (utente.getUsername().equals(username))
                    return 3;

            utenti.add(ut);
            SerializzaOggetti.serializza(utenti, Constants.UTENTI_PATH);

            return 0;

        }
	}
}
