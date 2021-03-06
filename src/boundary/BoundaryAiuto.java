package boundary;

import control.ControlloreAiuto;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.util.ArrayList;

/**
 * Created by maria
 */
public class BoundaryAiuto {
    ControlloreAiuto ca = new ControlloreAiuto();

    public BoundaryAiuto() throws DeserializzazioneException {
    }

    public ArrayList<String> ritornaDomande(String type) throws DeserializzazioneException {

        return ca.ritornaDomande(Integer.parseInt(type));
    }


    public String ritornaRisposta(String domanda) throws DeserializzazioneException {

        return ca.ritornaRisposta(domanda);
    }

    public void inserisciDomanda(String nuovaDomanda, String type) throws SerializzazioneException, DeserializzazioneException {
        ca.inserisciDomanda(nuovaDomanda, Integer.parseInt(type));
    }


}
