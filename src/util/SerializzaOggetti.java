package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import exception.SerializzazioneException;

public class SerializzaOggetti {
	
	// Costruttore
	
	public SerializzaOggetti(){
		
	}
	
	// Metodo che prende come input il percorso del file e un oggetto ed effettua la serializzazione.

	public void serializza(Object obj, String percorso) throws SerializzazioneException {
		try{
			FileOutputStream f = new FileOutputStream(percorso);
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(obj);
			s.flush();
			s.close();
		} catch(IOException e) {
			throw new SerializzazioneException("Oggetto non serializzabile");
		}
	}
	
}
