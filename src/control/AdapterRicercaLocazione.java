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


    //aggregazione
    ControlloreRicercaGlobale crg;


    public AdapterRicercaLocazione(ControlloreRicercaGlobale c){
        this.crg = c;
    }



    @Override@SuppressWarnings("unchecked")
    public Object ricerca(Locazione locazione, String provincia, String prezzo)throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, DeserializzazioneException, IOException, SerializzazioneException {

        ArrayList<Locazione> elencoLocazioni;
        elencoLocazioni =  crg.ricercaGlobale(provincia,prezzo);

        if (locazione.getClass()==Albergo.class) {
            ArrayList<Albergo> alberghiDisponibili = new ArrayList<Albergo>(); //Qui verranno inseriti gli alberghi da restituire all'utente

            System.err.println("numero alberghi: " + elencoLocazioni.size());

            for (Locazione loc : elencoLocazioni) {
                if (loc.getClass()==Albergo.class) {
                    Albergo albergo = (Albergo) loc;
                    alberghiDisponibili.add(albergo);
                }
            }
            return alberghiDisponibili;
        }
        else if(locazione.getClass()== Appartamento.class){
            ArrayList<Appartamento> appartamentiDisponibili = new ArrayList<Appartamento>(); //Qui verranno inseriti gli appartamenti da restituire all'utente
            for (Locazione loc : elencoLocazioni) {
                if (loc.getClass()==Appartamento.class) {
                    Appartamento appartamento = (Appartamento) loc;
                    appartamentiDisponibili.add(appartamento);
                }
            }
            return appartamentiDisponibili;

        }else if(locazione.getClass()==Beb.class){
            ArrayList<Beb> bebDisponibili = new ArrayList<Beb>(); //Qui verranno inseriti i beb da restituire all'utente

            for (Locazione loc : elencoLocazioni) {
                if (loc.getClass()==Beb.class) {
                    Beb beb = (Beb) loc;
                    bebDisponibili.add(beb);
                }
            }
            System.err.println("numero beb: " + bebDisponibili.size());
            return bebDisponibili;
        }else if(locazione.getClass()== CasaVacanza.class){
            ArrayList<CasaVacanza> casaVacanzeDisponibili = new ArrayList<CasaVacanza>(); //Qui verranno inseriti gli appartamenti da restituire all'utente

            for (Locazione loc : elencoLocazioni) {
                if (loc.getClass()==CasaVacanza.class) {
                    CasaVacanza casaVacanza= (CasaVacanza) loc;
                    casaVacanzeDisponibili.add(casaVacanza);
                }
            }
            return casaVacanzeDisponibili;
        }else{
            ArrayList<Ostello> ostelliDisponibili = new ArrayList<Ostello>(); //Qui verranno inseriti gli ostelli da restituire all'utente

            for (Locazione loc : elencoLocazioni) {
                if (loc.getClass()==Ostello.class) {
                    Ostello ostello = (Ostello) loc;
                    ostelliDisponibili.add(ostello);
                }
            }
            return ostelliDisponibili;
        }
    }
    @Override@SuppressWarnings("unchecked")
    public Object ricercaAvanzata(Locazione locazione,String provincia, String prezzo, String sParchegio, String sWifi, String sPet ,String caratteristica)throws ClassNotFoundException,
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
            Albergo alb = (Albergo) locazione;
            ArrayList<Albergo> elencoAlberghi = (ArrayList<Albergo>) ricerca(alb,provincia,prezzo);
            ArrayList<Albergo> alberghiDisponibili = new ArrayList<Albergo>(); //Qui verranno inseriti gli alberghi da restituire all'utente

            System.err.println("numero alberghi: " + elencoAlberghi.size());

            for (Albergo albergo : elencoAlberghi) {
                if ((albergo.isParcheggio()==parcheggio)&&
                        (albergo.isWifi()==wifi)&&
                        (albergo.isPet()==pet)&&
                        (albergo.getTipoPensione().equals(caratteristica)) ){
                    alberghiDisponibili.add(albergo);
                }
            }
            return alberghiDisponibili;
        }
        else if(locazione.getClass()== Appartamento.class){
            Appartamento app = (Appartamento) locazione;
            ArrayList<Appartamento> elencoAppartamenti=(ArrayList<Appartamento>) ricerca(app,provincia,prezzo);
            ArrayList<Appartamento> appartamentiDisponibili = new ArrayList<Appartamento>(); //Qui verranno inseriti gli appartamenti da restituire all'utente
            System.err.println("numero appartamenti: " + elencoAppartamenti.size());
            for (Appartamento appartamento: elencoAppartamenti) {
                if ((appartamento.isParcheggio()==parcheggio)&&
                        (appartamento.isWifi()==wifi)&&
                        (appartamento.isPet()==pet)&&
                        (Integer.parseInt(appartamento.getNumeroStanze())>=Integer.parseInt(caratteristica.trim()))){
                                appartamentiDisponibili.add(appartamento);
                }
            }
            return appartamentiDisponibili;

        }else if(locazione.getClass()==Beb.class){
            Beb bb = (Beb) locazione;
            ArrayList<Beb> elencoBeb = (ArrayList<Beb>) ricerca(bb, provincia,prezzo);
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
            CasaVacanza cv = (CasaVacanza) locazione;
            ArrayList<CasaVacanza> elencoCasaVacanze=(ArrayList<CasaVacanza>) ricerca(cv,provincia,prezzo);
            ArrayList<CasaVacanza> casaVacanzeDisponibili = new ArrayList<CasaVacanza>(); //Qui verranno inseriti gli appartamenti da restituire all'utente

            System.err.println("numero CasaVacanza: " + elencoCasaVacanze.size());

            for (CasaVacanza casa: elencoCasaVacanze) {
                if ((casa.isParcheggio()==parcheggio)&&
                        (casa.isWifi()==wifi)&&
                        (casa.isPet()==pet)&&
                         (Integer.parseInt(casa.getNumeroCamere())>=Integer.parseInt(caratteristica.trim()))){

                    casaVacanzeDisponibili.add(casa);
                }
            }
            return casaVacanzeDisponibili;
        }else{
            Ostello ost = (Ostello) locazione;
            ArrayList<Ostello> elencoOstelli=(ArrayList<Ostello>)ricerca( ost, provincia,prezzo);
            ArrayList<Ostello> ostelliDisponibili = new ArrayList<Ostello>(); //Qui verranno inseriti gli ostelli da restituire all'utente
            System.err.println("numero Ostelli: " + elencoOstelli.size());

            for (Ostello ostello:elencoOstelli) {
                if ((ostello.isParcheggio()==parcheggio)&&
                        (ostello.isWifi()==wifi)&&
                        (ostello.isPet()==pet)&&
                        ostello.getTipoPensione().equals(caratteristica)){
                    ostelliDisponibili.add(ostello);
                }
            }

            return ostelliDisponibili;
        }
    }

    public static void main(String[]  argc) throws DeserializzazioneException, ClassNotFoundException, IOException, InstantiationException, SerializzazioneException, IllegalAccessException {
        ControlloreRicercaGlobale g = new ControlloreRicercaGlobale();
        ControlloreRicerca c = new AdapterRicercaLocazione(g) ;
        ArrayList<Locazione> l = new ArrayList<Locazione>();
        Appartamento a = new Appartamento();
        /*l = (ArrayList<Locazione>) c.ricerca(a,"Roma","800");
        for(Locazione loc: l){
            a = (Appartamento) loc;
            System.out.println(loc.getNomeLocazione());
            System.out.println(a.getNumeroBagni());
        }*/
        l = (ArrayList<Locazione>) c.ricercaAvanzata(a,"Firenze","800", "false","false","true","qwwe");
        for(Locazione loc: l){
            a = (Appartamento) loc;
            System.out.println(loc.getNomeLocazione());
            System.out.println(a.getNumeroBagni());
        }
    }

}
