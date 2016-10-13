package control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.*;

// Classe che gestisce la prenotazione a seconda della tipologia di locazione scelta.

public class ControllorePrenotazione {
	
	// Percorsi
	
	private String percorsoAlbergo = Constants.ALBERGHI_PATH;
	private String percorsoAppartamento = Constants.APPART_PATH;
	private String percorsoBeb = Constants.BEB;
	private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
	private String percorsoOstello = Constants.OSTELLI_PATH;
	private static String percorsoTemp = Constants.TMPDATE_PATH;
	
	// Costruttore

	public ControllorePrenotazione(){
		
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
	
	// Metodo che consente di prenotare un appartamento specificando la data di inizio e la data di fine soggiorno. 
	
	@SuppressWarnings("unchecked")
	public boolean prenotaAppartamento(Appartamento appartamento, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException{
		
		int giornoInizio = Integer.parseInt(dataInizio.substring(0,2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3,5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6,10));
		int giornoFine = Integer.parseInt(dataFine.substring(0,2));
		int meseFine = Integer.parseInt(dataFine.substring(3,5));
		int annoFine = Integer.parseInt(dataFine.substring(6,10));
		
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio,meseInizio,giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine,meseFine,giornoFine);
		
		ArrayList<GregorianCalendar> datePrenotazione = new ArrayList<GregorianCalendar>();
		ArrayList<GregorianCalendar> datePrenotate = new ArrayList<GregorianCalendar>();
		
		datePrenotazione = contaGiorni(gcInizio, gcFine);
		
		datePrenotate = appartamento.getDate();
		
		for(int i=0;i<datePrenotazione.size();i++){
			for(int j=0;j<datePrenotate.size();j++){
				if(datePrenotazione.get(i).equals(datePrenotate.get(j))){
					return false;
				}
			}
		}
		for(int i=0;i<datePrenotazione.size();i++){
			datePrenotate.add(datePrenotazione.get(i));
		}
		
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		ArrayList<Appartamento> listaAppartamentiFile = new ArrayList<Appartamento>();
		listaAppartamentiFile = (ArrayList<Appartamento>)dobj.deserializza(percorsoAppartamento);
		
		for(int i = 0;i<listaAppartamentiFile.size();i++){
			if(listaAppartamentiFile.get(i).getNomeLocazione().equals(appartamento.getNomeLocazione())&&
					listaAppartamentiFile.get(i).getIndirizzo().equals(appartamento.getIndirizzo())){
				System.out.println(listaAppartamentiFile.get(i).getDate());
				listaAppartamentiFile.get(i).setDate(datePrenotate);
				System.out.println(listaAppartamentiFile.get(i).getDate());
			}
		}
		
		SerializzaOggetti sobj = new SerializzaOggetti();
		sobj.serializza(listaAppartamentiFile, percorsoAppartamento);
		return true;		
	}
	
	
	// Metodo che consente di prenotare una casa vacanze specificando la data di inizio e la data di fine soggiorno. 
	
	@SuppressWarnings("unchecked")
	public boolean prenotaCasaVacanza(CasaVacanza casavacanza, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException{
		
		int giornoInizio = Integer.parseInt(dataInizio.substring(0,2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3,5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6,10));
		int giornoFine = Integer.parseInt(dataFine.substring(0,2));
		int meseFine = Integer.parseInt(dataFine.substring(3,5));
		int annoFine = Integer.parseInt(dataFine.substring(6,10));
		
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio,meseInizio,giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine,meseFine,giornoFine);
		
		ArrayList<GregorianCalendar> datePrenotazione = new ArrayList<GregorianCalendar>();
		ArrayList<GregorianCalendar> datePrenotate = new ArrayList<GregorianCalendar>();
		
		datePrenotazione = contaGiorni(gcInizio, gcFine);
		
		datePrenotate = casavacanza.getDate();
		
		for(int i=0;i<datePrenotazione.size();i++){
			for(int j=0;j<datePrenotate.size();j++){
				if(datePrenotazione.get(i).equals(datePrenotate.get(j))){
					return false;
				}
			}
		}
		for(int i=0;i<datePrenotazione.size();i++){
			datePrenotate.add(datePrenotazione.get(i));
		}
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		ArrayList<CasaVacanza> listaCasaVacanzeFile = new ArrayList<CasaVacanza>();
		listaCasaVacanzeFile = (ArrayList<CasaVacanza>)dobj.deserializza(percorsoCasaVacanza);
		for(int i = 0;i<listaCasaVacanzeFile.size();i++){
			if(listaCasaVacanzeFile.get(i).equals(casavacanza)){
				listaCasaVacanzeFile.get(i).setDate(datePrenotate);
			}
		}
		
		SerializzaOggetti sobj = new SerializzaOggetti();
		sobj.serializza(listaCasaVacanzeFile, percorsoCasaVacanza);
		
		
		
		return true;		
	}

	// Metodo che consente di prenotare un albergo specificando la data di inizio e la data di fine soggiorno. 
	
	@SuppressWarnings("unchecked")
	public boolean prenotaAlbergo(Albergo albergo, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException{
		
		int giornoInizio = Integer.parseInt(dataInizio.substring(0,2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3,5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6,10));
		int giornoFine = Integer.parseInt(dataFine.substring(0,2));
		int meseFine = Integer.parseInt(dataFine.substring(3,5));
		int annoFine = Integer.parseInt(dataFine.substring(6,10));
		
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio,meseInizio,giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine,meseFine,giornoFine);
		
		ArrayList<GregorianCalendar> datePrenotazione = new ArrayList<GregorianCalendar>();
		ArrayList<GregorianCalendar> datePrenotate = new ArrayList<GregorianCalendar>();
		
		datePrenotazione = contaGiorni(gcInizio, gcFine);
		
		datePrenotate = albergo.getDate();
		
		int count;
		
		for(int i=0;i<datePrenotazione.size();i++){
			count = 0;
			for(int j=0;j<datePrenotate.size();j++){
				if(datePrenotazione.get(i).equals(datePrenotate.get(j))){
					count = count + 1;
					if(count==Integer.parseInt(albergo.getCamereTotali())){
						return false;
					}
				}
			}
		}
		
		for(int i=0;i<datePrenotazione.size();i++){
			datePrenotate.add(datePrenotazione.get(i));
		}
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		ArrayList<Albergo> listaAlbergoFile = new ArrayList<Albergo>();
		listaAlbergoFile = (ArrayList<Albergo>)dobj.deserializza(percorsoAlbergo);
		for(int i = 0;i<listaAlbergoFile.size();i++){
			if(listaAlbergoFile.get(i).equals(albergo)){
				listaAlbergoFile.get(i).setDate(datePrenotate);
			}
		}
		
		SerializzaOggetti sobj = new SerializzaOggetti();
		sobj.serializza(listaAlbergoFile, percorsoAlbergo);
	
		return true;		
	}
	
	// Metodo che consente di prenotare un bed&breakfast specificando la data di inizio e la data di fine soggiorno. 
	
	@SuppressWarnings("unchecked")
	public boolean prenotaBeb(Beb beb, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException{
		
		int giornoInizio = Integer.parseInt(dataInizio.substring(0,2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3,5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6,10));
		int giornoFine = Integer.parseInt(dataFine.substring(0,2));
		int meseFine = Integer.parseInt(dataFine.substring(3,5));
		int annoFine = Integer.parseInt(dataFine.substring(6,10));
		
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio,meseInizio,giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine,meseFine,giornoFine);
		
		ArrayList<GregorianCalendar> datePrenotazione = new ArrayList<GregorianCalendar>();
		ArrayList<GregorianCalendar> datePrenotate = new ArrayList<GregorianCalendar>();
		
		datePrenotazione = contaGiorni(gcInizio, gcFine);
		
		datePrenotate = beb.getDate();
		
		int count;
		
		for(int i=0;i<datePrenotazione.size();i++){
			count = 0;
			for(int j=0;j<datePrenotate.size();j++){
				if(datePrenotazione.get(i).equals(datePrenotate.get(j))){
					count = count + 1;
					if(count==Integer.parseInt(beb.getCamereTotali())){
						return false;
					}
				}
			}
		}
		
		for(int i=0;i<datePrenotazione.size();i++){
			datePrenotate.add(datePrenotazione.get(i));
		}
		
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		ArrayList<Beb> listaBebFile = new ArrayList<Beb>();
		listaBebFile = (ArrayList<Beb>)dobj.deserializza(percorsoBeb);
		for(int i = 0;i<listaBebFile.size();i++){
			if(listaBebFile.get(i).equals(beb)){
				listaBebFile.get(i).setDate(datePrenotate);
			}
		}
		
		SerializzaOggetti sobj = new SerializzaOggetti();
		sobj.serializza(listaBebFile, percorsoBeb);
		
		
		
		return true;		
	}
	
	// Metodo che consente di prenotare un ostello specificando la data di inizio e la data di fine soggiorno. 
	
	@SuppressWarnings("unchecked")
	public boolean prenotaOstello(Ostello ostello, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException{
		
		int giornoInizio = Integer.parseInt(dataInizio.substring(0,2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3,5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6,10));
		int giornoFine = Integer.parseInt(dataFine.substring(0,2));
		int meseFine = Integer.parseInt(dataFine.substring(3,5));
		int annoFine = Integer.parseInt(dataFine.substring(6,10));
		
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio,meseInizio,giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine,meseFine,giornoFine);
		
		ArrayList<GregorianCalendar> datePrenotazione = new ArrayList<GregorianCalendar>();
		ArrayList<GregorianCalendar> datePrenotate = new ArrayList<GregorianCalendar>();
		
		datePrenotazione = contaGiorni(gcInizio, gcFine);
		
		datePrenotate = ostello.getDate();
		
		int count;
		
		for(int i=0;i<datePrenotazione.size();i++){
			count = 0;
			for(int j=0;j<datePrenotate.size();j++){
				if(datePrenotazione.get(i).equals(datePrenotate.get(j))){
					count = count + 1;
					if(count==Integer.parseInt(ostello.getNumeroLettiTotali())){
						return false;
					}
				}
			}
		}
		
		for(int i=0;i<datePrenotazione.size();i++){
			datePrenotate.add(datePrenotazione.get(i));
		}
		
		
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		ArrayList<Ostello> listaOstelloFile = new ArrayList<Ostello>();
		listaOstelloFile = (ArrayList<Ostello>)dobj.deserializza(percorsoOstello);
		for(int i = 0;i<listaOstelloFile.size();i++){
			if(listaOstelloFile.get(i).equals(ostello)){
				listaOstelloFile.get(i).setDate(datePrenotate);
			}
		}
		
		SerializzaOggetti sobj = new SerializzaOggetti();
		sobj.serializza(listaOstelloFile, percorsoOstello);
		
		return true;		
	}
	
	
	
	
	
}
