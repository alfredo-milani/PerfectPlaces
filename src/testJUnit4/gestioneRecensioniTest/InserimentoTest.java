package testJUnit4.gestioneRecensioniTest;


import control.ControlloreInserimentoRecensione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import org.junit.Assert;
import org.junit.Test;

public class InserimentoTest {

    /*
    test effettuati sapendo che in questo momento nel sistema
      -non è presente un "Appartamento Bianchi"
      -è presente un "appartamento rossi"
      -non è stata effettuata un prenotazione all'appartamento rossi dell'utente "aldo"
      -è stata effettuata un prenotazione all'appartamento rossi dell'utente "gab"
      */

    @Test
    public void testUnoNomeLocazione() throws SerializzazioneException, DeserializzazioneException {
        ControlloreInserimentoRecensione cir = new ControlloreInserimentoRecensione();

        int controllo = cir.inserisci("","Albergo","Aldo", 5, "Ottimo albergo!!!");
        Assert.assertEquals(1,controllo);

    }


    @Test
    public void testUnoLocazioneNonPresente() throws SerializzazioneException, DeserializzazioneException {
        ControlloreInserimentoRecensione cir = new ControlloreInserimentoRecensione();

        int controllo = cir.inserisci("Appartamento Bianchi","Appartamento","Aldo", 5, "Appartamento accogliente");
        Assert.assertEquals(2,controllo);

    }

    @Test
    public void testDueLocazioneNonPresente() throws SerializzazioneException, DeserializzazioneException {
        ControlloreInserimentoRecensione cir = new ControlloreInserimentoRecensione();

        int controllo = cir.inserisci("Appartamento Bianchi","Ostello","Aldo", 5, "Appartamento accogliente");
        Assert.assertEquals(2,controllo);

    }


    @Test
    public void testPrenotazioneNonPresente() throws SerializzazioneException, DeserializzazioneException {
        ControlloreInserimentoRecensione cir = new ControlloreInserimentoRecensione();

        int controllo = cir.inserisci("Appartamento rossi","Appartamento","Aldo", 5, "Appartamento accogliente");
        Assert.assertEquals(3,controllo);

    }

    @Test
    public void testRensioneCorta() throws SerializzazioneException, DeserializzazioneException {
        ControlloreInserimentoRecensione cir = new ControlloreInserimentoRecensione();

        int controllo = cir.inserisci("Appartamento rossi","Appartamento","gab", 5, "buono");
        Assert.assertEquals(4,controllo);

    }

    @Test
    public void testRensioneLunga() throws SerializzazioneException, DeserializzazioneException {
        ControlloreInserimentoRecensione cir = new ControlloreInserimentoRecensione();

        int controllo = cir.inserisci("Appartamento rossi","Appartamento","gab", 0, "aaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Assert.assertEquals(5,controllo);

    }


}
