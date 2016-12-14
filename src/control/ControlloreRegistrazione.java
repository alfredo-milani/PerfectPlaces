package control;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.*;
import databaseManager.RegistrazioneDBManager;

// Questa classe si occupa della registrazione degli utenti.

public class ControlloreRegistrazione {
	private String percorsoUtenti = Constants.UTENTI_PATH;
	private ArrayList<Utente> utenti = new ArrayList<>();
	private RegistrazioneDBManager rDBM;
	
	// Costruttore
	public ControlloreRegistrazione() {
	    this.rDBM = new RegistrazioneDBManager();
	}
	
	//Inserimento nuovo getUtente nel file utenti. Viene restituito un intero che indica il tipo di errore.
	// 0 --> Tutto ok
	// 1 --> C'è qualche campo del form vuoto
	// 2 --> Le due password non sono uguali
	// 3 --> Lo username inserito è già stato utilizzato
	@SuppressWarnings("unchecked")
	public synchronized int registrazione(String username, String password,
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

            if (!password.equals(password2))
                return 2;

            if (rDBM.usernameEsistente(username))
                return 3;

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

            File file = new File(percorsoUtenti);
            if (file.length() == 0) {
                utenti.add(ut);
                SerializzaOggetti.serializza(utenti, percorsoUtenti);
                return 0;
            }

            utenti = (ArrayList<Utente>) DeserializzaOggetti.deserializza(percorsoUtenti);

            for (Utente utente : utenti)
                if (utente.getUsername().equals(username))
                    return 3;

            utenti.add(ut);
            SerializzaOggetti.serializza(utenti, percorsoUtenti);

            return 0;

        }
	}
}
