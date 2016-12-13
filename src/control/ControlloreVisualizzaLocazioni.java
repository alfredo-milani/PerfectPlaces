package control;

import constants.Constants;
import entity.Locazione;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by gabriele on 27/11/16.
 */
public class ControlloreVisualizzaLocazioni {
    private String percorsoAlbergo = Constants.ALBERGHI_PATH;
    private String percorsoAppartamento = Constants.APPART_PATH;
    private String percorsoBeb = Constants.BEB;
    private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
    private String percorsoOstello = Constants.OSTELLI_PATH;


    public ControlloreVisualizzaLocazioni(){

    }

    // Metodo permette ad un proprietario di visualizzare le proprie locazioni la ricerca avviene in base allo Username.
    @SuppressWarnings("unchecked")
    public ArrayList<Locazione> visualizzaLocazioni(String username) throws DeserializzazioneException {

        ArrayList<Locazione> locazioni = new ArrayList<Locazione>();
        ArrayList<Locazione> locazioniProvvisorio;
        DeserializzaOggetti dobj = new DeserializzaOggetti();

        File file = new File(percorsoAlbergo);
        if (file.length() == 0) {

            ;

        } else {

            locazioniProvvisorio = (ArrayList<Locazione>) DeserializzaOggetti.deserializza(percorsoAlbergo);
            for (Locazione aLocazioniProvvisorio : locazioniProvvisorio) {
                if (aLocazioniProvvisorio.getUserLocatore().equals(username)) {
                    aLocazioniProvvisorio.setTipo("albergo");
                    locazioni.add(aLocazioniProvvisorio);
                }
            }
        }

        file = new File(percorsoAppartamento);
        if (file.length() == 0) {

            ;

        } else {
            locazioniProvvisorio = (ArrayList<Locazione>) DeserializzaOggetti.deserializza(percorsoAppartamento);
            for (int i = 0; i < locazioniProvvisorio.size(); i++) {
                if (locazioniProvvisorio.get(i).getUserLocatore().equals(username)) {
                    locazioniProvvisorio.get(i).setTipo("appartamento");
                    locazioni.add(locazioniProvvisorio.get(i));
                }
            }
        }

        file = new File(percorsoBeb);
        if (file.length() == 0) {

            ;

        } else {
            locazioniProvvisorio = (ArrayList<Locazione>) DeserializzaOggetti.deserializza(percorsoBeb);
            for (int i = 0; i < locazioniProvvisorio.size(); i++) {
                if (locazioniProvvisorio.get(i).getUserLocatore().equals(username)) {
                    locazioniProvvisorio.get(i).setTipo("beb");
                    locazioni.add(locazioniProvvisorio.get(i));
                }
            }
        }

        file = new File(percorsoCasaVacanza);
        if (file.length() == 0) {

            ;

        } else {
            locazioniProvvisorio = (ArrayList<Locazione>) DeserializzaOggetti.deserializza(percorsoCasaVacanza);
            for (int i = 0; i < locazioniProvvisorio.size(); i++) {
                if (locazioniProvvisorio.get(i).getUserLocatore().equals(username)) {
                    locazioniProvvisorio.get(i).setTipo("casavacanza");
                    locazioni.add(locazioniProvvisorio.get(i));
                }
            }
        }

        file = new File(percorsoOstello);
        if (file.length() == 0) {

            ;

        } else {
            locazioniProvvisorio = (ArrayList<Locazione>) DeserializzaOggetti.deserializza(percorsoOstello);
            for (int i = 0; i < locazioniProvvisorio.size(); i++) {
                if (locazioniProvvisorio.get(i).getUserLocatore().equals(username)) {
                    locazioniProvvisorio.get(i).setTipo("ostello");
                    locazioni.add(locazioniProvvisorio.get(i));
                }
            }
        }

        return locazioni;
    }


}


