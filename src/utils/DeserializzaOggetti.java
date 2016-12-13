package utils;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import exception.DeserializzazioneException;

public class DeserializzaOggetti {
	
	// Costruttore
	public DeserializzaOggetti(){
		
	}
	
	// Metodo che prende come input il percorso del file, effettua la deserializzazione e ritorna l'oggetto che verr� castato dal chiamante.
	public static Object deserializza(String percorso)
			throws DeserializzazioneException {
		try{
			FileInputStream f = new FileInputStream(percorso);
			ObjectInputStream s = new ObjectInputStream(f);
			Object obj = s.readObject();
			s.close();
			return obj;
		} catch(Exception e){
			throw new DeserializzazioneException("Oggetto non deserializzabile");
		}
	}
}
