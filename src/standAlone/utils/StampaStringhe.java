package standAlone.utils;

import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import javax.swing.*;

public class StampaStringhe {

	JTextArea area;

	public StampaStringhe(JTextArea area){
		this.area = area;
	}
	
	//Percorso
	
	private String percorsoUtenti = Constants.UTENTI_PATH;
	
	//Viene stampato l'array degli utenti nella JTextArea.
	
	@SuppressWarnings("unchecked")
	public void visualizzaUtenti(){
		
		ArrayList<Utente> utenti;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		String elenco = "Utenti attualmente nel sistema: \n";
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



}
