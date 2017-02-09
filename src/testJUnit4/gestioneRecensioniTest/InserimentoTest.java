package testJUnit4.gestioneRecensioniTest;


import constants.Constants;
import control.ControlloreRecensione;
import entity.Appartamento;
import entity.Locazione;
import entity.Prenotazione;
import entity.Recensione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InserimentoTest {

    /*
    test effettuati sapendo che in questo momento nel sistema
      -non è presente un "Appartamento Bianchi"
      -è presente un "appartamento rossi"
      -non è stata effettuata un prenotazione all'appartamento rossi dell'utente "aldo"
      -è stata effettuata un prenotazione all'appartamento rossi dell'utente "gab"
      */

    private Appartamento apBianchi = new Appartamento();
    private ArrayList<Prenotazione> prenotazioniAldo = new ArrayList<>();

    @Before
    public void precondizioni(){
        /*
        ArrayList<Appartamento> appartamenti = new ArrayList<>();
        boolean controlloApp= false;

        File file = new File(Constants.APPART_PATH);
        if(file.length()!=0) {
            try {
                appartamenti = (ArrayList<Appartamento>) DeserializzaOggetti.deserializza(Constants.APPART_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }
            if(appartamenti.size()!=0) {
                for (int i = 0; i < appartamenti.size(); ++i) {
                    if (appartamenti.get(i).getNomeLocazione().equals("Appartamento Bianchi")) {
                        apBianchi = appartamenti.get(i);
                        appartamenti.remove(i);
                    }
                    if (appartamenti.get(i).getNomeLocazione().equals("Appartamento rossi")) {
                        controlloApp = true;
                    }

                }
            }

            if (!controlloApp) {
                Appartamento apRossi = new Appartamento("Apppartamento rossi", "1", "Roma", "Piazza del Popolo n.4", "mario rossi", "20", "spazioso",
                        true, true, false, "5", "2", false, "1");
                appartamenti.add(apRossi);
                try {
                    SerializzaOggetti.serializza(appartamenti, Constants.APPART_PATH);
                } catch (SerializzazioneException e) {
                    e.printStackTrace();
                }
            }
        }
        File f = new File(Constants.PRENOTATI_APPARTAMENTO_PATH);
        if(f.length()!=0) {

            ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
            boolean controlloPre = false;
            try {
                prenotazioni = (ArrayList<Prenotazione>) DeserializzaOggetti.deserializza(Constants.PRENOTATI_APPARTAMENTO_PATH);
            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }
            if(prenotazioni.size()!=0) {

                for (int i = 0; i < prenotazioni.size(); ++i) {
                    if (prenotazioni.get(i).getCliente().equalsIgnoreCase("aldo"))
                        prenotazioniAldo.add(prenotazioni.get(i));
                    prenotazioni.remove(i);
                    if (prenotazioni.get(i).getCliente().equalsIgnoreCase("gab"))
                        controlloPre = true;
                }
            }

            if (!controlloPre) {
                GregorianCalendar inizio = new GregorianCalendar(2017, Calendar.JANUARY, 10);
                GregorianCalendar fine = new GregorianCalendar(2017, Calendar.JANUARY, 15);
                Prenotazione preGab = new Prenotazione("Appartamento rossi", "mario rossi", "gab", inizio, fine, "Appartamento", "20", "3");
                prenotazioni.add(preGab);
                try {
                    SerializzaOggetti.serializza(prenotazioni, Constants.PRENOTATI_APPARTAMENTO_PATH);
                } catch (SerializzazioneException e) {
                    e.printStackTrace();
                }
            }

        }
        */

        }

    @Test
    public void testUnoNomeLocazione() throws SerializzazioneException, DeserializzazioneException {
        ControlloreRecensione cir = new ControlloreRecensione();

        int controllo = cir.inserisci("","Albergo","Aldo", 5, "Ottimo albergo!!!");
        Assert.assertEquals(1,controllo);

    }


    @Test
    public void testUnoLocazioneNonPresente() throws SerializzazioneException, DeserializzazioneException {
        ControlloreRecensione cir = new ControlloreRecensione();

        int controllo = cir.inserisci("Appartamento Bianchi","Appartamento","Aldo", 5, "Appartamento accogliente");
        Assert.assertEquals(2,controllo);

    }

    @Test
    public void testDueLocazioneNonPresente() throws SerializzazioneException, DeserializzazioneException {
        ControlloreRecensione cir = new ControlloreRecensione();

        int controllo = cir.inserisci("Appartamento Bianchi","Ostello","Aldo", 5, "Appartamento accogliente");
        Assert.assertEquals(2,controllo);

    }


    @Test
    public void testPrenotazioneNonPresente() throws SerializzazioneException, DeserializzazioneException {
        ControlloreRecensione cir = new ControlloreRecensione();

        int controllo = cir.inserisci("Appartamento rossi","Appartamento","Aldo", 5, "Appartamento accogliente");
        Assert.assertEquals(3,controllo);

    }

    @Test
    public void testRensioneCorta() throws SerializzazioneException, DeserializzazioneException {
        ControlloreRecensione cir = new ControlloreRecensione();

        int controllo = cir.inserisci("Appartamento rossi","Appartamento","gab", 5, "buono");
        Assert.assertEquals(4,controllo);

    }

    @Test
    public void testRensioneLunga() throws SerializzazioneException, DeserializzazioneException {
        ControlloreRecensione cir = new ControlloreRecensione();

        int controllo = cir.inserisci("Appartamento rossi","Appartamento","gab", 0, "aaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Assert.assertEquals(5,controllo);

    }

    @After
    public void postcondizioni(){
        /*
        ArrayList<Appartamento> ap = new ArrayList<>();
        try {
            ap = (ArrayList<Appartamento>) DeserializzaOggetti.deserializza(Constants.APPART_PATH);
        } catch (DeserializzazioneException e) {
            e.printStackTrace();
        }
        ap.add(apBianchi);
        try {
            SerializzaOggetti.serializza(ap, Constants.APPART_PATH);
        } catch (SerializzazioneException e) {
            e.printStackTrace();
        }
        ArrayList<Prenotazione> pr = new ArrayList<>();
        try {
            pr = (ArrayList<Prenotazione>) DeserializzaOggetti.deserializza(Constants.PRENOTATI_APPARTAMENTO_PATH);
        } catch (DeserializzazioneException e) {
            e.printStackTrace();
        }
        pr.addAll(prenotazioniAldo);
        try {
            SerializzaOggetti.serializza(pr,Constants.PRENOTATI_APPARTAMENTO_PATH);
        } catch (SerializzazioneException e) {
            e.printStackTrace();
        }
        */
    }



}
