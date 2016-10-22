package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.*;

// Classe che consente la gestione del profilo personale, in particolare la restituzione dei valori
// associati ad un particolare utente, modificarli, e modificare l'immagine.
public class ControlloreGestioneProfilo {
	
	// Percorso
	private String percorsoUtenti = Constants.UTENTI_PATH;
	
	// Costruttore
	public ControlloreGestioneProfilo(){
	}
	
	// Viene dato in input un username, il metodo ricerca nel file l'oggetto Utente corrispondente e lo restituisce.
	@SuppressWarnings("unchecked")
	public synchronized Utente ottieniUtente(String username) throws DeserializzazioneException {
		// Caso di default in cui non venga trovato l'utente nel file
		Utente u = new Utente("errore", "errore", "errore", "errore", "errore", "errore");
		
		ArrayList<Utente> utenti;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>) dobj.deserializza(percorsoUtenti);

		for (Utente anUtenti : utenti)
			if (anUtenti.getUsername().equals(username))
				u = anUtenti;

		return u;
	}
	
	// Metodo che consente la modifca del profilo, viene effettuato un controllo sull'input per verificare che tutti i parametri
	// siano corretti, se tutto è corretto prende l'utente corrispondente dal file, ne modifica i parametri e lo salva sul file.
	// L'unico campo che non è modificabile èl'username, perchè consente di identificare univocamente l'utente nel sistema.
	
	// Ritorna: 1 --> Password vecchia non corretta
	// 		    2 --> La nuova password e la conferma della nuova password non sono uguali
	//			3 --> La nuova password è vuota
	//			4 --> Sessione utente scaduta
	//			0 --> Se tutto va bene
	@SuppressWarnings("unchecked")
	public synchronized int modificaProfilo(String username, String nome, String cognome,
			String email, String vecchiaPassword, String nuovaPassword, String confermaNuovaPassword) throws DeserializzazioneException, SerializzazioneException{
		ArrayList<Utente> utenti;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>) dobj.deserializza(percorsoUtenti);
		SerializzaOggetti sobj = new SerializzaOggetti();

        if (username == null || nome == null ||
                cognome == null || email == null ||
                vecchiaPassword == null || nuovaPassword == null ||
                confermaNuovaPassword == null) return 4;

		for (int i = 0; i < utenti.size(); ++i){
			if(utenti.get(i).getUsername().equals(username)){
				if(!vecchiaPassword.equals("")){
					if(!vecchiaPassword.equals(utenti.get(i).getPassword()))
						return 1;
					else if(!nuovaPassword.equals(confermaNuovaPassword))
						return 2;
					else if(nuovaPassword.equals(""))
						return 3;
				}

				// Se il campo vecchia password è vuoto non viene restituito errore,
                // infatti un utente potrebbe scegliere di modificare
				// il suo profilo senza modificare la password.
				if (vecchiaPassword.equals("")){
					utenti.get(i).setNome(nome);
					utenti.get(i).setCognome(cognome);
					utenti.get(i).setEmail(email);
					sobj.serializza(utenti, percorsoUtenti);
					return 0;
				}

				utenti.get(i).setNome(nome);
				utenti.get(i).setCognome(cognome);
				utenti.get(i).setEmail(email);
				utenti.get(i).setPassword(nuovaPassword);
			}
		}
		
		sobj.serializza(utenti, percorsoUtenti);
		return 0;
	}
}
