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
	private static String percorsoPrenotazioniAlberghi = Constants.PRENOTAZIONE_ALBERGO_PATH;
	private static String percorsoPrenotazioniBeb = Constants.PRENOTAZIONE_BEB_PATH;
	private static String percorsoPrenotazioniOstelli = Constants.PRENOTAZIONE_OSTELLO_PATH;
	private static String percorsoPrenotazioniAppartamenti = Constants.PRENOTAZIONE_APPARTAMENTO_PATH;
	private static String percorsoPrenotazioniCaseVacanza = Constants.PRENOTAZIONE_CASAVACANZA_PATH;
    private static String percorsoTemp = Constants.TMPDATE_PATH;


	// Costruttore

	public ControllorePrenotazione(){

	}

	public boolean prenotazione(Locazione locazione, String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException {
        if(locazione.getClass()==Albergo.class){
            if(controlloCamerePrenotate(locazione,percorsoPrenotazioniAlberghi,dataInizio,dataInizio))
                return true;
        }
        else if(locazione.getClass()==Appartamento.class){
           if(controlloCase(locazione,percorsoPrenotazioniAppartamenti,dataInizio,dataFine))
                 return true;
        }
        else if(locazione.getClass()==Beb.class){
            if(controlloCamerePrenotate(locazione,percorsoPrenotazioniBeb,dataInizio,dataFine))
                return true;
        }
        else if(locazione.getClass()==CasaVacanza.class){
            if(controlloCase(locazione,percorsoPrenotazioniCaseVacanza,dataInizio,dataFine))
                return true;
        }
        else {
            if(controlloCamerePrenotate(locazione,percorsoPrenotazioniOstelli,dataInizio,dataFine))
                 return true;
        }

        return false;
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
            System.out.println("array date: "+ elenco.get(Calendar.DATE)+" " + " "+elenco.get(Calendar.MONTH)+" "+ " "+elenco.get(Calendar.YEAR));
            System.out.println(elenco);
        }

		return elencoDate;
	}

	@SuppressWarnings("unchecked")
	private boolean controlloCamerePrenotate(Locazione loc,String percorsoPrenotazioni, String dataInizio, String dataFine) throws  DeserializzazioneException, SerializzazioneException,IOException {

        TrasformaDate td = new TrasformaDate();

		Integer totali = Integer.parseInt(loc.getPostiTotali().trim());
		GregorianCalendar gcInizio = td.trasformaInGregorianCalendar(dataInizio);
		GregorianCalendar gcFine = td.trasformaInGregorianCalendar(dataFine);

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

        TrasformaDate td = new TrasformaDate();

		GregorianCalendar gcInizio = td.trasformaInGregorianCalendar(dataInizio);
		GregorianCalendar gcFine = td.trasformaInGregorianCalendar(dataFine);

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

