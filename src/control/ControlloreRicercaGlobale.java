package control;

import constants.Constants;
import entity.Locazione;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;

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

    public ArrayList<Locazione> ricercaGlobale(String provincia, String prezzo,int numeroGiorni) throws DeserializzazioneException {
        ArrayList<Locazione> locazioni;
        ArrayList<Locazione> locazioniTotali = new ArrayList<>();


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

        return locazioniTotali;

    }
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

}
