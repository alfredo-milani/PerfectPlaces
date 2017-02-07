package boundary;

import control.ControlloreAiuto;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.util.ArrayList;

/**
 * Created by maria
 */
public class BoundaryAiuto {
    ControlloreAiuto cf = new ControlloreAiuto();

    public BoundaryAiuto() throws DeserializzazioneException {
    }

    public ArrayList<String> ritornaDomande(String type) throws DeserializzazioneException {

        return cf.ritornaDomande(Integer.parseInt(type));
    }


    public String ritornaRisposta(String domanda) throws DeserializzazioneException {

        return cf.ritornaRisposta(domanda);
    }

    public void inserisciDomanda(String nuovaDomanda, String type) throws SerializzazioneException, DeserializzazioneException {
        cf.inserisciDomanda(nuovaDomanda, Integer.parseInt(type));
    }


}
