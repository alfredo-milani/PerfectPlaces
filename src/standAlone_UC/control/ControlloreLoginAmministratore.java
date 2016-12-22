package standAlone_UC.control;

import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import standAlone_UC.boundary.ConfineAmministrazione;
import standAlone_UC.boundary.ConfineLogin;
import utils.DeserializzaOggetti;

public class ControlloreLoginAmministratore {
	
	//Variabili e percorso
	
	public static ArrayList<Utente> utenti = new ArrayList<Utente>();
	public String percorsoAmministratori = Constants.ADMIN_PATH;
	
	
	//Costruttore
	public ControlloreLoginAmministratore(){
	
	}
	
	// Deserializza gli Utenti del file utenti e li posiziona in un ArrayList. Viene fatta una scansione
	// di tutti gli elementi e viene verificata la corrispondenza di Username e Password inserite
	
	@SuppressWarnings("unchecked")
	public void login(String username, char[] password)
			throws DeserializzazioneException{
		
		String pw = String.valueOf(password);
		utenti = (ArrayList<Utente>) DeserializzaOggetti
				.deserializza(percorsoAmministratori);

		for (Utente anUtenti : utenti) {
			if (anUtenti.getUsername().equals(username)) {
				if (anUtenti.getPassword().equals(pw)) {
                    new ConfineAmministrazione();
                    return;
                }

                break;
			}
		}

        ConfineLogin confineLogin = new ConfineLogin();
		confineLogin.mostraErrori.setVisible(true);

        // new ConfineLoginErrato();
	}
}