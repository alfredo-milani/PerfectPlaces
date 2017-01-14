package boundary;

import control.ControlloreFaq;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.util.ArrayList;

/**
 * Created by maria on 21/12/16.
 */
public class BoundaryFaq {
    ControlloreFaq cf = new ControlloreFaq();

    public BoundaryFaq() throws DeserializzazioneException {
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
