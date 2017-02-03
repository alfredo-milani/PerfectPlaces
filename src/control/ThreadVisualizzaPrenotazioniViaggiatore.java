package control;

import entity.Prenotazione;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gabriele on 22/01/17.
 */
public class ThreadVisualizzaPrenotazioniViaggiatore implements Runnable {

    private String viaggiatore;
    private String percorso;
    private static ArrayList<Prenotazione> prenotazioniViaggiatore;
    private static ReentrantLock l = new ReentrantLock();

    public ThreadVisualizzaPrenotazioniViaggiatore( String viaggiatore, String percorso,ArrayList<Prenotazione> prenotazioniViaggiatore){
        this.viaggiatore=viaggiatore;
        this.percorso=  percorso;
        ThreadVisualizzaPrenotazioniViaggiatore.prenotazioniViaggiatore= prenotazioniViaggiatore;

    }

    @Override
    public void run() {
        ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<>();

        File file = new File(percorso);
        if (file.length() != 0) {
            try {
                listaPrenotazioni = (ArrayList<Prenotazione>) DeserializzaOggetti.deserializza(percorso);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }

            for (Prenotazione p : listaPrenotazioni) {
                if (p.getCliente().equals(viaggiatore)) {
                    l.lock();
                    try {
                        prenotazioniViaggiatore.add(p);
                    }finally {
                        l.unlock();
                    }
                }
            }
        }
    }

}
