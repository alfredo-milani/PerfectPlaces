package entity;

public class Utente implements java.io.Serializable {
	// Variabili
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private String immagine;
	private String nascita;
	private String sesso;
	
	// Costruttore
	public Utente(String username, String password, String nome,
				  String cognome, String email, String immagine) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.immagine = immagine;
		this.nascita = null;
		this.sesso = null;
	}
	
	// Getters e Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getNascita() {
        return nascita;
    }

    public void setNascita(String nascita) {
        this.nascita = nascita;
    }
}
