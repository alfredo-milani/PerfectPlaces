package control;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.*;

// Questa classe si occupa della registrazione degli utenti.

public class ControlloreRegistrazione {
	private String percorsoUtenti = Constants.UTENTI_PATH;
	private ArrayList<Utente> utenti = new ArrayList<Utente>();
	
	// Costruttore
	public ControlloreRegistrazione() {
	}
	
	//Inserimento nuovo utente nel file utenti. Viene restituito un intero che indica il tipo di errore.
	// 0 --> Tutto ok
	// 1 --> C'è qualche campo del form vuoto
	// 2 --> Le due password non sono uguali
	// 3 --> Lo username inserito è già stato utilizzato
	@SuppressWarnings("unchecked")
	public synchronized int registrazione(String username, String password,
										  String password2, String nome,
                                          String cognome, String email, Locale lingua)
            throws DeserializzazioneException, SerializzazioneException {
		if(username == null ||
                password == null ||
                password2 == null ||
                nome == null ||
                cognome == null ||
                email == null ||
                username.equals("") ||
				password.equals("") ||
				password.equals("") ||
				nome.equals("") ||
				cognome.equals("") ||
				email.equals("")) return 1;
		
		if(!password.equals(password2)) return 2;

		String immagine = "profiloDefault.png";
		Utente ut = new Utente(username, password, nome,
                cognome, email, immagine, lingua);

		File file = new File(percorsoUtenti);
		if(file.length() == 0){
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
