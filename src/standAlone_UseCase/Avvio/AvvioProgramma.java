package standAlone_UseCase.Avvio;

import java.io.File;
import java.util.ArrayList;

import standAlone_UseCase.boundary.ConfineAvvio;
import constants.Constants;
import entity.Utente;
import exception.SerializzazioneException;
import util.SerializzaOggetti;

public class AvvioProgramma {
	
	public String percorsoAmministratori = Constants.ADMIN_PATH;
	
	// Costruttore
	
	public AvvioProgramma(){
		
	}
	
	// Se il file percorsoAmministratori è vuoto, il metodo aggiunge l'amministratore root
	
	public void checkRoot() throws SerializzazioneException{
		File file = new File(percorsoAmministratori);
		if(file.length() == 0){
			ArrayList<Utente> amministratori = new ArrayList<Utente>();
			Utente u = new Utente("root","root","root","root","root","root");
			amministratori.add(u);
			SerializzaOggetti sobj = new SerializzaOggetti();
			sobj.serializza(amministratori, percorsoAmministratori);
		}
	}
	
	public static void main(String[] args) throws SerializzazioneException {
		AvvioProgramma ap = new AvvioProgramma();
		ap.checkRoot();		
		new ConfineAvvio();	
	}
}
