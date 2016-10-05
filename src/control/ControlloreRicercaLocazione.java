package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import constants.Constants;
import util.*;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

// Questa classe viene utilizzata per effettuare delle ricerche delle locazioni o in base al proprietario o in base ad altri 
// parametri come prezzo, data inizio e data fine. 

public class ControlloreRicercaLocazione {
	
	// Percorsi
	
	private String percorsoAlbergo = Constants.ALBERGHI_PATH;
	private String percorsoAppartamento = Constants.APPART_PATH;
	private String percorsoBeb = Constants.BEB;
	private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
	private String percorsoOstello = Constants.OSTELLI_PATH;
	private String percorsoTemp = Constants.TMPDATE_PATH;
	
	// Costruttore
	
	public ControlloreRicercaLocazione(){
		
	}
	
	// Metodo ricerca locazioni per Username, serve ad elencare nel profilo dell'utente le sue locazioni.

	@SuppressWarnings("unchecked")
	public ArrayList<Locazione> ricercaLocPerUser(String username) throws DeserializzazioneException{
		
		ArrayList<Locazione> locazioni = new ArrayList<Locazione>();
		ArrayList<Locazione> locazioniProvvisorio = new ArrayList<Locazione>();
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		
		File file = new File(percorsoAlbergo);
		if(file.length()==0){
			
			;
			
		} else {
		
			locazioniProvvisorio = (ArrayList<Locazione>) dobj.deserializza(percorsoAlbergo);
			for(int i=0;i<locazioniProvvisorio.size();i++){
				if(locazioniProvvisorio.get(i).getUserLocatore().equals(username)){
					locazioniProvvisorio.get(i).setTipo("albergo");
					locazioni.add(locazioniProvvisorio.get(i));
				}
			}
		}
		
		file = new File(percorsoAppartamento);
		if(file.length()==0){
			
			;
			
		} else {
			locazioniProvvisorio = (ArrayList<Locazione>) dobj.deserializza(percorsoAppartamento);
			for(int i=0;i<locazioniProvvisorio.size();i++){
				if(locazioniProvvisorio.get(i).getUserLocatore().equals(username)){
					locazioniProvvisorio.get(i).setTipo("appartamento");
					locazioni.add(locazioniProvvisorio.get(i));
				}
			}
		}
		
		file = new File(percorsoBeb);
		if(file.length()==0){
			
			;
			
		} else {
			locazioniProvvisorio = (ArrayList<Locazione>) dobj.deserializza(percorsoBeb);
			for(int i=0;i<locazioniProvvisorio.size();i++){
				if(locazioniProvvisorio.get(i).getUserLocatore().equals(username)){
					locazioniProvvisorio.get(i).setTipo("beb");
					locazioni.add(locazioniProvvisorio.get(i));
				}
			}
		}
		
		file = new File(percorsoCasaVacanza);
		if(file.length()==0){
			
			;
			
		} else {
			locazioniProvvisorio = (ArrayList<Locazione>) dobj.deserializza(percorsoCasaVacanza);
			for(int i=0;i<locazioniProvvisorio.size();i++){
				if(locazioniProvvisorio.get(i).getUserLocatore().equals(username)){
					locazioniProvvisorio.get(i).setTipo("casavacanza");
					locazioni.add(locazioniProvvisorio.get(i));
				}
			}
		}
		
		file = new File(percorsoOstello);
		if(file.length()==0){
			
			;
			
		} else {
			locazioniProvvisorio = (ArrayList<Locazione>) dobj.deserializza(percorsoOstello);
			for(int i=0;i<locazioniProvvisorio.size();i++){
				if(locazioniProvvisorio.get(i).getUserLocatore().equals(username)){
					locazioniProvvisorio.get(i).setTipo("ostello");
					locazioni.add(locazioniProvvisorio.get(i));
				}
			}
		}
		
		return locazioni;
	}
	
	
	// Questo metodo riceve in input due date in formato Gregorian Calendar e restituisce un ArrayList di date in formato
	// GregorianCalendar che vanno dal primo giorno indicato (dataInizio) all'ultimo (dataFine)
	// (Ad esempio dataInizio = 10/10/2016, dataFine = 13/10/2016 
	//  Restituisce --> elencoDate =  [10/10/2016,11/10/2016,12/10/2016,13/10/2016]  )
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public ArrayList<GregorianCalendar> contaGiorni(GregorianCalendar dataInizio, GregorianCalendar dataFine) throws IOException, SerializzazioneException, DeserializzazioneException{
		
		ArrayList<GregorianCalendar> elencoDate = new ArrayList<GregorianCalendar>();
		
		FileWriter w = new FileWriter(percorsoTemp);
	    BufferedWriter b = new BufferedWriter(w);
	    b.write("");
	    b.flush();
	    b.close();
		
		SerializzaOggetti sobj = new SerializzaOggetti();
		
		sobj.serializza(elencoDate, percorsoTemp);
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		
		
		while(!dataInizio.equals(dataFine)){
			
			elencoDate = (ArrayList<GregorianCalendar>) dobj.deserializza(percorsoTemp);
			elencoDate.add(dataInizio);
			sobj.serializza(elencoDate, percorsoTemp);
			dataInizio.add(dataInizio.DAY_OF_MONTH, 1);
		}
		
		elencoDate = (ArrayList<GregorianCalendar>) dobj.deserializza(percorsoTemp);
		elencoDate.add(dataInizio);
		sobj.serializza(elencoDate, percorsoTemp);
		return elencoDate;	
	}
	
