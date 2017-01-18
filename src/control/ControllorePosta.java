package control;

import constants.Constants;
import databaseManager.PostaDBManager;
import entity.Messaggio;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Classe che si occupa della gestione della posta, sia dell'invio dei messaggi che della ricerca.
public class ControllorePosta {

	private PostaDBManager gPDBM;

	public ControllorePosta() {
	    if (Constants.DB == 1)
	        this.gPDBM = new PostaDBManager();
	}

    // Metodo che riceve in input il codice di un messaggio, lo cerca all'interno del DB e, se lo trova, lo restituisce.
    public synchronized Messaggio ricercaMessaggioPerCodice(int codice) {
	    if (Constants.DB == 1) {

            return gPDBM.getMessaggioCod(codice);

        } else {

            ArrayList<Messaggio> elencoMessaggi = new ArrayList<>();
            try {
                elencoMessaggi = (ArrayList<Messaggio>) DeserializzaOggetti
                        .deserializza(Constants.MSG_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            Messaggio messaggio;

            for (Messaggio anElencoMessaggi : elencoMessaggi)
                if (anElencoMessaggi.getCodice() == codice)
                    return anElencoMessaggi;

            messaggio = new Messaggio("errore", "errore",
                    "errore", "errore",
                    "errore", 0);
            return messaggio;

        }
    }
	
	// Metodo che riceve in input uno username, ricerca nel DB tutti i messaggi che hanno quello username come
	// destinatario e li restituisce in un Array List.
	public synchronized ArrayList<Messaggio> ricercaMessaggi(String username) {

	    if (Constants.DB == 1) {

            return gPDBM.getMessaggi(username);

        } else {

            ArrayList<Messaggio> elencoMessaggi = new ArrayList<>();
            ArrayList<Messaggio> elencoMessaggiUser = new ArrayList<>();

            File file = new File(Constants.MSG_PATH);

            if (file.length() == 0) {
                return elencoMessaggiUser;
            } else {

                try {
                    elencoMessaggi = (ArrayList<Messaggio>) DeserializzaOggetti.deserializza(Constants.MSG_PATH);
                } catch (DeserializzazioneException e) {
                    e.printStackTrace();
                }

                for (Messaggio anElencoMessaggi : elencoMessaggi)
                    if (anElencoMessaggi.getDestinatario().equals(username))
                        elencoMessaggiUser.add(anElencoMessaggi);

                return elencoMessaggiUser;
            }

        }
	}
	
	// Metodo che permette di inviare un messaggio. Effettua un controllo sull'input ritornando un codice specifico se
	// viene riscontrato qualche errore. Se non c'è nessun errore, aggiunge al DB il messaggio
	// Restituisce: 1 --> Se il destinatario è vuoto
	// 		  2 --> Se l'oggetto è vuoto
	//        3 --> Se il contenuto è vuoto
	//        4 --> Se il destinatario non esiste
    //        5 --> Se la sessione dell'utente è scaduta
	//        6 --> Se il mittente e il destinatario coincidono
	//        0 --> Se tutto va bene
	public synchronized int scriviMessaggio(String oggetto, String mittente,
											String destinatario, String contenuto) {

	    if (Constants.DB == 1) {

            if (destinatario == null || destinatario.equals(""))
                return 1;
            else if (oggetto == null || oggetto.equals(""))
                return 2;
            else if (contenuto == null || contenuto.equals(""))
                return 3;
            else if (mittente == null || mittente.equals(""))
                return 5;
            else if (mittente.equals(destinatario))
                return 6;

            if (!gPDBM.controlloUsername(destinatario))
                return 4;

            gPDBM.setMessaggio(oggetto, mittente, destinatario,
                    contenuto, calcolaData());

            return 0;

        } else {

            if (destinatario == null || destinatario.equals(""))
                return 1;
            else if (oggetto == null || oggetto.equals(""))
                return 2;
            else if (contenuto == null || contenuto.equals(""))
                return 3;
            else if (mittente == null || mittente.equals(""))
                return 5;
            else if (mittente.equals(destinatario))
                return 6;

            ArrayList<Utente> elencoUtenti = new ArrayList<>();
            try {
                elencoUtenti = (ArrayList<Utente>) DeserializzaOggetti
                        .deserializza(Constants.UTENTI_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            boolean controllo = false;
            for (Utente anElencoUtenti : elencoUtenti)
                if (anElencoUtenti.getUsername().equals(destinatario))
                    controllo = true;

            if (!controllo)
                return 4;

            ArrayList<Messaggio> elencoMessaggi = new ArrayList<>();
            File file = new File(Constants.MSG_PATH);
            if (file.length() != 0) {
                try {
                    elencoMessaggi = (ArrayList<Messaggio>)
                            DeserializzaOggetti
                                    .deserializza(Constants.MSG_PATH);
                } catch (DeserializzazioneException e) {
                    e.printStackTrace();
                }
            }

            Messaggio messaggio = new Messaggio(oggetto,
                    mittente,
                    destinatario,
                    contenuto,
                    calcolaData(),
                    ControllorePosta.assegnaCodice()
            );
            elencoMessaggi.add(messaggio);

            try {
                SerializzaOggetti.serializza(elencoMessaggi, Constants.MSG_PATH);
            } catch (SerializzazioneException e) {
                e.printStackTrace();
            }

            return 0;

        }
	}

	// Metodo che riceve in input il codice di un messaggio e si occupa della sua eliminazione
    // Ritorna il vaolre 0 se il messaggio non è stato rimosso correttamente altrimenti ritorna il valore 1
	public synchronized int eliminaMessaggio(int oggetto) {
        if (Constants.DB == 1) {

            return gPDBM.rimuoviMessaggio(oggetto) ? 1 : 0;

        } else {

            ArrayList<Messaggio> elencoMessaggi = new ArrayList<>();
            try {
                elencoMessaggi = (ArrayList<Messaggio>)
                        DeserializzaOggetti
                                .deserializza(Constants.MSG_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }


            boolean esito = elencoMessaggi
                    .removeIf(messaggio -> messaggio.getCodice() == oggetto);

            try {
                SerializzaOggetti.serializza(elencoMessaggi, Constants.MSG_PATH);
            } catch (SerializzazioneException e) {
                e.printStackTrace();
            }

            return esito ? 1 : 0;

        }
	}

    private String calcolaData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }

    // Metodo per assegnare un codice univoco ad un messaggio.
    @SuppressWarnings("unchecked")
    private static int assegnaCodice() {
        //Se non c'è nessun messaggio ritorna il codice 0
        File file = new File(Constants.MSG_PATH);
        if(file.length() == 0)
            return 0;

        // Se il file non è vuoto calcola il primo codice disponibile
        ArrayList<Messaggio> elencoMessaggi = new ArrayList<>();
        try {
            elencoMessaggi = (ArrayList<Messaggio>) DeserializzaOggetti
                    .deserializza(Constants.MSG_PATH);
        } catch (DeserializzazioneException e) {
            e.printStackTrace();
        }

        int codice = elencoMessaggi.get(elencoMessaggi.size() - 1)
                .getCodice();

        return ++codice;
    }
}