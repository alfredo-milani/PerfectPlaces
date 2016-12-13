package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import constants.Constants;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.*;
import databaseManager.GestioneProfiloDBManager;

// Classe che consente la gestione del profilo personale, in particolare la restituzione dei valori
// associati ad un particolare getUtente, modificarli, e modificare l'immagine.
public class ControlloreGestioneProfilo {
	
	// Percorso
	private String percorsoUtenti = Constants.UTENTI_PATH;
	private GestioneProfiloDBManager gPDBM =
            new GestioneProfiloDBManager();
	
	// Costruttore
	public ControlloreGestioneProfilo() {
	}
	
	// Viene dato in input un username, il metodo ricerca nel file l'oggetto Utente corrispondente e lo restituisce.
	@SuppressWarnings("unchecked")
	public synchronized Utente ottieniUtente(String username)
            throws DeserializzazioneException {

	    if (Constants.DB == 1) {

            ControlloreLingua controlloreLingua = new ControlloreLingua();
            String[] info = gPDBM.getUtente(username);
            Locale lang;

            if (info[0] != null) {
                lang = controlloreLingua.getLocaleFromString(info[6]);
                return new Utente(info[0], info[1], info[2],
                        info[3], info[4], info[5], lang,
                        info[7], info[8]);
            } else {
                lang = controlloreLingua.getLang();
                return new Utente("errore", "errore", "errore",
                        "errore", "errore", "errore", lang,
                        "errore", "errore");
            }

        } else {

            ArrayList<Utente> utenti = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(percorsoUtenti);

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
	//			4 --> Sessione getUtente scaduta
    //          5 --> Informazini campo "Sesso" errate
    //          6 --> Formato data errato
	//			0 --> Se tutto va bene
	@SuppressWarnings("unchecked")
	public synchronized int modificaProfilo(String username, String nome, String cognome,
                                            String email, String sesso, String nascita,
                                            String vecchiaPassword, String nuovaPassword,
                                            String confermaNuovaPassword)
            throws DeserializzazioneException, SerializzazioneException {

	    if (Constants.DB == 1) {


            if (username == null || nome == null ||
                    cognome == null || email == null ||
                    vecchiaPassword == null || nuovaPassword == null ||
                    confermaNuovaPassword == null)
                return 4;

            String info[] = gPDBM.getUtente(username);

            if (!vecchiaPassword.equals("")) {
                if (!vecchiaPassword.equals(info[1]))
                    return 1;
                else if (!nuovaPassword
                        .equals(confermaNuovaPassword))
                    return 2;
                else if (nuovaPassword.equals(""))
                    return 3;
                else
                    info[1] = nuovaPassword;
            } else {
                if (!nuovaPassword.equals("") ||
                        !confermaNuovaPassword.equals(""))
                    return 1;
            }

            if (sesso != null) {
                if (!sesso.equals("")) {
                    if (!(sesso.equals("M") || sesso.equals("F")))
                        return 5;
                } else {
                    sesso = null;
                }

                System.out.println("SESSO: " + sesso);
                info[8] = sesso;
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

                info[7] = nascita;
            }

            info[2] = nome;
            info[3] = cognome;
            info[4] = email;

            System.out.println(
                    "DDDDDDUs: " + info[0] +
                            "\tnome: " + info[2] +
                            "\tcognome: " + info[3] +
                            "\temail: " + info[4] +
                            "\tsesso: " + info[8] +
                            "\tnascita: " + info[7] +
                            "\tvecchiaèsw: " + vecchiaPassword +
                            "\tnuovapsw: " + nuovaPassword +
                            "\tconfermanuova: " + confermaNuovaPassword
            );

            gPDBM.aggiornaStato(info);

            return 0;


        } else {

            ArrayList<Utente> utenti;
            utenti = (ArrayList<Utente>) DeserializzaOggetti
                    .deserializza(percorsoUtenti);

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
}