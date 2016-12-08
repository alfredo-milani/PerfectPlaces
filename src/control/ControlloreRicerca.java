package control;

import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by gabriele on 02/12/16.
 */
public interface ControlloreRicerca {

    Object ricerca(Locazione l, String provincia, String prezzo)throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException;


    Object ricercaAvanzata(Locazione l, String provincia,String prezzo, String parcheggio, String wifi, String pet,String caratteristica)throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException;
}
