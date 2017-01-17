package control;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.util.ArrayList;

// Questa classe serve a rimuovere una locazione dal sistema da parte del proprietario. 
// Vengono anche eliminate le prenotazioni associate alla locazione da rimuovere

public class ControlloreRimuoviLocazione {
	
	// Percorsi
	
	private String percorsoAlbergo = Constants.ALBERGHI_PATH;
	private String percorsoAppartamento = Constants.APPART_PATH;
	private String percorsoBeb = Constants.BEB;
	private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
	private String percorsoOstello = Constants.OSTELLI_PATH;

	private static String percorsoPrenotatiAlbergo = Constants.PRENOTATI_ALBERGO_PATH;
	private static String percorsoPrenotatiAppartamento = Constants.PRENOTATI_APPARTAMENTO_PATH;
	private static String percorsoPrenotatiBeb = Constants.PRENOTATI_BEB_PATH;
	private static String percorsoPrenotatiCasaVacanza = Constants.PRENOTATI_CASEVACANZA_PATH;
	private static String percorsoPrenotatiOstello = Constants.PRENOTATI_OSTELLO_PATH;

	private static String percorsoPrenotazioniAlberghi = Constants.PRENOTAZIONE_ALBERGO_PATH;
	private static String percorsoPrenotazioniBeb = Constants.PRENOTAZIONE_BEB_PATH;
	private static String percorsoPrenotazioniOstelli = Constants.PRENOTAZIONE_OSTELLO_PATH;
	private static String percorsoPrenotazioniAppartamenti = Constants.PRENOTAZIONE_APPARTAMENTO_PATH;
	private static String percorsoPrenotazioniCaseVacanza = Constants.PRENOTAZIONE_CASAVACANZA_PATH;
	
	// Costruttore
	
	public ControlloreRimuoviLocazione(){
		
	}

	//metodo che passa al metodo privato "rimuovi" la locazione scelta dall'utente e il percorso corrispondente
	@SuppressWarnings("unchecked")
	public String rimuoviLocazione(Locazione locazione) throws DeserializzazioneException, SerializzazioneException{

		String nomeLocazione = locazione.getNomeLocazione();

		if(locazione.getClass()==Albergo.class){
			rimuovi(locazione,percorsoAlbergo);
			rimuoviCamerePrenotate(locazione,percorsoPrenotazioniAlberghi);
			rimuoviPrenotati(locazione,percorsoPrenotatiAlbergo);

		}
		else if(locazione.getClass()==Appartamento.class){
			rimuovi(locazione,percorsoAppartamento);
			rimuoviCamerePrenotate(locazione,percorsoPrenotazioniAppartamenti);
			rimuoviPrenotati(locazione,percorsoPrenotatiAppartamento);
		}
		else if(locazione.getClass()==Beb.class){
			rimuovi(locazione,percorsoBeb);
			rimuoviCamerePrenotate(locazione,percorsoPrenotazioniBeb);
			rimuoviPrenotati(locazione,percorsoPrenotatiBeb);
		}
		else if(locazione.getClass()==CasaVacanza.class){
			rimuovi(locazione,percorsoCasaVacanza);
			rimuoviCamerePrenotate(locazione,percorsoPrenotazioniCaseVacanza);
			rimuoviPrenotati(locazione,percorsoPrenotatiCasaVacanza);
		}
		else {
			rimuovi(locazione,percorsoOstello);
			rimuoviCamerePrenotate(locazione,percorsoPrenotazioniOstelli);
			rimuoviPrenotati(locazione,percorsoPrenotatiOstello);


		}
		return nomeLocazione;


	}
	//metodo che rimuove una locazione da un file
	@SuppressWarnings("unchecked")
	private void rimuovi(Locazione locazione, String percorso) throws DeserializzazioneException, SerializzazioneException {
		String nomeLocazione = locazione.getNomeLocazione();
		String provincia =locazione.getProvincia();
		String indirizzo = locazione.getIndirizzo();
		ArrayList<Locazione> locazioni;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		SerializzaOggetti sobj = new SerializzaOggetti();

		locazioni=(ArrayList<Locazione>) dobj.deserializza(percorso);

		for(int i=0; i<locazioni.size();++i){
			if(locazioni.get(i).getNomeLocazione().equals(nomeLocazione)&&
					locazioni.get(i).getProvincia().equals(provincia)&&
					locazioni.get(i).getIndirizzo().equals(indirizzo)){
						locazioni.remove(i);

			}
		}
		sobj.serializza(locazioni,percorso);
	}

	@SuppressWarnings("unchecked")
	private void rimuoviCamerePrenotate(Locazione locazione, String percorso) throws DeserializzazioneException, SerializzazioneException {
		String nomeLocazione = locazione.getNomeLocazione();
		ArrayList<PostiDisponibili> camerePrenotate;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		SerializzaOggetti sobj = new SerializzaOggetti();

		camerePrenotate = (ArrayList<PostiDisponibili>) dobj.deserializza(percorso);

		for(int i=0; i<camerePrenotate.size(); ++i){
			if(camerePrenotate.get(i).getNomeLocazion().equals(nomeLocazione)) {
				camerePrenotate.remove(i);
			}
		}
		sobj.serializza(camerePrenotate,percorso);

	}

	@SuppressWarnings("unchecked")
	private void rimuoviPrenotati(Locazione locazione,String percorso) throws DeserializzazioneException, SerializzazioneException {
		String nomeLocazione = locazione.getNomeLocazione();
		ArrayList<Prenotazione> prenotati;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		SerializzaOggetti sobj = new SerializzaOggetti();

		prenotati = (ArrayList<Prenotazione>) dobj.deserializza(percorso);

		for(int i=0; i< prenotati.size(); ++i){
			if(prenotati.get(i).getNomeLocazione().equals(nomeLocazione)){
				prenotati.remove(i);
			}
		}
		sobj.serializza(prenotati,percorso);

	}
}