	// Metodo che effettua una ricerca degli alberghi disponibili in base al prezzo, la data di inizio e la data di fine specificate.
	
	@SuppressWarnings("unchecked")
	public ArrayList<Albergo> ricercaAlbergo(String prezzo, String dataInizio, String dataFine) throws DeserializzazioneException, IOException, SerializzazioneException {
		int giornoInizio = Integer.parseInt(dataInizio.substring(0,2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3,5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6,10));
		int giornoFine = Integer.parseInt(dataFine.substring(0,2));
		int meseFine = Integer.parseInt(dataFine.substring(3,5));
		int annoFine = Integer.parseInt(dataFine.substring(6,10));
		
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio,meseInizio,giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine,meseFine,giornoFine);
		
		ArrayList<Albergo> elencoAlberghi = new ArrayList<Albergo>();
		ArrayList<Albergo> alberghiDisponibili = new ArrayList<Albergo>(); //Qui verranno inseriti gli alberghi da restituire all'utente
		ArrayList<GregorianCalendar> giorniInizioFine = new ArrayList<GregorianCalendar>();
		ArrayList<GregorianCalendar> giorniPrenotatiAlbergo = new ArrayList<GregorianCalendar>();
		
		giorniInizioFine = contaGiorni(gcInizio,gcFine); //Tutti i giorni da dataInizio a dataFine
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		int count;  //Occorrenze di una data nell'array list
		elencoAlberghi = (ArrayList<Albergo>) dobj.deserializza(percorsoAlbergo); //Elenco di tutti gli alberghi nel file alberghi

		System.err.println("numero alberghi: " + elencoAlberghi.size());

