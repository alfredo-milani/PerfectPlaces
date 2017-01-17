package standAlone.utils;

import constants.Constants;
import entity.Locazione;
import entity.Utente;
import exception.DeserializzazioneException;
import standAlone.control.ControlloreProfiloAmministratore;
import utils.DeserializzaOggetti;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

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
		ControlloreProfiloAmministratore cp =
				new ControlloreProfiloAmministratore();
		Utente utente = cp.ottieniUtente(System.getProperty(Constants.USER_KEY));

		Locale langLocale;
		if (utente != null) {
			langLocale = utente.getLingua();
		} else {
			langLocale = Locale.getDefault();
		}

		ResourceBundle bundle = ResourceBundle
				.getBundle(Constants.PACKAGE_LANGUAGE, langLocale);

		ArrayList<Utente> utenti;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		String elenco = bundle.getString("boundaryRimuoviUtente_utenti_presenti");
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

        ControlloreProfiloAmministratore cp =
                new ControlloreProfiloAmministratore();
        Utente utente = cp.ottieniUtente(System.getProperty(Constants.USER_KEY));

        Locale langLocale;
        if (utente != null) {
            langLocale = utente.getLingua();
        } else {
            langLocale = Locale.getDefault();
        }

        ResourceBundle bundle = ResourceBundle
                .getBundle(Constants.PACKAGE_LANGUAGE, langLocale);

		ArrayList<Locazione> locazioni;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		String elenco = bundle.getString("boundaryRimuoviUtente_rimossi");
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
