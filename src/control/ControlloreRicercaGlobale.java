package control;

import constants.Constants;
import entity.Locazione;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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

    public ArrayList<Locazione> ricercaGlobale(String provincia, String prezzo,int numeroGiorni) throws DeserializzazioneException, InterruptedException {

        List<Locazione> locazioni = Collections.synchronizedList(new ArrayList<Locazione>());


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

        synchronized (locazioni) {
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
        }

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();


/*
        locazioni = controllo(percorsoAlbergo, provincia, prezzo,numeroGiorni);
        locazioniTotali.addAll(locazioni);

        locazioni = controllo(percorsoAppartamento, provincia, prezzo,numeroGiorni);
        locazioniTotali.addAll(locazioni);

        locazioni = controllo(percorsoBeb,provincia,prezzo,numeroGiorni);
        locazioniTotali.addAll(locazioni);

        locazioni = controllo(percorsoCasaVacanza, provincia, prezzo,numeroGiorni);
        locazioniTotali.addAll(locazioni);

        locazioni = controllo(percorsoOstello,provincia,prezzo,numeroGiorni);
        locazioniTotali.addAll(locazioni);



        return locazioniTotali; */
        locazioniTotali.addAll(locazioni);
        return locazioniTotali;

    }
    /*
    @SuppressWarnings("unchecked")
    private ArrayList<Locazione> controllo (String percorso,String provincia,String prezzo,int numeroGiorni) throws DeserializzazioneException {
        ArrayList<Locazione> locazioniTemp;
        ArrayList<Locazione> locazioni= new ArrayList<>();

        File file = new File(percorso);
        if(file.length()==0)
            return locazioni;
        else {
            DeserializzaOggetti dobj = new DeserializzaOggetti();
            locazioniTemp = (ArrayList<Locazione>) dobj.deserializza(percorso);

            for (Locazione loc : locazioniTemp) {
                if ((loc.getProvincia().equals(provincia)) &&                    //controllo sulla provincia
                        ((Integer.parseInt(loc.getPrezzo().trim()))*numeroGiorni) <= ((Integer.parseInt(prezzo.trim()))*numeroGiorni)) {//controllo sul prezzo
                    locazioni.add(loc);
                }
            }
            return locazioni;
        }
    }
    */

    public static void main(String[] args) throws DeserializzazioneException, InterruptedException {
        ControlloreRicercaGlobale crg = new ControlloreRicercaGlobale();
        List<Locazione> locazioni;
        locazioni=crg.ricercaGlobale("Frosinone","8000",3);
        for(Locazione loc: locazioni){
            System.out.println(loc.getNomeLocazione());
            System.out.println(loc.getUserLocatore());
            System.out.println(loc.getIndirizzo());
        }
    }
}
