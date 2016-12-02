package entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CasaVacanza extends Locazione {
	
	// Variabili
	
	private static final long serialVersionUID = 1L;
	private String numeroCamere;
	private String numeroBagni;
	private boolean giardino;
	private String numeroLetti;
	private ArrayList<GregorianCalendar> date = new ArrayList<GregorianCalendar>();
	
	// Costruttore

	public CasaVacanza(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String numeroCamere, String numeroBagni, 
			boolean giardino, String numeroLetti) {
		super(nomeLocazione,postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
		
		this.numeroCamere = numeroCamere;
		this.numeroBagni = numeroBagni;
		this.giardino = giardino;
		this.numeroLetti = numeroLetti;
	}
	
	// Getters e Setters
	
	public String getNumeroCamere() {
		return numeroCamere;
	}
	public void setNumeroCamere(String numeroCamere) {
		this.numeroCamere = numeroCamere;
	}
	public String getNumeroBagni() {
		return numeroBagni;
	}
	public void setNumeroBagni(String numeroBagni) {
		this.numeroBagni = numeroBagni;
	}
	public boolean isGiardino() {
		return giardino;
	}
	public void setGiardino(boolean giardino) {
		this.giardino = giardino;
	}
	public String getNumeroLetti() {
		return numeroLetti;
	}
	public void setNumeroLetti(String numeroLetti) {
		this.numeroLetti = numeroLetti;
	}

	public ArrayList<GregorianCalendar> getDate() {
		return date;
	}

	public void setDate(ArrayList<GregorianCalendar> date) {
		this.date = date;
	}
	
	
}
