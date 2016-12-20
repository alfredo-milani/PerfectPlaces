package standAlone_UC.control;

import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standAlone_UC.boundary.ConfineInsuccesso;
import standAlone_UC.boundary.ConfineSuccesso;
import utils.*;

public class ControlloreNominaAmministratore {

	private String percorsoUtenti = Constants.UTENTI_PATH;
	private String percorsoAmministratori = Constants.ADMIN_PATH;

	public ControlloreNominaAmministratore(){
		
	}
	
	//Metodo che verifica la presenza di un getUtente registrato con l'username indicato come input. Se la presenza � conferma
	//viene inserito nell'arraylist del file degli amministratori.
	
	@SuppressWarnings("unchecked")
	public void nomina(String username)
			throws DeserializzazioneException, SerializzazioneException{

	    ArrayList<Utente> utenti;
		utenti = (ArrayList<Utente>)DeserializzaOggetti
                .deserializza(percorsoUtenti);

		Utente u = new Utente("errore", "errore", "errore",
				"errore", "errore",
                "errore", null,
				null, null);
		boolean controllo = false;

        for (Utente anUtenti : utenti) {
            if (anUtenti.getUsername().equals(username)) {
                u = anUtenti;
                controllo = true;
                break;
            }
        }
		
		if(!controllo){
			new ConfineInsuccesso();
			return ;
		}
		
		utenti = (ArrayList<Utente>)DeserializzaOggetti
                .deserializza(percorsoAmministratori);
		utenti.add(u);
		
		SerializzaOggetti
                .serializza(utenti, percorsoAmministratori);
		new ConfineSuccesso();
	}
	
}
