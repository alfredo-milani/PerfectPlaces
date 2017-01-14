package control;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.ContaGiorni;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ControlloreRimuoviPrenotazione {

    private static String percorsoPrenotatiAlbergo = Constants.PRENOTATI_ALBERGO_PATH;
    private static String percorsoPrenotatiAppartamento = Constants.PRENOTATI_APPARTAMENTO_PATH;
    private static String percorsoPrenotatiBeb = Constants.PRENOTATI_BEB_PATH;
    private static String percorsoPrenotatiCasaVacanza = Constants.PRENOTATI_CASEVACANZA_PATH;
    private static String percorsoPrenotatiOstello = Constants.PRENOTATI_OSTELLO_PATH;

    private static String percorsoPrenotazioniAlberghi = Constants.PRENOTAZIONE_ALBERGO_PATH;
    private static String percorsoPrenotazioniBeb = Constants.PRENOTAZIONE_BEB_PATH;
    private static String percorsoPrenotazioniOstelli = Constants.PRENOTAZIONE_OSTELLO_PATH;
    private static String percorsoPrenotazioniAppartamenti = Constants.PRENOTAZIONE_APPARTAMENTO_PATH;
    private static String percorsoPrenotazioniCaseVacanza = Constants.PRENOTAZIONE_CASAVACANZA_PATH;


    /**
     *metodo che rimuove una prenotazione per farlo oltre ad eliminare una classe prenotati(che registra i clienti) deve
     *eliminare anche tante CamerePrenotati quante sono le date per le quali l'utente si era precedentente prenotazione
     */
    public void rimuoviPrenotazione(Prenotazione prenotazione) throws DeserializzazioneException, SerializzazioneException, IOException {

        if (prenotazione.getTipo().equals("Albergo")) {
            rimuoviPrenotati(prenotazione,percorsoPrenotatiAlbergo);
            rimuoviPerNonCase(prenotazione,  percorsoPrenotazioniAlberghi);

        } else if (prenotazione.getTipo().equals("Appartamento")) {
            rimuoviPrenotati(prenotazione,percorsoPrenotatiAppartamento);
            rimuoviPerCase(prenotazione, percorsoPrenotazioniAppartamenti);

        } else if (prenotazione.getTipo().equals("Beb")) {
            rimuoviPrenotati(prenotazione,percorsoPrenotatiBeb);
            rimuoviPerNonCase(prenotazione, percorsoPrenotazioniBeb);

        } else if (prenotazione.getTipo().equals("CasaVacanza")) {
            rimuoviPrenotati(prenotazione,percorsoPrenotatiCasaVacanza);
            rimuoviPerCase(prenotazione, percorsoPrenotazioniCaseVacanza);


        } else {
            rimuoviPrenotati(prenotazione,percorsoPrenotatiOstello);
            rimuoviPerNonCase(prenotazione,percorsoPrenotazioniOstelli);
        }



    }


    private void rimuoviPrenotati(Prenotazione prenotazione, String percorsoPrenotati) throws SerializzazioneException, DeserializzazioneException {
        String nomeLocazione = prenotazione.getNomeLocazione();
        String cliente = prenotazione.getCliente();
        GregorianCalendar dataInizio = prenotazione.getDataInizio();
        GregorianCalendar dataFine = prenotazione.getDataFine();
        ArrayList<Prenotazione> prenotazioniClienti;


        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();

        prenotazioniClienti=(ArrayList<Prenotazione>) dobj.deserializza(percorsoPrenotati);
        System.out.println("Dimensione Prenotazione prima della rimozione "+ prenotazioniClienti.size());

        for(int i=0; i<prenotazioniClienti.size();++i){
            if(prenotazioniClienti.get(i).getNomeLocazione().equals(nomeLocazione)&&
                    prenotazioniClienti.get(i).getCliente().equals(cliente)&&
                    prenotazioniClienti.get(i).getDataInizio().equals(dataInizio)&&
                    prenotazioniClienti.get(i).getDataFine().equals(dataFine)) {
                prenotazioniClienti.remove(i);
            }

        }
        System.out.println("Dimensione prenotati dopo della rimozione "+ prenotazioniClienti.size());
        sobj.serializza(prenotazioniClienti,percorsoPrenotati);

    }

    @SuppressWarnings("unchecked")
    private void rimuoviPerNonCase(Prenotazione prenotazione, String percorsoCamere) throws DeserializzazioneException, SerializzazioneException, IOException {
        String nomeLocazione = prenotazione.getNomeLocazione();
        GregorianCalendar dataInizio = prenotazione.getDataInizio();
        GregorianCalendar dataFine = prenotazione.getDataFine();
        ArrayList<PostiDisponibili> postiDisponibili;
        Integer postiOccupati = Integer.parseInt(prenotazione.getNumeroPersone().trim());

        ArrayList<GregorianCalendar> datePrenotazione;
        datePrenotazione = ContaGiorni.restituisciArrayDate(dataInizio, dataFine);

        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();

        postiDisponibili = (ArrayList<PostiDisponibili>) dobj.deserializza(percorsoCamere);
        System.out.println("Dimensione postiDisponibili prima della rimozione "+ postiDisponibili.size());
        for( GregorianCalendar data: datePrenotazione){
            for (int i = 0; i< postiDisponibili.size(); ++i){
                if(postiDisponibili.get(i).getNomeLocazion().equals(nomeLocazione) && postiDisponibili.get(i).getData().equals(data)){
                     //se  è presente in postiDisponibili il contatore è almeno 1--> ha senso decrementarlo
                    int contatore_aggiornato=(postiDisponibili.get(i).getContatore()-postiOccupati);
                    System.out.println("contatore aggiornato " + contatore_aggiornato);
                    if(contatore_aggiornato == 0) {
                        postiDisponibili.remove(i);
                    }
                    else
                        {
                            postiDisponibili.get(i).setContatore(contatore_aggiornato);}

                    break;
                }

            }
        }
        System.out.println("Dimensione postiDisponibili dopo della rimozione "+ postiDisponibili.size());
        sobj.serializza(postiDisponibili,percorsoCamere);
    }

    @SuppressWarnings("unchecked")
    private void rimuoviPerCase(Prenotazione prenotazione, String percorsoCamere) throws DeserializzazioneException, SerializzazioneException, IOException {
        String nomeLocazione = prenotazione.getNomeLocazione();
        GregorianCalendar dataInizio = prenotazione.getDataInizio();
        GregorianCalendar dataFine = prenotazione.getDataFine();
        ArrayList<PostiDisponibili> postiDisponibili;

        ArrayList<GregorianCalendar> datePrenotazione;

        datePrenotazione = ContaGiorni.restituisciArrayDate(dataInizio, dataFine);

        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();


        postiDisponibili = (ArrayList<PostiDisponibili>) dobj.deserializza(percorsoCamere);
        System.out.println("Dimensione postiDisponibili prima della rimozione "+ postiDisponibili.size());
        for( GregorianCalendar data: datePrenotazione){
            for (int i = 0; i< postiDisponibili.size(); ++i){
                if(postiDisponibili.get(i).getNomeLocazion().equals(nomeLocazione) && postiDisponibili.get(i).getData().equals(data)){
                        postiDisponibili.remove(i);
                }

            }
        }
        System.out.println("Dimensione postiDisponibili dopo della rimozione "+ postiDisponibili.size());
        sobj.serializza(postiDisponibili,percorsoCamere);
    }







}