		for(int i=0;i<elencoAlberghi.size();i++){
			boolean controllore = true;
			if(Integer.parseInt(elencoAlberghi.get(i).getPrezzo())<=Integer.parseInt(prezzo)){
				giorniPrenotatiAlbergo = elencoAlberghi.get(i).getDate();
				for(int j=0;j<giorniInizioFine.size();j++){
					count = 0;
					for(int k=0;k<giorniPrenotatiAlbergo.size();k++){
						if(giorniInizioFine.get(j).equals(giorniPrenotatiAlbergo.get(k))){
							count = count+1;
						}
					}
					if(count>Integer.parseInt(elencoAlberghi.get(i).getCamereTotali())){
						controllore = false;
					}
				}
			}
			if(controllore){
				alberghiDisponibili.add(elencoAlberghi.get(i));
			}
		}
		return alberghiDisponibili;
		
	}
	
	// Metodo che effettua una ricerca degli appartamenti disponibili in base al prezzo, la data di inizio e la data di fine specificate.
	
	@SuppressWarnings("unchecked")
	public ArrayList<Appartamento> ricercaAppartamento(String prezzo, String dataInizio, String dataFine) throws DeserializzazioneException, IOException, SerializzazioneException{
		int giornoInizio = Integer.parseInt(dataInizio.substring(0,2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3,5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6,10));
		int giornoFine = Integer.parseInt(dataFine.substring(0,2));
		int meseFine = Integer.parseInt(dataFine.substring(3,5));
		int annoFine = Integer.parseInt(dataFine.substring(6,10));
		
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio,meseInizio,giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine,meseFine,giornoFine);
		
		ArrayList<Appartamento> elencoAppartamenti = new ArrayList<Appartamento>();
		ArrayList<Appartamento> appartamentiDisponibili = new ArrayList<Appartamento>(); //Qui verranno inseriti gli appartamenti da restituire all'utente
		ArrayList<GregorianCalendar> giorniInizioFine = new ArrayList<GregorianCalendar>();
		ArrayList<GregorianCalendar> giorniPrenotatiAppartamento = new ArrayList<GregorianCalendar>();
		
		giorniInizioFine = contaGiorni(gcInizio,gcFine); //Tutti i giorni da dataInizio a dataFine
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoAppartamenti = (ArrayList<Appartamento>) dobj.deserializza(percorsoAppartamento); //Elenco di tutti gli appartamenti nel file alberghi
		boolean count;
		
		for(int i=0;i<elencoAppartamenti.size();i++){
			
			count = true;
			if(Integer.parseInt(elencoAppartamenti.get(i).getPrezzo())<=Integer.parseInt(prezzo)){
				giorniPrenotatiAppartamento = elencoAppartamenti.get(i).getDate();
				for(int j=0;j<giorniInizioFine.size();j++){
					for(int k=0;k<giorniPrenotatiAppartamento.size();k++){
						if(giorniInizioFine.get(j).equals(giorniPrenotatiAppartamento.get(k))){
							count = false; //Ho trovato due date uguali tra quelle richieste dall'utente e quelle gi� prenotate nell'appartamento
						}
						if(!count)
							break;  // Se la data non � disponibile, non scansionare pi� L'array
					}
					if(!count)
						break;	//Se la data non � disponibile, non scansionare pi� l'array
					}
			} else {
				count = false;
			}
			if(count)
				appartamentiDisponibili.add(elencoAppartamenti.get(i));		
		}
		return appartamentiDisponibili;
		
	}
	
	// Metodo che effettua una ricerca dei B&B disponibili in base al prezzo, la data di inizio e la data di fine specificate.
	
	@SuppressWarnings("unchecked")
	public ArrayList<Beb> ricercaBeb(String prezzo, String dataInizio, String dataFine) throws DeserializzazioneException, IOException, SerializzazioneException{
		int giornoInizio = Integer.parseInt(dataInizio.substring(0,2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3,5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6,10));
		int giornoFine = Integer.parseInt(dataFine.substring(0,2));
		int meseFine = Integer.parseInt(dataFine.substring(3,5));
		int annoFine = Integer.parseInt(dataFine.substring(6,10));
		
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio,meseInizio,giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine,meseFine,giornoFine);
		
		ArrayList<Beb> elencoBeb = new ArrayList<Beb>();
		ArrayList<Beb> bebDisponibili = new ArrayList<Beb>(); //Qui verranno inseriti i beb da restituire all'utente
		ArrayList<GregorianCalendar> giorniInizioFine = new ArrayList<GregorianCalendar>();
		ArrayList<GregorianCalendar> giorniPrenotatiBeb = new ArrayList<GregorianCalendar>();
		
		giorniInizioFine = contaGiorni(gcInizio,gcFine); //Tutti i giorni da dataInizio a dataFine
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		int count;  //Occorrenze di una data nell'array list
		elencoBeb = (ArrayList<Beb>) dobj.deserializza(percorsoBeb); //Elenco di tutti i beb nel file alberghi
		
		for(int i=0;i<elencoBeb.size();i++){
			boolean controllore = true;
			if(Integer.parseInt(elencoBeb.get(i).getPrezzo())<=Integer.parseInt(prezzo)){
				giorniPrenotatiBeb = elencoBeb.get(i).getDate();
				for(int j=0;j<giorniInizioFine.size();j++){
					count = 0;
					for(int k=0;k<giorniPrenotatiBeb.size();k++){
						if(giorniInizioFine.get(j).equals(giorniPrenotatiBeb.get(k))){
							count = count+1;
						}
					}
					if(count>Integer.parseInt(elencoBeb.get(i).getCamereTotali())){
						controllore = false;
					}
					}
			
			}
			if(controllore)
				bebDisponibili.add(elencoBeb.get(i));
		}

		return bebDisponibili;
		
	}
	
	// Metodo che effettua una ricerca degli case vacanza disponibili in base al prezzo, la data di inizio e la data di fine specificate.
	
	@SuppressWarnings("unchecked")
	public ArrayList<CasaVacanza> ricercaCasaVacanze(String prezzo, String dataInizio, String dataFine) throws DeserializzazioneException, IOException, SerializzazioneException{
		int giornoInizio = Integer.parseInt(dataInizio.substring(0,2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3,5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6,10));
		int giornoFine = Integer.parseInt(dataFine.substring(0,2));
		int meseFine = Integer.parseInt(dataFine.substring(3,5));
		int annoFine = Integer.parseInt(dataFine.substring(6,10));
		
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio,meseInizio,giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine,meseFine,giornoFine);
		
		ArrayList<CasaVacanza> elencoCasaVacanze = new ArrayList<CasaVacanza>();
		ArrayList<CasaVacanza> casaVacanzeDisponibili = new ArrayList<CasaVacanza>(); //Qui verranno inseriti gli appartamenti da restituire all'utente
		ArrayList<GregorianCalendar> giorniInizioFine = new ArrayList<GregorianCalendar>();
		ArrayList<GregorianCalendar> giorniPrenotatiCasaVacanze = new ArrayList<GregorianCalendar>();
		
		giorniInizioFine = contaGiorni(gcInizio,gcFine); //Tutti i giorni da dataInizio a dataFine
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoCasaVacanze = (ArrayList<CasaVacanza>) dobj.deserializza(percorsoCasaVacanza); //Elenco di tutti gli appartamenti nel file alberghi
		
		for(int i=0;i<elencoCasaVacanze.size();i++){
			boolean count = true;
			if(Integer.parseInt(elencoCasaVacanze.get(i).getPrezzo())<=Integer.parseInt(prezzo)){
				giorniPrenotatiCasaVacanze = elencoCasaVacanze.get(i).getDate();
				for(int j=0;j<giorniInizioFine.size();j++){
					for(int k=0;k<giorniPrenotatiCasaVacanze.size();k++){
						if(giorniInizioFine.get(j).equals(giorniPrenotatiCasaVacanze.get(k))){
							count = false; //Ho trovato due date uguali tra quelle richieste dall'utente e quelle gi� prenotate nell'appartamento
						}
						if(!count)
							break;  // Se la data non � disponibile, non scansionare pi� L'array
					}
					if(!count)
						break;	//Se la data non � disponibile, non scansionare pi� l'array
					}
			} else {
				count = false;
			}
			if(count)
				casaVacanzeDisponibili.add(elencoCasaVacanze.get(i));		
		}
		return casaVacanzeDisponibili;
		
	}
	
	// Metodo che effettua una ricerca degli ostelli disponibili in base al prezzo, la data di inizio e la data di fine specificate.
	
	@SuppressWarnings("unchecked")
	public ArrayList<Ostello> ricercaOstello(String prezzo, String dataInizio, String dataFine) throws DeserializzazioneException, IOException, SerializzazioneException{
		int giornoInizio = Integer.parseInt(dataInizio.substring(0,2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3,5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6,10));
		int giornoFine = Integer.parseInt(dataFine.substring(0,2));
		int meseFine = Integer.parseInt(dataFine.substring(3,5));
		int annoFine = Integer.parseInt(dataFine.substring(6,10));
		
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio,meseInizio,giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine,meseFine,giornoFine);
		
		ArrayList<Ostello> elencoOstelli = new ArrayList<Ostello>();
		ArrayList<Ostello> ostelliDisponibili = new ArrayList<Ostello>(); //Qui verranno inseriti gli ostelli da restituire all'utente
		ArrayList<GregorianCalendar> giorniInizioFine = new ArrayList<GregorianCalendar>();
		ArrayList<GregorianCalendar> giorniPrenotatiOstello = new ArrayList<GregorianCalendar>();
		
		giorniInizioFine = contaGiorni(gcInizio,gcFine); //Tutti i giorni da dataInizio a dataFine
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoOstelli = (ArrayList<Ostello>) dobj.deserializza(percorsoOstello); //Elenco di tutti gli appartamenti nel file alberghi
		
		for(int i=0;i<elencoOstelli.size();i++){
			boolean count = true;
			if(Integer.parseInt(elencoOstelli.get(i).getPrezzo())<=Integer.parseInt(prezzo)){
				giorniPrenotatiOstello = elencoOstelli.get(i).getDate();
				for(int j=0;j<giorniInizioFine.size();j++){
					for(int k=0;k<giorniPrenotatiOstello.size();k++){
						if(giorniInizioFine.get(j).equals(giorniPrenotatiOstello.get(k))){
							count = false; //Ho trovato due date uguali tra quelle richieste dall'utente e quelle gi� prenotate nell'appartamento
						}
						if(!count)
							break;  // Se la data non � disponibile, non scansionare pi� L'array
					}
					if(!count)
						break;	//Se la data non � disponibile, non scansionare pi� l'array
					}
			} else {
				count = false;
			}
			if(count)
				ostelliDisponibili.add(elencoOstelli.get(i));		
		}
		return ostelliDisponibili;
	}
}
