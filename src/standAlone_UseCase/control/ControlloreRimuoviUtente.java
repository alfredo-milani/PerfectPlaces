package standAlone_UseCase.control;

import java.util.ArrayList;

import constants.Constants;
import standAlone_UseCase.boundary.ConfineInsuccesso;
import standAlone_UseCase.boundary.ConfineSuccesso;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standAlone_UseCase.thread.ThreadRimozione;
import utils.*;

public class ControlloreRimuoviUtente {
	
	//Percorso
	
	public String percorsoUtenti = Constants.UTENTI_PATH;
	
	//Costruttore
	
	public ControlloreRimuoviUtente(){
		
		
	}
	
	// Viene verificata la presenza dell'utente nel sistema, una volta accertata viene rimosso dall'arraylist, che viene riserializzato.
	// Vengono utilizzati i thread per stampare a schermo l'array degli utenti prima e dopo la rimozione. 
	
	@SuppressWarnings("unchecked")
	public void rimuovi(String username) throws DeserializzazioneException,
			SerializzazioneException, InterruptedException{
		ThreadRimozione tr = new ThreadRimozione();
		Thread t1 = new Thread(tr);
		Thread t2 = new Thread(tr);
		
		t1.start();
		
		ArrayList<Utente> utenti;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>)dobj.deserializza(percorsoUtenti);
		
		
		for(int i = 0; i<utenti.size();i++){
			if(utenti.get(i).getUsername().equals(username)){
				t1.join();
				System.out.println("L'utente rimosso e':"+utenti.get(i).getUsername());
				utenti.remove(i);
				SerializzaOggetti sobj = new SerializzaOggetti();
				sobj.serializza(utenti, percorsoUtenti);
				new ConfineSuccesso();
				t2.start();
				return;
			}
		}
		new ConfineInsuccesso();
		t2.start();
	}
}
