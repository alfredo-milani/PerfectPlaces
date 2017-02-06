package control;

import constants.Constants;
import entity.Prenotazione;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;

public class ControlloreVisualizzaPrenotazioni {

    private static String percorsoPrenotatiAlbergo = Constants.PRENOTATI_ALBERGO_PATH;
    private static String percorsoPrenotatiAppartamento = Constants.PRENOTATI_APPARTAMENTO_PATH;
    private static String percorsoPrenotatiBeb = Constants.PRENOTATI_BEB_PATH;
    private static String percorsoPrenotatiCasaVacanza = Constants.PRENOTATI_CASEVACANZA_PATH;
    private static String percorsoPrenotatiOstello = Constants.PRENOTATI_OSTELLO_PATH;

    public ArrayList<Prenotazione> visualizzaPrenotazioniPerViaggiatore (String cliente) throws DeserializzazioneException, InterruptedException {
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        ArrayList<Prenotazione> prenotazioneTotali = new ArrayList<>();

        ThreadVisualizzaPrenotazioniViaggiatore tAlb = new ThreadVisualizzaPrenotazioniViaggiatore(cliente, percorsoPrenotatiAlbergo, prenotazioni);
        ThreadVisualizzaPrenotazioniViaggiatore tApp = new ThreadVisualizzaPrenotazioniViaggiatore(cliente, percorsoPrenotatiAppartamento, prenotazioni);
        ThreadVisualizzaPrenotazioniViaggiatore tBeb = new ThreadVisualizzaPrenotazioniViaggiatore(cliente, percorsoPrenotatiBeb, prenotazioni);
        ThreadVisualizzaPrenotazioniViaggiatore tCasa = new ThreadVisualizzaPrenotazioniViaggiatore(cliente, percorsoPrenotatiCasaVacanza, prenotazioni);
        ThreadVisualizzaPrenotazioniViaggiatore tOst = new ThreadVisualizzaPrenotazioniViaggiatore(cliente, percorsoPrenotatiOstello, prenotazioni);

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
        /*
        prenotazioneTotali.clear();

        factoryPrenotazione =restituisciPrenotazioniPerViaggiatore(cliente,percorsoPrenotatiAlbergo);
        if(!factoryPrenotazione.isEmpty()){
            prenotazioneTotali.addAll(factoryPrenotazione);}
        factoryPrenotazione.clear();
        factoryPrenotazione =restituisciPrenotazioniPerViaggiatore(cliente,percorsoPrenotatiAppartamento);
        if(!factoryPrenotazione.isEmpty()){
            prenotazioneTotali.addAll(factoryPrenotazione);}
        factoryPrenotazione.clear();
        factoryPrenotazione =restituisciPrenotazioniPerViaggiatore(cliente,percorsoPrenotatiBeb);
        if(!factoryPrenotazione.isEmpty()){
            prenotazioneTotali.addAll(factoryPrenotazione);}
        factoryPrenotazione.clear();
        factoryPrenotazione =restituisciPrenotazioniPerViaggiatore(cliente,percorsoPrenotatiCasaVacanza);
        if(!factoryPrenotazione.isEmpty()){
            prenotazioneTotali.addAll(factoryPrenotazione);}
        factoryPrenotazione.clear();
        factoryPrenotazione =restituisciPrenotazioniPerViaggiatore(cliente,percorsoPrenotatiOstello);
        if(!factoryPrenotazione.isEmpty()){
           prenotazioneTotali.addAll(factoryPrenotazione);}
        factoryPrenotazione.clear();

        */
        prenotazioneTotali.addAll(prenotazioni);

        return prenotazioneTotali;
    }
    @SuppressWarnings("unchecked")
    private ArrayList<Prenotazione> restituisciPrenotazioniPerViaggiatore(String cliente, String percorso) throws DeserializzazioneException {
        ArrayList<Prenotazione> listaPrenotazioni;
        ArrayList<Prenotazione> listaPrenotazioniCliente = new ArrayList<>();

        File file = new File(percorso);
        if (file.length() != 0) {
            DeserializzaOggetti dobj = new DeserializzaOggetti();
            listaPrenotazioni = (ArrayList<Prenotazione>) dobj.deserializza(percorso);

            for (Prenotazione locazioni : listaPrenotazioni) {
                if (locazioni.getCliente().equals(cliente)) {
                    listaPrenotazioniCliente.add(locazioni);
                }
            }
        }
        return listaPrenotazioniCliente;
    }

