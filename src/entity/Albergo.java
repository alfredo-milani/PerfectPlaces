package entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Albergo extends Locazione{
	
	// Variabili
	
	private static final long serialVersionUID = 1L;
	private String camereTotali;
	private String camereOccupate;
	private String tipoPensione;
	private String orarioColazione;
	private String orarioPranzo;
	private String orarioCena;
	private ArrayList<GregorianCalendar> date = new ArrayList<GregorianCalendar>();

	// Costruttore
	
	public Albergo(String nomeLocazione, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String camereTotali,
			String tipoPensione, String orarioColazione, String orarioPranzo, String orarioCena) {
		super(nomeLocazione, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
		
		this.camereTotali = camereTotali;
		this.tipoPensione = tipoPensione;
		this.orarioColazione = orarioColazione;
		this.orarioPranzo = orarioPranzo;
		this.orarioCena = orarioCena;
		
	}
	
	// Getters e Setters

	public String getCamereTotali() {
		return camereTotali;
	}

	public void setCamereTotali(String camereTotali) {
		this.camereTotali = camereTotali;
	}

	public String getCamereOccupate() {
		return camereOccupate;
	}

	public void setCamereOccupate(String camereOccupate) {
		this.camereOccupate = camereOccupate;
	}

	public String getTipoPensione() {
		return tipoPensione;
	}

	public void setTipoPensione(String tipoPensione) {
		this.tipoPensione = tipoPensione;
	}

	public String getOrarioColazione() {
		return orarioColazione;
	}

	public void setOrarioColazione(String orarioColazione) {
		this.orarioColazione = orarioColazione;
	}

	public String getOrarioPranzo() {
		return orarioPranzo;
	}

	public void setOrarioPranzo(String orarioPranzo) {
		this.orarioPranzo = orarioPranzo;
	}

	public String getOrarioCena() {
		return orarioCena;
	}

	public void setOrarioCena(String orarioCena) {
		this.orarioCena = orarioCena;
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
