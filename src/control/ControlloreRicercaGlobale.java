package control;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by gabriele on 06/12/16.
 */
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

    public ArrayList<Locazione> ricercaGlobale(String provincia, String prezzo) throws DeserializzazioneException {
        ArrayList<Locazione> locazioni = new ArrayList<Locazione>();
        ArrayList<Locazione> locazioniTotali = new ArrayList<Locazione>();


        File file = new File(percorsoAlbergo);
        if (file.length() == 0) {

            ;

        } else {
            locazioni = controllo(percorsoAlbergo, provincia, prezzo);
            locazioniTotali.addAll(locazioni);
        }
        file = new File(percorsoAppartamento);
        if (file.length() == 0) {

            ;

        } else {
            locazioni = controllo(percorsoAppartamento, provincia, prezzo);
            locazioniTotali.addAll(locazioni);
        }

        file = new File(percorsoBeb);
        if (file.length() == 0) {

            ;

        } else
            locazioni = controllo(percorsoBeb,provincia,prezzo);
            locazioniTotali.addAll(locazioni);
        file = new File(percorsoCasaVacanza);
        if (file.length() == 0) {

            ;

        } else {
            locazioni = controllo(percorsoCasaVacanza, provincia, prezzo);
            locazioniTotali.addAll(locazioni);
        }

        file = new File(percorsoOstello);
        if (file.length() == 0) {

            ;

        } else {
            locazioni = controllo(percorsoOstello,provincia,prezzo);
            locazioniTotali.addAll(locazioni);
        }


        return locazioniTotali;

    }
    private ArrayList<Locazione> controllo (String percorso,String provincia,String prezzo) throws DeserializzazioneException {
        ArrayList<Locazione> locazioniTemp;
        ArrayList<Locazione> locazioni= new ArrayList<Locazione>();
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        locazioniTemp = (ArrayList<Locazione>) dobj.deserializza(percorso);

        for (Locazione loc : locazioniTemp) {
            if ((loc.getProvincia().equals(provincia)) &&                    //controllo sulla provincia
                    (Integer.parseInt(loc.getPrezzo().trim()) <= Integer.parseInt(prezzo.trim()))) {//controllo sul prezzo
                locazioni.add(loc);
            }
        }
        return locazioni;
    }

}
