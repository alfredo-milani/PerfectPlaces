package entity;

public class Locazione implements java.io.Serializable {
	
	// Variabili

	private static final long serialVersionUID = 1L;
	protected String nomeLocazione;
	protected String indirizzo;
	protected String userLocatore;
	protected String prezzo;
	protected String descrizione;
	protected boolean parcheggio;
	protected boolean wifi;
	protected boolean prenotabile = true;
	protected boolean pet;
	protected String tipo;
	
	// Costruttore

	public Locazione(String nomeLocazione, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet){
		

		this.nomeLocazione = nomeLocazione;
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


	public boolean isPrenotabile() {
		return prenotabile;
	}


	public void setPrenotabile(boolean prenotabile) {
		this.prenotabile = prenotabile;
	}

	
}
