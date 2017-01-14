package standAlone.control;

import java.util.ArrayList;

import constants.Constants;
import standAlone.boundary.BoundaryFallimento;
import standAlone.boundary.BoundarySuccesso;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.*;

public class ControlloreRimuoviUtente {
	
	//Percorso
	
	public String percorsoUtenti = Constants.UTENTI_PATH;
	
	//Costruttore
	
	public ControlloreRimuoviUtente(){
		
		
	}
	
	// Viene verificata la presenza dell'utente nel sistema, una volta accertata viene rimosso dall'arraylist, che viene riserializzato.
	// Vengono utilizzati i utils per stampare a schermo l'array degli utenti prima e dopo la rimozione.
	
	@SuppressWarnings("unchecked")
	public void rimuovi(String username) throws DeserializzazioneException, SerializzazioneException, InterruptedException{
		
		ArrayList<Utente> utenti;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>)dobj.deserializza(percorsoUtenti);
		
		
		for(int i = 0; i<utenti.size();i++){
			if(utenti.get(i).getUsername().equals(username)){
				System.out.println("L'utente rimosso e':"+utenti.get(i).getUsername());
				utenti.remove(i);
				SerializzaOggetti sobj = new SerializzaOggetti();
				sobj.serializza(utenti, percorsoUtenti);
				new BoundarySuccesso();
				return;
			}
		}
		new BoundaryFallimento("L'utente non è tra quelli presenti nel sistema");

	}
}
