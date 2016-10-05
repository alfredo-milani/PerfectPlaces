package entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Beb extends Locazione {
	
	// Variabili

	private static final long serialVersionUID = 1L;
	private String camereTotali;
	private String camereOccupate;
	private String orarioColazione;
	private ArrayList<GregorianCalendar> date = new ArrayList<GregorianCalendar>();
	
	// Costruttore
	
	public Beb(String nomeLocazione, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String camereTotali, String orarioColazione) {
		super(nomeLocazione, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
		this.camereTotali = camereTotali;
		this.orarioColazione = orarioColazione;
	}
	
	// Getters e Setters

	public String getCamereTotali() {
		return camereTotali;
	}

	public void setCamereTotali(String camereTotali) {
		this.camereTotali = camereTotali;
	}

	public String getOrarioColazione() {
		return orarioColazione;
	}

	public void setOrarioColazione(String orarioColazione) {
		this.orarioColazione = orarioColazione;
	}
	
	// Aumenta di 1 le camere occupate, se viene raggiunto il limite di camere prenotabili, imposta la variabile booleana prenotabile a false.
	
	public void aumentaOccupate(){
		int camereOccupateInt = Integer.parseInt(camereOccupate);
		int camereTotaliInt = Integer.parseInt(camereTotali);
		
		camereOccupateInt = camereOccupateInt + 1;
		if(camereOccupateInt == camereTotaliInt){
			setPrenotabile(false);
		}
		
		camereOccupate = camereOccupateInt + "";
	}

	public ArrayList<GregorianCalendar> getDate() {
		return date;
	}

	public void setDate(ArrayList<GregorianCalendar> date) {
		this.date = date;
	}
	
}
