package control;

import java.io.File;
import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import util.DeserializzaOggetti;

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
		
		if(file.length()==0){
			return false;
		}
		
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>) dobj.deserializza(percorsoUtenti);
		
		for(int i=0; i<utenti.size();i++){
			if(utenti.get(i).getUsername().equals(username)){
				if(utenti.get(i).getPassword().equals(password)){
					logged_in = true;
					this.username = username;
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	// Check se l'utente � loggato o meno
	
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
			return "Benvenuto!";
		else
			return "Attenzione!";
	}
	
	// Restituisce l'username associato all'istanza 
	
	public String getUser(){
		return username;
	}
	
	
}
