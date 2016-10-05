package control;

import java.io.File;
import java.util.ArrayList;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import util.DeserializzaOggetti;
import util.SerializzaOggetti;

// Classe che consente l'inserimento di nuove locazioni all'interno del sistema.

public class ControlloreInserimentoLocazione {
	
	// Variabili e percorsi
	
	private ArrayList<Appartamento> appartamentoList = new ArrayList<Appartamento>();
	private ArrayList<Beb> bebList = new ArrayList<Beb>();
	private ArrayList<Albergo> albergoList = new ArrayList<Albergo>();
	private ArrayList<CasaVacanza> vacanzaList = new ArrayList<CasaVacanza>();
	private ArrayList<Ostello> ostelloList = new ArrayList<Ostello>();
	private String percorsoAlbergo = Constants.ALBERGHI_PATH;
	private String percorsoAppartamento = Constants.APPART_PATH;
	private String percorsoBeb = Constants.BEB;
	private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
	private String percorsoOstello = Constants.OSTELLI_PATH;
	
	// Costruttore 
	
	public ControlloreInserimentoLocazione(){
		
	}
	
	// Metodo che effettua tutte le verifiche sugli input e se tutto va bene inserisce un nuovo Albergo nel file alberghi.
	
	@SuppressWarnings("unchecked")
	public boolean inserisciAlbergo(String nomeLocazione, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String camereTotali,
			String tipoPensione, String orarioColazione, String orarioPranzo, String orarioCena)throws SerializzazioneException, DeserializzazioneException{
		
		if(nomeLocazione.equals("")||indirizzo.equals("")||userLocatore.equals("")||prezzo.equals("")||
				descrizione.equals("")||camereTotali.equals("")||tipoPensione.equals("")||orarioColazione.equals("")||
				orarioPranzo.equals("")||orarioCena.equals("")){
			return false;
		}
		
		Albergo albergo = new Albergo(nomeLocazione, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, 
				camereTotali, tipoPensione, orarioColazione, orarioPranzo, orarioCena);

		File file = new File(percorsoAlbergo);
		
		SerializzaOggetti sobj = new SerializzaOggetti();		
		
		if(file.length()==0){
			albergoList.clear();
			albergoList.add(albergo);
			sobj.serializza(albergoList, percorsoAlbergo);
			return true;
		}
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		albergoList = (ArrayList<Albergo>) dobj.deserializza(percorsoAlbergo);
		albergoList.add(albergo);
		sobj.serializza(albergoList, percorsoAlbergo);
		return true;
			
	}
	
	
	// Metodo che effettua tutte le verifiche sugli input e se tutto va bene inserisce un nuovo appartamento nel file appartamenti.
	
	@SuppressWarnings("unchecked")
	public boolean inserisciAppartamento(String nomeLocazione, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String numeroStanze, 
			String numeroBagni, boolean giardino, String numeroLetti)throws SerializzazioneException, DeserializzazioneException{
		
		if(nomeLocazione.equals("")||indirizzo.equals("")||userLocatore.equals("")||prezzo.equals("")||
				descrizione.equals("")||numeroStanze.equals("")||numeroBagni.equals("")||numeroLetti.equals("")){
			return false;
		}
		
		Appartamento appartamento = new Appartamento(nomeLocazione, indirizzo, userLocatore, prezzo, descrizione, parcheggio, 
				wifi, pet, numeroStanze, numeroBagni, giardino, numeroLetti);

		File file = new File(percorsoAppartamento);
		
		SerializzaOggetti sobj = new SerializzaOggetti();		
		
		if(file.length()==0){
			appartamentoList.add(appartamento);
			sobj.serializza(appartamentoList, percorsoAppartamento);
			return true;
		}
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		appartamentoList = (ArrayList<Appartamento>) dobj.deserializza(percorsoAppartamento);
		appartamentoList.add(appartamento);
		sobj.serializza(appartamentoList, percorsoAppartamento);
		return true;
			
	}
	
	
	// Metodo che effettua tutte le verifiche sugli input e se tutto va bene inserisce un nuovo B&B nel file beb.
	
