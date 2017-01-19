package control;

import entity.Locazione;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriele on 19/01/17.
 */
public class ThreadRicerca implements Runnable{

    private String percorso, provincia, prezzo;
    private int numeroGiorni;
    private List<Locazione> locazioni;

    public ThreadRicerca(String percorso,String provincia,String prezzo,int numeroGiorni,List<Locazione> locazioni){
        this.percorso=percorso;
        this.provincia=provincia;
        this.prezzo=prezzo;
        this.numeroGiorni=numeroGiorni;
        this.locazioni=locazioni;
    }
    @Override
    public void run() {
        ArrayList<Locazione> locazioniTemp = new ArrayList<>();

        File file = new File(percorso);
        if(file.length()!=0){
            DeserializzaOggetti dobj = new DeserializzaOggetti();
            try {
                locazioniTemp = (ArrayList<Locazione>) dobj.deserializza(percorso);
            }catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            for (Locazione loc : locazioniTemp) {
                if ((loc.getProvincia().equals(provincia)) &&  //controllo sulla provincia
                        ((Integer.parseInt(loc.getPrezzo().trim()))*numeroGiorni) <= ((Integer.parseInt(prezzo.trim()))*numeroGiorni)) {//controllo sul prezzo
                    locazioni.add(loc);
                }
            }
        }
    }
}
