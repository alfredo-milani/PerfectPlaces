package entity;

import java.io.Serializable;

public class Locazione implements Serializable {
	// Variabili
	private static final long serialVersionUID = 1L;
	protected String nomeLocazione;
	protected String postiTotali;
	protected String provincia;
	protected String indirizzo;
	private String userLocatore;
	protected String prezzo;
	protected String descrizione;
	protected boolean parcheggio;
	protected boolean wifi;
	private boolean prenotabile = true;
	protected boolean pet;
	private String tipo;
	
	// Costruttori
	public Locazione(){

	}
	public Locazione(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet) {
		this.nomeLocazione = nomeLocazione;
		this.postiTotali=postiTotali;
		this.provincia= provincia;
		this.indirizzo = indirizzo;
		this.userLocatore = userLocatore;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.parcheggio = parcheggio;
		this.wifi = wifi;
		this.pet = pet;
	}
	
	// Getters e Setters
	public String getNomeLocazione() {
		return nomeLocazione;
	}

	public void setNomeLocazione(String nomeLocazione) {
		this.nomeLocazione = nomeLocazione;
	}

	public String getPostiTotali(){return postiTotali;}

	public String getProvincia(){return provincia;}

	public void setProvincia(String provincia){
		this.provincia = provincia;}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getUserLocatore() {
		return userLocatore;
	}

	public void setUserLocatore(String userLocatore) {
		this.userLocatore = userLocatore;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean isParcheggio() {
		return parcheggio;
	}

	public void setParcheggio(boolean parcheggio) {
		this.parcheggio = parcheggio;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isPet() {
		return pet;
	}

	public void setPet(boolean pet) {
		this.pet = pet;
	}
		
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
