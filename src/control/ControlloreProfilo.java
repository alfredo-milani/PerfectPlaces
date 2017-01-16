package control;

import constants.Constants;
import databaseManager.ProfiloDBManager;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

// Classe che consente la gestione del profilo personale.
public class ControlloreProfilo {
	
	private ProfiloDBManager gPDBM;
	
	public ControlloreProfilo() {
	    if (Constants.DB == 1)
	        this.gPDBM = new ProfiloDBManager();
	}
	
	// Viene dato in input un username, il metodo ricerca nel file l'oggetto Utente corrispondente e lo restituisce.
    // Se l'oggetto non viene trovato viene restituito l'oggetto utente fittizio "errore".
	public synchronized Utente ottieniUtente(String username) {
	    if (Constants.DB == 1) {

            Utente utente = gPDBM.getUtente(username);
            if (utente == null) {
                return new Utente("errore", "errore", "errore",
                        "errore", "errore", "errore", Locale.getDefault(),
                        "errore", "errore");
            }

            return utente;

        } else {

            ArrayList<Utente> utenti = new ArrayList<>();
            try {
                utenti = (ArrayList<Utente>)
                        DeserializzaOggetti
                                .deserializza(Constants.UTENTI_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            for (Utente anUtenti : utenti)
                if (anUtenti.getUsername().equals(username))
                    return anUtenti;

            return null;

        }
	}
	
	// Metodo che consente la modifca del profilo, viene effettuato un controllo sull'input per verificare che tutti i parametri
	// siano corretti, se tutto è corretto prende l'getUtente corrispondente dal file, ne modifica i parametri e lo salva sul file.
	// L'unico campo che non è modificabile èl'username, perchè consente di identificare univocamente l'getUtente nel sistema.
	
	// Ritorna: 1 --> Password vecchia non corretta
	// 		    2 --> La nuova password e la conferma della nuova password non sono uguali
	//			3 --> La nuova password è vuota
	//			4 --> Sessione utente scaduta
    //          5 --> Informazini campo "Sesso" errate
    //          6 --> Formato data errato
	//			0 --> Se tutto va bene
	public synchronized int modificaProfilo(String username, String nome, String cognome,
                                            String email, String sesso, String nascita,
                                            String vecchiaPassword, String nuovaPassword,
                                            String confermaNuovaPassword) {

	    if (Constants.DB == 1) {

            if (username == null || nome == null ||
                    cognome == null || email == null ||
                    vecchiaPassword == null || nuovaPassword == null ||
                    confermaNuovaPassword == null)
                return 4;

            Utente utente = gPDBM.getUtente(username);

            int result = this.checkPsw(utente, vecchiaPassword,
                    nuovaPassword, confermaNuovaPassword);
            if (result != 0)
                return result;

            if (sesso != null) {
                if (!sesso.equals("")) {
                    if (!(sesso.equals("M") || sesso.equals("F")))
                        return 5;
                } else {
                    sesso = null;
                }

                utente.setSesso(sesso);
            }

            if (nascita != null) {
                if (!nascita.equals("")) {
                    String dateFormat = "dd/MM/yyyy";
                    if (nascita.length() != dateFormat.length())
                        return 6;

                    Date date = null;
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                        date = sdf.parse(nascita);
                        if (!nascita.equals(sdf.format(date))) {
                            date = null;
                        }
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    if (date == null)
                        return 6;
                } else {
                    nascita = null;
                }

                utente.setNascita(nascita);
            }

            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);

            gPDBM.aggiornaStato(utente);

            return 0;

        } else {

            ArrayList<Utente> utenti = new ArrayList<>();
            try {
                utenti = (ArrayList<Utente>) DeserializzaOggetti
                        .deserializza(Constants.UTENTI_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            if (username == null || nome == null ||
                    cognome == null || email == null ||
                    vecchiaPassword == null || nuovaPassword == null ||
                    confermaNuovaPassword == null) return 4;

            for (Utente anUtenti : utenti) {
                if (anUtenti.getUsername().equals(username)) {
                    if (!vecchiaPassword.equals("")) {
                        if (!vecchiaPassword.equals(anUtenti.getPassword()))
                            return 1;
                        else if (!nuovaPassword.equals(confermaNuovaPassword))
                            return 2;
                        else if (nuovaPassword.equals(""))
                            return 3;
                        else
                            anUtenti.setPassword(nuovaPassword);
                    } else {
                        if (!nuovaPassword.equals("") || !confermaNuovaPassword.equals(""))
                            return 1;
                    }

                    if (sesso != null) {
                        if (!sesso.equals("")) {
                            if (!(sesso.equals("M") || sesso.equals("F")))
                                return 5;
                        } else {
                            sesso = null;
                        }

                        anUtenti.setSesso(sesso);
                    }

                    if (nascita != null) {
                        if (!nascita.equals("")) {
                            String dateFormat = "dd/MM/yyyy";
                            if (nascita.length() != dateFormat.length())
                                return 6;

                            Date date = null;
                            try {
                                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                                date = sdf.parse(nascita);
                                if (!nascita.equals(sdf.format(date))) {
                                    date = null;
                                }
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                            if (date == null)
                                return 6;
                        } else {
                            nascita = null;
                        }

                        anUtenti.setNascita(nascita);
                    }

                    anUtenti.setNome(nome);
                    anUtenti.setCognome(cognome);
                    anUtenti.setEmail(email);
                }
            }

            try {
                SerializzaOggetti.serializza(utenti, Constants.UTENTI_PATH);
            } catch (SerializzazioneException e) {
                e.printStackTrace();
            }

            return 0;

        }
	}

	protected int checkPsw(Utente user, String oldPsw,
                         String newPsw, String cNewPsw) {
        if (!oldPsw.equals("")) {
            if (!oldPsw.equals(user.getPassword()))
                return 1;
            else if (!newPsw
                    .equals(cNewPsw))
                return 2;
            else if (newPsw.equals(""))
                return 3;
            else
                user.setPassword(newPsw);
        } else {
            if (!newPsw.equals("") ||
                    !cNewPsw.equals(""))
                return 1;
        }

        return 0;
    }

}