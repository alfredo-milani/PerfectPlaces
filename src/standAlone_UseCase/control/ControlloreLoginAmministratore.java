package standAlone_UseCase.control;

import java.util.ArrayList;

import constants.Constants;
import standAlone_UseCase.boundary.ConfineAmministrazione;
import standAlone_UseCase.boundary.ConfineLoginErrato;
import entity.Utente;
import exception.DeserializzazioneException;
import util.DeserializzaOggetti;

public class ControlloreLoginAmministratore {
	
	//Variabili e percorso
	
	public static ArrayList<Utente> utenti = new ArrayList<Utente>();
	public String percorsoAmministratori = Constants.ADMIN_PATH;
	
	
	//Costruttore
	public ControlloreLoginAmministratore(){
	
	}
	
	// Deserializza gli Utenti del file utenti e li posiziona in un ArrayList. Viene fatta una scansione
	// di tutti gli elementi e viene verificata la corrispondenza di Username e Password inserite
	
	@SuppressWarnings("unchecked")
	public void login(String username, char[] password) throws DeserializzazioneException{
		
		String pw = String.valueOf(password);
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>) dobj.deserializza(percorsoAmministratori);
		
		for(int i=0; i<utenti.size();i++){
			if(utenti.get(i).getUsername().equals(username)){
				if(utenti.get(i).getPassword().equals(pw)){
					new ConfineAmministrazione();
					return;
				} else {
					break;
				}
			}
		}
		new ConfineLoginErrato();
	}
}