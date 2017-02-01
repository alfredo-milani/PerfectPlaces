package boundary;

import control.ControlloreRimuoviPrenotazione;
import control.ControlloreVisualizzaPrenotazioni;
import control.prenotazione.ControllorePrenotazione;
import entity.Locazione;
import entity.Prenotazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.IOException;
import java.util.ArrayList;


public class BoundaryGestionePrenotazioni {


    private ControllorePrenotazione cp;
    private ControlloreVisualizzaPrenotazioni cvp;
    private ControlloreRimuoviPrenotazione crp;

    public BoundaryGestionePrenotazioni(){

    }


    public boolean effettuaPrenotazione(Locazione locazione, String cliente, String dataInizio, String dataFine, String numeroPersone) throws DeserializzazioneException, SerializzazioneException, IOException {

        cp  =new ControllorePrenotazione();

        return cp.controlloPrenotazione(locazione,cliente,dataInizio,dataFine,numeroPersone);

    }


    public ArrayList<Prenotazione> visualizzaPerViaggiatore(String cliente) throws DeserializzazioneException {
        cvp = new ControlloreVisualizzaPrenotazioni();
        return cvp.visualizzaPrenotazioniPerViaggiatore(cliente);
    }

    public ArrayList<Prenotazione> visualizzaPerProprietario(String proprietario) throws DeserializzazioneException {
        cvp= new ControlloreVisualizzaPrenotazioni();
        return cvp.visualizzaPrenotazioniPerProprietario(proprietario);
    }

    public void avvioRimozione(Prenotazione prenotazione) throws DeserializzazioneException, SerializzazioneException, IOException {
        crp = new ControlloreRimuoviPrenotazione();
        crp.rimuovi(prenotazione);
    }
}
