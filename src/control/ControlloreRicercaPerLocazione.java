package control;

import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.IOException;


public interface ControlloreRicercaPerLocazione {

    Object ricerca(Locazione l, String provincia, String prezzo,int numeroGiorni)throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException;


    Object ricercaAvanzata(Locazione l, String provincia,String prezzo,int numeroGiorni, String parcheggio, String wifi, String pet,String caratteristica)throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException;
}