    public ArrayList<Prenotazione> visualizzaPrenotazioniPerProprietario (String proprietario) throws DeserializzazioneException, InterruptedException {
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        ArrayList<Prenotazione> prenotazioneTotali = new ArrayList<>();

        ThreadVisualizzaPrenotazioniProprietario tAlb = new ThreadVisualizzaPrenotazioniProprietario(proprietario, percorsoPrenotatiAlbergo, prenotazioni);
        ThreadVisualizzaPrenotazioniProprietario tApp = new ThreadVisualizzaPrenotazioniProprietario(proprietario, percorsoPrenotatiAppartamento, prenotazioni);
        ThreadVisualizzaPrenotazioniProprietario tBeb = new ThreadVisualizzaPrenotazioniProprietario(proprietario, percorsoPrenotatiBeb, prenotazioni);
        ThreadVisualizzaPrenotazioniProprietario tCasa = new ThreadVisualizzaPrenotazioniProprietario(proprietario, percorsoPrenotatiCasaVacanza, prenotazioni);
        ThreadVisualizzaPrenotazioniProprietario tOst = new ThreadVisualizzaPrenotazioniProprietario(proprietario, percorsoPrenotatiOstello, prenotazioni);

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

        /*
        prenotazioneTotali.clear();

        factoryPrenotazione =restituisciPrenotazioniPerProprietario(proprietario,percorsoPrenotatiAlbergo);
        if(!factoryPrenotazione.isEmpty()){
            prenotazioneTotali.addAll(factoryPrenotazione);}
        factoryPrenotazione.clear();
        factoryPrenotazione =restituisciPrenotazioniPerProprietario(proprietario,percorsoPrenotatiAppartamento);
        if(!factoryPrenotazione.isEmpty()){
            prenotazioneTotali.addAll(factoryPrenotazione);}
        factoryPrenotazione.clear();
        factoryPrenotazione =restituisciPrenotazioniPerProprietario(proprietario,percorsoPrenotatiBeb);
        if(!factoryPrenotazione.isEmpty()){
            prenotazioneTotali.addAll(factoryPrenotazione);}
        factoryPrenotazione.clear();
        factoryPrenotazione =restituisciPrenotazioniPerProprietario(proprietario,percorsoPrenotatiCasaVacanza);
        if(!factoryPrenotazione.isEmpty()){
            prenotazioneTotali.addAll(factoryPrenotazione);}
        factoryPrenotazione.clear();
        factoryPrenotazione =restituisciPrenotazioniPerProprietario(proprietario,percorsoPrenotatiOstello);
        if(!factoryPrenotazione.isEmpty()){
            prenotazioneTotali.addAll(factoryPrenotazione);}
        factoryPrenotazione.clear();
        */
        prenotazioneTotali.addAll(prenotazioni);

        return prenotazioneTotali;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Prenotazione> restituisciPrenotazioniPerProprietario(String proprietario, String percorso) throws DeserializzazioneException {
        ArrayList<Prenotazione> listaPrenotazioni;
        ArrayList<Prenotazione> listaPrenotazioniCliente = new ArrayList<>();

        File file = new File(percorso);
        if (file.length() != 0) {
            DeserializzaOggetti dobj = new DeserializzaOggetti();
            listaPrenotazioni = (ArrayList<Prenotazione>) dobj.deserializza(percorso);

            for (Prenotazione locazioni : listaPrenotazioni) {
                if (locazioni.getProprietario().equals(proprietario)) {
                    listaPrenotazioniCliente.add(locazioni);
                }
            }
        }
        return listaPrenotazioniCliente;
    }




}
