package entity;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringJoiner;

import constants.Constants;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

public class Messaggio implements java.io.Serializable {
	// Variabili
	private static final long serialVersionUID = 1L;
	private String oggetto;
	private String mittente;
	private String destinatario;
	private String contenuto;
	private String data;
	private int codice;

	// Costruttore
	public Messaggio(String oggetto, String mittente,
					 String destinatario, String contenuto)
			throws DeserializzazioneException {
		this.oggetto = oggetto;
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.contenuto = contenuto;
		this.codice = assegnaCodice();
        this.data = calcolaData();
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

	public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String calcolaData() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}
	
	// Metodo per assegnare un codice univoco ad un messaggio.
	@SuppressWarnings("unchecked")
	private int assegnaCodice() throws DeserializzazioneException{
		//Se non c'è nessun messaggio ritorna il codice 0
		File file = new File(Constants.MSG_PATH);
		if(file.length() == 0)
			return 0;
		
		// Se il file non è vuoto calcola il primo codice disponibile
		ArrayList<Messaggio> elencoMessaggi;
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		elencoMessaggi = (ArrayList<Messaggio>) dobj.deserializza(Constants.MSG_PATH);
		
		int codice = elencoMessaggi.get(elencoMessaggi.size() - 1).getCodice();
		
		return ++codice;
	}
}
