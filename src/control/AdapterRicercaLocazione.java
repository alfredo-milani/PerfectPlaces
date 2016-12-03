package control;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by gabriele on 02/12/16.
 */
public class AdapterRicercaLocazione implements ControlloreRicerca {

    //Istanza necessaria per il pattern SINGLETON
    private static AdapterRicercaLocazione instance =null;
    // Percorsi

    private String percorsoAlbergo = Constants.ALBERGHI_PATH;
    private String percorsoAppartamento = Constants.APPART_PATH;
    private String percorsoBeb = Constants.BEB;
    private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
    private String percorsoOstello = Constants.OSTELLI_PATH;

    private AdapterRicercaLocazione(){

    }

    //Metodo necessario per il SINGLETON
    public synchronized static final AdapterRicercaLocazione getSingletonInstance() {
        if (AdapterRicercaLocazione.instance == null)
            AdapterRicercaLocazione.instance = new AdapterRicercaLocazione();
        return instance;
    }


    @Override@SuppressWarnings("unchecked")
    public Object ricerca(Locazione locazione, String provincia, String prezzo)throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException {

        if (locazione.getClass()==Albergo.class) {
            ArrayList<Albergo> elencoAlberghi;
            ArrayList<Albergo> alberghiDisponibili = new ArrayList<Albergo>(); //Qui verranno inseriti gli alberghi da restituire all'utente

            DeserializzaOggetti dobj = new DeserializzaOggetti();
            elencoAlberghi = (ArrayList<Albergo>) dobj.deserializza(percorsoAlbergo); //Elenco di tutti gli alberghi presenti ne sistema

            System.err.println("numero alberghi: " + elencoAlberghi.size());

            for (Albergo albergo : elencoAlberghi) {
                if ((albergo.getProvincia().equals(provincia)) &&                    //controllo sulla provincia
                        (Integer.parseInt(albergo.getPrezzo()) <= Integer.parseInt(prezzo))) {//controllo sul prezzo
                    alberghiDisponibili.add(albergo);
                }
            }
            return alberghiDisponibili;
        }
        else if(locazione.getClass()== Appartamento.class){
            ArrayList<Appartamento> elencoAppartamenti;
            ArrayList<Appartamento> appartamentiDisponibili = new ArrayList<Appartamento>(); //Qui verranno inseriti gli appartamenti da restituire all'utente

            DeserializzaOggetti dobj = new DeserializzaOggetti();
            elencoAppartamenti = (ArrayList<Appartamento>) dobj.deserializza(percorsoAppartamento); //Elenco di tutti gli appartamenti presenti nel sistema

            for (Appartamento appartamento: elencoAppartamenti) {
                if (appartamento.getProvincia().equals(provincia) &&
                        Integer.parseInt(appartamento.getPrezzo()) <= Integer.parseInt(prezzo)) {

                    appartamentiDisponibili.add(appartamento);
                }
            }
            return appartamentiDisponibili;

        }else if(locazione.getClass()==Beb.class){
            ArrayList<Beb> elencoBeb;
            ArrayList<Beb> bebDisponibili = new ArrayList<Beb>(); //Qui verranno inseriti i beb da restituire all'utente

            DeserializzaOggetti dobj = new DeserializzaOggetti();
            elencoBeb = (ArrayList<Beb>) dobj.deserializza(percorsoBeb); //Elenco di tutti i beb presenti

            for (Beb beb : elencoBeb) {
                if (beb.getProvincia().equals(provincia) &&
                        Integer.parseInt(beb.getPrezzo()) <= Integer.parseInt(prezzo)) {
                    bebDisponibili.add(beb);
                }
            }

            return bebDisponibili;
        }else if(locazione.getClass()== CasaVacanza.class){

            ArrayList<CasaVacanza> elencoCasaVacanze;
            ArrayList<CasaVacanza> casaVacanzeDisponibili = new ArrayList<CasaVacanza>(); //Qui verranno inseriti gli appartamenti da restituire all'utente
            DeserializzaOggetti dobj = new DeserializzaOggetti();
            elencoCasaVacanze = (ArrayList<CasaVacanza>) dobj.deserializza(percorsoCasaVacanza); //Elenco di tutti gli appartamenti presenti

            for (CasaVacanza casa: elencoCasaVacanze) {
                if (casa.getProvincia().equals(provincia) &&
                        Integer.parseInt(casa.getPrezzo()) <= Integer.parseInt(prezzo)) {
                    casaVacanzeDisponibili.add(casa);
                }
            }
            return casaVacanzeDisponibili;
        }else{
            ArrayList<Ostello> elencoOstelli;
            ArrayList<Ostello> ostelliDisponibili = new ArrayList<Ostello>(); //Qui verranno inseriti gli ostelli da restituire all'utente


            DeserializzaOggetti dobj = new DeserializzaOggetti();
            elencoOstelli = (ArrayList<Ostello>) dobj.deserializza(percorsoOstello); //Elenco di tutti gli ostelli presenti

            for (Ostello ostello:elencoOstelli) {
                if (ostello.getProvincia().equals(provincia) &&
                        Integer.parseInt(ostello.getPrezzo()) <= Integer.parseInt(prezzo)) {
                    ostelliDisponibili.add(ostello);
                }
            }

            return ostelliDisponibili;
        }
    }
    @Override@SuppressWarnings("unchecked")
    public Object ricercaAvanzata(Locazione locazione, Object elencoLocazioni, String sParchegio, String sWifi, String sPet)throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException {

        Boolean parcheggio=false, wifi =false, pet=false;

        if (sParchegio.equals("true")) {
            parcheggio = true;
        }
        if (sWifi.equals("true")) {
            wifi = true;
        }
        if (sPet.equals("true")) {
            pet = true;
        }

        if (locazione.getClass()==Albergo.class) {
            ArrayList<Albergo> elencoAlberghi = (ArrayList<Albergo>) elencoLocazioni;
            ArrayList<Albergo> alberghiDisponibili = new ArrayList<Albergo>(); //Qui verranno inseriti gli alberghi da restituire all'utente

            System.err.println("numero alberghi: " + elencoAlberghi.size());

            for (Albergo albergo : elencoAlberghi) {
                if ((albergo.isParcheggio()==parcheggio)&&
                        (albergo.isWifi()==wifi)&&
                        (albergo.isPet()==pet)) {
                    alberghiDisponibili.add(albergo);
                }
            }
            return alberghiDisponibili;
        }
        else if(locazione.getClass()== Appartamento.class){
            ArrayList<Appartamento> elencoAppartamenti=(ArrayList<Appartamento>) elencoLocazioni;
            ArrayList<Appartamento> appartamentiDisponibili = new ArrayList<Appartamento>(); //Qui verranno inseriti gli appartamenti da restituire all'utente
            System.err.println("numero appartamenti: " + elencoAppartamenti.size());
            for (Appartamento appartamento: elencoAppartamenti) {
                if ((appartamento.isParcheggio()==parcheggio)&&
                        (appartamento.isWifi()==wifi)&&
                        (appartamento.isPet()==pet)) {

                    appartamentiDisponibili.add(appartamento);
                }
            }
            return appartamentiDisponibili;

        }else if(locazione.getClass()==Beb.class){
            ArrayList<Beb> elencoBeb = (ArrayList<Beb>) elencoLocazioni;
            ArrayList<Beb> bebDisponibili = new ArrayList<Beb>(); //Qui verranno inseriti i beb da restituire all'utente
            System.err.println("numero beb: " + elencoBeb.size());

            for (Beb beb : elencoBeb) {
                if ((beb.isParcheggio()==parcheggio)&&
                        (beb.isWifi()==wifi)&&
                        (beb.isPet()==pet)) {
                    bebDisponibili.add(beb);
                }
            }

            return bebDisponibili;
        }else if(locazione.getClass()== CasaVacanza.class){

            ArrayList<CasaVacanza> elencoCasaVacanze=(ArrayList<CasaVacanza>) elencoLocazioni;
            ArrayList<CasaVacanza> casaVacanzeDisponibili = new ArrayList<CasaVacanza>(); //Qui verranno inseriti gli appartamenti da restituire all'utente

            System.err.println("numero CasaVacanza: " + elencoCasaVacanze.size());

            for (CasaVacanza casa: elencoCasaVacanze) {
                if ((casa.isParcheggio()==parcheggio)&&
                        (casa.isWifi()==wifi)&&
                        (casa.isPet()==pet)) {
                    casaVacanzeDisponibili.add(casa);
                }
            }
            return casaVacanzeDisponibili;
        }else{
            ArrayList<Ostello> elencoOstelli=(ArrayList<Ostello>)elencoLocazioni;
            ArrayList<Ostello> ostelliDisponibili = new ArrayList<Ostello>(); //Qui verranno inseriti gli ostelli da restituire all'utente
            System.err.println("numero Ostelli: " + elencoOstelli.size());

            for (Ostello ostello:elencoOstelli) {
                if ((ostello.isParcheggio()==parcheggio)&&
                        (ostello.isWifi()==wifi)&&
                        (ostello.isPet()==pet)) {
                    ostelliDisponibili.add(ostello);
                }
            }

            return ostelliDisponibili;
        }
    }

}
