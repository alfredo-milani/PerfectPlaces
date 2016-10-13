package standAlone_UseCase.control;

import java.util.ArrayList;

import constants.Constants;
import standAlone_UseCase.boundary.ConfineInsuccesso;
import standAlone_UseCase.boundary.ConfineSuccesso;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.*;

public class ControlloreNominaAmministratore {
	
	//Percorsi
	
	private String percorsoUtenti = Constants.UTENTI_PATH;
	private String percorsoAmministratori = Constants.ADMIN_PATH;

	//Costruttore
	
	public ControlloreNominaAmministratore(){
		
	}
	
	//Metodo che verifica la presenza di un utente registrato con l'username indicato come input. Se la presenza � conferma
	//viene inserito nell'arraylist del file degli amministratori.
	
	@SuppressWarnings("unchecked")
	public void nomina(String username) throws DeserializzazioneException, SerializzazioneException{
		ArrayList<Utente> utenti = new ArrayList<Utente>();
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		utenti = (ArrayList<Utente>)dobj.deserializza(percorsoUtenti);
		Utente u = new Utente("errore", "errore", "errore", "errore", "errore", "errore");
		boolean controllo = false;
		
		for(int i = 0;i<utenti.size();i++){
			if(utenti.get(i).getUsername().equals(username)){
				u = utenti.get(i);
				controllo = true;
				break;
			}
		}
		
		if(!controllo){
			new ConfineInsuccesso();
			return ;
		}
		
		utenti = (ArrayList<Utente>)dobj.deserializza(percorsoAmministratori);
		utenti.add(u);
		
		SerializzaOggetti sobj = new SerializzaOggetti();
		sobj.serializza(utenti, percorsoAmministratori);
		new ConfineSuccesso();
		return ;
		
		
	}
	
}
