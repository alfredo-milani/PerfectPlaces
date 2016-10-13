package control;

import java.io.File;
import java.util.ArrayList;

import constants.Constants;
import entity.Messaggio;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

// Classe che si occupa della gestione della posta, sia dell'invio dei messaggi che della ricerca.

public class ControlloreGestionePosta {
	
	// Percorsi dei file
	
	private String percorsoMessaggi = Constants.MSG_PATH;
	private String percorsoUtenti = Constants.UTENTI_PATH;

	// Costruttore
	
	public ControlloreGestionePosta(){
		
	}
	
	// Metodo che riceve in input uno username, ricerca nel file messaggi tutti i messaggi che hanno quello username come
	// destinatario e li restituisce in un Array List. In questo modo quando un utente controlla la posta verr� effettuata una ricerca
	// con il suo username e gli verranno presentati tutti i messaggi che lo vedono come destinatario.
	
	@SuppressWarnings("unchecked")
	public ArrayList<Messaggio> ricercaMessaggiPerDestinatario(String username) throws DeserializzazioneException{
		
		ArrayList<Messaggio> elencoMessaggi = new ArrayList<Messaggio>();
		ArrayList<Messaggio> elencoMessaggiUser = new ArrayList<Messaggio>();
		
		File file = new File(percorsoMessaggi);
		if(file.length()==0){
			
			return elencoMessaggiUser;
			
		} else {
		
			DeserializzaOggetti dobj = new DeserializzaOggetti();
			elencoMessaggi = (ArrayList<Messaggio>)dobj.deserializza(percorsoMessaggi);
		
			for(int i = 0;i<elencoMessaggi.size();i++){
				if(elencoMessaggi.get(i).getDestinatario().equals(username)){
					elencoMessaggiUser.add(elencoMessaggi.get(i));
				}
			}
			return elencoMessaggiUser;		
		}
	}
	
	
	// Metodo che riceve in input il codice di un messaggio, lo cerca all'interno del file messaggi e, se lo trova, lo restituisce. 
	
	@SuppressWarnings("unchecked")
	public Messaggio ricercaMessaggioPerCodice(int codice) throws DeserializzazioneException{
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		ArrayList<Messaggio> elencoMessaggi = new ArrayList<Messaggio>();
		elencoMessaggi = (ArrayList<Messaggio>)dobj.deserializza(percorsoMessaggi);
		
		Messaggio messaggio;
		
		for(int i = 0;i<elencoMessaggi.size();i++){
			if(elencoMessaggi.get(i).getCodice()==codice){
				return elencoMessaggi.get(i);
			}
		}
		
		messaggio = new Messaggio("errore","errore", "errore","errore");
		return messaggio;
	}
	
	
	// Metodo che permette di inviare un messaggio. Effettua un controllo sull'input ritornando un codice specifico se
	// viene riscontrato qualche errore. Se non c'� nessun errore apre il file messaggi, aggiunge all'array il messaggio 
	// che si vuole inviare e riserializza l'oggetto nel file.
	
	// Torna: 1 --> Se il destinatario � vuoto
	// 		  2 --> Se l'oggetto � vuoto
	//        3 --> Se il contenuto � vuoto
	//        4 --> Se il destinatario non esiste 
	//        0 --> Se tutto va bene
	
	@SuppressWarnings("unchecked")
	public int scriviMessaggio(String oggetto, String mittente, String destinatario, String contenuto) throws DeserializzazioneException, SerializzazioneException{
		
		if(destinatario.equals("")){
			return 1;
		}
		if(oggetto.equals("")){
			return 2;
		}
		if(contenuto.equals("")){
			return 3;
		}
		
		ArrayList<Utente> elencoUtenti = new ArrayList<Utente>();
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoUtenti = (ArrayList<Utente>)dobj.deserializza(percorsoUtenti);	
		boolean controllo = false;
		for(int i = 0; i<elencoUtenti.size();i++){
			if(elencoUtenti.get(i).getUsername().equals(destinatario)){
				controllo = true;
			}
		}
		if(!controllo){
			return 4;
		}
		ArrayList<Messaggio> elencoMessaggi = new ArrayList<Messaggio>();
		
		File file = new File(percorsoMessaggi);
		if(file.length()!=0){
			elencoMessaggi = (ArrayList<Messaggio>)dobj.deserializza(percorsoMessaggi);
		}
		
		
		Messaggio messaggio = new Messaggio(oggetto, mittente, destinatario,contenuto);
		elencoMessaggi.add(messaggio);
		SerializzaOggetti sobj = new SerializzaOggetti();
		sobj.serializza(elencoMessaggi, percorsoMessaggi);
		return 0;
	}
	
	
	
}
