package boundary;

import control.ControlloreRimuoviPrenotazione;
import control.ControlloreVisualizzaPrenotazioni;
import entity.Prenotazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.IOException;
import java.util.ArrayList;


public class BoundaryGestionePrenotazioni {

    private ControlloreVisualizzaPrenotazioni cvp;
    private ControlloreRimuoviPrenotazione crp;

    public BoundaryGestionePrenotazioni(){

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
