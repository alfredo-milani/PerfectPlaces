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
import util.*;

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
	public Utente ottieniUtente(String username) throws DeserializzazioneException{
		
		// Caso di default in cui non venga trovato l'utente nel file
		Utente u = new Utente("errore","errore","errore","errore","errore","errore");
		
		ArrayList<Utente> utenti = new ArrayList<Utente>();
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		
		utenti = (ArrayList<Utente>)dobj.deserializza(percorsoUtenti);
		
		for(int i = 0;i<utenti.size();i++){
			if(utenti.get(i).getUsername().equals(username)){
				u = utenti.get(i);
			}
		}
		return u;
	}
	
	
	// Metodo che consente la modifca del profilo, viene effettuato un controllo sull'input per verificare che tutti i parametri
	// siano corretti, se tutto � corretto prende l'utente corrispondente dal file, ne modifica i parametri e lo salva sul file.
	// L'unico campo che non � modificabile � l'username, perch� consente di identificare univocamente l'utente nel sistema.
	
	// Ritorna: 1 --> Password vecchia non corretta
	// 		    2 --> La nuova password e la conferma della nuova password non sono uguali
	//			3 --> La nuova password � vuota
	//			0 --> Se tutto va bene
	
	@SuppressWarnings("unchecked")
	public int modificaProfilo(String username, String nome, String cognome, 
			String email, String vecchiaPassword, String nuovaPassword, String confermaNuovaPassword) throws DeserializzazioneException, SerializzazioneException{
		
		ArrayList<Utente> utenti = new ArrayList<Utente>();
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>)dobj.deserializza(percorsoUtenti);
		SerializzaOggetti sobj = new SerializzaOggetti();
		
		for(int i = 0;i<utenti.size();i++){
			if(utenti.get(i).getUsername().equals(username)){
				
				if(!vecchiaPassword.equals("")){
					if(!vecchiaPassword.equals(utenti.get(i).getPassword()))
						return 1;
					if(!nuovaPassword.equals(confermaNuovaPassword))
						return 2;
					if(nuovaPassword.equals(""))
						return 3;
				}
				
				// Se il campo vecchia password � vuoto non viene restituito errore, infatti un utente potrebbe scegliere di modificare 
				// il suo profilo senza modificare la password.
				
				if(vecchiaPassword.equals("")){
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
	
	// Metodo che consente la modifica dell'immagine del profilo. Viene preso un file dal sistema dell'utente e viene salvato nella 
	// cartella images del progetto con un nome univoco, che fa riferimento all'username dell'utente.
	
	public void copiaImmagine(String strOrigine, String username) throws IOException {
		
		File fOrigine = new File(strOrigine);
		File fDestinazione = new File(Constants.ABS_PATH.concat("web/images/img"+username+".jpg"));

		try (InputStream input = new FileInputStream(fOrigine); OutputStream output = new FileOutputStream(fDestinazione)) {
			byte[] buf = new byte[1024000];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		}
	}
	
}
