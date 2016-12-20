package standAlone_UC.thread;

import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

public class ThreadRimozione implements Runnable {

	private String percorsoUtenti = Constants.UTENTI_PATH;
	
	//Viene stampato l'array degli utenti a schermo.
	
	@SuppressWarnings("unchecked")
	public void run(){
		
		ArrayList<Utente> utenti;
		String elenco = "Utenti: [";
		try {
			utenti = (ArrayList<Utente>)DeserializzaOggetti
					.deserializza(percorsoUtenti);
			
			if(utenti.size()==0){
				elenco = elenco + " ]";
			}
			
			for (int i = 0; i < utenti.size(); i++)
				if (i == (utenti.size() - 1))
				    elenco = elenco + utenti.get(i).getUsername() + "]";
                else
                    elenco = elenco + utenti.get(i).getUsername() + ", ";

			System.out.println(elenco);
		} catch (DeserializzazioneException e) {
			e.printStackTrace();
		}
	}

}
