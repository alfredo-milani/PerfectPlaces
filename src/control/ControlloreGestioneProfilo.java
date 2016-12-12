package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.*;

// Classe che consente la gestione del profilo personale, in particolare la restituzione dei valori
// associati ad un particolare utente, modificarli, e modificare l'immagine.
public class ControlloreGestioneProfilo {
	
	// Percorso
	private String percorsoUtenti = Constants.UTENTI_PATH;
	
	// Costruttore
	public ControlloreGestioneProfilo() {
	}
	
	// Viene dato in input un username, il metodo ricerca nel file l'oggetto Utente corrispondente e lo restituisce.
	@SuppressWarnings("unchecked")
	public synchronized Utente ottieniUtente(String username) throws DeserializzazioneException {
		// Caso di default in cui non venga trovato l'utente nel file
		Utente u = new Utente("errore", "errore",
                "errore", "errore", "errore",
                "errore", null);
		
		ArrayList<Utente> utenti;
		utenti = (ArrayList<Utente>) DeserializzaOggetti.deserializza(percorsoUtenti);

		for (Utente anUtenti : utenti)
			if (anUtenti.getUsername().equals(username))
				u = anUtenti;

		return u;
	}
	
	// Metodo che consente la modifca del profilo, viene effettuato un controllo sull'input per verificare che tutti i parametri
	// siano corretti, se tutto è corretto prende l'utente corrispondente dal file, ne modifica i parametri e lo salva sul file.
	// L'unico campo che non è modificabile èl'username, perchè consente di identificare univocamente l'utente nel sistema.
	
	// Ritorna: 1 --> Password vecchia non corretta
	// 		    2 --> La nuova password e la conferma della nuova password non sono uguali
	//			3 --> La nuova password è vuota
	//			4 --> Sessione utente scaduta
    //          5 --> Informazini campo "Sesso" errate
    //          6 --> Formato data errato
	//			0 --> Se tutto va bene
	@SuppressWarnings("unchecked")
	public synchronized int modificaProfilo(String username, String nome, String cognome,
                                            String email, String sesso, String nascita,
                                            String vecchiaPassword, String nuovaPassword,
                                            String confermaNuovaPassword)
            throws DeserializzazioneException, SerializzazioneException{
		ArrayList<Utente> utenti;
		utenti = (ArrayList<Utente>) DeserializzaOggetti.deserializza(percorsoUtenti);

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
		
		SerializzaOggetti.serializza(utenti, percorsoUtenti);
		return 0;
	}
}
