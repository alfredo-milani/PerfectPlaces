package control;

import java.io.File;
import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import util.*;

// Questa classe si occupa della registrazione degli utenti.

public class ControlloreRegistrazione {
	
	
	// Variabili
	
	private String immagine = "default";  // Inizialmente l'utente ha un immagine di default.
	private String percorsoUtenti = Constants.UTENTI_PATH;
	private ArrayList<Utente> utenti = new ArrayList<Utente>();
	
	// Costruttore
	
	public ControlloreRegistrazione(){
		
	}
	
	
	//Inserimento nuovo utente nel file utenti. Viene restituito un intero che indica il tipo di errore.
	// 0 --> Tutto ok
	// 1 --> C'� qualche campo del form vuoto
	// 2 --> Le due password non sono uguali
	// 3 --> Lo username inserito � gi� stato utilizzato
	
	@SuppressWarnings("unchecked")
	public int registrazione(String username, String password, String password2, String nome, String cognome,
			String email) throws DeserializzazioneException, SerializzazioneException{
		
		if(username.equals("") ||
				password.equals("") ||
				password.equals("") ||
				nome.equals("") ||
				cognome.equals("") ||
				email.equals("")) {
			return 1;
		}
		
		if(!password.equals(password2)){
			return 2;
		}
		Utente ut = new Utente(username,password,nome,cognome,email,immagine);
		File file = new File(percorsoUtenti);
		SerializzaOggetti sobj = new SerializzaOggetti();
		if(file.length()==0){
			utenti.add(ut);
			sobj.serializza(utenti, percorsoUtenti);
			return 0;
		}

		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>) dobj.deserializza(percorsoUtenti);

		for(int i = 0; i<utenti.size();i++){
			if(utenti.get(i).getUsername().equals(username)){
				return 3;
			}
		}
		utenti.add(ut);
		sobj.serializza(utenti, percorsoUtenti);
		
		return 0;
	}

}