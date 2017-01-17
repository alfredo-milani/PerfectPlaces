package boundary;


import control.prenotazione.ControllorePrenotazione;
import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.IOException;


public class BoundaryPrenotaLocazioni {

	  ControllorePrenotazione cp;

    public boolean prenotaLocazione(Locazione locazione,String cliente, String dataInizio, String dataFine,String numeroPersone) throws DeserializzazioneException, SerializzazioneException, IOException {

        cp  =new ControllorePrenotazione();

        return cp.controlloPrenotazione(locazione,cliente,dataInizio,dataFine,numeroPersone);

    }


}
