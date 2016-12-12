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

    public ArrayList<Locazione> ritonaLocazioni (String username) throws DeserializzazioneException {
        ControlloreVisualizzaLocazioni crl = new ControlloreVisualizzaLocazioni();

        ArrayList<Locazione> locazioni = new ArrayList<Locazione>();
        locazioni = crl.visualizzaLocazioni(username);

        return locazioni;

    }

    public void  avvioRimozione(Locazione locazione) throws SerializzazioneException, DeserializzazioneException {
        ControlloreRimuoviLocazione cr =  new ControlloreRimuoviLocazione();
        cr.rimuoviLocazione(locazione);
    }
}
