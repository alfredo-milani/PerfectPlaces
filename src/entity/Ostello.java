package entity;


public class Ostello extends Locazione {
	
	// Variabili
	
	private static final long serialVersionUID = 1L;
	private String tipoPensione;

	
	// Costruttori
	public Ostello(){

	}
	
	public Ostello(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
			String descrizione, boolean parcheggio, boolean wifi, boolean pet, String tipoPensione) {
		super(nomeLocazione,postiTotali,provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
		this.tipoPensione=tipoPensione;
	}
	
	// Getters e Setters

	public String getTipoPensione() {
		return tipoPensione;
	}

	public void setTipoPensione(String tipoPensione) {
		this.tipoPensione = tipoPensione;
	}

	
	
}
