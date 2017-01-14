package standAlone.control;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standAlone.boundary.BoundaryFallimento;
import standAlone.boundary.BoundarySuccesso;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.util.ArrayList;

public class ControlloreRimuoviUtente {
	
	//Costruttore
	
	public ControlloreRimuoviUtente(){
		
		
	}
	
	// Viene verificata la presenza dell'utente nel sistema, una volta accertata viene rimosso dall'arraylist, che viene riserializzato.
	// Vengono utilizzati i utils per stampare a schermo l'array degli utenti prima e dopo la rimozione.
	
	@SuppressWarnings("unchecked")
	public void rimuovi(String username) throws DeserializzazioneException, SerializzazioneException, InterruptedException{
		
		ArrayList<Utente> utenti;
		utenti = (ArrayList<Utente>)DeserializzaOggetti.deserializza(Constants.UTENTI_PATH);
		
		
		for(int i = 0; i<utenti.size();i++){
			if(utenti.get(i).getUsername().equals(username)){
				System.out.println("L'utente rimosso e':"+utenti.get(i).getUsername());
				utenti.remove(i);
				SerializzaOggetti.serializza(utenti, Constants.UTENTI_PATH);
				new BoundarySuccesso();
				return;
			}
		}
		new BoundaryFallimento("L'utente non è tra quelli presenti nel sistema");

	}
}
