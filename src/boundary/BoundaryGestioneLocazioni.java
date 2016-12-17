package boundary;

import control.ControlloreRimuoviLocazione;
import control.ControlloreVisualizzaLocazioni;
import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.util.ArrayList;

/**
 * Created by gabriele on 12/12/16.
 */
public class BoundaryGestioneLocazioni {

    public ArrayList<Locazione> ritonaLocazioni (String username)
            throws DeserializzazioneException {
        ControlloreVisualizzaLocazioni crl = new ControlloreVisualizzaLocazioni();

        return crl.visualizzaLocazioni(username);
    }

    public void  avvioRimozione(Locazione locazione)
            throws SerializzazioneException, DeserializzazioneException {
        ControlloreRimuoviLocazione cr =  new ControlloreRimuoviLocazione();
        cr.rimuoviLocazione(locazione);
    }
}
