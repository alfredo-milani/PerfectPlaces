package control.ControlloreGestioneLocazione;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by maria
 */

// Questa classe serve a rimuovere una locazione dal sistema da parte del proprietario. 
// Vengono anche eliminate le prenotazioni associate alla locazione da rimuovere

public class ControlloreRimuoviLocazione {
	
	// Percorsi
	

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

	//metodo che passa al metodo privato "rimuoviLocazione" la locazione scelta dall'utente e il percorso corrispondente
	//chiama dunque anche le operazioni per rimuovere le corrispondenti CamerePrenotate e Prenotati corrispondenti a quella locazione
	@SuppressWarnings("unchecked")
	public String rimuovi(Locazione locazione) throws DeserializzazioneException, SerializzazioneException{

		String nomeLocazione = locazione.getNomeLocazione();

		//generale per tutti i tipi di locazioni
		rimuoviLocazione(locazione,locazione.selectPath());

		if(locazione.getClass()==Albergo.class){

			File fPrenotazioni = new File(percorsoPrenotazioniAlberghi);
			File fPrenotati = new File(percorsoPrenotatiAlbergo);

			if(fPrenotazioni.length() != 0)
				rimuoviCamerePrenotate(locazione,percorsoPrenotazioniAlberghi);
			if(fPrenotati.length() != 0)
				rimuoviPrenotati(locazione,percorsoPrenotatiAlbergo);

		}
		else if(locazione.getClass()==Appartamento.class){

			File fPrenotazioni = new File(percorsoPrenotazioniAppartamenti);
			File fPrenotati = new File(percorsoPrenotatiAppartamento);

			if(fPrenotazioni.length() != 0)
				rimuoviCamerePrenotate(locazione,percorsoPrenotazioniAppartamenti);
			if(fPrenotati.length() != 0)
				rimuoviPrenotati(locazione,percorsoPrenotatiAppartamento);
		}
		else if(locazione.getClass()==Beb.class){

			File fPrenotazioni = new File(percorsoPrenotazioniBeb);
			File fPrenotati = new File(percorsoPrenotatiBeb);

			if(fPrenotazioni.length() != 0)
				rimuoviCamerePrenotate(locazione,percorsoPrenotazioniBeb);
			if(fPrenotati.length() != 0)
				rimuoviPrenotati(locazione,percorsoPrenotatiBeb);
		}
		else if(locazione.getClass()==CasaVacanza.class){

			File fPrenotazioni = new File(percorsoPrenotazioniCaseVacanza);
			File fPrenotati = new File(percorsoPrenotatiCasaVacanza);

			if(fPrenotazioni.length() != 0)
				rimuoviCamerePrenotate(locazione,percorsoPrenotazioniCaseVacanza);
			if(fPrenotati.length() != 0)
				rimuoviPrenotati(locazione,percorsoPrenotatiCasaVacanza);
		}
		else {

			File fPrenotazioni = new File(percorsoPrenotazioniOstelli);
			File fPrenotati = new File(percorsoPrenotatiOstello);

			if(fPrenotazioni.length() != 0)
				rimuoviCamerePrenotate(locazione,percorsoPrenotazioniOstelli);
			if(fPrenotati.length() != 0)
				rimuoviPrenotati(locazione,percorsoPrenotatiOstello);


		}
		return nomeLocazione;


	}
	//metodo che rimuove una locazione da un file
	@SuppressWarnings("unchecked")
	private void rimuoviLocazione(Locazione locazione, String percorso) throws DeserializzazioneException, SerializzazioneException {
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

	//metodo che rimuove le camere prenotate legate ad una determinata locazione
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

	//metodo che rimuove i prenotati legati ad una determinata locazione
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
