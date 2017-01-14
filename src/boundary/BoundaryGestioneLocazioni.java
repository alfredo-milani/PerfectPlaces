package boundary;

import control.ControlloreRimuoviLocazione;
import control.ControlloreVisualizzaLocazioni;
import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.util.ArrayList;

public class BoundaryGestioneLocazioni {

    public ArrayList<Locazione> ritonaLocazioni (String username) throws DeserializzazioneException {
        ControlloreVisualizzaLocazioni crl = new ControlloreVisualizzaLocazioni();

        ArrayList<Locazione> locazioni;
        locazioni = crl.visualizzaLocazioni(username);

        return locazioni;

    }

    public String  avvioRimozione(Locazione locazione) throws SerializzazioneException, DeserializzazioneException {
        ControlloreRimuoviLocazione cr =  new ControlloreRimuoviLocazione();

        return cr.rimuoviLocazione(locazione);
    }
}
