package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import constants.Constants;
import utils.*;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;



public class ControlloreRicercaLocazione {

	// Percorsi

	private String percorsoAlbergo = Constants.ALBERGHI_PATH;
	private String percorsoAppartamento = Constants.APPART_PATH;
	private String percorsoBeb = Constants.BEB;
	private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
	private String percorsoOstello = Constants.OSTELLI_PATH;

	// Costruttore

	public ControlloreRicercaLocazione() {

	}



	// Metodo che effettua una ricerca degli alberghi disponibili in base al prezzo e alla provincia specificati.

	@SuppressWarnings("unchecked")
	public ArrayList<Albergo> ricercaAlbergo(String provincia, String prezzo) throws DeserializzazioneException, IOException, SerializzazioneException {
		ArrayList<Albergo> elencoAlberghi = new ArrayList<Albergo>();
		ArrayList<Albergo> alberghiDisponibili = new ArrayList<Albergo>(); //Qui verranno inseriti gli alberghi da restituire all'utente

		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoAlberghi = (ArrayList<Albergo>) dobj.deserializza(percorsoAlbergo); //Elenco di tutti gli alberghi presenti

		System.err.println("numero alberghi: " + elencoAlberghi.size());

		for (int i = 0; i < elencoAlberghi.size(); i++) {
			if ((elencoAlberghi.get(i).getProvincia().equals(provincia)) &&                    //controllo sulla provincia
					(Integer.parseInt(elencoAlberghi.get(i).getPrezzo()) <= Integer.parseInt(prezzo))){//controllo sul prezzo
						alberghiDisponibili.add(elencoAlberghi.get(i));
			}
		}
		return alberghiDisponibili;

	}

	// Metodo che effettua una ricerca degli appartamenti disponibili in base al prezzo e alla provincia specificati.

	@SuppressWarnings("unchecked")
	public ArrayList<Appartamento> ricercaAppartamento(String provincia, String prezzo) throws DeserializzazioneException, IOException, SerializzazioneException {
		ArrayList<Appartamento> elencoAppartamenti = new ArrayList<Appartamento>();
		ArrayList<Appartamento> appartamentiDisponibili = new ArrayList<Appartamento>(); //Qui verranno inseriti gli appartamenti da restituire all'utente

		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoAppartamenti = (ArrayList<Appartamento>) dobj.deserializza(percorsoAppartamento); //Elenco di tutti gli appartamenti presenti

		for (int i = 0; i < elencoAppartamenti.size(); i++) {
			if (elencoAppartamenti.get(i).getProvincia().equals(provincia) &&
					Integer.parseInt(elencoAppartamenti.get(i).getPrezzo()) <= Integer.parseInt(prezzo)) {

				appartamentiDisponibili.add(elencoAppartamenti.get(i));
			}
		}
		return appartamentiDisponibili;

	}

	// Metodo che effettua una ricerca dei B&B disponibili in base al prezzo e alla provincia

	@SuppressWarnings("unchecked")
	public ArrayList<Beb> ricercaBeb(String provincia, String prezzo) throws DeserializzazioneException, IOException, SerializzazioneException {
		ArrayList<Beb> elencoBeb = new ArrayList<Beb>();
		ArrayList<Beb> bebDisponibili = new ArrayList<Beb>(); //Qui verranno inseriti i beb da restituire all'utente

		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoBeb = (ArrayList<Beb>) dobj.deserializza(percorsoBeb); //Elenco di tutti i beb presenti

		for (int i = 0; i < elencoBeb.size(); i++) {
			if (elencoBeb.get(i).getProvincia().equals(provincia) &&
					Integer.parseInt(elencoBeb.get(i).getPrezzo()) <= Integer.parseInt(prezzo)) {
					bebDisponibili.add(elencoBeb.get(i));
			}
		}

		return bebDisponibili;

	}

	// Metodo che effettua una ricerca degli case vacanza disponibili in base al prezzo e alla provincia

	@SuppressWarnings("unchecked")
	public ArrayList<CasaVacanza> ricercaCasaVacanze(String provincia, String prezzo) throws DeserializzazioneException, IOException, SerializzazioneException {

		ArrayList<CasaVacanza> elencoCasaVacanze = new ArrayList<CasaVacanza>();
		ArrayList<CasaVacanza> casaVacanzeDisponibili = new ArrayList<CasaVacanza>(); //Qui verranno inseriti gli appartamenti da restituire all'utente
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoCasaVacanze = (ArrayList<CasaVacanza>) dobj.deserializza(percorsoCasaVacanza); //Elenco di tutti gli appartamenti presenti

		for (int i = 0; i < elencoCasaVacanze.size(); i++) {
			boolean count = true;
			if (elencoCasaVacanze.get(i).getProvincia().equals(provincia) &&
					Integer.parseInt(elencoCasaVacanze.get(i).getPrezzo()) <= Integer.parseInt(prezzo)) {
				casaVacanzeDisponibili.add(elencoCasaVacanze.get(i));
			}
		}
		return casaVacanzeDisponibili;

	}

	// Metodo che effettua una ricerca degli ostelli disponibili in base al prezzo e alla provincia

	@SuppressWarnings("unchecked")
	public ArrayList<Ostello> ricercaOstello(String provincia, String prezzo) throws DeserializzazioneException, IOException, SerializzazioneException {


		ArrayList<Ostello> elencoOstelli = new ArrayList<Ostello>();
		ArrayList<Ostello> ostelliDisponibili = new ArrayList<Ostello>(); //Qui verranno inseriti gli ostelli da restituire all'utente


		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoOstelli = (ArrayList<Ostello>) dobj.deserializza(percorsoOstello); //Elenco di tutti gli ostelli presenti

		for (int i = 0; i < elencoOstelli.size(); i++) {
			if (elencoOstelli.get(i).getProvincia().equals(provincia) &&
					Integer.parseInt(elencoOstelli.get(i).getPrezzo()) <= Integer.parseInt(prezzo)) {
				ostelliDisponibili.add(elencoOstelli.get(i));
			}
		}

		return ostelliDisponibili;

	}


}
