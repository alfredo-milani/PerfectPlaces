package testJUnit4.gestionePrenotazioneTest;


import control.factoryPrenotazione.ControlloreDisponibilità;
import control.factoryPrenotazione.ControlloreDisponibilitàSingoloPosto;
import entity.Albergo;
import entity.Appartamento;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PrenotazioneSingoloPostoTest{

/*
test sviluppati sapendo che:
 -l'appartamento non è più disponibile dal 5/01/2017 al 9/01/2017
 */

    @Test
    public void testUnoAppartamento() throws DeserializzazioneException, SerializzazioneException, IOException {
        Appartamento appartamento= new Appartamento("Appartamento in centro","10","Roma","Piazza del Popolo n.10","Aldo","30","Appartamento Centrale",
                true,true,true,"5","1",false, "2");
        ControlloreDisponibilità cd = new ControlloreDisponibilitàSingoloPosto();

        GregorianCalendar dataInizio =  new GregorianCalendar(2017, Calendar.JANUARY, 5);
        GregorianCalendar dataFine = new GregorianCalendar(2017,Calendar.JANUARY,9);

        boolean controllo = cd.controllo(appartamento,dataInizio,dataFine,"1");
        Assert.assertFalse(controllo);
    }

        @Test
        public void testDueAppartamento() throws DeserializzazioneException, SerializzazioneException, IOException {
            Appartamento appartamento= new Appartamento("Appartamento in centro","10","Roma","Piazza del Popolo n.10","Aldo","30","Appartamento Centrale",
                    true,true,true,"5","1",false, "2");
            ControlloreDisponibilità cd = new ControlloreDisponibilitàSingoloPosto();

            GregorianCalendar dataInizio =  new GregorianCalendar(2017, Calendar.JANUARY, 3);
            GregorianCalendar dataFine = new GregorianCalendar(2017,Calendar.JANUARY,7);

            boolean controllo = cd.controllo(appartamento,dataInizio,dataFine,"1");
            Assert.assertFalse(controllo);
        }

        @Test
        public void tesTreAppartamento() throws DeserializzazioneException, SerializzazioneException, IOException {
            Appartamento appartamento= new Appartamento("Appartamento in centro","10","Roma","Piazza del Popolo n.10","Aldo","30","Appartamento Centrale",
                    true,true,true,"5","1",false, "2");
            ControlloreDisponibilità cd = new ControlloreDisponibilitàSingoloPosto();

            GregorianCalendar dataInizio =  new GregorianCalendar(2017, Calendar.JANUARY, 9);
            GregorianCalendar dataFine = new GregorianCalendar(2017,Calendar.JANUARY,10);

            boolean controllo = cd.controllo(appartamento,dataInizio,dataFine,"1");
            Assert.assertFalse(controllo);
        }
}
