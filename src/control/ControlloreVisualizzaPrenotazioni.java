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

    public ArrayList<Prenotazione> visualizzaPrenotazioniPerViaggiatore (String cliente) throws DeserializzazioneException {
        ArrayList<Prenotazione> prenotazione;
        ArrayList<Prenotazione> prenotazioneTotali = new ArrayList<>();
        prenotazioneTotali.clear();

        prenotazione =restituisciPrenotazioniPerViaggiatore(cliente,percorsoPrenotatiAlbergo);
        if(!prenotazione.isEmpty()){
            prenotazioneTotali.addAll(prenotazione);}
        prenotazione.clear();
        prenotazione =restituisciPrenotazioniPerViaggiatore(cliente,percorsoPrenotatiAppartamento);
        if(!prenotazione.isEmpty()){
            prenotazioneTotali.addAll(prenotazione);}
        prenotazione.clear();
        prenotazione =restituisciPrenotazioniPerViaggiatore(cliente,percorsoPrenotatiBeb);
        if(!prenotazione.isEmpty()){
            prenotazioneTotali.addAll(prenotazione);}
        prenotazione.clear();
        prenotazione =restituisciPrenotazioniPerViaggiatore(cliente,percorsoPrenotatiCasaVacanza);
        if(!prenotazione.isEmpty()){
            prenotazioneTotali.addAll(prenotazione);}
        prenotazione.clear();
        prenotazione =restituisciPrenotazioniPerViaggiatore(cliente,percorsoPrenotatiOstello);
        if(!prenotazione.isEmpty()){
           prenotazioneTotali.addAll(prenotazione);}
        prenotazione.clear();


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

    public ArrayList<Prenotazione> visualizzaPrenotazioniPerProprietario (String proprietario) throws DeserializzazioneException {
        ArrayList<Prenotazione> prenotazione;
        ArrayList<Prenotazione> prenotazioneTotali = new ArrayList<>();
        prenotazioneTotali.clear();

        prenotazione =restituisciPrenotazioniPerProprietario(proprietario,percorsoPrenotatiAlbergo);
        if(!prenotazione.isEmpty()){
            prenotazioneTotali.addAll(prenotazione);}
        prenotazione.clear();
        prenotazione =restituisciPrenotazioniPerProprietario(proprietario,percorsoPrenotatiAppartamento);
        if(!prenotazione.isEmpty()){
            prenotazioneTotali.addAll(prenotazione);}
        prenotazione.clear();
        prenotazione =restituisciPrenotazioniPerProprietario(proprietario,percorsoPrenotatiBeb);
        if(!prenotazione.isEmpty()){
            prenotazioneTotali.addAll(prenotazione);}
        prenotazione.clear();
        prenotazione =restituisciPrenotazioniPerProprietario(proprietario,percorsoPrenotatiCasaVacanza);
        if(!prenotazione.isEmpty()){
            prenotazioneTotali.addAll(prenotazione);}
        prenotazione.clear();
        prenotazione =restituisciPrenotazioniPerProprietario(proprietario,percorsoPrenotatiOstello);
        if(!prenotazione.isEmpty()){
            prenotazioneTotali.addAll(prenotazione);}
        prenotazione.clear();


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
