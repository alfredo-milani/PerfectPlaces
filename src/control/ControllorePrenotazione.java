package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import org.omg.PortableInterceptor.INACTIVE;
import utils.*;


public class ControllorePrenotazione {

	// Percorsi
	private static String percorsoTemp = Constants.TMPDATE_PATH;
	private static String percorsoPrenotazioniAlberghi = Constants.PRENOTAZIONE_ALBERGO_PATH;
	private static String percorsoPrenotazioniBeb = Constants.PRENOTAZIONE_BEB_PATH;
	private static String percorsoPrenotazioniOstelli = Constants.PRENOTAZIONE_OSTELLO_PATH;
	private static String percorsoPrenotazioniAppartamenti = Constants.PRENOTAZIONE_APPARTAMENTO_PATH;
	private static String percorsoPrenotazioniCaseVacanza = Constants.PRENOTAZIONE_CASAVACANZA_PATH;


	// Costruttore

	public ControllorePrenotazione(){

	}

	@SuppressWarnings("unchecked")
	private ArrayList<GregorianCalendar> contaGiorni(GregorianCalendar dataInizio, GregorianCalendar dataFine) throws IOException, SerializzazioneException, DeserializzazioneException{
        ArrayList<GregorianCalendar> elencoDate = new ArrayList<GregorianCalendar>();
		File f = new File(percorsoTemp);
		f.delete();
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
        for(GregorianCalendar elenco: elencoDate){
            System.out.println(elenco.get(Calendar.DATE)+" " + " "+elenco.get(Calendar.MONTH)+" "+ " "+elenco.get(Calendar.YEAR));
            System.out.println(elenco);
        }

		return elencoDate;
	}

	// Metodo che consente di prenotare un appartamento specificando la data di inizio e la data di fine soggiorno.

