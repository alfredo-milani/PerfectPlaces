package control;

import constants.Constants;
import databaseManager.LoginDBManager;
import entity.Utente;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;

public class ControlloreLogin {

	private String username;
	private String password;
	private boolean logged_in;
    private LoginDBManager lDBM;


	public ControlloreLogin() {
		if (Constants.DB == 1)
		    this.lDBM = new LoginDBManager();

		this.username = "";
		this.password = "";
		this.logged_in = false;
	}


	/**
	 * Se @username e @password sono presenti nel sistema, l'utente associato ad @username viene
     * autenticato e può accedere ai servizi offerti dal sistema
	 * @param username utente che vuole usufruire di servizi del sistema
	 * @param password codice personale dell'utente che gli permette l'accesso al sistema
	 */
	public void login(String username, String password)
			throws DeserializzazioneException {

	    if (Constants.DB == 1) {

	    	if (username != null && password != null) {
                int result = this.lDBM.accesso(username, password);
                if (result != -1) {
                    this.logged_in = result == 1;
                    this.username = username;
                    this.password = password;
                }
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
                    }

                    break;
                }
            }


	    }
    }

    /**
     * Ritorna lo stato di autenticazione dell'utente
     * @return stato di autenticazione corrente
     */
	public boolean getLogged(){
		return this.logged_in;
	}

    /**
     * L'utente vine deautenticato dal sistema
     */
	public void logout(){
		this.username = "";
		this.password = "";
		this.logged_in = false;
	}

    /**
     * Restituisce l'username dell'utente associato all'istanza corrente
     * @return usernmae dell'utente corrente
     */
	public String getUser(){
		return this.username;
	}

    /**
     * Restituisce la passowrd dell'utente associato all'istanza corrente
     * @return password dell'utente corrente
     */
	public String getPsw() {
        return this.password;
    }
}