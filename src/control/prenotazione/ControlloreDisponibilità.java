package control.prenotazione;

import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.IOException;
import java.util.GregorianCalendar;


public abstract class ControlloreDisponibilità {


    public abstract boolean controllo(Locazione loc, GregorianCalendar gcInizio, GregorianCalendar gcFine,String numeroPersone) throws DeserializzazioneException, SerializzazioneException, IOException;

}