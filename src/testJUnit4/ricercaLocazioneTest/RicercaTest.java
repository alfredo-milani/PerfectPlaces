package testJUnit4.ricercaLocazioneTest;


import control.ControlloreRicercaGlobale;
import entity.Locazione;
import exception.DeserializzazioneException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class RicercaTest {
/*
test svolti sapendo che al momento erano presenti nel sistema locazioni
- situate a Frosinone
- non erano presenti situate a Viterbo
- non erano presenti locazione con il prezzo per una notte inferiore a 10
 */
    @Test
    public void  testUnoRicercaGlobale() throws InterruptedException, DeserializzazioneException {
        ControlloreRicercaGlobale crg = new ControlloreRicercaGlobale();
        ArrayList<Locazione> locazioni = crg.ricercaGlobale("Frosinone","800",3);
        Assert.assertNotNull(locazioni);
    }

    @Test
    public void  testDueRicercaGlobale() throws InterruptedException, DeserializzazioneException {
        ControlloreRicercaGlobale crg = new ControlloreRicercaGlobale();
        ArrayList<Locazione> locazioni = crg.ricercaGlobale("Viterbo","800",3);
        int numeroLocazioni = locazioni.size();
        Assert.assertEquals(0,numeroLocazioni);
    }


    @Test
    public void  testTreRicercaGlobale() throws InterruptedException, DeserializzazioneException {
        ControlloreRicercaGlobale crg = new ControlloreRicercaGlobale();
        ArrayList<Locazione> locazioni = crg.ricercaGlobale("Viterbo","10",1);
        int numeroLocazioni = locazioni.size();
        Assert.assertEquals(0,numeroLocazioni);
    }

    @Test
    public void  testPrezzoRicercaGlobale() throws InterruptedException, DeserializzazioneException {
        ControlloreRicercaGlobale crg = new ControlloreRicercaGlobale();
        ArrayList<Locazione> locazioni = crg.ricercaGlobale("Frosinone","800",3);

        int numeroLocazioni = locazioni.size(), contatore=0;

        for(Locazione locazione:locazioni){
            if(Integer.parseInt(locazione.getPrezzo())*3<800){
                ++contatore;
            }
        }

        Assert.assertEquals(numeroLocazioni,contatore);
    }

    @Test
    public void  testProvinciaRicercaGlobale() throws InterruptedException, DeserializzazioneException {
        ControlloreRicercaGlobale crg = new ControlloreRicercaGlobale();
        ArrayList<Locazione> locazioni = crg.ricercaGlobale("Frosinone","800",3);

        int numeroLocazioni = locazioni.size(), contatore=0;

        for(Locazione locazione:locazioni){
            if(locazione.getProvincia().equals("Frosinone")){
                ++contatore;
            }
        }

        Assert.assertEquals(numeroLocazioni,contatore);
    }




}
