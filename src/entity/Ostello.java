package entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Ostello extends Locazione {
	
	// Variabili
	
	private static final long serialVersionUID = 1L;

	private ArrayList<GregorianCalendar> date = new ArrayList<GregorianCalendar>();
	
	// Costruttore
	
	public Ostello(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet) {
		super(nomeLocazione,postiTotali,provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);

	}
	
	// Getters e Setters

	public ArrayList<GregorianCalendar> getDate() {
		return date;
	}

	public void setDate(ArrayList<GregorianCalendar> date) {
		this.date = date;
	}
	
	
	
}
