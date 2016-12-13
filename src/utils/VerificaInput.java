package utils;
import constants.Constants;
import exception.DeserializzazioneException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

// Questa classe è utilizzata per effettuare controlli sugli input dei form nelle pagine JSP.

public class VerificaInput {

	private static  String percorsoProvince = Constants.PROVINCE_PATH;
	
	// Costruttore

	public VerificaInput(){
		
	}
	// Metodo che controlla se il prezzo indicato in un form JSP sia effettivamente un numero intero, che non sia negativo e che non sia 
	// superiore a 1'000'000.
	
	public boolean verificaPrezzo(String prezzo){
        if(prezzo.trim().equals(""))
            return false;

		int prezzoInt;
		
		try{
			prezzoInt = Integer.parseInt(prezzo);
		} catch(Exception e){
			return false;
		}
		if(prezzoInt<=0||prezzoInt>1000000){
			return false;
		}

		return true;
	}

//metodo che controlla se l'getUtente ha inserito una provincia esistente e nel formato voluto
	public boolean verificaProvincia(String provincia) throws IOException {
		String pr = provincia.trim();
        if(provincia.equals(""))
            return false;
        else {
            FileReader f;
            f = new FileReader(percorsoProvince);
            BufferedReader b;
            b = new BufferedReader(f);
            String s;
            while (true) {
                s = b.readLine();
                if (s == null)
                    break;
                if (s.trim().equals(pr))
                    return true;
            }
            return false;
        }
    }
    //metodo che controlla se data inizio e data fine siano state inserite correttamente
    public boolean verificaDate(String dataInizio, String dataFine){

        if(dataInizio.trim().equals("")||dataInizio.equals(""))
            return false;

        TrasformaDate td = new TrasformaDate();

        GregorianCalendar gcInizio = td.trasformaInGregorianCalendar(dataInizio);
        GregorianCalendar gcFine = td.trasformaInGregorianCalendar(dataFine);
        if(gcInizio.get(Calendar.YEAR)<2017|| gcFine.get(Calendar.YEAR)<2017){  //controlla che le date non siano di anni passati
            return false;
        }
        if(gcInizio.get(Calendar.DAY_OF_YEAR)>gcFine.get(Calendar.DAY_OF_YEAR))//controlla che effettivamente la data inizo sia precedente a data fine
            return false;
        return true;
    }

}