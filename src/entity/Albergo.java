package entity;



public class Albergo extends Locazione{
	
	// Variabili
	
	private static final long serialVersionUID = 1L;
	private String tipoPensione;
	private String orarioColazione;
	private String orarioPranzo;
	private String orarioCena;

	// Costruttori
	public Albergo(){
	}
	
	public Albergo(String nomeLocazione,String postTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet,
			String tipoPensione, String orarioColazione, String orarioPranzo, String orarioCena) {
		super(nomeLocazione,postTotali,provincia,indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);

		this.tipoPensione = tipoPensione;
		this.orarioColazione = orarioColazione;
		this.orarioPranzo = orarioPranzo;
		this.orarioCena = orarioCena;
		
	}
	
	// Getters e Setters

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


}
