package entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Beb extends Locazione {
	
	// Variabili

	private static final long serialVersionUID = 1L;
	private String orarioColazione;
	private ArrayList<GregorianCalendar> date = new ArrayList<GregorianCalendar>();
	
	// Costruttore
	
	public Beb(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String orarioColazione) {
		super(nomeLocazione,postiTotali,provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
		this.orarioColazione = orarioColazione;
	}
	
	// Getters e Setters
	public String getOrarioColazione() {
		return orarioColazione;
	}

	public void setOrarioColazione(String orarioColazione) {
		this.orarioColazione = orarioColazione;
	}

	public ArrayList<GregorianCalendar> getDate() {
		return date;
	}

	public void setDate(ArrayList<GregorianCalendar> date) {
		this.date = date;
	}
	
}
