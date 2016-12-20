package standAlone_UC.control;

import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standAlone_UC.boundary.ConfineInsuccesso;
import standAlone_UC.boundary.ConfineSuccesso;
import standAlone_UC.thread.ThreadRimozione;
import utils.*;

public class ControlloreRimuoviUtente {

	private String percorsoUtenti = Constants.UTENTI_PATH;

	public ControlloreRimuoviUtente(){
		
		
	}
	
	// Viene verificata la presenza dell'getUtente nel sistema, una volta accertata viene rimosso dall'arraylist, che viene riserializzato.
	// Vengono utilizzati i thread per stampare a schermo l'array degli utenti prima e dopo la rimozione. 
	
	@SuppressWarnings("unchecked")
	public void rimuovi(String username) throws DeserializzazioneException,
			SerializzazioneException, InterruptedException{
		ThreadRimozione tr = new ThreadRimozione();
		Thread t1 = new Thread(tr);
		Thread t2 = new Thread(tr);
		
		t1.start();
		
		ArrayList<Utente> utenti;
		utenti = (ArrayList<Utente>)DeserializzaOggetti
				.deserializza(percorsoUtenti);
		
		
		for(int i = 0; i<utenti.size();i++){
			if(utenti.get(i).getUsername().equals(username)){
				t1.join();
				System.out.println("L'utente rimosso e': " + utenti.get(i).getUsername());
				utenti.remove(i);
				SerializzaOggetti
						.serializza(utenti, percorsoUtenti);
				new ConfineSuccesso();
				t2.start();
				return;
			}
		}
		new ConfineInsuccesso();
		t2.start();
	}
}
