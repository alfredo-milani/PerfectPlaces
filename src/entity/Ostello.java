package entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Ostello extends Locazione {
	
	// Variabili
	
	private static final long serialVersionUID = 1L;
	private String numeroLettiTotali;
	private String numeroLettiOccupati;
	private ArrayList<GregorianCalendar> date = new ArrayList<GregorianCalendar>();
	
	// Costruttore
	
	public Ostello(String nomeLocazione,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String numeroLettiTotali) {
		super(nomeLocazione,provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
		
		this.numeroLettiTotali = numeroLettiTotali;
	}
	
	// Getters e Setters
	
	public String getNumeroLettiTotali() {
		return numeroLettiTotali;
	}
	public void setNumeroLettiTotali(String numeroLettiTotali) {
		this.numeroLettiTotali = numeroLettiTotali;
	}
	public String getNumeroLettiOccupati() {
		return numeroLettiOccupati;
	}
	public void setNumeroLettiOccupati(String numeroLettiOccupati) {
		this.numeroLettiOccupati = numeroLettiOccupati;
	}	
	
	// Aumenta di 1 i letti occupati, se viene raggiunto il limite di letti prenotabili, imposta la variabile booleana prenotabile a false.
	
	public void aumentaOccupati(){
		int lettiOccupatiInt = Integer.parseInt(numeroLettiOccupati);
		int lettiTotaliInt = Integer.parseInt(numeroLettiTotali);
		
		lettiOccupatiInt = lettiOccupatiInt+1;
		
		if(lettiOccupatiInt == lettiTotaliInt){
			setPrenotabile(false);
		}
		numeroLettiOccupati = lettiOccupatiInt + "";
		
	}

	public ArrayList<GregorianCalendar> getDate() {
		return date;
	}

	public void setDate(ArrayList<GregorianCalendar> date) {
		this.date = date;
	}
	
	
	
}
