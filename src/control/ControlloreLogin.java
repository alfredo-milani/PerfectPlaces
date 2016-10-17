package control;

import java.io.File;
import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

// Classe che gestisce il login al sistema.

public class ControlloreLogin {
	
	// Variabili
	
	private String username;
	private String password;
	private ArrayList<Utente> utenti;
	private boolean logged_in = false;
	private String percorsoUtenti = Constants.UTENTI_PATH;
	
	
	// Costruttore
	public ControlloreLogin(){


	}
	
	
	// Deserializza gli Utenti del file utenti e li posiziona in un ArrayList. Viene fatta una scansione
	// di tutti gli elementi e viene verificata la corrispondenza di Username e Password inserite
	
	@SuppressWarnings("unchecked")
	public void login(String username, String password) throws DeserializzazioneException{
		File file = new File(percorsoUtenti);
		
		if(file.length() == 0)
			return;
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>) dobj.deserializza(percorsoUtenti);

		for (Utente anUtenti : utenti)
			if (anUtenti.getUsername().equals(username)) {
				if (anUtenti.getPassword().equals(password)) {
					logged_in = true;
					this.username = username;
                    this.password = password;
				}
				break;
			}
    }
	
	// Check se l'utente è loggato o meno
	
	public boolean getLogged(){
		return logged_in;
	}
	
	// Viene effettuato il logout settando la variabile logged_in
	
	public void logout(){
		logged_in = false;
	}
	
	// Restituisce l'username associato all'istanza 
	
	public String getUser(){
		return username;
	}
	
	public String getPsw() {
        return password;
    }
}
