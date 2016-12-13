package control;

import java.io.File;
import java.util.ArrayList;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;
import utils.dbManager.LoginDBManager;

// Classe che gestisce il login al sistema.
public class ControlloreLogin {
	// Variabili
    private LoginDBManager lDBM = new LoginDBManager();
	private String username = "";
	private String password = "";
	private boolean logged_in = false;


	// Costruttore
	public ControlloreLogin(){
	}

	// Deserializza gli Utenti del file utenti e li posiziona in un ArrayList. Viene fatta una scansione
	// di tutti gli elementi e viene verificata la corrispondenza di Username e Password inserite
	@SuppressWarnings("unchecked")
	public synchronized void login(String username, String password)
			throws DeserializzazioneException {

	    if (Constants.DB == 1) {

            this.logged_in = this.lDBM.accesso(username, password);
            if (logged_in) {
                this.username = username;
                this.password = password;
            }

        } else {


            File file = new File(Constants.UTENTI_PATH);
            ArrayList<Utente> utenti = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(Constants.UTENTI_PATH);

            if (file.length() == 0)
                return;

            for (Utente anUtenti : utenti) {
                if (anUtenti.getUsername().equals(username)) {
                    if (anUtenti.getPassword().equals(password)) {
                        logged_in = true;
                        this.username = username;
                        this.password = password;
                        break;
                    }
                    logged_in = false;
                    break;
                }
            }


	    }
    }
	
	// Check se l'getUtente è loggato o meno
	public boolean getLogged(){
		return this.logged_in;
	}
	
	// Viene effettuato il logout settando la variabile logged_in
	public void logout(){
		this.username = "";
		this.password = "";
		this.logged_in = false;
	}
	
	// Restituisce l'username associato all'istanza
	public String getUser(){
		return this.username;
	}

	// Restituisce la password associata all'istanza
	public String getPsw() {
        return this.password;
    }
}