	@SuppressWarnings("unchecked")
	public boolean prenotaAppartamento(Appartamento appartamento, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException{

		if(controlloCase(appartamento,percorsoPrenotazioniAppartamenti,dataInizio,dataFine))
			return true;
		return false;
	}


	// Metodo che consente di prenotare una casa vacanze specificando la data di inizio e la data di fine soggiorno.

	@SuppressWarnings("unchecked")
	public boolean prenotaCasaVacanza(CasaVacanza casavacanza, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException{

		if(controlloCase(casavacanza, percorsoPrenotazioniCaseVacanza,dataInizio,dataFine))
			return true;
		return false;
	}

	// Metodo che consente di prenotare un albergo specificando la data di inizio e la data di fine soggiorno.

	@SuppressWarnings("unchecked")
	public boolean prenotaAlbergo(Albergo albergo, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException {
		if(controlloCamerePrenotate(albergo,percorsoPrenotazioniAlberghi,dataInizio,dataFine))
			return true;
		return false;
    }


	
	// Metodo che consente di prenotare un bed&breakfast specificando la data di inizio e la data di fine soggiorno. 
	
	@SuppressWarnings("unchecked")
	public boolean prenotaBeb(Beb beb, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException{
		
		if(controlloCamerePrenotate(beb,percorsoPrenotazioniBeb,dataInizio,dataFine))
			return true;
		return false;

	}
	
	// Metodo che consente di prenotare un ostello specificando la data di inizio e la data di fine soggiorno. 
	
	@SuppressWarnings("unchecked")
	public boolean prenotaOstello(Ostello ostello, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException{
		
		if(controlloCamerePrenotate(ostello,percorsoPrenotazioniOstelli,dataInizio,dataFine))
			return  true;
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean controlloCamerePrenotate(Locazione loc,String percorsoPrenotazioni, String dataInizio, String dataFine) throws  DeserializzazioneException, SerializzazioneException,IOException {
		int giornoInizio = Integer.parseInt(dataInizio.substring(0, 2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3, 5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6, 10));
		int giornoFine = Integer.parseInt(dataFine.substring(0, 2));
		int meseFine = Integer.parseInt(dataFine.substring(3, 5));
		int annoFine = Integer.parseInt(dataFine.substring(6, 10));


		Integer totali = Integer.parseInt(loc.getPostiTotali().trim());
		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio, meseInizio, giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine, meseFine, giornoFine);

		ArrayList<GregorianCalendar> datePrenotazione = new ArrayList<GregorianCalendar>();

		datePrenotazione = contaGiorni(gcInizio, gcFine);

		ArrayList<CamerePrenotate> prenotateList = new ArrayList<CamerePrenotate>();

		ArrayList<CamerePrenotate> temp = new ArrayList<CamerePrenotate>();

		File file = new File(percorsoPrenotazioni);

		SerializzaOggetti sobj = new SerializzaOggetti();
		if (file.length() == 0) {
			for (GregorianCalendar data_prenotazione : datePrenotazione) {
				CamerePrenotate cp = new CamerePrenotate(loc.getNomeLocazione(), data_prenotazione);
				int contatore_aggiornato = cp.getContatore() + 1;
				System.out.println("quando faccio +1 e file vuoto " + contatore_aggiornato);
				cp.setContatore(contatore_aggiornato);
				prenotateList.add(cp);

			}
			sobj.serializza(prenotateList, percorsoPrenotazioni);
			System.out.println("vero 1");
			return true;
		} else {

			DeserializzaOggetti dobj = new DeserializzaOggetti();
			prenotateList = (ArrayList<CamerePrenotate>) dobj.deserializza(percorsoPrenotazioni);
			for (GregorianCalendar data_prenotazione : datePrenotazione) {
				System.out.println("iterEST");
				boolean condizione = false;
				for (CamerePrenotate camera_prenotata : prenotateList) {
					System.out.println("iterINT");
					if (camera_prenotata.getNomeLocazion().equals(loc.getNomeLocazione()) && data_prenotazione.equals(camera_prenotata.getData())) {
						if (totali == camera_prenotata.getContatore())
							return false;
						if (!(totali == camera_prenotata.getContatore())) {
							int contatore_aggiornato = camera_prenotata.getContatore() + 1;
							System.out.println("quando faccio +1 caso posti disp  " + contatore_aggiornato);
							camera_prenotata.setContatore(contatore_aggiornato);
							condizione = true;
							break;
						}
					}
				}
				if (condizione)
					continue;
				CamerePrenotate nuovaData = new CamerePrenotate(loc.getNomeLocazione(), data_prenotazione);
				int contatore_aggiornato = nuovaData.getContatore() + 1;
				System.out.println("quando faccio +1 caso data o albergo non presente" + contatore_aggiornato);
				nuovaData.setContatore(contatore_aggiornato);
				temp.add(nuovaData);
				System.out.println("secondo else");
			}
			prenotateList.addAll(temp);
			sobj.serializza(prenotateList, percorsoPrenotazioni);
			System.out.println("vero ");
			return true;

		}
	}

	@SuppressWarnings("unchecked")
	private boolean controlloCase(Locazione loc,String percorsoPrenotazioni, String dataInizio, String dataFine) throws  DeserializzazioneException, SerializzazioneException,IOException {
		int giornoInizio = Integer.parseInt(dataInizio.substring(0, 2));
		int meseInizio = Integer.parseInt(dataInizio.substring(3, 5));
		int annoInizio = Integer.parseInt(dataInizio.substring(6, 10));
		int giornoFine = Integer.parseInt(dataFine.substring(0, 2));
		int meseFine = Integer.parseInt(dataFine.substring(3, 5));
		int annoFine = Integer.parseInt(dataFine.substring(6, 10));


		GregorianCalendar gcInizio = new GregorianCalendar(annoInizio, meseInizio, giornoInizio);
		GregorianCalendar gcFine = new GregorianCalendar(annoFine, meseFine, giornoFine);

		ArrayList<GregorianCalendar> datePrenotazione = new ArrayList<GregorianCalendar>();

		datePrenotazione = contaGiorni(gcInizio, gcFine);

		ArrayList<CamerePrenotate> prenotateList = new ArrayList<CamerePrenotate>();

		ArrayList<CamerePrenotate> temp = new ArrayList<CamerePrenotate>();

		File file = new File(percorsoPrenotazioni);

		SerializzaOggetti sobj = new SerializzaOggetti();
		if (file.length() == 0) {
			for (GregorianCalendar data_prenotazione : datePrenotazione) {
				CamerePrenotate cp = new CamerePrenotate(loc.getNomeLocazione(), data_prenotazione);
				prenotateList.add(cp);

			}
			sobj.serializza(prenotateList, percorsoPrenotazioni);
			System.out.println("vero 1");
			return true;
		} else {
			DeserializzaOggetti dobj = new DeserializzaOggetti();
			prenotateList = (ArrayList<CamerePrenotate>) dobj.deserializza(percorsoPrenotazioni);
			for (GregorianCalendar data_prenotazione : datePrenotazione) {
				System.out.println("iterEST");
				for (CamerePrenotate camera_prenotata : prenotateList) {
					System.out.println("iterINT");
					if (camera_prenotata.getNomeLocazion().equals(loc.getNomeLocazione()) && data_prenotazione.equals(camera_prenotata.getData()))
						return false;
				}
				CamerePrenotate nuovaData = new CamerePrenotate(loc.getNomeLocazione(), data_prenotazione);
				temp.add(nuovaData);
				System.out.println("secondo else");
			}
			prenotateList.addAll(temp);
			sobj.serializza(prenotateList, percorsoPrenotazioni);
			System.out.println("vero ");
			return true;

		}
	}

}

