package entity;

import java.util.ArrayList;
import java.util.*;

public class Appartamento extends Locazione {
	
	// Variabili

	private static final long serialVersionUID = 1L;
	private String numeroStanze;
	private String numeroBagni;
	private boolean giardino;
	private String numeroLetti;
	private ArrayList<GregorianCalendar> date = new ArrayList<GregorianCalendar>();
	
	// Costruttore	
	
	public Appartamento(String nomeLocazione, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String numeroStanze, String numeroBagni, boolean giardino, String numeroLetti) {
		super(nomeLocazione, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
		
		this.numeroStanze = numeroStanze;
		this.numeroBagni = numeroBagni;
		this.giardino = giardino;
		this.numeroLetti = numeroLetti;
	}
	
	// Getters e Setters	
	
	public String getNumeroStanze() {
		return numeroStanze;
	}

	public void setNumeroStanze(String numeroStanze) {
		this.numeroStanze = numeroStanze;
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

