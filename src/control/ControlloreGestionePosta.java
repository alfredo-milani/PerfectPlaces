package control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entity.Messaggio;
import databaseManager.GestionePostaDBManager;
import databaseManager.RegistrazioneDBManager;

// Classe che si occupa della gestione della posta, sia dell'invio dei messaggi che della ricerca.
public class ControlloreGestionePosta {

	private GestionePostaDBManager gPDBM;

	// Costruttore
	public ControlloreGestionePosta() {
	    this.gPDBM = new GestionePostaDBManager();
	}

    // Metodo che riceve in input il codice di un messaggio, lo cerca all'interno del file messaggi e, se lo trova, lo restituisce.
    @SuppressWarnings("unchecked")
    public synchronized Messaggio ricercaMessaggioPerCodice(int codice) {
        return gPDBM.getMessaggioCod(codice);
    }
	
	// Metodo che riceve in input uno username, ricerca nel file messaggi tutti i messaggi che hanno quello username come
	// destinatario e li restituisce in un Array List.
    @SuppressWarnings("unchecked")
	public synchronized ArrayList<Messaggio> ricercaMessaggi(String username) {

        return gPDBM.getMessaggi(username);
	}
	
	// Metodo che permette di inviare un messaggio. Effettua un controllo sull'input ritornando un codice specifico se
	// viene riscontrato qualche errore. Se non c'è nessun errore apre il file messaggi, aggiunge all'array il messaggio
	// che si vuole inviare e riserializza l'oggetto nel file.
	// Torna: 1 --> Se il destinatario è vuoto
	// 		  2 --> Se l'oggetto è vuoto
	//        3 --> Se il contenuto è vuoto
	//        4 --> Se il destinatario non esiste
    //        5 --> Se la sessione dell'getUtente è scaduta
	//        6 --> Se il mittente e il destinatario coincidono
	//        0 --> Se tutto va bene
	@SuppressWarnings("unchecked")
	public synchronized int scriviMessaggio(String oggetto, String mittente,
											String destinatario, String contenuto) {

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

        RegistrazioneDBManager rDBM = new RegistrazioneDBManager();
        if (!rDBM.usernameEsistente(destinatario))
            return 4;

        gPDBM.setMessaggio(oggetto, mittente, destinatario,
                contenuto, calcolaData());

        return 0;
	}

	// Metodo che riceve in input il codice di un messaggio e si occupa della sua eliminazione
    // Ritorna il vaolre 0 se il messaggio non è stato rimosso correttamente altrimenti ritorna il valore 1
    @SuppressWarnings("unchecked")
	public synchronized int eliminaMessaggio(int oggetto) {
	    return gPDBM.rimuoviMessaggio(oggetto) ? 1 : 0;
	}

    private String calcolaData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }
}