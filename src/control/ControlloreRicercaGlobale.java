package control;

import constants.Constants;
import entity.Locazione;
import exception.DeserializzazioneException;

import java.util.ArrayList;
import java.util.List;

public class ControlloreRicercaGlobale {
    //percorsi
    private String percorsoAlbergo = Constants.ALBERGHI_PATH;
    private String percorsoAppartamento = Constants.APPART_PATH;
    private String percorsoBeb = Constants.BEB;
    private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
    private String percorsoOstello = Constants.OSTELLI_PATH;

    //costruttore
    public ControlloreRicercaGlobale(){
    }

    /*
    questo metodo ricerca tutte le locazioni presenti nel sistema in base alla provincia e al
    prezzo(per una notte) calcolato per il numero di giorni richiesti, vengono utilizzati 5 thread
    in modo da fa partire nello stesso momento la ricerca sui 5 file sui quali sono memorizzati i diversi
    tipo di locazioni
     */
    public ArrayList<Locazione> ricercaGlobale(String provincia, String prezzo,int numeroGiorni) throws DeserializzazioneException, InterruptedException {

        ArrayList<Locazione> locazioni = new ArrayList<>();

        ArrayList<Locazione> locazioniTotali = new ArrayList<>();

        ThreadRicerca tAlb= new ThreadRicerca(percorsoAlbergo,provincia,prezzo,numeroGiorni,locazioni);
        ThreadRicerca tApp= new ThreadRicerca(percorsoAppartamento,provincia,prezzo,numeroGiorni,locazioni);
        ThreadRicerca tBeb= new ThreadRicerca(percorsoBeb,provincia,prezzo,numeroGiorni,locazioni);
        ThreadRicerca tCasa= new ThreadRicerca(percorsoCasaVacanza,provincia,prezzo,numeroGiorni,locazioni);
        ThreadRicerca tOst= new ThreadRicerca(percorsoOstello,provincia,prezzo,numeroGiorni,locazioni);

        Thread t1= new Thread(tAlb);
        Thread t2 = new Thread(tApp);
        Thread t3 = new Thread(tBeb);
        Thread t4 = new Thread(tCasa);
        Thread t5 = new Thread(tOst);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        locazioniTotali.addAll(locazioni);
        return locazioniTotali;

    }

}
