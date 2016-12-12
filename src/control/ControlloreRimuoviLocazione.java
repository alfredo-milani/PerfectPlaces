package control;

import java.util.ArrayList;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

// Questa classe serve a rimuovere una locazione dal sistema da parte del proprietario. 

public class ControlloreRimuoviLocazione {
	
	// Percorsi
	
	private String percorsoAlbergo = Constants.ALBERGHI_PATH;
	private String percorsoAppartamento = Constants.APPART_PATH;
	private String percorsoBeb = Constants.BEB;
	private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
	private String percorsoOstello = Constants.OSTELLI_PATH;
	
	// Costruttore
	
	public ControlloreRimuoviLocazione(){
		
	}
	
	// Viene passato un oggetto di tipo locazione da eliminare. Viene effettuata una ricerca a seconda del tipo di locazione all'interno
	// dei rispettivi file e viene eliminato. 
	
	@SuppressWarnings("unchecked")
	public void rimuoviLocazione(Locazione locazione) throws DeserializzazioneException, SerializzazioneException{
		String percorso;

		if(locazione.getClass()==Albergo.class){
			rimuovi(locazione,percorsoAlbergo);

		}
		if(locazione.getClass()==Albergo.class){
			percorso=percorsoAlbergo;
		}


	}
	@SuppressWarnings("unchecked")
	public void rimuovi(Locazione locazione, String percorso) throws DeserializzazioneException, SerializzazioneException {
		String nomeLocazione = locazione.getNomeLocazione();
		String provincia =locazione.getProvincia();
		String indirizzo = locazione.getIndirizzo();
		ArrayList<Locazione> locazioni;
		locazioni=(ArrayList<Locazione>) DeserializzaOggetti.deserializza(percorso);

		for(Locazione loc: locazioni){
			if(loc.getNomeLocazione().equals(nomeLocazione)&&
					loc.getProvincia().equals(provincia)&&
					loc.getIndirizzo().equals(indirizzo)){
						locazioni.remove(loc);

			}
		}
		SerializzaOggetti.serializza(locazioni,percorso);
	}
}
