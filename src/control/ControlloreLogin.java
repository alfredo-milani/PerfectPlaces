package control;

import java.io.File;
import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

// Classe che gestisce il login al sistema e altri piccoli metodi utili.

public class ControlloreLogin {
	
	// Variabili
	
	private String username;
	private ArrayList<Utente> utenti = new ArrayList<Utente>();
	private boolean logged_in = false;
	private String percorsoUtenti = Constants.UTENTI_PATH;
	
	
	// Costruttore
	
	public ControlloreLogin(){


	}
	
	
	// Deserializza gli Utenti del file utenti e li posiziona in un ArrayList. Viene fatta una scansione
	// di tutti gli elementi e viene verificata la corrispondenza di Username e Password inserite
	
	@SuppressWarnings("unchecked")
	public boolean login(String username, String password) throws DeserializzazioneException{
		File file = new File(percorsoUtenti);
		
		if(file.length()==0)
			return false;
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>) dobj.deserializza(percorsoUtenti);

        /* controlla perche senza questo statement non funziona anche se dovrebbe */
        if (username == null ||
                username.equals("") ||
                password == null ||
                password.equals("")) {
            System.out.println("un2: " + username + "\tpsw2: " + password);
            logged_in = false;
            return false;
        }

		for (Utente anUtenti : utenti) {
			if (anUtenti.getUsername().equals(username)) {
				if (anUtenti.getPassword().equals(password)) {
					logged_in = true;
					this.username = username;
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	// Check se l'utente è loggato o meno
	
	public boolean getLogged(){
		return logged_in;
	}
	
	// Viene effettuato il logout settando la variabile logged_in
	
	public void logout(){
		logged_in = false;
	}
	
	// Metodo per il titolo in JSP
	
	public String getTitle(String un) {
		if (logged_in)
			return un + "\tBenvenuto!";
		return "Attenzione!";
	}
	
	// Restituisce l'username associato all'istanza 
	
	public String getUser(){
		return username;
	}
	
	
}
