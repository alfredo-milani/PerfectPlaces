package standAlone.utils;

import java.io.File;
import java.util.ArrayList;

import constants.Constants;
import entity.Faq;
import entity.Locazione;
import entity.Utente;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import javax.swing.*;

public class StampaStringhe {

	private JTextArea area;

	public StampaStringhe(JTextArea area){
		this.area = area;
	}
	
	//Percorsi
	private static String percorsoLocazioniRimosse = Constants.RIMOSSE_PATH;
	private String percorsoUtenti = Constants.UTENTI_PATH;
	
	//Viene stampato l'array degli utenti nella JTextArea.
	
	@SuppressWarnings("unchecked")
	public void visualizzaUtenti(){
		
		ArrayList<Utente> utenti;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		String elenco = "UTENTI ATTUALMENTE NEL SISTEMA: \n";
		try {
			utenti = (ArrayList<Utente>) dobj.deserializza(percorsoUtenti);

			for (Utente user : utenti ){
				elenco = elenco + user.getUsername() + '\n';
			}
			area.insert(elenco,0);

		} catch (DeserializzazioneException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void visualizzaUtentiRimossiMaConDati(){

		ArrayList<Locazione> locazioni;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		String elenco = "UTENTI RIMOSSI DAL SISTEMA:  \n";
		File file = new File(percorsoLocazioniRimosse);
		if(file.length()!=0) {
			try {
				locazioni = (ArrayList<Locazione>) dobj.deserializza(percorsoLocazioniRimosse);

				elenco = elenco + locazioni.get(0).getUserLocatore() + '\n';
				area.insert(elenco, 0);

			} catch (DeserializzazioneException e) {
				e.printStackTrace();
			}
		}
	}



}
