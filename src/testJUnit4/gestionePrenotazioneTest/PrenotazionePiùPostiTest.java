package testJUnit4.gestionePrenotazioneTest;


import control.factoryPrenotazione.ControlloreDisponibilitàPiùPosti;
import entity.Albergo;
import entity.Beb;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PrenotazionePiùPostiTest {

/*
test sviluppati sapendo che:
 -l'albergo "Sole" non ha più posti disponibili dal 10/01/2017 al 15/01/2017
 -il beb "Luna" non ha più posti disponibili dal 08/01/2017 al 12/01/2017
 */
    @Test
    public void testUnoAlbergo() throws DeserializzazioneException, SerializzazioneException, IOException {
        ControlloreDisponibilitàPiùPosti cd =  new ControlloreDisponibilitàPiùPosti();
        Albergo albergo= new Albergo("Sole","10","Roma","Piazza del Popolo n.4","Giuseppe","30","Albergo Centrale",
                true,true,true,"completa", "7:00","13:15","20:00");

        GregorianCalendar dataInizio =  new GregorianCalendar(2017, Calendar.JANUARY, 10);
        GregorianCalendar dataFine = new GregorianCalendar(2017,Calendar.JANUARY,15);

        boolean c = cd.controllo(albergo,dataInizio,dataFine,"5");

        Assert.assertFalse(c);

    }


    @Test
    public void testUnoBeb() throws DeserializzazioneException, SerializzazioneException, IOException {
        ControlloreDisponibilitàPiùPosti cd =  new ControlloreDisponibilitàPiùPosti();
        Beb beb= new Beb("Luna","7","Roma","Piazza del Popolo n.3","Antonio","20","beb a conduzione familiare",
                true,true,true,"7:00");

        GregorianCalendar dataInizio =  new GregorianCalendar(2017, Calendar.JANUARY, 8);
        GregorianCalendar dataFine = new GregorianCalendar(2017,Calendar.JANUARY,12);

        boolean c = cd.controllo(beb,dataInizio,dataFine,"5");
        Assert.assertFalse(c);

    }

    @Test
    public void testDueAlbergo() throws DeserializzazioneException, SerializzazioneException, IOException {
        ControlloreDisponibilitàPiùPosti cd =  new ControlloreDisponibilitàPiùPosti();
        Albergo albergo= new Albergo("Sole","10","Roma","Piazza del Popolo n.4","Giuseppe","30","Albergo Centrale",
                true,true,true,"completa", "7:00","13:15","20:00");

        GregorianCalendar dataInizio =  new GregorianCalendar(2017, Calendar.JANUARY, 13);
        GregorianCalendar dataFine = new GregorianCalendar(2017,Calendar.JANUARY,17);

        boolean c = cd.controllo(albergo,dataInizio,dataFine,"5");
        Assert.assertFalse(c);

    }


    @Test
    public void testDueBeb() throws DeserializzazioneException, SerializzazioneException, IOException {
        ControlloreDisponibilitàPiùPosti cd =  new ControlloreDisponibilitàPiùPosti();
        Beb beb= new Beb("Luna","7","Roma","Piazza del Popolo n.3","Antonio","20","beb a conduzione familiare",
                true,true,true,"7:00");

        GregorianCalendar dataInizio =  new GregorianCalendar(2017, Calendar.JANUARY, 5);
        GregorianCalendar dataFine = new GregorianCalendar(2017,Calendar.JANUARY,9);

        boolean c = cd.controllo(beb,dataInizio,dataFine,"5");
        Assert.assertFalse(c);

    }

}