	@SuppressWarnings("unchecked")
	public boolean inserisciBeb(String nomeLocazione, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String camereTotali, String orarioColazione) throws SerializzazioneException, DeserializzazioneException{
		if(nomeLocazione.equals("")||indirizzo.equals("")||userLocatore.equals("")||prezzo.equals("")||
				descrizione.equals("")||camereTotali.equals("")||orarioColazione.equals("")){
			return false;
		}
		
		Beb beb = new Beb(nomeLocazione, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, camereTotali, orarioColazione);

		File file = new File(percorsoBeb);
		
		SerializzaOggetti sobj = new SerializzaOggetti();		
		
		if(file.length()==0){
			bebList.add(beb);
			sobj.serializza(bebList, percorsoBeb);
			return true;
		}
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		bebList = (ArrayList<Beb>) dobj.deserializza(percorsoBeb);
		bebList.add(beb);
		sobj.serializza(bebList, percorsoBeb);
		return true;
			
	}
	
	// Metodo che effettua tutte le verifiche sugli input e se tutto va bene inserisce una nuova Casa Vacanze nel file casevacanza.
	
	@SuppressWarnings("unchecked")
	public boolean inserisciCasaVacanza(String nomeLocazione, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String numeroCamere, String numeroBagni, 
			boolean giardino, String numeroLetti )throws SerializzazioneException, DeserializzazioneException{
		
		if(nomeLocazione.equals("")||indirizzo.equals("")||userLocatore.equals("")||prezzo.equals("")||
				descrizione.equals("")||numeroCamere.equals("")||numeroBagni.equals("")||numeroLetti.equals("")){
			return false;
		}
		
		CasaVacanza casavacanza = new CasaVacanza(nomeLocazione, indirizzo, userLocatore, prezzo, descrizione, parcheggio, 
				wifi, pet, numeroCamere, numeroBagni, giardino, numeroLetti);

		File file = new File(percorsoCasaVacanza);
		
		SerializzaOggetti sobj = new SerializzaOggetti();		
		
		if(file.length()==0){
			vacanzaList.add(casavacanza);
			sobj.serializza(vacanzaList, percorsoCasaVacanza);
			return true;
		}
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		vacanzaList = (ArrayList<CasaVacanza>) dobj.deserializza(percorsoCasaVacanza);
		vacanzaList.add(casavacanza);
		sobj.serializza(vacanzaList, percorsoCasaVacanza);
		return true;
			
	}
	
	// Metodo che effettua tutte le verifiche sugli input e se tutto va bene inserisce un nuovo Ostello nel file ostelli.
	
	@SuppressWarnings("unchecked")
	public boolean inserisciOstello(String nomeLocazione, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String numeroLettiTotali )throws SerializzazioneException, DeserializzazioneException{
		
		if(nomeLocazione.equals("")||indirizzo.equals("")||userLocatore.equals("")||prezzo.equals("")||
				descrizione.equals("")||numeroLettiTotali.equals("")){
			return false;
		}
		
		Ostello ostello = new Ostello(nomeLocazione, indirizzo, userLocatore, prezzo, descrizione, parcheggio, 
				wifi, pet, numeroLettiTotali);

		File file = new File(percorsoOstello);
		
		SerializzaOggetti sobj = new SerializzaOggetti();		
		
		if(file.length()==0){
			ostelloList.add(ostello);
			sobj.serializza(ostelloList, percorsoOstello);
			return true;
		}
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		ostelloList = (ArrayList<Ostello>) dobj.deserializza(percorsoOstello);
		ostelloList.add(ostello);
		sobj.serializza(ostelloList, percorsoOstello);
		return true;
			
	}
}
