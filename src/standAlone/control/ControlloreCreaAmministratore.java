package standAlone.control;

import java.util.ArrayList;

import constants.Constants;
import standAlone.boundary.BoundaryFallimento;
import standAlone.boundary.BoundarySuccesso;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.*;

public class ControlloreCreaAmministratore {
	
	//Percorsi

	private String percorsoAmministratori = Constants.ADMIN_PATH;

	//Costruttore
	
	public ControlloreCreaAmministratore(){
		
	}
	
	//Metodo che verifica la presenza di un utente registrato con l'username indicato come input. Se la presenza � conferma
	//viene inserito nell'arraylist del file degli amministratori.
	
	@SuppressWarnings("unchecked")
	public void creaAmministratore(String username, char[] password ) throws DeserializzazioneException, SerializzazioneException{

        String pw = String.valueOf(password);
        ArrayList<Utente> amministratori ;

        if(username.equals("")|| pw.equals("")){
            new BoundaryFallimento("Hai lasciato qualche campo vuoto");
            return ;
        }

        DeserializzaOggetti dobj = new DeserializzaOggetti();
		amministratori = (ArrayList<Utente>)dobj.deserializza(percorsoAmministratori);
		boolean controllo = true;

        for(int i = 0;i<amministratori.size();i++){
			if(amministratori.get(i).getUsername().equals(username)){
				controllo=false;
				break;
			}
		}
		
		if(!controllo){
			new BoundaryFallimento("Username non disponibile");
			return ;
		}
		Utente u = new Utente(username, pw, " ", " ", "", " ");
		amministratori = (ArrayList<Utente>)dobj.deserializza(percorsoAmministratori);
		amministratori.add(u);
		
		SerializzaOggetti sobj = new SerializzaOggetti();
		sobj.serializza(amministratori, percorsoAmministratori);
		new BoundarySuccesso();
		
		
	}
	
}
