package entity;

import java.io.File;
import java.util.ArrayList;

import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

public class Messaggio implements java.io.Serializable {
	
	// Variabili
	
	private static final long serialVersionUID = 1L;
	private String oggetto;
	private String mittente;
	private String destinatario;
	private String contenuto;
	private int codice;
	private String percorsoMessaggi = "C:/Users/Marco_000/workspace/MarcoeStefano/messaggi";
	
	// Costruttore
	
	public Messaggio(String oggetto, String mittente, String destinatario, String contenuto) throws DeserializzazioneException{
		this.oggetto = oggetto;
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.contenuto = contenuto;
		this.codice = assegnaCodice();
	}

	
	// Getters e Setters
	
	
	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getMittente() {
		return mittente;
	}

	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}
	
	// Metodo per assegnare un codice univoco ad un messaggio.
	
	@SuppressWarnings("unchecked")
	public int assegnaCodice() throws DeserializzazioneException{
		
		
		//Se non c'è nessun messaggio ritorna il codice 0
		
		File file = new File(percorsoMessaggi);
		if(file.length()==0){
			return 0;
		}
		
		//Se il file non è vuoto calcola il primo codice disponibile
		
		ArrayList<Messaggio> elencoMessaggi = new ArrayList<Messaggio>();
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoMessaggi = (ArrayList<Messaggio>)dobj.deserializza(percorsoMessaggi);
		
		boolean controllo = false;
		int codice = 0;
		
		while(!controllo){
			controllo = true;
			for(int i=0;i<elencoMessaggi.size();i++){
				if(elencoMessaggi.get(i).getCodice()==codice){
					codice = codice + 1;
					controllo = false;
					break;
				}
			}
		}
		
		return codice;
	}
	

	
}
